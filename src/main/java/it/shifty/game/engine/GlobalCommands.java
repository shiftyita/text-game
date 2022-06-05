package it.shifty.game.engine;

import org.springframework.core.env.Environment;

public enum GlobalCommands {
    SAVE("SALVA"), LOAD("CARICA"), EXIT ("ESCI");

    public String description;

    GlobalCommands(String description) {
        this.description = description;
    }

}
