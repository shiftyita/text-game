package it.shifty.textgame.core.engine.builder;

import it.shifty.textgame.core.engine.GameService;
import it.shifty.textgame.core.engine.gameobjects.Character;
import it.shifty.textgame.core.engine.gameobjects.ItemObject;
import it.shifty.textgame.core.engine.map.MapEngine;
import it.shifty.textgame.core.engine.map.Room;
import it.shifty.textgame.core.presentation.GameBuilder;

import java.util.HashMap;
import java.util.List;

public class GameServiceBuilder implements GameBuilder {
    private List<Room> roomList;
    private MapEngine mapEngine;
    private Character character;
    private List<Character> characterList;
    private HashMap<String, ItemObject> itemsInGame;

    @Override
    public void addRooms(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public void addMapEngine(MapEngine mapEngine) {
        this.mapEngine = mapEngine;
    }

    @Override
    public void addMainCharacter(Character character) {
        this.character = character;
    }

    @Override
    public void addCharacters(List<Character> characterList) {
        this.characterList = characterList;
    }

    @Override
    public void addItems(HashMap<String, ItemObject> itemsInGame) {
        this.itemsInGame = itemsInGame;
    }

    public GameService getResult() {
        return new GameService(roomList, mapEngine, character, characterList, itemsInGame);
    }
}
