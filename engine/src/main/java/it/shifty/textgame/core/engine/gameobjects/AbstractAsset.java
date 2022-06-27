package it.shifty.textgame.core.engine.gameobjects;

import it.shifty.textgame.core.engine.utils.GameUtils;
import it.shifty.textgame.core.LocaleUtils;

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
