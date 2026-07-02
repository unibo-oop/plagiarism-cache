package model;

import model.map.Map;
import model.player.Player;
import model.tower.TowerType;
import model.wave.Wave;
import utilityclasses.Pair;
/**
 * 
 * This interface defines the core of the game, methods to interact with the
 * game.
 * 
 */
public interface GameModel {

    /**
     * Places a Tower in the current playing map.
     * @param location x, y position of the Tower
     * @param tt Type of the tower that is going to be placed
     * @return  True if the player has enought money, false otherwise
     */
    boolean placeTower(Pair<Integer, Integer> location, TowerType tt);

    /**
     * Removes a Tower from the map.
     * @param location x, y position of the Tower
     */
    void removeTower(Pair<Integer, Integer> location);

    /**
     * 
     * @return the GameStatus
     */
    GameStatus getGameStatus();

    /**
     * 
     * @return return the Player
     */
    Player getPlayer();

    /**
     * 
     * @return return the Current Wave
     */
    Wave getCurrentWave();

    /**
     *Starts the next Wave.
     */
    void nextWave();

    /**
     *Update everything and moves the game forward by one tick.
     */
    void update();

    /**
     *  Returns the map of the game.
     * @return the current Map
     */
    Map getMap();

    /**
     *  if true, starts the current wave.
     * @param b boolean
     */
    void setReadyToSpawn(boolean b);

}
