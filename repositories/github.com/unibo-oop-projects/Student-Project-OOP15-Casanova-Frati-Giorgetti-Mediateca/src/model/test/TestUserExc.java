package model.test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import model.Model;
import model.ModelImpl;
import model.UserException;
import utils.ItemGenre;

/**
 * This is a testing class for the model.
 *
 * @author Edoardo
 *
 */
public class TestUserExc {

  /**
   * This method tests user exception in the case which you want add two times
   * the same user.
   *
   * @throws Exception
   *           in the case which user is already into the archive.
   */
  @org.junit.Test(expected = UserException.class)
  public void testUserException() throws Exception {
    Model m = new ModelImpl();
    LinkedList<ItemGenre> ls = new LinkedList<ItemGenre>();
    ls.add(ItemGenre.ADVENTURE);
    ls.add(ItemGenre.CLASSICAL_CRITICISM);
    ls.add(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT);

    m.registerUser("AAAA", "BBBB", new GregorianCalendar(1995, 06, 20), "aabb", "aabb",
                "aaaabbbb@mail.com", "3333333333", ls, ls);
    m.registerUser("AAAA", "BBBB", new GregorianCalendar(1995, 06, 20), "aabb", "aabb",
                "aaaabbbb@mail.com", "3333333333", ls, ls);
    Set<Integer> s = new HashSet<Integer>(m.getAllUserId());
    int num = s.size();
    for (Integer i : s) {
      m.deleteUser(i);
      num--;
      org.junit.Assert.assertEquals(m.getAllUserId().size(), num);
    }
  }
}
