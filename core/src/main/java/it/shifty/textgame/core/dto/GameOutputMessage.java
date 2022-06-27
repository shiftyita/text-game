package it.shifty.textgame.core.dto;

import it.shifty.textgame.core.LocaleUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameOutputMessage extends OutputMessage {

    public GameOutputMessage() {
        super();
    }

    public GameOutputMessage(List<String> multiMessage) {
        super(LocaleUtils.localizeString(multiMessage));
    }

    public void addMessage(String message) {
        super.addMessage(LocaleUtils.localizeString(message));
    }

    public GameOutputMessage(String message) {
        super(LocaleUtils.localizeString(message));
    }

    public GameOutputMessage(String message, Object... params) {
        super(String.format(LocaleUtils.localizeString(message), params));
    }

}
