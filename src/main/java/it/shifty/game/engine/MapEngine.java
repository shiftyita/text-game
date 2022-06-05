package it.shifty.game.engine;

import it.shifty.game.engine.exception.RoomMisplacedException;
import it.shifty.game.gameobjects.Room;

import java.util.Optional;

public class MapEngine {

    private Room[][] map;

    private int xSize;
    private int ySize;

    public MapEngine(Room[][] map) {
        this.map = map;
        xSize = map.length;
        ySize = map[0].length;
    }

    public void check(int x, int y) throws RoomMisplacedException {
        if ( x < 0 || y < 0 )
            throw new IndexOutOfBoundsException("Illegal coordinates");
        if ( x > xSize || y > ySize)
            throw new RoomMisplacedException("The room cannot fit in the grid");
    }

    public boolean existsRoomInSpot(int x, int y) {
        return map[x][y] != null;
    }

    public Room insertRoomInMap(Room room, int x, int y) throws RoomMisplacedException {
        check(x,y);
        //check if it's not already occupied
        if (existsRoomInSpot(x,y))
            throw new RoomMisplacedException("A room exists already in the spot ("+x+","+y+")");
        map[x][y] = room;
        room.setX(x);
        room.setY(y);
        return room;

    }

    public Optional<Room> getRoom(int x, int y) {
        try {
            check(x,y);
            if (existsRoomInSpot(x,y))
                return Optional.of(map[x][y]);
            else
                return Optional.empty();
        }
        catch (Exception | RoomMisplacedException e) {
            return Optional.empty();
        }
    }
}
