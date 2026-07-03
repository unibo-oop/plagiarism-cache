package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.game.impl.LevelModel;
import it.unibo.roguekong.view.impl.GameView;

import java.util.List;

/**
 * This class represents the level manager
 */
public class LevelController {
    private final List<LevelModel> levelsList;
    private int currentLevelIndex;

    /**
     * Create a new LevelController
     * @param levels list that contains each level model
     */
    public LevelController(List<LevelModel> levels) {
        this.levelsList = levels;
        this.currentLevelIndex = 0;
    }

    public int getCurrentLevelIndex() { return this.currentLevelIndex; }

    public List<LevelModel>  getLevelsList() { return levelsList; }

    public LevelModel getCurrentLevel() { return levelsList.get(this.currentLevelIndex); }

    public boolean isThereAnotherLevel() {
        return this.currentLevelIndex < this.levelsList.size() - 1;
    }

    /**
     * Method that reset the index if the player lose or quit the game
     */
    public void reset(GameView gameView) {
        this.currentLevelIndex = 0;
        this.setUpLevel();
        this.getCurrentLevel().getPlayer().setSprite("/assets/sprites/standing-mario-right.png");
        this.getCurrentLevel().getPlayer().setTileManager(this.getCurrentLevel().getTileManager());
        gameView.loadMap(this.getCurrentLevel().getTileManager());
        gameView.clearEnemies();
    }

    /**
     * Method that set and init the next level once the previous one is completed
     */
    public void nextLevelIfIsComplete(GameView gameView, Runnable r) {
        this.getCurrentLevel().checkIfPlayerIsOnEndPoint();

        if(this.isThereAnotherLevel() && this.getCurrentLevel().isLevelComplete()){
            this.currentLevelIndex++;
            this.setUpLevel();
            this.getCurrentLevel().getPlayer().setSprite("/assets/sprites/standing-mario-right.png");
            this.getCurrentLevel().getPlayer().setTileManager(this.getCurrentLevel().getTileManager());
            r.run();
            gameView.loadMap(this.getCurrentLevel().getTileManager());
            gameView.clearEnemies();
        }
    }

    /**
     * Just for test without gameView
     */
    public void nextLevelIfIsComplete() {
        this.getCurrentLevel().checkIfPlayerIsOnEndPoint();

        if(this.isThereAnotherLevel() && this.getCurrentLevel().isLevelComplete()){
            this.currentLevelIndex++;
            this.getCurrentLevel().getPlayer().setSprite("/assets/sprites/standing-mario-right.png");
            this.setUpLevel();
        }
    }

    /**
     * Method that set up the level calling the init of the current level
     */
    public void setUpLevel() {
        LevelModel level = getCurrentLevel();
        level.init();
        level.getPlayer().setTileManager(level.getTileManager());
        level.getEnemies().forEach(e -> e.setTileManager(level.getTileManager()));
    }

    /**
     * Check if player has won the game
     */
    public boolean hasPlayerWon() {
        return (this.getCurrentLevel().isLevelComplete()) && (!this.isThereAnotherLevel());
    }
}