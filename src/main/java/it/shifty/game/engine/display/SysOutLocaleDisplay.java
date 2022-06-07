package it.shifty.game.engine.display;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SysOutLocaleDisplay implements DisplayOutput {

    private Locale locale;
    ResourceBundle words;

    public SysOutLocaleDisplay(Locale locale) {
        this.locale = locale;
        words = ResourceBundle.getBundle("language/text", locale);
    }

    @Override
    public void printTextOutput(OutputMessage message) {
        if (!message.isGameMessage())

        if (message.getMessage() != null) {
            System.out.println(words.getString(message.getMessage()));
            if (message.getMultiMessage() != null) {
                for (String text : message.getMultiMessage()) {
                    System.out.println(words.getString(text));
                }
            }
        }
    }

}
