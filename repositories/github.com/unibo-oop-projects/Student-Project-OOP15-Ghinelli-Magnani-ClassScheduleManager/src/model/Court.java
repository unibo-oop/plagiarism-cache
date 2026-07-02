package model;

/**
 * The course of "Engineering and Computer Sciences" of Bologna expects 
 * in the second year of studies the choice between two addresses: 
 * Science address, or Engineering address.
 * With this enumerator class will stand the "common teachings" to both curricula, 
 * the teachings only the curriculum of engineering and those only of the curriculum of Sciences.
 * @author Martina Magnani.
 *
 */
public enum Court {
  /**
   * The teachings that are used by both the courses.
   */
  COMUNE("Corte comune ad entrambi i curricula"),
  
  /**
   * The teachings of the University of Computer Engineering in Cesena.
   */
  INGEGNERIA("Corte d'Ingegneria"),
  
  /**
   * The teachings of the University of Computer Science in Cesena.
   */
  SCIENZE("Corte di Scienze");
  private final String def;
  
  private Court(final String def) {
    this.def = def;
  }
  
  /**
   * 
   * @return
   *          definition of court.
   */
  public String getDef() {
    return this.def;
  }
  
}
