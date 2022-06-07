package it.shifty.game.engine.parser;

import it.shifty.game.engine.display.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static it.shifty.game.engine.parser.Actions.*;
import static it.shifty.game.engine.parser.Actions.LOOK;

public class CommandParser {

    static HashMap<String, Words> vocab = new HashMap<>();

    @Autowired
    private Environment env;

    public CommandParser() {
        initializeEnums();
    }

    private void initializeEnums() {
        GO_E.addOperation(Operations.NONE);
        GO_N.addOperation(Operations.NONE);
        GO_S.addOperation(Operations.NONE);
        GO_W.addOperation(Operations.NONE);
        INVENTORY.addOperation(Operations.NONE);
        LOOK.addOperation(Operations.NONE);
    }


    public static List<String> wordList(String lowerCaseString) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = lowerCaseString.split(delims);
        Collections.addAll(strlist, words);
        return strlist;
    }

    public static String parseCommand(List<String> wordList) {
        return "";
    }

    public void addObjectName(String name) {
        vocab.put(name, Words.NOUN);
    }
}
