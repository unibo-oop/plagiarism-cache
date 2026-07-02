package model.test;

import java.util.HashSet;
import java.util.Set;

import model.Model;
import model.ModelImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItem;

/**
 * This is a testing class for the model.
 *
 * @author Edoardo
 *
 */
public class TestItemCreateDestroy {

  /**
   * This method tests item creation and destroy.
   *
   * @throws Exception
   *           in the case which item is already present into the archive.
   */
  @org.junit.Test
  public void testItemCreation() throws Exception {
    Model m = new ModelImpl();

    m.registerBook("Book1", 2016, "Author", Language.ITALIAN, "1234567890EDC", ItemGenre.ADVENTURE,
                "Mondadori", 1, 4);
    org.junit.Assert.assertEquals(m.getAllItemId(TypeItem.BOOK).size(), 1);

    m.registerMovie("Movie1", 2016, "Marvel", "Lee", Language.ITALIAN, ItemGenre.ADVENTURE, 300,
                TypeColor.COLOR, 3);
    m.registerMovie("Movie2", 2013, "Marvel", "Lee", Language.ENGLISH, ItemGenre.ANIMATION, 180,
                TypeColor.BLACK_AND_WHITE, 1);
    org.junit.Assert.assertEquals(m.getAllItemId(TypeItem.MOVIE).size(), 2);

    Set<Integer> s = new HashSet<Integer>(m.getAllItemId(TypeItem.MOVIE));
    int num = s.size();
    for (Integer i : s) {
      m.deleteItem(i);
      num--;
      org.junit.Assert.assertEquals(m.getAllItemId(TypeItem.MOVIE).size(), num);
    }
  }

}
