package it.shifty.textgame.engine.display;

import it.shifty.textgame.presentation.DisplayOutput;

public class SysOutLocaleDisplay implements DisplayOutput {

    @Override
    public void printTextOutput(OutputMessage message) {
        if (message instanceof GameOutputMessage) { //if it's a game message, it's localized
            if (message.getMessage() != null) {
                System.out.println(message.getMessage());
            } else {
                for (String text : message.getMultiMessage()) {
                    System.out.println(text);
                }
            }
        } else
            System.out.println(message.getMessage());
    }
}
