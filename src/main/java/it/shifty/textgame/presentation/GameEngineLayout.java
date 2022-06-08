package it.shifty.textgame.presentation;

import it.shifty.textgame.engine.display.OutputMessage;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.map.Direction;

import java.util.List;

public interface GameEngineLayout {

    OutputMessage moveCharacter(Direction direction);

    OutputMessage describeInventory();

    List<ItemObject> showInventory();

    OutputMessage describeRoom();

}
