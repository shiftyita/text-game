package it.shifty.textgame.engine.gameobjects;

import it.shifty.textgame.engine.locale.LocaleUtils;
import it.shifty.textgame.engine.utils.GameUtils;

public abstract class AbstractAsset {

    private final String name;
    private final String description;
    private final String internalName;
    private final String keyName;

    public AbstractAsset(String name, String description) {
        this.keyName = name;
        this.name = LocaleUtils.localizeString(name);
        this.description = description;
        this.internalName = formatName(LocaleUtils.localizeString(name));
    }

    public String formatName(String name) {
        return GameUtils.abstractAssetNameFormatter(name);
    }

    public String getKeyName() {
        return keyName;
    }

    public String getName() {
        return name;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getDescription() {
        return description;
    }
}
