package it.shifty.textgame.presentation.commandline.configuration;

import it.shifty.textgame.engine.exception.RoomMisplacedException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.gameobjects.Weapon;
import it.shifty.textgame.engine.map.Key;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;
import it.shifty.textgame.engine.map.RoomClosedWithKey;
import it.shifty.textgame.presentation.GameBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInitializer {

    List<Room> roomList = new ArrayList<>();

    public void constructDefaultGameService(GameBuilder builder) {
        try {
            HashMap<String, ItemObject> itemsInGame = new HashMap<>();

            Key universalKey = new Key("items.key.name", "items.key.description");
            addRoom(new RoomClosedWithKey("room.closed.0.0.name", "room.description.standard", 0, 0, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.0.1.name", "room.description.standard", 0, 1, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.1.0.name", "room.description.standard", 1, 0, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.1.1.name", "room.description.standard", 1, 1, universalKey));
            addRoom(new RoomClosedWithKey("room.closed.2.2.name", "room.description.standard", 2, 2, universalKey));

            MapEngine mapEngine = new MapEngine(roomList, 3, 3);
            Character character = new Character("character.mainplayer.name", "character.mainplayer.description", true, mapEngine.getRoom(0, 0).get());
            character.setHoldenItem(universalKey);

            Weapon weapon = new Weapon("items.sword.bastard.sword.name", "items.sword.bastard.sword.description");
            itemsInGame.put(weapon.getInternalName(), weapon);

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
