package it.shifty.textgame.core.presentation;

import it.shifty.textgame.core.engine.GameService;

public interface MenuGameLayout {

    void saveGame(GameService gameService);

    void loadGame(GameService gameService);

    void execute(GameService gameService);

    void exit();

}
