package it.shifty.textgame.engine.map;

import it.shifty.textgame.engine.gameobjects.ItemObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Key extends ItemObject {

    public Key(String name, String description) {
        super(name, description);
    }
}
