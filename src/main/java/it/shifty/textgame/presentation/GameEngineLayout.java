package it.shifty.textgame.presentation;

import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;

import java.util.List;

public interface GameEngineLayout {

    GameOutputMessage moveCharacter(Direction direction);

    GameOutputMessage describeInventory();

    List<ItemObject> showInventory();

    GameOutputMessage describeRoom();

}
