package model.test;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import model.Model;
import model.ModelImpl;
import utils.ItemGenre;

/**
 * This is a testing class for the model.
 *
 * @author Edoardo
 *
 */
public class TestStudyRoom {

  /**
   * This method tests the studyRoom service.
   *
   * @throws Exception
   */
  @org.junit.Test
  public void testSR() throws Exception {
    Model m = new ModelImpl();
    LinkedList<ItemGenre> ls = new LinkedList<ItemGenre>();
    ls.add(ItemGenre.ADVENTURE);
    ls.add(ItemGenre.CLASSICAL_CRITICISM);
    ls.add(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT);
    try {
      m.registerUser("NNNN", "MMMM", new GregorianCalendar(1995, 06, 20), "nnmm", "nnmm",
                  "nnnnmmmm@mail.com", "3333333333", ls, ls);
      m.registerUser("CCCC", "DDDD", new GregorianCalendar(1950, 04, 5), "ccdd", "ccdd",
                  "ccccdddd@mail.com", "3333333333", ls, ls);

      m.registerUser("EEEE", "FFFF", new GregorianCalendar(1925, 03, 04), "eeff", "eeff",
                  "eeeeffff@mail.com", "3333333333", ls, ls);

      m.registerUser("GGGG", "HHHH", new GregorianCalendar(2000, 04, 5), "gghh", "gghh",
                  "gggghhhh@mail.com", "3333333333", ls, ls);
      System.out.println(m.getAllUserId().toString());
      for (int i = 0; i < m.getStudyRoomSit(); i++) {
        m.bookSit(new GregorianCalendar(2016, 8, 20), i, 197392167);
        org.junit.Assert.assertTrue(
                    m.getAllUserSit(new GregorianCalendar(2016, 8, 20)).get(i).equals(197392167));
      }
      System.out.println(m.getAllUserSit(new GregorianCalendar(2016, 8, 20)));
      for (int i = 0; i < m.getStudyRoomSit(); i++) {
        m.cancelSit(new GregorianCalendar(2016, 8, 20), i, 197392167);
        org.junit.Assert.assertTrue(
                    m.getAllUserSit(new GregorianCalendar(2016, 8, 20)).get(i).equals(0));
      }
      System.out.println(m.getAllUserSit(new GregorianCalendar(2016, 8, 20)));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
