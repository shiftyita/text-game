package it.shifty.textgame.core.presentation;

import it.shifty.textgame.core.engine.combat.CombatEngine;
import it.shifty.textgame.core.engine.gameobjects.ItemObject;
import it.shifty.textgame.core.engine.map.Direction;

import java.util.List;

public interface GameEngineLayout {

    void moveCharacter(Direction direction);

    void describeInventory();

    List<ItemObject> showInventory();

    void describeRoom();

    ItemObject getItemGivenName(String name);

    void addItemInInventory(ItemObject itemObject);

    void startCombat();

    boolean isInCombat();

    void performCombatAction(CombatEngine.CombatActions actions);

    void performEnemyAction();

}
