package model;

/**
 * The enumerator class "Hour" has the task of model college schedules.
 * @author Martina Magnani
 **/
public enum Hour {
  
  NOVE("09/10"),
  DIECI("10/11"),
  UNDICI("11/12"),
  DODICI("12/13"),
  //TREDICI("13/14"), pausa pranzo
  QUATTORDICI("14/15"),
  QUINDICI("15/16"),
  SEDICI("16/17"),
  DICIASSETE("17/18");
  
  private final String hour;
  
  /**
   * Constructor of the enum Hour.
   * @param hour
   *          the hour of the lesson
   */
  private Hour(final String hour) {
    this.hour = hour;
  }
  
  /**
   * Method that return the required hour. 
   * @return
   *         required hour 
   */
  public String getHour() {
    return this.hour;
  }
}
