package model.test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import model.Model;
import model.ModelImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeItem;

/**
 * This is a testing class for the model.
 *
 * @author Edoardo
 *
 */
public class TestLike {

  /**
   * This method tests the like service.
   */
  @org.junit.Test
  public void testerLike() {
    Model m = new ModelImpl();
    LinkedList<ItemGenre> ls = new LinkedList<ItemGenre>();
    ls.add(ItemGenre.ADVENTURE);
    ls.add(ItemGenre.CLASSICAL_CRITICISM);
    ls.add(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT);
    try {
      m.registerUser("NNNN", "MMMM", new GregorianCalendar(1995, 06, 20), "nnmm", "nnmm",
                  "nnnnmmmm@mail.com", "3333333333", ls, ls);
      m.registerBook("BookN", 2016, "Author", Language.ITALIAN, "1234567890EDC",
                  ItemGenre.ADVENTURE, "Mondadori", 1, 4);
      m.registerBook("BookM", 2012, "Autore", Language.ITALIAN, "1234567880EDC",
                  ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Elleci", 1, 3);
      Set<Integer> s = new HashSet<Integer>(m.getAllItemId(TypeItem.BOOK));
      int num = 0;
      for (Integer i : s) {
        m.addLike(i, 135426987);
        num++;
        org.junit.Assert.assertEquals(m.getRequiredUser(135426987).getWishlist().size(), num);
      }
      for (Integer i : s) {
        m.removeLike(i, 135426987);
        num--;
        org.junit.Assert.assertEquals(m.getRequiredUser(135426987).getWishlist().size(), num);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
