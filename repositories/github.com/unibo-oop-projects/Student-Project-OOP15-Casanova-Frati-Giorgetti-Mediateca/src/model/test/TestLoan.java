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
public class TestLoan {

  /**
   * This method tests the loan service.
   *
   * @throws Exception
   *           in the case which there are problems with item or user.
   */
  @org.junit.Test
  public void testLoan() throws Exception {
    Model m = new ModelImpl();
    LinkedList<ItemGenre> ls = new LinkedList<ItemGenre>();
    ls.add(ItemGenre.ADVENTURE);
    ls.add(ItemGenre.CLASSICAL_CRITICISM);
    ls.add(ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT);
    try {
      m.registerUser("NNNN", "MMMM", new GregorianCalendar(1995, 06, 20), "nnmm", "nnmm",
                  "nnnnmmmm@mail.com", "3333333333", ls, ls);

      org.junit.Assert.assertEquals(m.getAllUserId().size(), 1);

      org.junit.Assert.assertEquals(m.getAllItemId(TypeItem.BOOK).size(), 0);
      System.out.println("ooo" + m.getAllUserId().toString());
      System.out.println("ooo" + m.getAllItemId(TypeItem.BOOK).toString());

      m.registerBook("BookN", 2016, "Author", Language.ITALIAN, "1234567890EDC",
                  ItemGenre.ADVENTURE, "Mondadori", 1, 4);

      m.registerBook("BookM", 2012, "Autore", Language.ITALIAN, "1234567880EDC",
                  ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Elleci", 1, 3);

      System.out.println(m.getAllUserId().size());
      System.out.println(m.getAllItemId(TypeItem.BOOK).size());
      System.out.println(m.getAllItemId(TypeItem.BOOK));

      org.junit.Assert.assertEquals(m.getAllUserId().size(), 1);
      org.junit.Assert.assertEquals(m.getAllItemId(TypeItem.BOOK).size(), 2);
      Set<Integer> s = new HashSet<Integer>(m.getAllItemId(TypeItem.BOOK));
      int num = 0;
      for (Integer i : s) {
        m.borrowItem(i, 135426987);
        num++;
        org.junit.Assert.assertEquals(m.getRequiredUser(135426987).getLoanArchive().keySet().size(),
                    num);
      }

      Set<Integer> l = new HashSet<Integer>(m.getRequiredUser(135426987).getLoanArchive().keySet());
      num = l.size();
      System.out.println("" + l.size());
      for (Integer i : l) {
        m.returnItem(i, 135426987);
        num--;
        org.junit.Assert.assertEquals(m.getRequiredUser(135426987).getNowOnLoan().size(), num);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
