package it.shifty.game.configuration;

import it.shifty.game.engine.Game;
import it.shifty.game.engine.display.DisplayOutput;
import it.shifty.game.engine.display.SysOutLocaleDisplay;
import it.shifty.game.engine.exception.RoomMisplacedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class GameConfig {

    @Bean
    public DisplayOutput displayOutput() {
        return new SysOutLocaleDisplay(Locale.ITALIAN);
    }

    @Bean
    public Game game(DisplayOutput displayOutput) {
        return new Game(displayOutput);
    }

}
