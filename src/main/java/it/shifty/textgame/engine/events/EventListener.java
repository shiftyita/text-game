package it.shifty.textgame.engine.events;

import it.shifty.textgame.engine.display.OutputMessage;

public interface EventListener {

    void publishEvent(EventManager.EventMessageType eventType, OutputMessage message);

}
