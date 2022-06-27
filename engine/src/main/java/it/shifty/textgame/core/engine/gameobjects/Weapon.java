package it.shifty.textgame.core.engine.gameobjects;

public class Weapon extends ItemObject implements DamageDealer {

    private int damage = 0;
    private int bonus;
    private int malus;

    public Weapon(String name, String description) {
        super(name, description);
    }

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        int totalDamage = damage + bonus + malus;
        return Math.max(totalDamage, 0);
    }
}
