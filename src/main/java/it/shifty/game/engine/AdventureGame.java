package it.shifty.game.engine;

import it.shifty.game.engine.exception.LoseGameException;
import it.shifty.game.engine.exception.RoomMisplacedException;
import it.shifty.game.engine.map.MapEngine;
import it.shifty.game.engine.map.Room;
import it.shifty.game.gameobjects.Character;

import java.util.ArrayList;
import java.util.List;

public class AdventureGame {

    public static void main(String[] args) throws RoomMisplacedException {

        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("0-0", "", 0,0 ));
        roomList.add(new Room("0-1", "", 0,1 ));
        roomList.add(new Room("1-0", "", 1,0 ));
        roomList.add(new Room("1-1", "", 1,1 ));
        roomList.add(new Room("2-2", "", 2,2 ));

        MapEngine mapEngine = new MapEngine(roomList, 3,3);

        mapEngine.drawMap();
    }

    public void manageDamage(Character attackingCharacter, Character defendingCharacter) throws LoseGameException {
        int firstDamage = attackingCharacter.getPrimaryWeapon() != null ? attackingCharacter.getPrimaryWeapon().getDamage() : 0;
        int secondDamage = attackingCharacter.getSecondaryWeapon() != null ? attackingCharacter.getPrimaryWeapon().getDamage() : 0;
        int damageTaken = defendingCharacter.getArmor().absorbDamage(firstDamage + secondDamage);
        if (damageTaken > 0)
        {
            defendingCharacter.absorbDamage(damageTaken);
            if (defendingCharacter.isDestroyed() && defendingCharacter.isMainCharacter())
                throw new LoseGameException("You lose the game. Your character died");
        }
    }

}
