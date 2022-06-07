package it.shifty.textgame.engine.display;

import java.util.Locale;
import java.util.ResourceBundle;

public class SysOutLocaleDisplay implements DisplayOutput {

    ResourceBundle localizationEngine;

    public SysOutLocaleDisplay(final Locale locale) {
        localizationEngine = ResourceBundle.getBundle("language/text", locale);
    }

    @Override
    public void printTextOutput(OutputMessage message) {
        if (!message.isGameMessage()) //if it's a system message, no localization possible
            System.out.println(message.getMessage());
        else
        {
            if (message.getMessage() != null) {
                System.out.println(localizationEngine.getString(message.getMessage()));
                if (message.getMultiMessage() != null) {
                    for (String text : message.getMultiMessage()) {
                        System.out.println(localizationEngine.getString(text));
                    }
                }
            }
        }
    }
}
