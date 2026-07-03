package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.entity.Player;

public interface Game {
    public void setCurrentLevel(Level level);
    public Level getCurrentLevel();
    public Player getCurrentPlayer();
    public long getScore();
    private void start() {}
    private void end() {}
}
