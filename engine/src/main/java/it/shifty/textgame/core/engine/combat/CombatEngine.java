package it.shifty.textgame.core.engine.combat;

import it.shifty.textgame.core.engine.exception.LoseGameException;
import it.shifty.textgame.core.engine.gameobjects.Character;
import it.shifty.textgame.core.engine.gameobjects.Enemy;
import it.shifty.textgame.core.events.Publisher;
import it.shifty.textgame.core.presentation.commandline.engine.parser.Actions;
import it.shifty.textgame.core.dto.OutputMessage;
import it.shifty.textgame.events.PublisherEngine;
import it.shifty.textgame.events.majorevents.EnemyDiedEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class CombatEngine {

    @Autowired
    private Publisher publisher;

    public enum CombatActions {
        AGGRESSIVE_ATTACK(3,3,-1),
        TOTAL_DEFENSE(3, -1, 3),
        DEFAULT_ATTACK(2,2,1),
        INVENTORY_LOOK(1, 0,0),
        PARRY_AND_FIGHT (2, 1,2),
        EQUIP(1,0,0),
        PASS(0,0,0),
        SHOW_AVAILABLE_ACTIONS(0,0,0);

        private final int actionPoint;
        private final int attackBonus;
        private final int defenseBonus;

        CombatActions(int actionPoint, int attackBonus, int defenseBonus) {
            this.actionPoint = actionPoint;
            this.attackBonus = attackBonus;
            this.defenseBonus =  defenseBonus;
        }

        public int getActionPoint() {
            return actionPoint;
        }

        public static List<String> actionNamesWithActionPointLessOrEqualThan(int actionPoint) {
            List<String> actionNamesList = new ArrayList<>();
            for (CombatActions action: values()) {
                if (action.getActionPoint() <= actionPoint) {
                    actionNamesList.add(action.name());
                }
            }
            return actionNamesList;
        }
    }

    private Character mainCharacter;
    private Character enemy;

    private boolean isCombatFinished = false;

    public void setMainCharacter(Character mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    private int manageDamage(Character attackingCharacter, Character defendingCharacter, int attackBonus, int defenseBonus) throws LoseGameException, EnemyDiedEvent {
        int firstDamage = attackingCharacter.getPrimaryWeapon() != null ? (attackingCharacter.getPrimaryWeapon().getDamage() + attackBonus) : 0;
        int secondDamage = attackingCharacter.getSecondaryWeapon() != null ? (attackingCharacter.getPrimaryWeapon().getDamage() + attackBonus) : 0;
        int damageTaken = defendingCharacter.getArmor().absorbDamage(firstDamage + secondDamage - defenseBonus);
        if (damageTaken > 0) {
            if (!defendingCharacter.isMainCharacter()) {
                publisher.gameEventNotification("game.combat.damage.enemy.suffer");
            }
            else {
                publisher.gameEventNotification("game.combat.character.take.damage");
            }

            defendingCharacter.absorbDamage(damageTaken);

            if (defendingCharacter.isMainCharacter()) {
                if (defendingCharacter.isDestroyed()) {
                    throw new LoseGameException("You lose the game. Your character died");
                }
            }
            else {
                if (defendingCharacter.isDestroyed()) {
                    publisher.gameEventNotification("game.combat.enemy.died", defendingCharacter.getName());
                    throw new EnemyDiedEvent();
                }
            }
        }
        else {
            publisher.gameEventNotification("game.combat.damage.enemy.absorbed");
        }
        return damageTaken;
    }

    public void performAction(CombatActions actions, boolean isMainChar) throws LoseGameException, EnemyDiedEvent {
        int attackBonus = actions.attackBonus;
        int defenseBonus = actions.defenseBonus;
        int damageTaken;

        switch (actions) {
            case SHOW_AVAILABLE_ACTIONS -> {
                List<String> commands = CombatActions.actionNamesWithActionPointLessOrEqualThan(mainCharacter.getActionPointsLeft());
                publisher.gameEventNotification(new OutputMessage(Actions.fromNames(commands), true));
            }
            case AGGRESSIVE_ATTACK, DEFAULT_ATTACK, PARRY_AND_FIGHT -> {
                if (isMainChar)
                    damageTaken = manageDamage(mainCharacter, enemy, attackBonus, defenseBonus);
                else
                    damageTaken = manageDamage(enemy, mainCharacter, attackBonus, defenseBonus);
                publisher.gameStatsNotification("game.combat.enemy.damage", damageTaken);
            }
            case INVENTORY_LOOK -> {
                if (isMainChar) {
                    publisher.gameEventNotification(mainCharacter.describeInventory());
                }
            }
            case PASS -> {
                if (isMainChar) {
                    mainCharacter.resetActionPoints();
                }
            }
        }
    }

}
