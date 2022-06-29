package it.shifty.textgame.events;

import it.shifty.textgame.core.dto.GameMessage;

public interface EventListener {

    void publishEvent(EventManager.EventMessageType eventType, GameMessage message);

}
