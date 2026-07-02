package interfaces;

import board.Cell;
import enumeration.Action;

/**
 * interfaccia di GameEngine
 * 
 * @author Alessandro
 *
 */
public interface GameEngineInterface {
  
  /**
   * viene creata la Board e stampata
   * 
   * @param rows
   *    numero di righe
   * @param columns
   *    numero di colonne
   * @param bombs
   *    numero di bombe
   */
  public void newGame(int rows, int columns, int bombs);
  
  /**
   * funzione che gestisce il click su una cella
   * 
   * @param cell
   *    cella su cui agire
   * @param action
   *    azione da compiere
   */
  public void click(Cell cell, Action action);
  
  /**
   * 
   * @return l'array di celle
   */
  public Cell[][] getCells();

}
