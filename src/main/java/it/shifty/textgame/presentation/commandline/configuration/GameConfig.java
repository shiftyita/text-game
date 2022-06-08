package it.shifty.textgame.presentation.commandline.configuration;

import it.shifty.textgame.engine.Game;
import it.shifty.textgame.engine.display.DisplayOutput;
import it.shifty.textgame.engine.display.SysOutLocaleDisplay;
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
