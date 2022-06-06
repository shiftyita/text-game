package it.shifty.game.engine.map;

import it.shifty.game.engine.exception.RoomMisplacedException;
import it.shifty.game.gameobjects.Character;

import java.util.List;
import java.util.Optional;

public class MapEngine {

    private Room[][] map;
    private int xSize;
    private int ySize;

    public MapEngine(List<Room> rooms, int xGrid, int yGrid) throws RoomMisplacedException {
        xSize = xGrid;
        ySize = yGrid;
        placeRooms(rooms);
        calculateRoomSurroundigs();
    }

    public String moveCharacter(Character character, Direction direction) {
        Room currentRoom = character.getPosition();
        switch (direction) {
            case NORTH:
                if (currentRoom.getN()!=null)
                    return moveCharacterTo(character, currentRoom.getN());
                break;
            case SOUTH:
                if (currentRoom.getS()!=null)
                    return moveCharacterTo(character, currentRoom.getS());
                break;
            case WEST:
                if (currentRoom.getW()!=null)
                    return moveCharacterTo(character, currentRoom.getW());
                break;
            case EAST:
                if (currentRoom.getE()!=null)
                    return moveCharacterTo(character, currentRoom.getE());
                break;
            default:
                return "Non posso andare lì";
        }
        return "";
    }

    private String moveCharacterTo(Character character, Room destRoom) {
        if (character.getPosition().isSurroundedBy(destRoom)) {
            character.setPosition(destRoom);
            return character.describeRoom();
        }
        else
            return "";
    }

    public void placeRooms(List<Room> rooms) throws RoomMisplacedException {
        map = new Room[xSize][ySize];
        for (Room room: rooms) {
            insertRoomInMap(room, room.getX(), room.getY());
        }
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

    public void drawMap() {
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < xSize; y++) {
                if (!existsRoomInSpot(x,y))
                    System.out.print(" ");
                else
                    System.out.print("X");
            }
            System.out.println("\r");
        }
    }

    public void calculateRoomSurroundigs() {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (!existsRoomInSpot(x,y))
                    continue;
                Room existingRoom = getRoom(x,y).get();
                //get north
                Optional<Room> northRoom = this.getRoom(x-1, y);
                //get south
                Optional<Room> southRoom = this.getRoom(x+1,y);
                //get east
                Optional<Room> eastRoom = this.getRoom(x, y + 1);
                //get west
                Optional<Room> westRoom = this.getRoom(x, y - 1);

                northRoom.ifPresent(existingRoom::setN);
                southRoom.ifPresent(existingRoom::setS);
                eastRoom.ifPresent(existingRoom::setE);
                westRoom.ifPresent(existingRoom::setW);

//                logExtractedRoom(existingRoom, northRoom, southRoom, eastRoom, westRoom);

            }
        }
    }

    private void logExtractedRoom(Room existingRoom, Optional<Room> northRoom, Optional<Room> southRoom, Optional<Room> eastRoom, Optional<Room> westRoom) {
        System.out.println("La stanza in coordinata (" + existingRoom.getX() + "," + existingRoom.getY()+")");
        if (northRoom.isPresent())
            System.out.println("confina a Nord con " + northRoom.get().getName());
        else
            System.out.println("confina a Nord con un muro");

        if (southRoom.isPresent())
            System.out.println("confina a Sud con " + southRoom.get().getName());
        else
            System.out.println("confina a Sud con un muro");

        if (westRoom.isPresent())
            System.out.println("confina ad Ovest con " + westRoom.get().getName());
        else
            System.out.println("confina ad Ovest con un muro");

        if (eastRoom.isPresent())
            System.out.println("confina ad Est con " + eastRoom.get().getName());
        else
            System.out.println("confina ad Est con un muro");
    }
}
