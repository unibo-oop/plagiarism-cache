package model;

public enum Year {
  
  /**
   * Common First Year LT (degree).
   */
  PRIMO_TRIENNALE("1° year LT"),
  
  /**
   * Common Second Year LT (degree).
   */
  SECONDO_TRIENNALE("2° year LT"),
  
  /**
   * Second Year Engineering LT (degree).
   */
  SECONDO_ING_TRIENNALE("2° year LT - ENGINEERING"),
  
  /**
   * Second Year Science LT (degree).
   */
  SECONDO_SCI_TRIENNALE("2° year LT - SCIENCE"),
  
  /**
   * Common Third Year LT (degree).
   */
  TERZO_TRIENNALE("3° year LT "),
  
  /**
   * Third Year Engineering LT (degree).
   */
  TERZO_ING_TRIENNALE("3° year LT  - ENGINEERING"),
  
  /**
   * Third Year Science LT (degree).
   */
  TERZO_SCI_TRIENNALE("3° year LT - SCIENCE"),
  
  /**
   * Third Year Optional LT (degree).
   */
  TERZO_OPZ_TRIENNALE("3° year LT - OPTIONAL");
  
  private final String year;
  
  /**
   * Constructor of the enumerator class "Year".
   * @param hour
   *          the year of the lesson
   */
  private Year(final String year) {
    this.year = year;
  }
  
  /**
   * Method that return the required year.
   * @return
   *         required year 
   */
  public String getYear() {
    return this.year;
  }
}
