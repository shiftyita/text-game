package it.shifty.textgame.presentation.commandline.engine.parser;

import it.shifty.textgame.engine.GameService;
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
        GO_E.addOperation(Operations.NONE);
        GO_N.addOperation(Operations.NONE);
        GO_S.addOperation(Operations.NONE);
        GO_W.addOperation(Operations.NONE);
        INVENTORY.addOperation(Operations.NONE);
        LOOK.addOperation(Operations.NONE);
        TAKE.addOperation(Operations.NEED_TARGET);
    }

    public static List<String> wordList(String lowerCaseString) {
        String delims = "[ \t,.:;?!\"']+";
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
            wordList = CommandParser.wordList(lowerCaseString);
            try {
                //recognize the action
                Actions actionCatched = Actions.fromString(wordList.get(0));
                if (actionCatched.getOperation().equals(Operations.NONE)) {
                    return processSingleOperation(actionCatched);
                } else if (actionCatched.getOperation().equals(Operations.NEED_TARGET)) {
                    if (wordList.size() >= 2) {
                        wordList.remove(0);
                        return processDoubleOperation(actionCatched, GameUtils.abstractAssetNameFormatter(String.join("", wordList)));
                    }
                }
            } catch (Exception ex) {
                return new GameOutputMessage("default.message.not.understand");
            }
            return new GameOutputMessage(CommandParser.parseCommand(wordList));
        }
    }

    public GameOutputMessage processDoubleOperation(Actions actions, String itemName) {
        if (vocab.containsKey(itemName)) {
            ItemObject item = gameService.getItemGivenName(itemName);
            switch (actions) {
                case TAKE:
                    return gameService.addItemInInventory(item);
                default:
                    return new GameOutputMessage("text.blank");
            }
        } else {
            return new GameOutputMessage("default.message.not.understand");
        }
    }

    public GameOutputMessage processSingleOperation(Actions action) {
        switch (action) {
            case GO_E:
                return gameService.moveCharacter(Direction.EAST);
            case GO_N:
                return gameService.moveCharacter(Direction.NORTH);
            case GO_W:
                return gameService.moveCharacter(Direction.WEST);
            case GO_S:
                return gameService.moveCharacter(Direction.SOUTH);
            case INVENTORY:
                return gameService.describeInventory();
            case LOOK:
                return gameService.describeRoom();
            case COMBAT:
               return gameService.startCombat();

            default:
                return new GameOutputMessage("text.blank");
        }
    }

    public void showMessage(GameOutputMessage output) {
        displayOutput.printTextOutput(output);
    }
}
