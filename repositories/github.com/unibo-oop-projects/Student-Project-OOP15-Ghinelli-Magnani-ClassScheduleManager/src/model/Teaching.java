package model;

import model.interfaces.ITeaching;

/**
 * The Teaching class shapes the object that will identify a university course. 
 * @author Martina Magnani
 */
public class Teaching implements ITeaching {
    
  private static final long serialVersionUID = -6207742327835161721L;
  private final String name;
  private final Year year;
  private final Court court;

  /**
   * It creates a Teaching with passed parameters.
   * @param name
   *            name of teaching
   * @param year
   *            year of teaching
   * @param court
   *            court of teaching
   */
  public Teaching(final String name, final Year year, final Court court) {
    if (name == null || year == null) {
      throw new IllegalArgumentException("The values can't be null!");
    }
    this.name = name;
    this.year = year;
    this.court = court;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Year getYear() {
    return this.year;
  }
  
  @Override
  public Court getCourt() {
    return this.court;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((court == null) ? 0 : court.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Teaching other = (Teaching) obj;
    if (court != other.court) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (year != other.year) {
      return false;
    }
    return true;
  }

}
