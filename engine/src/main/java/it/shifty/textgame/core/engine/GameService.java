package it.shifty.textgame.core.engine;

import it.shifty.textgame.core.dto.LocalizedMessage;
import it.shifty.textgame.core.engine.combat.CombatEngine;
import it.shifty.textgame.core.engine.exception.InsufficientActionPointsException;
import it.shifty.textgame.core.engine.exception.LoseGameException;
import it.shifty.textgame.core.engine.gameobjects.Character;
import it.shifty.textgame.core.engine.gameobjects.Enemy;
import it.shifty.textgame.core.engine.gameobjects.ItemObject;
import it.shifty.textgame.core.engine.map.Direction;
import it.shifty.textgame.core.engine.map.MapEngine;
import it.shifty.textgame.core.engine.map.Room;
import it.shifty.textgame.core.events.Publisher;
import it.shifty.textgame.core.presentation.GameEngineLayout;
import it.shifty.textgame.events.majorevents.EnemyDiedEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/*
* This is the main endpoint that will process all the commands
* */
public class GameService implements GameEngineLayout  {

    private final List<Room> roomList;
    private final HashMap<String, ItemObject> itemsInGame;
    private final MapEngine mapEngine;
    private final Character character;
    private final List<Character> characterList;
    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private boolean isBattleMode = false;
    @Autowired
    private CombatEngine combatEngine;

    @Autowired
    private Publisher publisher;

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

    public LocalizedMessage showIntro() {
        return new LocalizedMessage("game.intro");
    }

    @Override
    public void moveCharacter(Direction direction) {
        publisher.gameEventNotification(mapEngine.moveCharacter(character, direction));
    }

    @Override
    public void describeInventory() {
        publisher.gameEventNotification(character.describeInventory());
    }

    @Override
    public List<ItemObject> showInventory() {
        return character.getInventory();
    }

    @Override
    public void describeRoom() {
        publisher.gameEventNotification(character.describeRoom());
    }

    @Override
    public ItemObject getItemGivenName(String name) {
        return itemsInGame.get(name);
    }

    @Override
    public void addItemInInventory(ItemObject itemObject) {
        publisher.gameEventNotification(character.addItemInInventory(itemObject));
    }

    @Override
    public void startCombat() {
        //start battle only if there are enemies in the room.
        Optional<Enemy> enemy = mapEngine.getEnemyInRoom(character.getPosition());
        if (enemy.isPresent()) {
            isBattleMode = true;
            combatEngine.setMainCharacter(character);
            combatEngine.setEnemy(enemy.get());
            publisher.gameEventNotification(new LocalizedMessage("game.combat.start"));
        }
        else {
            publisher.gameEventNotification(new LocalizedMessage("game.combat.no.enemies"));
        }
    }

    @Override
    public boolean isInCombat() {
        return isBattleMode;
    }

    @Override
    public void performEnemyAction() {

    }

    @Override
    public void performCombatAction(CombatEngine.CombatActions actions) {
        try {
            //check if there are still actions
            character.reduceActionPoints(actions.getActionPoint());
            combatEngine.performAction(actions, true);
            if (actions.getActionPoint() > 0) //show message only if the action use the points
                publisher.gameEventNotification(new LocalizedMessage("character.stats.action.points", character.getActionPointsLeft()));
        } catch (Exception | LoseGameException | InsufficientActionPointsException ex) {
            publisher.gameEventNotification(new LocalizedMessage(ex.getMessage()));
        } catch (EnemyDiedEvent e) {
            isBattleMode = false;
            mapEngine.removeEnemyFromRoom(character.getPosition());
        }
    }
}
