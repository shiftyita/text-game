package it.shifty.textgame.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractAsset {

    private String name;
    private String description;

    public AbstractAsset(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
