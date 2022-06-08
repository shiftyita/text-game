package it.shifty.textgame.engine.display;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OutputMessage {

    private List<String> multiMessage;

    private String message;

    public OutputMessage() {
        this.multiMessage = new ArrayList<>();
    }

    public OutputMessage(List<String> multiMessage) {
        this.multiMessage = multiMessage;
    }

    public OutputMessage(String message) {
        this.message = message;
    }

    public void addMessage(String message) {
        multiMessage.add(message);
    }

}
