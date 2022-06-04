package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemObject extends Asset {

    private int health;

    public ItemObject(String name, String description, int health) {
        super(name, description);
        this.health =health;
    }

    public ItemObject(String name, String description) {
        super(name, description);
    }

}
