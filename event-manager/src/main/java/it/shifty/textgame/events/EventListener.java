package it.shifty.textgame.events;

import it.shifty.textgame.core.dto.OutputMessage;

public interface EventListener {

    void publishEvent(EventManager.EventMessageType eventType, OutputMessage message);

}
