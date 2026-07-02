package model;

import model.interfaces.IProfessor;

/**
 * The Professor class shapes the object that will identify the figure of Professor.
 * @author Martina Magnani
 */
public class Professor implements IProfessor {
  private static final long serialVersionUID = 1L;
  private final String namesurname;
  
  /**
   * It creates a Professor with passed parameters.
   * @param name
   *          name and surname of professor
   */
  public Professor(final String name) {
    if (name == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.namesurname = name;
  }
  
  @Override
  public String getName() {
    return this.namesurname;
  }

  @Override
  public String toString() {
    return this.namesurname;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((namesurname == null) ? 0 : namesurname.hashCode());
    return result;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
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
    Professor other = (Professor) obj;
    if (namesurname == null) {
      if (other.namesurname != null) {
        return false;
      }
    } else if (!namesurname.equals(other.namesurname)) {
      return false;
    }
    return true;
  }
}
