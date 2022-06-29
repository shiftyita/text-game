package it.shifty.textgame.core.events;

import it.shifty.textgame.core.dto.GameMessage;

public interface Publisher {

    void gameEventNotification(GameMessage message);

    void gameStatsNotification(GameMessage message);
}


