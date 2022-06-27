package it.shifty.textgame.events;

import it.shifty.textgame.core.dto.GameOutputMessage;
import it.shifty.textgame.core.dto.OutputMessage;
import it.shifty.textgame.core.events.Publisher;

import static it.shifty.textgame.events.EventManager.EventMessageType.GAME_MESSAGE;
import static it.shifty.textgame.events.EventManager.EventMessageType.STATS_MESSAGE;

public class PublisherEngine implements Publisher {

    @Override
    public void gameEventNotification(String message, Object... params) {
        if (params.length != 0)
            EventManager.getInstance().notify(GAME_MESSAGE, new GameOutputMessage(message,params));
        else
            EventManager.getInstance().notify(GAME_MESSAGE, new GameOutputMessage(message));
    }

    @Override
    public void gameEventNotification(OutputMessage message) {
        EventManager.getInstance().notify(GAME_MESSAGE, message);
    }

    @Override
    public void gameStatsNotification(String message, Object... params) {
        EventManager.getInstance().notify(STATS_MESSAGE, new GameOutputMessage(message,params));
    }



}
