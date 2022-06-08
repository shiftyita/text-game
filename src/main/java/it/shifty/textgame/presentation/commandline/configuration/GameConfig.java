package it.shifty.textgame.presentation.commandline.configuration;

import it.shifty.textgame.engine.GameService;
import it.shifty.textgame.engine.builder.GameServiceBuilder;
import it.shifty.textgame.engine.display.SysOutLocaleDisplay;
import it.shifty.textgame.engine.locale.LocaleUtils;
import it.shifty.textgame.presentation.DisplayOutput;
import it.shifty.textgame.presentation.commandline.engine.parser.CommandParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class GameConfig {

    @Bean
    public DisplayOutput displayOutput() {
        return new SysOutLocaleDisplay();
    }

    @Bean
    public LocaleUtils localeUtils() {
        return new LocaleUtils(Locale.ITALIAN);
    }

    @Bean
    public CommandParser commandParser(DisplayOutput displayOutput) {
        return new CommandParser(displayOutput);
    }

    @Bean
    public GameService game() {
        GameInitializer gameInitializer = new GameInitializer();
        GameServiceBuilder gameServiceBuilder = new GameServiceBuilder();
        gameInitializer.constructDefaultGameService(gameServiceBuilder);
        return gameServiceBuilder.getResult();
    }

}
