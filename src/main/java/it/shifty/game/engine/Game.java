package it.shifty.game.engine;

import it.shifty.game.engine.exception.LoseGameException;
import it.shifty.game.engine.exception.RoomMisplacedException;
import it.shifty.game.engine.map.Direction;
import it.shifty.game.engine.map.MapEngine;
import it.shifty.game.engine.map.Room;
import it.shifty.game.engine.parser.Actions;
import it.shifty.game.engine.parser.CommandParser;
import it.shifty.game.engine.parser.Operations;
import it.shifty.game.gameobjects.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.shifty.game.engine.parser.Actions.UNRECOGNIZED;

public class Game {

    CommandParser parser;

    private List<Room> roomList = new ArrayList<>();

    private MapEngine mapEngine;

    private Character character;

    public Game() throws RoomMisplacedException {
        initializeGame();
    }

    private void initializeGame() throws RoomMisplacedException {
        parser = new CommandParser();
        addRoom(new Room("0-0", "", 0,0 ));
        addRoom(new Room("0-1", "", 0,1 ));
        addRoom(new Room("1-0", "", 1,0 ));
        addRoom(new Room("1-1", "", 1,1 ));
        addRoom(new Room("2-2", "", 2,2 ));

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

    public String executeCommand(String input) {
        List<String> wordList;
        String lowerCaseString = input.toLowerCase();
        String outcome;
        if (lowerCaseString.isBlank())
            outcome = "Devi inserire un comando";
        else {
            wordList = CommandParser.wordList(lowerCaseString);
            try {
                //recognize the action
                Actions actionCatched = Actions.fromString(wordList.get(0));
                if (actionCatched.getOperation().equals(Operations.NONE)) {
                    outcome = processSingleOperation(actionCatched);
                }
            } catch (Exception ex) {
                outcome = "Scusa, non credo di aver capito";
            }
            outcome = CommandParser.parseCommand(wordList);
        }
        return outcome;
    }

    public String processSingleOperation(Actions action) {
        switch (action) {
            case GO_E:
                return mapEngine.moveCharacter(character, Direction.EAST);
            case GO_N:
                return mapEngine.moveCharacter(character, Direction.NORTH);
            case GO_W:
                return mapEngine.moveCharacter(character, Direction.NORTH);
            case GO_S:
                return mapEngine.moveCharacter(character, Direction.SOUTH);
            case INVENTORY:
                return character.describeInventory();
            case LOOK:
                return character.describeRoom();
            default:
                break;
        }
        return "";
    }

    public void showMessage(String output) {
    }
}
