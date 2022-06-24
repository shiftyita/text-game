package it.shifty.textgame.engine.map;


import it.shifty.textgame.engine.gameobjects.Asset;
import it.shifty.textgame.engine.gameobjects.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room extends Asset {

    private Room n,s,w,e;

    private int x;

    private int y;

    private Character enemy;

    public Room(String roomName, String roomDescription) {
        super(roomName, roomDescription, false);
    }

    public Room(String roomName, String roomDescription, int posX, int posY) {
        this(roomName, roomDescription);
        this.x = posX;
        this.y = posY;
    }

    void addEnemy(Character enemy) {
        this.enemy = enemy;
    }

    void setN(Room n) {
        this.n = n;
    }

    void setS(Room s) {
        this.s = s;
    }

    void setW(Room w) {
        this.w = w;
    }

    void setE(Room e) {
        this.e = e;
    }

}
