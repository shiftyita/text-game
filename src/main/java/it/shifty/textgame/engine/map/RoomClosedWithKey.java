package it.shifty.textgame.engine.map;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoomClosedWithKey extends Room {

    private boolean isClosed = true;

    private List<Key> keysThatOpensRoom = new ArrayList<>();

    public RoomClosedWithKey(String roomName, String roomDescription, Key keyThatOpensRoom) {
        super(roomName, roomDescription);
        this.keysThatOpensRoom.add(keyThatOpensRoom);
    }

    public RoomClosedWithKey(String roomName, String roomDescription, int posX, int posY, Key keyThatOpensRoom) {
        super(roomName, roomDescription, posX, posY);
        this.keysThatOpensRoom.add(keyThatOpensRoom);
    }

    public boolean canBeOpen(Key someKey) {
        if (isClosed) {
            if (keysThatOpensRoom.contains(someKey))
                return true;
            return false;
        } else {
            return true;
        }
    }


    public String open(Key someKey) {
        if (isClosed) {
            if (keysThatOpensRoom.contains(someKey)) {
                isClosed = false;
                return "items.key.door.open";
            }
            return "items.key.door.closed";
        } else {
            return "items.key.door.alreadyopen";
        }
    }
}
