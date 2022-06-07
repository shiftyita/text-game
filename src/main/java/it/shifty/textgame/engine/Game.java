package it.shifty.textgame.engine;

import it.shifty.textgame.engine.display.DisplayOutput;
import it.shifty.textgame.engine.display.OutputMessage;
import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.exception.RoomMisplacedException;
import it.shifty.textgame.engine.map.Direction;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;
import it.shifty.textgame.engine.parser.Actions;
import it.shifty.textgame.engine.parser.CommandParser;
import it.shifty.textgame.engine.parser.Operations;
import it.shifty.textgame.gameobjects.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    CommandParser parser;

    private List<Room> roomList = new ArrayList<>();

    private MapEngine mapEngine;

    private Character character;

    private DisplayOutput displayOutput;

    private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );

    public Game(DisplayOutput displayOutput) {
        try {
            initializeGame();
            this.displayOutput = displayOutput;
        } catch (Exception | RoomMisplacedException ex) {
            LOGGER.log(Level.SEVERE, ex.toString());
        }
    }

    private void initializeGame() throws RoomMisplacedException {
            parser = new CommandParser();
            addRoom(new Room("0-0", "room.description.standard", 0,0 ));
            addRoom(new Room("0-1", "room.description.standard", 0,1 ));
            addRoom(new Room("1-0", "room.description.standard", 1,0 ));
            addRoom(new Room("1-1", "room.description.standard", 1,1 ));
            addRoom(new Room("2-2", "room.description.standard", 2,2 ));

            mapEngine = new MapEngine(roomList, 3,3);
            character = new Character("Player", "Lovely game-player", true, mapEngine.getRoom(0,0).get());
    }

    private void manageDamage(Character attackingCharacter, Character defendingCharacter) throws LoseGameException {
        int firstDamage = attackingCharacter.getPrimaryWeapon() != null ? attackingCharacter.getPrimaryWeapon().getDamage() : 0;
        int secondDamage = attackingCharacter.getSecondaryWeapon() != null ? attackingCharacter.getPrimaryWeapon().getDamage() : 0;
        int damageTaken = defendingCharacter.getArmor().absorbDamage(firstDamage + secondDamage);
        if (damageTaken > 0)
        {
            defendingCharacter.absorbDamage(damageTaken);
            if (defendingCharacter.isDestroyed() && defendingCharacter.isMainCharacter())
                throw new LoseGameException("You lose the game. Your character died");
        }
    }

    private void addRoom(Room room) {
        roomList.add(room);
        parser.addObjectName(room.getName());
    }

    public void showIntro() {

    }

    public OutputMessage executeCommand(String input) {
        List<String> wordList;
        String lowerCaseString = input.toLowerCase();
        String outcome;
        if (lowerCaseString.isBlank())
            return new OutputMessage("default.message.command.missing");
        else {
            wordList = CommandParser.wordList(lowerCaseString);
            try {
                //recognize the action
                Actions actionCatched = Actions.fromString(wordList.get(0));
                if (actionCatched.getOperation().equals(Operations.NONE)) {
                    return processSingleOperation(actionCatched);
                }
            } catch (Exception ex) {
                return new OutputMessage("default.message.not.understand");
            }
            return new OutputMessage(CommandParser.parseCommand(wordList));
        }
    }

    public OutputMessage processSingleOperation(Actions action) {
        switch (action) {
            case GO_E:
                return mapEngine.moveCharacter(character, Direction.EAST);
            case GO_N:
                return mapEngine.moveCharacter(character, Direction.NORTH);
            case GO_W:
                return mapEngine.moveCharacter(character, Direction.WEST);
            case GO_S:
                return mapEngine.moveCharacter(character, Direction.SOUTH);
            case INVENTORY:
                return character.describeInventory();
            case LOOK:
                return character.describeRoom();
            default:
                return new OutputMessage("");
        }
    }

    public void showMessage(OutputMessage output) {
        displayOutput.printTextOutput(output);
    }
}
