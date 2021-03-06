package it.shifty.textgame.engine.gameobjects;

import it.shifty.textgame.core.dto.LocalizedMessage;
import it.shifty.textgame.engine.exception.InsufficientActionPointsException;
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
    private final int DEFAULT_ACTION_POINT = 5;
    private int actionPointsLeft;
    private int totalActionPoints;

    private Character(String name, String description) {
        super(name, description, 100);
        this.inventory = new ArrayList<>();
        this.totalActionPoints = DEFAULT_ACTION_POINT;
        this.actionPointsLeft = this.totalActionPoints;
    }

    public Character(String name, String description, boolean mainCharacter) {
        this(name, description);
        this.mainCharacter = mainCharacter;
    }

    public Character(String name, String description, boolean mainCharacter, Room position) {
        this(name, description, mainCharacter);
        this.position = position;
    }

    public Character(String name, String description, boolean mainCharacter, Room position, int actionPoint) {
        this(name, description, mainCharacter, position);
        this.totalActionPoints = actionPoint;
        this.actionPointsLeft = this.totalActionPoints;
    }

    public LocalizedMessage addItemInInventory(ItemObject itemObject) {
        inventory.add(itemObject);
        List<String> output = new ArrayList<>();
        output.add(itemObject.getKeyName());
        output.add("items.add.inventory");
        return new LocalizedMessage(output);
    }

    public LocalizedMessage describeRoom() {
        return new LocalizedMessage(position.getDescription());
    }

    public LocalizedMessage describeInventory() {
        List<String> output = new ArrayList<>();
        if (inventory.isEmpty()) {
            output.add("character.inventory.empty");
            return new LocalizedMessage(output);
        }
        output.add("character.inventory.start.description");
        for (ItemObject item : inventory) {
            output.add(item.getDescription());
        }
        return new LocalizedMessage(output);
    }

    private boolean canDoAction(int actionPoint) {
        return (actionPointsLeft - actionPoint >=0);
    }

    public void reduceActionPoints(int actionPoint) throws InsufficientActionPointsException {
        if (canDoAction(actionPoint)) {
            actionPointsLeft = actionPointsLeft - actionPoint;
        }
        else {
            throw new InsufficientActionPointsException("exception.insufficient.action.point");
        }
    }

    public void resetActionPoints() {
        this.actionPointsLeft = totalActionPoints;
    }

}
