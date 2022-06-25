package it.shifty.textgame.engine.events;

import it.shifty.textgame.engine.display.GameOutputMessage;

import static it.shifty.textgame.engine.events.EventManager.EventMessageType.GAME_MESSAGE;

public class PublisherEngine {

    public void gameEventNotification(String message, Object... params) {
        EventManager.getInstance().notify(GAME_MESSAGE, new GameOutputMessage(message,params));
    }

    public void gameEventNotification(GameOutputMessage message) {
        EventManager.getInstance().notify(GAME_MESSAGE, message);
    }

}
