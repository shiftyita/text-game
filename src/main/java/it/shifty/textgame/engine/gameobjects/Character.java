package it.shifty.textgame.engine.gameobjects;

import it.shifty.textgame.engine.display.GameOutputMessage;
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
    private ItemObject holdenItem;

    public Character(String name, String description) {
        super(name, description, 100);
        this.inventory = new ArrayList<>();
    }

    public Character(String name, String description, boolean mainCharacter, Room position) {
        this(name, description);
        this.mainCharacter = mainCharacter;
        this.position = position;
    }

    public GameOutputMessage describeRoom() {
        return new GameOutputMessage(position.getDescription());
    }

    public GameOutputMessage describeInventory() {
        List<String> output = new ArrayList<>();
        if (inventory.isEmpty()) {
            output.add("character.inventory.empty");
            return new GameOutputMessage(output);
        }
        output.add("Ecco cosa contiene il mio zaino:");
        for (ItemObject item : inventory) {
            output.add(item.getDescription());
        }
        return new GameOutputMessage(output);
    }


}
