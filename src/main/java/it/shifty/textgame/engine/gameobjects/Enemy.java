package it.shifty.textgame.engine.gameobjects;

public class Enemy extends Character {

    public Enemy(String name, String description, Weapon primaryWeapon, Armor defaultArmor) {
        super(name, description, false);
        this.setPrimaryWeapon(primaryWeapon);
        this.setArmor(defaultArmor);
    }
}
