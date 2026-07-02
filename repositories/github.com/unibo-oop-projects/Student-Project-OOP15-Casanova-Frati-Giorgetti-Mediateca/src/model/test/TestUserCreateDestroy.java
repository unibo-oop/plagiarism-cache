package model.test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Model;
import model.ModelImpl;
import utils.ItemGenre;

/**
 * This is a testing class for the model.
 *
 * @author Edoardo
 *
 */
public class TestUserCreateDestroy {
  /**
   * This method test user creation and destroy.
   *
   * @throws Exception
   *           in the case which user is already present into the archive.
   */
  @org.junit.Test
  public void testUserCreation() throws Exception {
    Model m = new ModelImpl();
    List<ItemGenre> ls = new LinkedList<ItemGenre>();
    ls.add(ItemGenre.ADVENTURE);
    ls.add(ItemGenre.CLASSICAL_CRITICISM);
    ls.add(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT);

    m.registerUser("AAAA", "BBBB", new GregorianCalendar(1995, 06, 20), "aabb", "aabb",
                "aaaabbbb@mail.com", "3333333333", ls, ls);

    org.junit.Assert.assertEquals(m.getAllUserId().size(), 1);
    m.registerUser("CCCC", "DDDD", new GregorianCalendar(1950, 04, 5), "ccdd", "ccdd",
                "ccccdddd@mail.com", "3333333333", ls, ls);
    org.junit.Assert.assertEquals(m.getAllUserId().size(), 2);
    m.registerUser("EEEE", "FFFF", new GregorianCalendar(1925, 03, 04), "eeff", "eeff",
                "eeeeffff@mail.com", "3333333333", ls, ls);
    org.junit.Assert.assertEquals(m.getAllUserId().size(), 3);
    m.registerUser("GGGG", "HHHH", new GregorianCalendar(2000, 04, 5), "gghh", "gghh",
                "gggghhhh@mail.com", "3333333333", ls, ls);
    org.junit.Assert.assertEquals(m.getAllUserId().size(), 04);
    System.out.println(m.getAllUserId().toString());
    Set<Integer> s = new HashSet<Integer>(m.getAllUserId());
    int num = s.size();
    for (Integer i : s) {
      m.deleteUser(i);
      num--;
      org.junit.Assert.assertEquals(m.getAllUserId().size(), num);
    }
  }
}
