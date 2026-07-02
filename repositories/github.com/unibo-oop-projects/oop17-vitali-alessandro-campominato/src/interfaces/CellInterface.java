package interfaces;

import board.Cell;

/**
 * interfaccia di Cell
 * 
 * @author Alessandro
 *
 */
public interface CellInterface {

  /**
   * imposta le celle confinanti
   * 
   * @param neighbors
   *    indica l'array di celle confinanti
   */
  public void setNeighbors(Cell[] neighbors);
  
  /**
   * 
   * @return un array di celle confinanti
   */
  public Cell[] getNeighbors();
  
  /**
   * 
   * @return il numero di celle confinanti
   */
  public int numberOfNeighbors();
  
  /**
   * 
   * @return il numero di celle confinanti bomba
   */
  public int getNumberNeighborBombs();
  
  /**
   * 
   * @return se la cella è una bomba
   */
  public boolean isBomb();
  
  /**
   * 
   * @return se la cella è rivelata
   */
  public boolean isRevealed();
  
  /**
   * imposta la coordinata x
   * 
   * @param x
   *    la riga della cella
   */
  public void setX(int x);
  
  /**
   * imposta la coordinata y
   * 
   * @param y
   *    la colonna della cella
   */
  public void setY(int y);
  
  /**
   * 
   * @return la riga della cella
   */
  public int getX();
  
  /**
   * 
   * @return la colonna della cella
   */
  public int getY();
  
  /**
   * aggiunge la bandiera alla cella
   */
  public void addFlag();
  
  /**
   * rimuove la bandiera dalla cella
   */
  public void removeFlag();
  
  /**
   * 
   * @return se la cella ha una bandiera
   */
  public boolean isFlag();
  
}
