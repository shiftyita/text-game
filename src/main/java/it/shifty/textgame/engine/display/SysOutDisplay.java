package it.shifty.textgame.engine.display;

public class SysOutDisplay implements DisplayOutput {
    @Override
    public void printTextOutput(OutputMessage message) {
        if (message.getMessage() != null)
                System.out.println(message.getMessage());
        if (message.getMultiMessage() != null) {
            for (String text: message.getMultiMessage()) {
                System.out.println(text);
            }
        }
    }
}
