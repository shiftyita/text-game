package it.shifty.textgame.presentation.commandline.engine.parser;

import it.shifty.textgame.engine.GameService;
import it.shifty.textgame.engine.combat.CombatEngine;
import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;
import it.shifty.textgame.engine.utils.GameUtils;
import it.shifty.textgame.presentation.DisplayOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.*;

import static it.shifty.textgame.presentation.commandline.engine.parser.Actions.*;

public class CommandParser {

    static HashMap<String, Words> vocab = new HashMap<>();

    @Autowired
    private Environment env;

    private GameService gameService;

    private DisplayOutput displayOutput;

    public CommandParser(DisplayOutput displayOutput, GameService gameService) {
        this.gameService = gameService;
        this.displayOutput = displayOutput;
        initializeEnums();
        itemsCensus();
    }

    private void initializeEnums() {

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

    public GameOutputMessage executeCommand(String input) {
        List<String> wordList;
        String lowerCaseString = input.toLowerCase();
        if (lowerCaseString.isBlank())
            return new GameOutputMessage("default.message.command.missing");
        else {
            if (!gameService.isInCombat()) {
                wordList = CommandParser.wordStandardList(lowerCaseString);
            }
            else {
                wordList = CommandParser.wordCombatList(lowerCaseString);
            }
            try {
                //recognize the action
                Actions actionCatched = Actions.fromString(wordList.get(0));
                if (actionCatched.getOperation().equals(Operations.NONE)) {
                    processSingleOperation(actionCatched);
                } else if (actionCatched.getOperation().equals(Operations.NEED_TARGET)) {
                    if (wordList.size() >= 2) {
                        wordList.remove(0);
                        return processDoubleOperation(actionCatched, GameUtils.abstractAssetNameFormatter(String.join("", wordList)));
                    }
                }
            } catch (Exception ex) {
                return new GameOutputMessage("default.message.not.understand");
            }
            return new GameOutputMessage();
        }
    }

    public GameOutputMessage processDoubleOperation(Actions actions, String itemName) {
        if (vocab.containsKey(itemName)) {
            ItemObject item = gameService.getItemGivenName(itemName);
            switch (actions) {
                case TAKE:
                    gameService.addItemInInventory(item);
                default:
                    return new GameOutputMessage("text.blank");
            }
        } else {
            return new GameOutputMessage("default.message.not.understand");
        }
    }

    public void processSingleOperation(Actions action) {
        switch (action) {
            case GO_E -> gameService.moveCharacter(Direction.EAST);
            case GO_N -> gameService.moveCharacter(Direction.NORTH);
            case GO_W -> gameService.moveCharacter(Direction.WEST);
            case GO_S -> gameService.moveCharacter(Direction.SOUTH);
            case INVENTORY -> gameService.describeInventory();
            case LOOK -> gameService.describeRoom();
            case COMBAT -> gameService.startCombat();
            case TOTAL_DEFENSE, AGGRESSIVE_ATTACK, DEFAULT_ATTACK, PARRY_AND_FIGHT,  INVENTORY_LOOK
                    -> gameService.performAction(CombatEngine.CombactActions.valueOf(action.name()));
            default -> new GameOutputMessage("text.blank");
        }
    }

    public void showMessage(GameOutputMessage output) {
        displayOutput.printTextOutput(output);
    }
}
