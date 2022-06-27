package it.shifty.textgame.core.engine.enums;

public enum GlobalCommands {
    SAVE("SALVA"), LOAD("CARICA"), EXIT ("ESCI");

    public String description;

    GlobalCommands(String description) {
        this.description = description;
    }

}
