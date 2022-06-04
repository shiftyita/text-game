package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemObject extends Asset {

    private boolean destroyed;

    public ItemObject(String name, String description) {
        super(name, description);
    }
}
