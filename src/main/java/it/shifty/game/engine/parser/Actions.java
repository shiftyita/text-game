package it.shifty.game.engine.parser;

import java.util.ArrayList;
import java.util.List;

public enum Actions {

    PUT("metti"), TAKE("prendi"), DROP("lascia"), OPEN("apri"), CLOSE("chiudi"), LOOK("guarda"),
    PULL("tira"), PUSH("spingi"), INVENTORY("inventario"), GO_N ("nord"), GO_S("sud"), GO_E("est"), GO_W("ovest"),
    UNRECOGNIZED("comando non riconosciuto");

    private String actionName;
    private List<String> synonyms = new ArrayList<>();

    private Operations operation;

    Actions(String defaultDescription) {
        this.actionName = defaultDescription;
        synonyms.add(defaultDescription);
    }

    Actions(String defaultDescription, Operations operation) {
        this(defaultDescription);
        this.operation = operation;
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }

    public void addOperation(Operations operation) {
        this.operation = operation;
    }

    public Operations getOperation() {
        return operation;
    }

    public static Actions fromString(String description) {
        for (Actions action: values()) {
            if (action.synonyms.contains(description))
                return action;
        }
        return null;
    }

}
