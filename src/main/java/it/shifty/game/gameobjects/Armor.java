package it.shifty.game.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Armor extends ItemObject{
    public Armor(String name, String description) {
        super(name, description);
    }

}
