package it.shifty.textgame.presentation;

import it.shifty.textgame.engine.Game;

public interface AdventureGameLayout {

    public void saveGame(Game game);

    public void loadGame(Game game);

    public void execute(Game game);

}
