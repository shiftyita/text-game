package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Character extends Asset {

    private int health;
    private List<ItemObject> inventory;

    public Character(String name, String description) {
        super(name,description);
    }


}
