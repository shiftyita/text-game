package it.shifty.textgame.presentation.commandline.configuration;

import it.shifty.textgame.engine.GameService;
import it.shifty.textgame.engine.display.DisplayOutput;
import it.shifty.textgame.engine.display.SysOutLocaleDisplay;
import it.shifty.textgame.presentation.commandline.engine.parser.CommandParser;
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
    public GameService game() {
        return new GameService();
    }

    @Bean
    public CommandParser commandParser(DisplayOutput displayOutput) {
        return new CommandParser(displayOutput);
    }

}
