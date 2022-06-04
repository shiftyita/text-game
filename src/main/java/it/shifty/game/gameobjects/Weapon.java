package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weapon extends ItemObject implements DamageDealer {

    private int damage;
    private int bonus;
    private int malus;

    public Weapon(String name, String description) {
        super(name, description);
    }

    @Override
    public int getDamage() {
        int totalDamage = damage + bonus + malus;
        return Math.max(totalDamage, 0);
    }
}
