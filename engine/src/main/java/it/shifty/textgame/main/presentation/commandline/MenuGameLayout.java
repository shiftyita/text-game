package it.shifty.textgame.main.presentation.commandline;

import it.shifty.textgame.engine.GameService;

public interface MenuGameLayout {

    void saveGame(GameService gameService);

    void loadGame(GameService gameService);

    void execute(GameService gameService);

    void exit();

}
