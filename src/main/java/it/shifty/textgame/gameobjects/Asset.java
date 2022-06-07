package it.shifty.textgame.gameobjects;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asset extends AbstractAsset implements DamageAbsorber  {

    private boolean destroyed;
    private int health = 99999;
    private boolean canBeDestroyed = true;

    public Asset(String name, String description, int health) {
        super(name,description);
        this.health = health;
    }

    public Asset(String name, String description, boolean canBeDestroyed) {
        super(name, description);
        this.canBeDestroyed = canBeDestroyed;
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
