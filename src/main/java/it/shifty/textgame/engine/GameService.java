package it.shifty.textgame.engine;

import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;
import it.shifty.textgame.presentation.GameEngineLayout;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class GameService implements GameEngineLayout {

    private final List<Room> roomList;
    private final HashMap<String, ItemObject> itemsInGame;
    private final MapEngine mapEngine;
    private final Character character;
    private final List<Character> characterList;
    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());

    public GameService(List<Room> roomList, MapEngine mapEngine, Character character, List<Character> characterList, HashMap<String, ItemObject> itemsInGame) {
        this.roomList = roomList;
        this.mapEngine = mapEngine;
        this.character = character;
        this.characterList = characterList;
        this.itemsInGame = itemsInGame;
    }

    public HashMap<String, ItemObject> getItemsInGame() {
        return itemsInGame;
    }

    public GameOutputMessage showIntro() {
        return new GameOutputMessage("game.intro");
    }

    @Override
    public GameOutputMessage moveCharacter(Direction direction) {
        return mapEngine.moveCharacter(character, direction);
    }

    @Override
    public GameOutputMessage describeInventory() {
        return character.describeInventory();
    }

    @Override
    public List<ItemObject> showInventory() {
        return character.getInventory();
    }

    @Override
    public GameOutputMessage describeRoom() {
        return character.describeRoom();
    }

    @Override
    public ItemObject getItemGivenName(String name) {
        return itemsInGame.get(name);
    }

    @Override
    public GameOutputMessage addItemInInventory(ItemObject itemObject) {
        return character.addItemInInventory(itemObject);
    }

}
