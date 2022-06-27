package it.shifty.textgame.engine.ai;

import it.shifty.textgame.engine.combat.CombatEngine;

public interface Behaviour {

    CombatEngine.CombatActions getCombatAction();
}
