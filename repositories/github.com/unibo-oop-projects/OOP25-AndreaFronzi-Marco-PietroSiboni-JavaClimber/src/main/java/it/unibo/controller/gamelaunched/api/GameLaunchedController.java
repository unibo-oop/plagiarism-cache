package it.unibo.controller.gamelaunched.api;

import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

/**
 * Rapresent a controller which give to the view the data to render.
 */
public interface GameLaunchedController {

  /**
   * Provide to the view the active skin to render.
   *
   * @return the id of the active skin
   */
  String getActiveSkin();

  /**
   * Provide to the view the {@link Alien} entity to render if present.
   *
   * @return the {@link Alien} entity
   */
  Optional<Alien> getAlien();

  /**
   * Provide to the view the {@link Coin} entities to render if present.
   *
   * @return the {@link Coin} entities
   */
  Optional<List<Coin>> getCoins();

  /**
   * Provide to the view the {@link Enemy} entities to render if present.
   *
   * @return the {@link Enemy} entities
   */
  Optional<List<Enemy>> getEnemy();

  /**
   * Provide to the view the {@link Gadget} entities to render if present.
   *
   * @return the {@link Gadget} entities
   */
  Optional<List<Gadget>> getGadgets();

  /**
   * Provide to the view the moving {@link Platform} entities to render if
   * present.
   *
   * @return the {@link Platform} entities
   */
  Optional<List<Platform>> getMovingPlatforms();

  /**
   * Provide to the view the {@link Platform} which have onTouch behavoioyr
   * entities to render if present.
   *
   * @return the {@link Platform} entities
   */
  Optional<List<Platform>> getOnTouchPlatform();

  /**
   * Provide to the view the static {@link Platform} entities to render if
   * present.
   *
   * @return the {@link Platform} entities
   */
  Optional<List<Platform>> getStaticPlatforms();

  /**
   * Run {@link LaunchedGame}.
   */
  void runGame();

  /**
   * Set the panel for the game.
   * 
   * @param panel the panel for the game
   */
  void setPanel(JPanel panel);

  /**
   * Return the current score of the player.
   * 
   * @return the current score of the player
   */
  int getCurrentScore();

  /**
   * Return the number of coins collected by the player.
   * 
   * @return the number of coins collected by the player
   */
  int getCollectedCoins();
}
