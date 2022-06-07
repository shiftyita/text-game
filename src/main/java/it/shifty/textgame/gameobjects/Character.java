package it.shifty.textgame.gameobjects;

import it.shifty.textgame.engine.display.OutputMessage;
import it.shifty.textgame.engine.map.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
        this.inventory = new ArrayList<>();
    }

    public Character(String name, String description, boolean main, Room position) {
        this(name, description);
        this.mainCharacter = main;
        this.position = position;
    }

    public OutputMessage describeRoom() {
        return new OutputMessage(position.getDescription());
    }

    public OutputMessage describeInventory() {
        List<String> output = new ArrayList<>();
        if (inventory.isEmpty()) {
            output.add("character.inventory.empty");
            return new OutputMessage(output);
        }
        output.add("Ecco cosa contiene il mio zaino:");
        for (ItemObject item : inventory) {
            output.add(item.getDescription());
        }
        return new OutputMessage(output);
    }


}
