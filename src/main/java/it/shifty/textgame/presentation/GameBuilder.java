package it.shifty.textgame.presentation;

import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;

import java.util.HashMap;
import java.util.List;

public interface GameBuilder {

    void addRooms(List<Room> roomList);

    void addMapEngine(MapEngine mapEngine);

    void addMainCharacter(Character character);

    void addCharacters(List<Character> characterList);

    void addItems(HashMap<String, ItemObject> itemsInGame);

}
