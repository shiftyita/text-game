package it.shifty.textgame.core.engine.ai;

import it.shifty.textgame.core.engine.combat.CombatEngine;

public class DefaultBehaviour implements Behaviour {

    private static DefaultBehaviour defaultBehaviourInstance = null;

    public static DefaultBehaviour getInstance(){
        if (defaultBehaviourInstance == null)
            defaultBehaviourInstance = new DefaultBehaviour();
        return defaultBehaviourInstance;

    }

    @Override
    public CombatEngine.CombatActions getCombatAction() {
        return CombatEngine.CombatActions.DEFAULT_ATTACK;
    }
}
