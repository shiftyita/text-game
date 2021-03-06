package it.shifty.textgame.engine.map;

import it.shifty.textgame.core.dto.LocalizedMessage;
import it.shifty.textgame.engine.exception.RoomMisplacedException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.Enemy;

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

    public LocalizedMessage moveCharacter(Character character, Direction direction) {
        Room currentRoom = character.getPosition();
        switch (direction) {
            case NORTH:
                if (currentRoom.getN() != null)
                    return moveCharacterTo(character, currentRoom.getN());
                break;
            case SOUTH:
                if (currentRoom.getS() != null)
                    return moveCharacterTo(character, currentRoom.getS());
                break;
            case WEST:
                if (currentRoom.getW()!=null)
                    return moveCharacterTo(character, currentRoom.getW());
                break;
            case EAST:
                if (currentRoom.getE() != null)
                    return moveCharacterTo(character, currentRoom.getE());
                break;
            default:
                return new LocalizedMessage("character.mapengine.donot.understand");
        }
        return new LocalizedMessage("character.mapengine.cannot.go");
    }

    private LocalizedMessage moveCharacterTo(Character character, Room destRoom) {
        LocalizedMessage message = new LocalizedMessage();
        boolean canMove = false;
        String msg = "";
        if (destRoom instanceof RoomClosedWithKey) {
            if (character.getHoldenItem() instanceof Key) {
                if (((RoomClosedWithKey) destRoom).canBeOpen((Key) character.getHoldenItem()))
                    canMove = true;
                message.addMessage(((RoomClosedWithKey) destRoom).open((Key) character.getHoldenItem()));
            } else {
                message.addMessage("items.key.door.need");
            }
        }
        if (canMove) {
            character.setPosition(destRoom);
            message.addMessage(character.describeRoom().getMessage());
        }
        return message;
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

    public Optional<Enemy> getEnemyInRoom(Room room) {
        Optional<Room> existingRoom = getRoom(room.getX(), room.getY());
        return Optional.ofNullable(existingRoom.map(Room::getEnemy).orElse(null));
    }

    public void removeEnemyFromRoom(Room room) {
        room.setEnemy(null);
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
