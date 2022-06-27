package it.shifty.textgame.engine.display;

import it.shifty.textgame.engine.events.EventListener;
import it.shifty.textgame.engine.events.EventManager;
import it.shifty.textgame.presentation.DisplayOutput;
import jdk.jfr.Event;

public class SysOutLocaleDisplay implements DisplayOutput, EventListener {

    public SysOutLocaleDisplay() {
        EventManager.getInstance().subscribe(EventManager.EventMessageType.GAME_MESSAGE, this);
    }

    @Override
    public void printTextOutput(OutputMessage message) {
        if (message.getMessage() != null) {
            System.out.println(message.getMessage());
        } else {
            if (!message.getMultiMessage().isEmpty()) {
                if (!message.isMustHaveSeparator())
                    System.out.println(String.join(" ", message.getMultiMessage()));
                else
                    System.out.println(String.join(",", message.getMultiMessage()));
            }

        }
    }

    @Override
    public void publishEvent(EventManager.EventMessageType eventType, OutputMessage message) {
        printTextOutput(message);
    }
}
