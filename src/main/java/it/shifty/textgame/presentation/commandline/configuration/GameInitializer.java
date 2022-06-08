package it.shifty.textgame.presentation.commandline.configuration;

import it.shifty.textgame.engine.exception.RoomMisplacedException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.map.Key;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;
import it.shifty.textgame.engine.map.RoomClosedWithKey;
import it.shifty.textgame.presentation.GameBuilder;

import java.util.ArrayList;
import java.util.List;

public class GameInitializer {

    List<Room> roomList = new ArrayList<>();

    public void constructDefaultGameService(GameBuilder builder) {
        try {
            Key universalKey = new Key("bella chiave", "key");
            addRoom(new RoomClosedWithKey("0-0", "room.description.standard", 0, 0, universalKey));
            addRoom(new RoomClosedWithKey("0-1", "room.description.standard", 0, 1, universalKey));
            addRoom(new RoomClosedWithKey("1-0", "room.description.standard", 1, 0, universalKey));
            addRoom(new RoomClosedWithKey("1-1", "room.description.standard", 1, 1, universalKey));
            addRoom(new RoomClosedWithKey("2-2", "room.description.standard", 2, 2, universalKey));

            MapEngine mapEngine = new MapEngine(roomList, 3, 3);
            Character character = new Character("Player", "Lovely game-player", true, mapEngine.getRoom(0, 0).get());
            character.setHoldenItem(universalKey);

            builder.addRooms(roomList);
            builder.addMapEngine(mapEngine);
            builder.addMainCharacter(character);

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
