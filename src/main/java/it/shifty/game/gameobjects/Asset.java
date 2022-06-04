package it.shifty.game.gameobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asset implements DamageAbsorber  {

    private String name;
    private String description;

    private boolean destroyed;
    private int health = 99999;
    private boolean canBeDestroyed = true;

    public Asset(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Asset(String name, String description, int health) {
        this(name,description);
        this.health = health;
    }

    @Override
    public int absorbDamage(int damage) {
        if (canBeDestroyed) {
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
        return 0;
    }

}
