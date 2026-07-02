package model;

import model.interfaces.ISchedulesModel;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Automatic test.
 * @author Martina Magnani
 *
 */
public class JUnitTest {
  private ISchedulesModel dominio = new SchedulesModel();
  
  @Test
  public void test() {
    
    assertEquals(this.dominio.getProfessorsList().isEmpty(), true);
    assertEquals(this.dominio.getTeachingsList().isEmpty(), true);
    assertEquals(this.dominio.getLessons(null, null, null, null, 
                                         null, null, null, null).isEmpty(), true);
    
    
    this.dominio.addProfessor("Mirco Viroli");
    this.dominio.addProfessor("Giulio Rossi");
    this.dominio.addProfessor("Marco Bianchi");
    
    assertEquals(this.dominio.getProfessorsList().size(), 3);
    assertEquals(this.dominio.getProfessorsList().get(1).toString(), "Giulio Rossi");
    
    System.out.println(this.dominio.getProfessorsList().get(0).toString());
    assertEquals(this.dominio.getProfessorsList().get(0).toString(), "Mirco Viroli");
  }
}
