package controller;

import game.Speed;
import game.SpeedManage;
import gameEnemiesManager.EnemiesManager;
import gameEnemiesManager.Enemy;

/**
 * Implementation of Controller interface.
 * 
 * @author Aurora
 * 
 */
public class Control implements Controller {

  public final Speed score = new SpeedManage();
  public EnemiesManager enemy = new EnemiesManager();

 
  @Override
public void start() {
    score.start();


  }

  @Override
public void gameOver() {
    score.reset();
    enemy.reset();

  }

}
