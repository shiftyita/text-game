package it.shifty.textgame.core.presentation.commandline;

import it.shifty.textgame.core.dto.LocalizedMessage;
import it.shifty.textgame.core.engine.GameService;
import it.shifty.textgame.core.presentation.MenuGameLayout;
import it.shifty.textgame.core.presentation.commandline.engine.parser.CommandParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.*;

@Controller
public class AdventureGameCommandLine implements CommandLineRunner, MenuGameLayout {

    private GameService gameService;

    public AdventureGameCommandLine(GameService gameService) {
        this.gameService = gameService;
    }

    private static String saveGameFilename = "game.sav";

    private static final String SAVE_COMMAND = "salva";
    private static final String LOAD_COMMAND = "carica";

    private static final String EXIT_COMMAND = "esci";

    @Autowired
    private CommandParser commandParser;

    @Override
    public void saveGame(GameService gameService) {
        try {
            FileOutputStream fos = new FileOutputStream(saveGameFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameService);
            oos.flush();
            oos.close();
            System.out.print("Game saved\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't save data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    @Override
    public void loadGame(GameService gameService) {
        try {
            FileInputStream fis = new FileInputStream(saveGameFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameService = (GameService) ois.readObject();
            ois.close();
            System.out.print("\n---Game loaded---\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't load data.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    @Override
    public void exit() {
        System.exit(1);
    }

    @Override
    public void execute(GameService gameService) {
        try {
            BufferedReader in;
            String input;
            LocalizedMessage output = new LocalizedMessage();
            in = new BufferedReader(new InputStreamReader(System.in));
            commandParser.showMessage(gameService.showIntro());
            do {
                System.out.print("> ");
                input = in.readLine().trim();
                switch (input) {
                    case SAVE_COMMAND:
                        saveGame(gameService);
                        break;
                    case LOAD_COMMAND:
                        loadGame(gameService);
                        break;
                    default:
                        output = commandParser.executeCommand(input);
                        break;
                }
                if (output.getMessage() != "") {
                    commandParser.showMessage(output);
                }
            } while (!EXIT_COMMAND.equals(input));
            exit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String[] args) {
        execute(gameService);
    }


}
