package it.shifty.textgame.core.engine.gameobjects;

public class ItemObject extends Asset {

    public ItemObject(String name, String description) {
        this(name, description, 100);
    }

    public ItemObject(String name, String description, int itemHealth) {
        super(name, description, itemHealth);
    }
}
