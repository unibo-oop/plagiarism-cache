package model.item;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;

/**
 * This class is a Item Factory, it has two methods in order to create two
 * different objects (Book and Movie).
 *
 * @author Edoardo
 *
 */
public final class ItemFactory {

  /**
   * Empty constructor.
   */
  private ItemFactory() {
  }

  /**
   * This method return a new Book(...).
   *
   * @param initTitle
   *          book's title
   * @param initReleaseYear
   *          year of the book release
   * @param initAuthor
   *          who wrote the book
   * @param initCurrentLanguage
   *          of the book
   * @param initISBN
   *          International Standard Book Number
   * @param initGenre
   *          of the book
   * @param initPublisher
   *          of the book
   * @param initNumRelease
   *          if there is more then one
   * @return new Book(...).
   */
  public static Book getNewBook(final String initTitle, final int initReleaseYear,
              final String initAuthor, final Language initCurrentLanguage, final String initISBN,
              final ItemGenre initGenre, final String initPublisher, final Integer initNumRelease) {
    return new Book(initTitle, initReleaseYear, initAuthor, initCurrentLanguage, initISBN,
                initGenre, initPublisher, initNumRelease);
  }

  /**
   * This method return a new Movie(...).
   *
   * @param initTitle
   *          of the movie.
   * @param initReleaseYear
   *          of the movie.
   * @param initPublisher
   *          of the movie.
   * @param initAuthor
   *          director of the movie.
   * @param initCurrentLanguage
   *          of the movie contained in the archive.
   *
   * @param initGenre
   *          of the movie.
   * @param initDuration
   *          of the movie (minutes)
   * @param initColour
   *          true == color, false == b/w.
   *
   * @return new Movie(...).
   */
  public static Movie getNewMovie(final String initTitle, final int initReleaseYear,
              final String initPublisher, final String initAuthor,
              final Language initCurrentLanguage, final ItemGenre initGenre,
              final Integer initDuration, final TypeColor initColour) {
    return new Movie(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage,
                initGenre, initDuration, initColour);
  }
}
