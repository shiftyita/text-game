package it.shifty.textgame.presentation;

import it.shifty.textgame.engine.GameService;

public interface MenuGameLayout {

    public void saveGame(GameService gameService);

    public void loadGame(GameService gameService);

    public void execute(GameService gameService);

    public void exit();

}
