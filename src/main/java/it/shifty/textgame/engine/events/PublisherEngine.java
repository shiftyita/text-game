package it.shifty.textgame.engine.events;

import it.shifty.textgame.engine.display.GameOutputMessage;

import static it.shifty.textgame.engine.events.EventManager.EventMessageType.GAME_MESSAGE;
import static it.shifty.textgame.engine.events.EventManager.EventMessageType.STATS_MESSAGE;

public class PublisherEngine {

    public void gameEventNotification(String message, Object... params) {
        if (params.length != 0)
            EventManager.getInstance().notify(GAME_MESSAGE, new GameOutputMessage(message,params));
        else
            EventManager.getInstance().notify(GAME_MESSAGE, new GameOutputMessage(message));
    }

    public void gameStatsNotification(String message, Object... params) {
        EventManager.getInstance().notify(STATS_MESSAGE, new GameOutputMessage(message,params));
    }

    public void gameEventNotification(GameOutputMessage message) {
        EventManager.getInstance().notify(GAME_MESSAGE, message);
    }

}
