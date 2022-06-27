package it.shifty.textgame.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtils {

    static ResourceBundle localizationEngine;

    public LocaleUtils(final Locale locale) {
        localizationEngine = ResourceBundle.getBundle("language/text", locale);
    }

    public static String localizeString(String text) {
        return localizationEngine.getString(text);
    }

    public static List<String> localizeString(List<String> texts) {
        List<String> localizedString = new ArrayList<>();
        for (String text : texts) {
            localizedString.add(localizationEngine.getString(text));
        }
        return localizedString;
    }

}
