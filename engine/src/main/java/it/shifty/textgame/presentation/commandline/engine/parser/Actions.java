package it.shifty.textgame.presentation.commandline.engine.parser;

import it.shifty.textgame.engine.exception.CommandNotRecognizedException;

import java.util.ArrayList;
import java.util.List;

public enum Actions {

    PUT("metti", Operations.NEED_SOURCE_TARGET),
    TAKE("prendi", Operations.NEED_TARGET),
    DROP("lascia", Operations.NEED_TARGET),
    OPEN("apri", Operations.NEED_TARGET),
    CLOSE("chiudi", Operations.NEED_TARGET),
    LOOK("guarda", Operations.NONE),
    PULL("tira", Operations.NEED_TARGET),
    PUSH("spingi", Operations.NEED_TARGET),
    INVENTORY("inventario", Operations.NONE),
    GO_N ("nord", Operations.NONE),
    GO_S("sud", Operations.NONE),
    GO_E("est", Operations.NONE),
    GO_W("ovest", Operations.NONE),
    COMBAT("combatti", Operations.NONE),
    UNRECOGNIZED("comando non riconosciuto", Operations.NONE),
    AGGRESSIVE_ATTACK("attacco poderoso", Operations.NONE),
    TOTAL_DEFENSE("difesa totale", Operations.NONE),
    DEFAULT_ATTACK("attacca", Operations.NONE),
    INVENTORY_LOOK("sbircia inventario", Operations.NONE),
    PARRY_AND_FIGHT("para e contrattacca", Operations.NONE),
    PASS("passa", Operations.NONE),
    SHOW_AVAILABLE_ACTIONS("comandi", Operations.NONE),
    EQUIP("equipaggia", Operations.NEED_TARGET),
    HEALTH("salute", Operations.NONE);

    private String actionName;
    private List<String> synonyms = new ArrayList<>();

    private final Operations operation;

    private final String defaultDescription;

    Actions(String defaultDescription, Operations operation) {
        this.defaultDescription = defaultDescription;
        this.operation = operation;
        this.addSynonym(defaultDescription);
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }

    public Operations getOperation() {
        return operation;
    }

    public static Actions fromString(String description) throws CommandNotRecognizedException {
        for (Actions action : values()) {
            if (action.synonyms.contains(description))
                return action;
        }
        throw new CommandNotRecognizedException();
    }

    public static List<String> fromNames(List<String> commands) {
        List<String> result = new ArrayList<>();
        for (Actions action: values()) {
            if (commands.contains(action.name())) {
                result.add(action.defaultDescription);
            }
        }
        return result;
    }

}
