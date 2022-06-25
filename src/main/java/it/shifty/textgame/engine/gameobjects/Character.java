package it.shifty.textgame.engine.gameobjects;

import it.shifty.textgame.engine.display.GameOutputMessage;
import it.shifty.textgame.engine.exception.InsufficientActionPointsException;
import it.shifty.textgame.engine.map.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static it.shifty.textgame.engine.gameobjects.GlobalParameters.DEFAULT_DAMAGE_WEAPON;

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

    public Character(String name, String description) {
        super(name, description, 100);
        this.inventory = new ArrayList<>();
    }

    public Character(String name, String description, boolean mainCharacter) {
        this(name, description);
        this.mainCharacter = mainCharacter;
    }

    public Character(String name, String description, boolean mainCharacter, Room position) {
        this(name, description, mainCharacter);
        this.position = position;
        this.totalActionPoints = DEFAULT_ACTION_POINT;
        this.actionPointsLeft = this.totalActionPoints;
    }

    public Character(String name, String description, boolean mainCharacter, Room position, int actionPoint) {
        this(name, description, mainCharacter, position);
        this.totalActionPoints = actionPoint;
        this.actionPointsLeft = this.totalActionPoints;
    }

    public GameOutputMessage addItemInInventory(ItemObject itemObject) {
        inventory.add(itemObject);
        List<String> output = new ArrayList<>();
        output.add(itemObject.getKeyName());
        output.add("items.add.inventory");
        return new GameOutputMessage(output);
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
        output.add("character.inventory.start.description");
        for (ItemObject item : inventory) {
            output.add(item.getDescription());
        }
        return new GameOutputMessage(output);
    }

    public void reduceActionPoints(int actionPoint) throws InsufficientActionPointsException {
        if (actionPointsLeft - actionPoint >= 0) {
            actionPointsLeft = actionPointsLeft - actionPoint;
        }
        else {
            throw new InsufficientActionPointsException("exception.insufficient.action.point");
        }
    }

}
