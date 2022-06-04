package it.shifty.game.gameobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asset {

    private String name;
    private String description;

    public Asset(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
