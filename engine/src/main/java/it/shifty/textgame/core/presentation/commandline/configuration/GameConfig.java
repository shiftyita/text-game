package it.shifty.textgame.core.presentation.commandline.configuration;

import it.shifty.textgame.core.engine.GameService;
import it.shifty.textgame.core.engine.builder.GameServiceBuilder;
import it.shifty.textgame.core.engine.display.SysOutLocaleDisplay;
import it.shifty.textgame.core.events.Publisher;
import it.shifty.textgame.core.presentation.DisplayOutput;
import it.shifty.textgame.core.presentation.commandline.engine.parser.CommandParser;
import it.shifty.textgame.core.LocaleUtils;
import it.shifty.textgame.events.PublisherEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
    @DependsOn({"localeUtils"})
    public GameService game() {
        GameInitializer gameInitializer = new GameInitializer();
        GameServiceBuilder gameServiceBuilder = new GameServiceBuilder();
        gameInitializer.constructDefaultGameService(gameServiceBuilder);
        return gameServiceBuilder.getResult();
    }

    @Bean
    public Publisher publisher() {
        return new PublisherEngine();
    }

    @Bean
    public CommandParser commandParser(DisplayOutput displayOutput, GameService gameService) {
        return new CommandParser(displayOutput, gameService);
    }


}
