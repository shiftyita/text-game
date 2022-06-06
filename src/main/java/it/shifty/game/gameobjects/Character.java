package it.shifty.game.gameobjects;

import it.shifty.game.engine.map.Room;
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
    private Room position;

    public Character(String name, String description) {
        super(name, description, 100);
    }

    public Character(String name, String description, boolean main, Room position) {
        this(name, description);
        this.mainCharacter = main;
        this.position = position;
    }

    public String describeRoom() {
        return position.getDescription();
    }

    public String describeInventory() {
        String message;
        if (inventory.isEmpty()) {
            message = "Il mio inventario è vuoto.";
            return message;
        }
        message = "Ecco cosa contiene il mio zaino:\n";
        for (ItemObject item : inventory) {
            message+= item.getDescription()+"\n";
        }
        return message;
    }


}
