package model;

/**
 * The enumerator class Day has the task of model the semester of the year.
 * @author Martina Magnani
 *
 */
public enum Semester {
  
  /**
   * First semester.
   */
  FIRST_SEMESTER("1° Semester"),
  
  /**
   * Second semester.
   */
  SECOND_SEMESTER("2° Semester");
    
  private final String name;
  
  private Semester(final String str) {
    name = str;
  }
    
  /**
   * Method that return the name of the Semester
   * @return 
   *          string containing the name of the semester.
   */
  public String getName() {
    return name;
  }
}
