package it.shifty.textgame.engine;
import it.shifty.textgame.engine.combat.CombatEngine;
import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.events.PublisherEngine;
import it.shifty.textgame.engine.events.majorevents.EnemyDiedEvent;
import it.shifty.textgame.engine.exception.InsufficientActionPointsException;
import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.Enemy;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;
import it.shifty.textgame.engine.map.MapEngine;
import it.shifty.textgame.engine.map.Room;
import it.shifty.textgame.presentation.GameEngineLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/*
* This is the main endpoint that will process all the commands
* */
public class GameService extends PublisherEngine implements GameEngineLayout  {

    private final List<Room> roomList;
    private final HashMap<String, ItemObject> itemsInGame;
    private final MapEngine mapEngine;
    private final Character character;
    private final List<Character> characterList;
    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private boolean isBattleMode = false;
    private CombatEngine combatEngine;

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
    public void moveCharacter(Direction direction) {
        gameEventNotification(mapEngine.moveCharacter(character, direction));
    }

    @Override
    public void describeInventory() {
        gameEventNotification(character.describeInventory());
    }

    @Override
    public List<ItemObject> showInventory() {
        return character.getInventory();
    }

    @Override
    public void describeRoom() {
        gameEventNotification(character.describeRoom());
    }

    @Override
    public ItemObject getItemGivenName(String name) {
        return itemsInGame.get(name);
    }

    @Override
    public void addItemInInventory(ItemObject itemObject) {
        gameEventNotification(character.addItemInInventory(itemObject));
    }

    @Override
    public void startCombat() {
        //start battle only if there are enemies in the room.
        Optional<Enemy> enemy = mapEngine.getEnemyInRoom(character.getPosition());
        if (enemy.isPresent()) {
            isBattleMode = true;
            combatEngine = new CombatEngine(character, enemy.get());
            gameEventNotification(new GameOutputMessage("game.combat.start"));
        }
        else {
            gameEventNotification(new GameOutputMessage("game.combat.no.enemies"));
        }
    }

    @Override
    public boolean isInCombat() {
        return isBattleMode;
    }

    @Override
    public void performCombatAction(CombatEngine.CombactActions actions) {
        try {
            //check if there are still actions
            character.reduceActionPoints(actions.getActionPoint());
            combatEngine.performAction(actions, true);
            gameEventNotification(new GameOutputMessage("character.stats.action.points", character.getActionPointsLeft()));
        } catch (Exception | LoseGameException | InsufficientActionPointsException ex) {
            gameEventNotification(new GameOutputMessage(ex.getMessage()));
        } catch (EnemyDiedEvent e) {
            isBattleMode = false;
            mapEngine.removeEnemyFromRoom(character.getPosition());
        }
    }
}
