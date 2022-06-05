package it.shifty.game.engine.parser;

import java.util.ArrayList;
import java.util.List;

public enum Actions {

    PUT("metti"), TAKE("prendi"), DROP("lascia"), OPEN("apri"), CLOSE("chiudi"), LOOK("guarda"),
    PULL("tira"), PUSH("spingi"), INVENTORY("inventario");

    private String actionName;
    private List<String> synonyms = new ArrayList<>();
    Actions(String defaultDescription) {
        this.actionName = defaultDescription;
        synonyms.add(defaultDescription);
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }

    public Actions fromString(String description) {
        for (Actions action: values()) {
            if (action.synonyms.contains(description))
                return action;
        }
        return null;
    }

}
