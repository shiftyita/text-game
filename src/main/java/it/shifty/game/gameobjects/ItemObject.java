package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemObject extends Asset {

    public ItemObject(String name, String description) {
        super(name, description, 100);
    }
}
