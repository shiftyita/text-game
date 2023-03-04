package it.shifty.textgame.core.dto;

import it.shifty.textgame.core.LocaleUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocalizedMessage extends GameMessage {

    public LocalizedMessage() {
        super();
    }

    public LocalizedMessage(List<String> multiMessage) {
        super(LocaleUtils.localizeString(multiMessage));
    }

    public void addMessage(String message) {
        super.addMessage(LocaleUtils.localizeString(message));
    }

    public void addMessage(LocalizedMessage message) {
        super.addMessage(message.getMessage());
    }

    public LocalizedMessage(String message) {
        super(LocaleUtils.localizeString(message));
    }

    public LocalizedMessage(String message, Object... params) {
        super(String.format(LocaleUtils.localizeString(message), params));
    }

}
