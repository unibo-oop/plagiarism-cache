package model.interfaces;

import model.Court;
import model.Year;

/**
 * Interface that represents a teaching used by the model. 
 * @author Martina Magnani
 */
public interface ITeaching extends java.io.Serializable {
  
  /**
   * Method that returns the name of the subject. 
   * @return name
   */
  String getName();
  
  /**
   * Method that returns the Year of the subject.
   * @return year
   */
  Year getYear();

  /**
   * Method that returns the Court of the subject.
   * @return court
   */
  Court getCourt();
}
