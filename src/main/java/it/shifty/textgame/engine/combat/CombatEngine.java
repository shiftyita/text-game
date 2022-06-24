package it.shifty.textgame.engine.combat;

import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.gameobjects.Character;
import it.shifty.textgame.engine.gameobjects.ItemObject;
import it.shifty.textgame.engine.gameobjects.Weapon;

public class CombatEngine {

    public enum CombatApproach {
        AGGRESSIVE_ATTACK(3), TOTAL_DEFENSE(1), DEFAULT_ATTACK(2);

        private int actionPoint;

        CombatApproach(int actionPoint) {
            this.actionPoint = actionPoint;
        }
    }

    private void manageDamage(Character attackingCharacter, Character defendingCharacter) throws LoseGameException {
        int firstDamage = attackingCharacter.getPrimaryWeapon() != null ? attackingCharacter.getPrimaryWeapon().getDamage() : 0;
        int secondDamage = attackingCharacter.getSecondaryWeapon() != null ? attackingCharacter.getPrimaryWeapon().getDamage() : 0;
        int damageTaken = defendingCharacter.getArmor().absorbDamage(firstDamage + secondDamage);
        if (damageTaken > 0) {
            defendingCharacter.absorbDamage(damageTaken);
            if (defendingCharacter.isDestroyed() && defendingCharacter.isMainCharacter())
                throw new LoseGameException("You lose the game. Your character died");
        }
    }

    


    
}
