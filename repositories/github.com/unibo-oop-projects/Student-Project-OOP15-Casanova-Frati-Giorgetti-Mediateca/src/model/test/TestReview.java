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
public class TestReview {

  /**
   * This method tests the review service.
   */
  @org.junit.Test
  public void testerReview() {
    Model m = new ModelImpl();
    LinkedList<ItemGenre> ls = new LinkedList<ItemGenre>();
    ls.add(ItemGenre.ADVENTURE);
    ls.add(ItemGenre.CLASSICAL_CRITICISM);
    ls.add(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT);
    try {
      m.registerUser("NNNN", "MMMM", new GregorianCalendar(1995, 06, 20), "nnmm", "nnmm",
                  "nnnnmmmm@mail.com", "3333333333", ls, ls);
      m.registerUser("AAAA", "BBBB", new GregorianCalendar(1995, 06, 20), "aabb", "aabb",
                  "aaaabbbb@mail.com", "3333333333", ls, ls);
      m.registerBook("BookN", 2016, "Author", Language.ITALIAN, "1234567890EDC",
                  ItemGenre.ADVENTURE, "Mondadori", 1, 4);
      m.registerBook("BookM", 2012, "Autore", Language.ITALIAN, "1234567880EDC",
                  ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Elleci", 1, 3);
      Set<Integer> s = new HashSet<Integer>(m.getAllItemId(TypeItem.BOOK));
      Set<Integer> u = new HashSet<Integer>(m.getAllUserId());
      for (Integer userId : u) {
        for (Integer itemID : s) {
          m.borrowItem(itemID, userId);
          m.addReview(itemID, userId, 4, "Consigliato a tutti, trama avvincente.");
        }
      }
      for (Integer i : s) {
        org.junit.Assert.assertEquals(m.getAllItemReview(i).size(), 2);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
