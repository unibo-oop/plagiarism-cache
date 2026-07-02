package controller;

/**
 * Link the model part of code to view one.
 * 
 * @author Aurora
 *
 */
public interface Controller {
  /**
   * Methods that include all restart (enemies, main character, score).
   */
  public void start();

  /**
   * Methods that include all reset (enemies, main character, score).
   */
  public void gameOver();


}
