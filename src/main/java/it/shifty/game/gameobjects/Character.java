package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Character extends Asset {

    private List<ItemObject> inventory;
    private Weapon primaryWeapon;
    private Weapon secondaryWeapon;
    private Armor armor;
    private boolean mainCharacter;

    public Character(String name, String description) {
        super(name,description, 100);
    }
}
