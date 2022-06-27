package it.shifty.textgame.core.engine.ai;

import it.shifty.textgame.core.engine.combat.CombatEngine;

public interface Behaviour {

    CombatEngine.CombatActions getCombatAction();
}
