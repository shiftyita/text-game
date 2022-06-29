package it.shifty.textgame.engine.ai;

import it.shifty.textgame.engine.combat.CombatEngine;

import java.security.SecureRandom;
import java.util.List;

public class DefaultBehaviour implements Behaviour {

    private static DefaultBehaviour defaultBehaviourInstance = null;

    private final List<CombatEngine.CombatActions> combatActions = CombatEngine.CombatActions.getAttackCombatActions();

    public static DefaultBehaviour getInstance() {
        if (defaultBehaviourInstance == null)
            defaultBehaviourInstance = new DefaultBehaviour();
        return defaultBehaviourInstance;

    }

    @Override
    public CombatEngine.CombatActions getCombatAction() {
        SecureRandom random = new SecureRandom();
        return combatActions.get(random.nextInt(combatActions.size()));
    }
}
