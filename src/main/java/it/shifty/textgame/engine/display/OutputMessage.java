package it.shifty.textgame.engine.display;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OutputMessage {

    private List<String> multiMessage;

    private String message;

    private boolean isGameMessage = true;

    public OutputMessage() {
        this.multiMessage = new ArrayList<>();
    }

    public OutputMessage(List<String> multiMessage) {
        this.multiMessage = multiMessage;
    }

    public void addMessage(String message) {
        multiMessage.add(message);
    }

    public OutputMessage(String message) {
        this.message = message;
    }

    public OutputMessage(String message, boolean isGameMessage) {
        this.message = message;
        this.isGameMessage = isGameMessage;
    }

}
