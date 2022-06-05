package it.shifty.game.gameobjects;

import it.shifty.game.engine.MapEngine;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Room extends Asset {

    private Room n,s,w,e;

    private int x;

    private int y;

    public Room(String roomName, String roomDescription, Room rN, Room rS, Room rW, Room rE) {
        super(roomName, roomDescription, false);
        this.n = rN;
        this.s = rS;
        this.w = rW;
        this.e = rE;
    }


    public void checkSurroundigs(MapEngine mapEngine) {

    }

}
