package it.shifty.textgame.engine.ai;

import it.shifty.textgame.engine.combat.CombatEngine;

import java.security.SecureRandom;

import static it.shifty.textgame.engine.combat.CombatEngine.CombatActions.DEFAULT_ATTACK;

public class DefaultBehaviour implements Behaviour {

    private static DefaultBehaviour defaultBehaviourInstance = null;

    public static DefaultBehaviour getInstance(){
        if (defaultBehaviourInstance == null)
            defaultBehaviourInstance = new DefaultBehaviour();
        return defaultBehaviourInstance;

    }

    @Override
    public CombatEngine.CombatActions getCombatAction() {
        return DEFAULT_ATTACK;
    }
}
