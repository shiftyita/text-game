package it.shifty.textgame.engine.gameobjects;

import it.shifty.textgame.engine.ai.Behaviour;
import it.shifty.textgame.engine.ai.DefaultBehaviour;

public class Enemy extends Character {

    private Behaviour behaviour;

    public Enemy(String name, String description, Weapon primaryWeapon, Armor defaultArmor) {
        super(name, description, false);
        this.setPrimaryWeapon(primaryWeapon);
        this.setArmor(defaultArmor);
        this.behaviour = DefaultBehaviour.getInstance();
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }
}
