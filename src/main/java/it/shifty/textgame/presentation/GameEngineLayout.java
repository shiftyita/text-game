package it.shifty.textgame.presentation;

import it.shifty.textgame.engine.combat.CombatEngine;
import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;

import java.util.List;

public interface GameEngineLayout {

    GameOutputMessage moveCharacter(Direction direction);

    GameOutputMessage describeInventory();

    List<ItemObject> showInventory();

    GameOutputMessage describeRoom();

    ItemObject getItemGivenName(String name);

    GameOutputMessage addItemInInventory(ItemObject itemObject);

    GameOutputMessage startCombat();

    boolean isInCombat();

    GameOutputMessage performAction(CombatEngine.CombactActions actions);



}
