package it.shifty.textgame.commandline.config;

import it.shifty.textgame.GameBuilder;
import it.shifty.textgame.engine.exception.RoomMisplacedException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.*;
import it.shifty.textgame.engine.map.Key;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;
import it.shifty.textgame.engine.map.RoomClosedWithKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static it.shifty.textgame.engine.gameobjects.GlobalParameters.DEFAULT_ARMOR_HEALTH;
import static it.shifty.textgame.engine.gameobjects.GlobalParameters.DEFAULT_DAMAGE_WEAPON;

public class GameInitializer {

    List<Room> roomList = new ArrayList<>();

    public void constructDefaultGameService(GameBuilder builder) {
        try {
            HashMap<String, ItemObject> itemsInGame = new HashMap<>();

            /*
            1 - ADD OBJECTS AND ITEMS
             */

            Key universalKey = new Key("items.key.name", "items.key.description");
            Weapon basicWeapon = new Weapon("items.sword.basic.name", "items.sword.basic.description", DEFAULT_DAMAGE_WEAPON);
            Armor defaultArmor = new Armor("items.armor.basic.name","items.armor.basic.description", DEFAULT_ARMOR_HEALTH);
            Weapon bastardSword = new Weapon("items.sword.bastard.sword.name", "items.sword.bastard.sword.description", 50);

            /*
            2 -- ADD ENEMIES
             */

            Enemy enemy = new Enemy("game.combat.enemy.1.name", "game.combat.enemy.1.description", basicWeapon, defaultArmor);

            /*
            3 - ADD ROOMS
             */

            Room startingRoom = new RoomClosedWithKey("room.closed.0.0.name", "room.description.standard", 0, 0, universalKey);
            startingRoom.setEnemy(enemy);
            addRoom(startingRoom);
            addRoom(new RoomClosedWithKey("room.closed.0.1.name", "room.description.standard", 0, 1, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.1.0.name", "room.description.standard", 1, 0, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.1.1.name", "room.description.standard", 1, 1, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.2.2.name", "room.description.standard", 2, 2, universalKey));

            MapEngine mapEngine = new MapEngine(roomList, 3, 3);

            /*
            4 - ADD MAIN CHARACTER
             */
            Character character = new Character("character.mainplayer.name", "character.mainplayer.description", true, startingRoom);
            character.setHoldenItem(universalKey);
            character.setPrimaryWeapon(bastardSword);
            character.setArmor(defaultArmor);

            itemsInGame.put(bastardSword.getInternalName(), bastardSword);
            builder.addRooms(roomList);
            builder.addMapEngine(mapEngine);
            builder.addMainCharacter(character);
            builder.addItems(itemsInGame);

        } catch (RoomMisplacedException ex) {

        }
    }

    //FEATURE
    public void constructMongoDbGameService(GameBuilder builder) {

    }

    private void addRoom(Room room) {
        roomList.add(room);
    }
}
