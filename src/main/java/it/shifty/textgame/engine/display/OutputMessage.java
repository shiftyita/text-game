package it.shifty.textgame.engine.display;

import lombok.Data;

import java.util.List;

@Data
public class OutputMessage {

    private List<String> multiMessage;

    private String message;

    private boolean isGameMessage;

    public OutputMessage(List<String> multiMessage) {
        this.multiMessage =multiMessage;
        this.isGameMessage = true;
    }

    public OutputMessage(String message) {
        this.message = message;
        this.isGameMessage = true;
    }

    public OutputMessage(String message, boolean isGameMessage) {
        this.message = message;
        this.isGameMessage = isGameMessage;
    }

}
