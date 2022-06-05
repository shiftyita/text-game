package it.shifty.game.engine.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandParser {

    static HashMap<String, Words> vocab = new HashMap<>();

    @Autowired
    private Environment env;

    public static List<String> wordList(String lowerCaseString) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = lowerCaseString.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }

    public static String parseCommand(List<String> wordList) {
        return "";
    }

    public void addObjectName(String name) {
        vocab.put(name, Words.NOUN);
    }
}
