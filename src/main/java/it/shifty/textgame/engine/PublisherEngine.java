package it.shifty.textgame.engine;

import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.display.OutputMessage;
import it.shifty.textgame.engine.events.EventManager;

import static it.shifty.textgame.engine.events.EventManager.EventMessageType.GAME_MESSAGE;

public class PublisherEngine {
    public void notify(String message, Object... params) {
        EventManager.getInstance().notify(GAME_MESSAGE, new GameOutputMessage(message,params));
    }

}
