package it.shifty.game;

import it.shifty.game.engine.Game;
import it.shifty.game.engine.exception.LoseGameException;
import it.shifty.game.engine.exception.RoomMisplacedException;
import it.shifty.game.engine.map.MapEngine;
import it.shifty.game.engine.map.Room;
import it.shifty.game.gameobjects.Character;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "it.shifty.game")
public class AdventureGame implements CommandLineRunner {

    static Game game;

    private static String saveGameFilename = "game.sav";

    private static Environment environment;

    private static final String SAVE_COMMAND = "salva";
    private static final String LOAD_COMMAND = "carica";

    private static final String EXIT_COMMAND = "esci";

    private static void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream(saveGameFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.print("Game saved\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't save data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    private static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream(saveGameFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.print("\n---Game loaded---\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't load data.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    @Override
    public void run(String[] args) {
        try {
            BufferedReader in;
            String input;
            String output = "";
            game = new Game();
            in = new BufferedReader(new InputStreamReader(System.in));
            game.showIntro();
            do {
                System.out.print("> ");
                input = in.readLine().trim();
                switch (input) {
                    case SAVE_COMMAND:
                        saveGame();
                        break;
                    case LOAD_COMMAND:
                        loadGame();
                        break;
                    default:
                        output = game.executeCommand(input);
                        break;
                }
                if (!output.trim().isEmpty()) {
                    game.showMessage(output);
                }
            } while (!EXIT_COMMAND.equals(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RoomMisplacedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdventureGame.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
