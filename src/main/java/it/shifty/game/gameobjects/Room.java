package it.shifty.game.gameobjects;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Room extends Asset {

    private Room n,s,w,e;

    public Room(String roomName, String roomDescription, Room rN, Room rS, Room rW, Room rE) {
        super(roomName, roomDescription);
        this.n = rN;
        this.s = rS;
        this.w = rW;
        this.e = rE;
    }


}
