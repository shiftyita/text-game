package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Character extends Asset {

    private int health = 100;
    private List<ItemObject> inventory;
    private Weapon primaryWeapon;
    private Weapon secondaryWeapon;
    private ItemObject armor;

    public Character(String name, String description) {
        super(name,description);
    }


}
