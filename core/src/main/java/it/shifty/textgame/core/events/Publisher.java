package it.shifty.textgame.core.events;

import it.shifty.textgame.core.dto.GameOutputMessage;
import it.shifty.textgame.core.dto.OutputMessage;

public interface Publisher {
    void gameEventNotification(String message, Object... params);

    void gameEventNotification(OutputMessage message);

    void gameStatsNotification(String message, Object... params);
}


