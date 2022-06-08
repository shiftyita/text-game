package it.shifty.textgame.engine.gameobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Armor extends ItemObject{
    public Armor(String name, String description) {
        super(name, description);
    }

}
