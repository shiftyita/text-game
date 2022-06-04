package it.shifty.game.engine;

import it.shifty.game.engine.exception.LoseGameException;
import it.shifty.game.gameobjects.Character;

public class AdventureGame {


    public void manageDamage(Character attackingCharacter, Character defendingCharacter) throws LoseGameException {

        //first weapon attack
        int damageTaken = defendingCharacter.getArmor().absorbDamage(attackingCharacter.getPrimaryWeapon().getDamage());
        if (damageTaken > 0)
        {
            defendingCharacter.absorbDamage(damageTaken);
            if (defendingCharacter.isDestroyed() && defendingCharacter.isMainCharacter())
                throw new LoseGameException("You lose the game. Your character died");
        }
        defendingCharacter.getArmor().absorbDamage(attackingCharacter.getSecondaryWeapon().getDamage());

    }

}
