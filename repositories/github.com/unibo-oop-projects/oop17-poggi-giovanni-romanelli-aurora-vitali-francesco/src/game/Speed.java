package game;

/**
 * @author Romanelli Aurora 
 */
public interface Speed {


  /**
   * Set score to zero and reset speed.
   */
  public void reset();

  /**
  * Initiate increasing speed and score.
  */
  public void start();

  /** 
   * @return Speed X
   */
  public  float getSpeedX();

  /**
   * @return Speed Y
   */
  
  public  float getSpeedY();
  /**
   * @return Player's score
   */
  
  public Integer getScore();

  /**
   * Speed to a specific value of 3 available.
   * 
   * @param difficulty to set speed
   */
  public  void setSpeedX(float difficulty);//Opzionale

}
