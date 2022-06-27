package it.shifty.textgame.core.presentation;

import it.shifty.textgame.core.engine.gameobjects.Character;
import it.shifty.textgame.core.engine.gameobjects.ItemObject;
import it.shifty.textgame.core.engine.map.MapEngine;
import it.shifty.textgame.core.engine.map.Room;

import java.util.HashMap;
import java.util.List;

public interface GameBuilder {

    void addRooms(List<Room> roomList);

    void addMapEngine(MapEngine mapEngine);

    void addMainCharacter(Character character);

    void addCharacters(List<Character> characterList);

    void addItems(HashMap<String, ItemObject> itemsInGame);

}
