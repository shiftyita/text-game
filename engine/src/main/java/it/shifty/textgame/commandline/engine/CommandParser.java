package it.shifty.textgame.commandline.engine;

import it.shifty.textgame.core.dto.LocalizedMessage;
import it.shifty.textgame.core.presentation.DisplayOutput;
import it.shifty.textgame.engine.GameService;
import it.shifty.textgame.engine.combat.CombatEngine;
import it.shifty.textgame.engine.exception.CommandNotRecognizedException;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;
import it.shifty.textgame.engine.utils.GameUtils;
import it.shifty.textgame.presentation.commandline.engine.parser.Actions;
import it.shifty.textgame.presentation.commandline.engine.parser.Operations;
import it.shifty.textgame.presentation.commandline.engine.parser.Words;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.*;

import static it.shifty.textgame.presentation.commandline.engine.parser.Actions.PASS;

public class CommandParser {

    static HashMap<String, Words> vocab = new HashMap<>();

    @Autowired
    private Environment env;

    private GameService gameService;

    private DisplayOutput displayOutput;

    public CommandParser(DisplayOutput displayOutput, GameService gameService) {
        this.gameService = gameService;
        this.displayOutput = displayOutput;
        itemsCensus();
    }

    public static List<String> wordStandardList(String lowerCaseString) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = lowerCaseString.split(delims);
        Collections.addAll(strlist, words);
        return strlist;
    }

    public static List<String> wordCombatList(String lowerCaseString) {
        String delims = "[,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = lowerCaseString.split(delims);
        Collections.addAll(strlist, words);
        return strlist;
    }

    public static String parseCommand(List<String> wordList) {
        return "";
    }

    //TODO => Shared cache (Hazelcast?)
    private void itemsCensus() {
        final HashMap<String, ItemObject> itemObjects = gameService.getItemsInGame();
        for (Map.Entry<String, ItemObject> itemObjectEntry : itemObjects.entrySet()) {
            addObjectName(itemObjectEntry.getKey());
        }
    }

    public void addObjectName(String name) {
        vocab.put(name, Words.NOUN);
    }

    public LocalizedMessage executeCommand(String input) {
        String lowerCaseString = input.toLowerCase();
        if (lowerCaseString.isBlank())
            return new LocalizedMessage("default.message.command.missing");
        else {
            try {
                if (gameService.isInCombat()) {
                    processCombatCommands(lowerCaseString);
                } else {
                    processStandardCommands(lowerCaseString);
                }
            } catch (CommandNotRecognizedException ex) {
                return new LocalizedMessage("default.message.not.understand");
            }
            return new LocalizedMessage();
        }
    }

    private void processCombatCommands(String lowerCaseCommandString) throws CommandNotRecognizedException {
        List<String> wordList = CommandParser.wordCombatList(lowerCaseCommandString);
        Actions actionCaught = Actions.fromString(wordList.get(0));
        processCombatOperations(actionCaught);
        if (actionCaught.equals(PASS))
            processEnemyTurn();
    }

    private void processEnemyTurn() {
        gameService.performEnemyAction();
    }

    private void processStandardCommands(String lowerCaseCommandString) throws CommandNotRecognizedException {
        List<String> wordList = CommandParser.wordStandardList(lowerCaseCommandString);
        //recognize the action
        Actions actionCaught = Actions.fromString(wordList.get(0));
        if (actionCaught.getOperation().equals(Operations.NONE)) {
            processSingleOperation(actionCaught);
        } else if (actionCaught.getOperation().equals(Operations.NEED_TARGET)) {
            if (wordList.size() >= 2) {
                wordList.remove(0);
                processDoubleOperation(actionCaught, GameUtils.abstractAssetNameFormatter(String.join("", wordList)));
            }
        }
    }

    public LocalizedMessage processDoubleOperation(Actions actions, String itemName) {
        if (vocab.containsKey(itemName)) {
            ItemObject item = gameService.getItemGivenName(itemName);
            switch (actions) {
                case TAKE:
                    gameService.addItemInInventory(item);
                default:
                    return new LocalizedMessage("text.blank");
            }
        } else {
            return new LocalizedMessage("default.message.not.understand");
        }
    }

    public void processSingleOperation(Actions action) throws CommandNotRecognizedException {
        switch (action) {
            case GO_E -> gameService.moveCharacter(Direction.EAST);
            case GO_N -> gameService.moveCharacter(Direction.NORTH);
            case GO_W -> gameService.moveCharacter(Direction.WEST);
            case GO_S -> gameService.moveCharacter(Direction.SOUTH);
            case INVENTORY -> gameService.describeInventory();
            case LOOK -> gameService.describeRoom();
            case COMBAT -> gameService.startCombat();
            case HEALTH -> gameService.getCharacterHealth();
            default -> throw new CommandNotRecognizedException();
        }
    }

    public void processCombatOperations(Actions action) throws CommandNotRecognizedException {
        switch (action) {
            case TOTAL_DEFENSE, AGGRESSIVE_ATTACK, DEFAULT_ATTACK, PARRY_AND_FIGHT, INVENTORY_LOOK, EQUIP, PASS, SHOW_AVAILABLE_ACTIONS
                    -> gameService.performCombatAction(CombatEngine.CombatActions.valueOf(action.name()));
            default -> throw new CommandNotRecognizedException();
        }
    }

    public void showMessage(LocalizedMessage output) {
        displayOutput.printTextOutput(output);
    }
}
