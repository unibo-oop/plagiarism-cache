package model.test;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import model.Model;
import model.ModelImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeItem;
import utils.TypeItemInfo;

/**
 * This is a testing class for the model.
 *
 * @author Edoardo
 *
 */
public class TestFilters {

  /**
   * This method tests the filter.
   *
   * @throws Exception
   *           in the case which there are problems with item.
   */
  @org.junit.Test
  public void testFilters() throws Exception {
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

      m.registerBook("BookL", 2012, "Autore", Language.ITALIAN, "1234267880EDC",
                  ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Elleci", 1, 3);

      m.registerBook("BookK", 2012, "Autore", Language.ITALIAN, "12345aa80EDC",
                  ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Elleci", 1, 3);

      m.registerBook("BookA", 2012, "Autore", Language.ITALIAN, "1234567880EDC",
                  ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT, "Elleci", 1, 3);
      m.registerBook("BookW", 2012, "Autore", Language.ITALIAN, "1234567880EDC",
                  ItemGenre.ADVENTURE, "Elleci", 1, 3);

      org.junit.Assert.assertEquals(m.filtersItem(m.getAllItemId(TypeItem.BOOK), TypeItemInfo.GENRE,
                  ItemGenre.ADVENTURE.toString()).size(), 2);

      org.junit.Assert
                  .assertEquals(
                              m.filtersItem(m.getAllItemId(TypeItem.BOOK), TypeItemInfo.GENRE,
                                          ItemGenre.ART_FILM_MUSIC_ENTERTAINMENT.toString()).size(),
                              4);

      org.junit.Assert.assertEquals(m
                  .filtersItem(m.getAllItemId(TypeItem.BOOK), TypeItemInfo.AUTHOR, "Autore").size(),
                  5);
      org.junit.Assert.assertEquals(
                  m.filtersItem(m.getAllItemId(TypeItem.BOOK), TypeItemInfo.TITLE, "BookN").size(),
                  1);
      org.junit.Assert.assertEquals(
                  m.filtersItem(m.getAllItemId(TypeItem.BOOK), TypeItemInfo.TITLE, "Book").size(),
                  0);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
