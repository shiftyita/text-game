package it.shifty.textgame.engine.combat;

import it.shifty.textgame.engine.events.PublisherEngine;
import it.shifty.textgame.engine.exception.LoseGameException;
import it.shifty.textgame.engine.gameobjects.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombatEngine extends PublisherEngine {

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
            if (!defendingCharacter.isMainCharacter()) {
                gameEventNotification("game.combat.damage.enemy.suffer");
            }
            else {
                gameEventNotification("game.combat.character.take.damage");
            }

            defendingCharacter.absorbDamage(damageTaken);

            if (defendingCharacter.isMainCharacter()) {
                if (defendingCharacter.isDestroyed()) {
                    throw new LoseGameException("You lose the game. Your character died");
                }
            }
            else {
                if (defendingCharacter.isDestroyed()) {
                    gameEventNotification("game.combat.enemy.died", defendingCharacter.getName());
                }
            }
        }
        else {
            gameEventNotification("game.combat.damage.enemy.absorbed");
        }
        return damageTaken;
    }

    public void performAction(CombactActions actions) throws LoseGameException {
        int attackBonus = actions.attackBonus;
        int defenseBonus = actions.defenseBonus;
        int damageTaken;

        switch (actions) {
            case AGGRESSIVE_ATTACK, DEFAULT_ATTACK, PARRY_AND_FIGHT -> {
                damageTaken = manageDamage(mainCharacter, enemy, attackBonus, defenseBonus);
                gameStatsNotification("game.combat.enemy.damage", damageTaken);
            }
        }
    }

}
