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
        if (message instanceof GameOutputMessage) { //if it's a game message, it's localized
            if (message.getMessage() != null) {
                System.out.println(message.getMessage());
            } else {
                System.out.println(String.join(" ", message.getMultiMessage()));
            }
        } else
            System.out.println(message.getMessage());
    }

    @Override
    public void publishEvent(EventManager.EventMessageType eventType, OutputMessage message) {
        printTextOutput(message);
    }
}
