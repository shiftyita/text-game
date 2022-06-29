package it.shifty.textgame.engine.ai;

import it.shifty.textgame.engine.combat.CombatEngine;

import java.security.SecureRandom;

public class DefaultBehaviour extends Behaviour {

    private static DefaultBehaviour defaultBehaviourInstance = null;

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
