package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Armor extends ItemObject implements DamageAbsorber{

    private int health;

    public Armor(String name, String description, int health) {
        super(name, description);
        this.health = health;
    }

    public Armor(String name, String description) {
        super(name, description);
    }


    @Override
    public int absorbDamage(int damage) {
        if (isDestroyed())
            return damage;
        health = health - damage;
        if (health < 0) {
            this.setDestroyed(true);
            //this will be the damage that will get the character
            return Math.abs(health);
        }
        else
            return 0;
    }
}
