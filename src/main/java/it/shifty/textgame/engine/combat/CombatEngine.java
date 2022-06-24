package it.shifty.textgame.engine.combat;

import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.gameobjects.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombatEngine {

    public enum CombactActions {
        AGGRESSIVE_ATTACK(3,3,-1),
        TOTAL_DEFENSE(3, -1, 3),
        DEFAULT_ATTACK(2,2,1),
        INVENTORY_LOOK(1, 0,0),
        PARRY_AND_FIGHT (2, 1,2);

        private final int actionPoint;
        private final int attackBonus;
        private final int defenseBonus;

        CombactActions(int actionPoint, int attackBonus, int defenseBonus) {
            this.actionPoint = actionPoint;
            this.attackBonus = attackBonus;
            this.defenseBonus =  defenseBonus;
        }
    }

    private final Character mainCharacter;
    private final Character enemy;

    private boolean isCombatFinished = false;

    public CombatEngine(Character mainCharacter, Character enemy) {
        this.mainCharacter = mainCharacter;
        this.enemy = enemy;
    }

    private int manageDamage(Character attackingCharacter, Character defendingCharacter, int attackBonus, int defenseBonus) throws LoseGameException {
        int firstDamage = attackingCharacter.getPrimaryWeapon() != null ? (attackingCharacter.getPrimaryWeapon().getDamage() + attackBonus) : 0;
        int secondDamage = attackingCharacter.getSecondaryWeapon() != null ? (attackingCharacter.getPrimaryWeapon().getDamage() + attackBonus) : 0;
        int damageTaken = defendingCharacter.getArmor().absorbDamage(firstDamage + secondDamage - defenseBonus);
        if (damageTaken > 0) {
            defendingCharacter.absorbDamage(damageTaken);
            if (defendingCharacter.isDestroyed() && defendingCharacter.isMainCharacter())
                throw new LoseGameException("You lose the game. Your character died");
        }
        return damageTaken;
    }

    public void performAction(CombactActions actions) {
        int attackBonus = actions.attackBonus;
        int defenseBonus = actions.defenseBonus;




    }

}
