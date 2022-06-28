package it.shifty.textgame.events;

import it.shifty.textgame.core.dto.GameMessage;
import it.shifty.textgame.core.events.Publisher;

import static it.shifty.textgame.events.EventManager.EventMessageType.GAME_MESSAGE;
import static it.shifty.textgame.events.EventManager.EventMessageType.STATS_MESSAGE;

public class PublisherEngine implements Publisher {

    @Override
    public void gameEventNotification(GameMessage message) {
        EventManager.getInstance().notify(GAME_MESSAGE, message);
    }

    @Override
    public void gameStatsNotification(GameMessage message) {
        EventManager.getInstance().notify(STATS_MESSAGE,message);
    }





}
