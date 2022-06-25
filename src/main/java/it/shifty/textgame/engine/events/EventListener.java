package it.shifty.textgame.engine.events;

import it.shifty.textgame.engine.display.OutputMessage;

public interface EventListener {

    void update(EventManager.EventMessageType eventType, OutputMessage message);

}
