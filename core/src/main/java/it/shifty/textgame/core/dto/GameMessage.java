package it.shifty.textgame.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameMessage {

    private List<String> multiMessage;

    private String message;

    private boolean mustHaveSeparator = false;

    public GameMessage() {
        this.multiMessage = new ArrayList<>();
    }

    public GameMessage(List<String> multiMessage) {
        this.multiMessage = multiMessage;
    }

    public GameMessage(List<String> multiMessage, boolean mustHaveSeparator) {
        this.multiMessage = multiMessage;
        this.mustHaveSeparator = mustHaveSeparator;
    }

    public GameMessage(String message) {
        this.message = message;
    }

    public void addMessage(String message) {
        multiMessage.add(message);
    }

}
