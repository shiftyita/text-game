package it.shifty.textgame.core.engine.display;

import it.shifty.textgame.core.presentation.DisplayOutput;
import it.shifty.textgame.events.EventListener;
import it.shifty.textgame.events.EventManager;
import it.shifty.textgame.core.dto.OutputMessage;

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