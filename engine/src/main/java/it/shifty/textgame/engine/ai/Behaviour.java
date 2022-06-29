package it.shifty.textgame.engine.ai;

import it.shifty.textgame.engine.combat.CombatEngine;

import java.util.List;

public abstract class Behaviour {

    protected final List<CombatEngine.CombatActions> combatActions = CombatEngine.CombatActions.getAttackCombatActions();

    public abstract CombatEngine.CombatActions getCombatAction();
}
