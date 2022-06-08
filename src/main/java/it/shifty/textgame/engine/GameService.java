package it.shifty.textgame.engine;

import it.shifty.textgame.engine.display.OutputMessage;
import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.exception.RoomMisplacedException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.*;
import it.shifty.textgame.presentation.GameEngineLayout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GameService implements GameEngineLayout {

    private List<Room> roomList = new ArrayList<>();

    private MapEngine mapEngine;

    private Character character;

    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());

    public GameService() {
        initializeGame();
    }

    @Override
    public void initializeGame() {
        try {
            //            addRoom(new Room("0-0", "room.description.standard", 0,0 ));
//            addRoom(new Room("0-1", "room.description.standard", 0,1 ));
//            addRoom(new Room("1-0", "room.description.standard", 1,0 ));
//            addRoom(new Room("1-1", "room.description.standard", 1,1 ));
//            addRoom(new Room("2-2", "room.description.standard", 2,2 ));
            Key universalKey = new Key("bella chiave", "key");
            addRoom(new RoomClosedWithKey("0-0", "room.description.standard", 0, 0, universalKey));
            addRoom(new RoomClosedWithKey("0-1", "room.description.standard", 0, 1, universalKey));
            addRoom(new RoomClosedWithKey("1-0", "room.description.standard", 1, 0, universalKey));
            addRoom(new RoomClosedWithKey("1-1", "room.description.standard", 1, 1, universalKey));
            addRoom(new RoomClosedWithKey("2-2", "room.description.standard", 2, 2, universalKey));

            mapEngine = new MapEngine(roomList, 3, 3);
            character = new Character("Player", "Lovely game-player", true, mapEngine.getRoom(0, 0).get());
            character.setHoldenItem(universalKey);
        } catch (Exception | RoomMisplacedException ex) {
            LOGGER.log(Level.SEVERE, "Exception while initializing game : " + ex);
        }
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
//        parser.addObjectName(room.getName());
    }

    public OutputMessage showIntro() {
        return new OutputMessage("game.intro");
    }

    @Override
    public OutputMessage moveCharacter(Direction direction) {
        return mapEngine.moveCharacter(character, direction);
    }

    @Override
    public OutputMessage describeInventory() {
        return character.describeInventory();
    }

    @Override
    public List<ItemObject> showInventory() {
        return character.getInventory();
    }

    @Override
    public OutputMessage describeRoom() {
        return character.describeRoom();
    }
}
