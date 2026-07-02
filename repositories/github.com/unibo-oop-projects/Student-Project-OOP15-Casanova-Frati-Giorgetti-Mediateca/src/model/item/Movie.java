package model.item;

import com.google.common.base.Optional;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;

/**
 * Movie extends ItemImpl taking his common field with Book. It forms the
 * archive with Book.
 *
 * @author Edoardo
 *
 */
public class Movie extends ItemImpl {

  private static final long serialVersionUID = 856227185802047288L;
  private final Integer duration;
  private final TypeColor color;

  @Override
  public String toString() {
    return "MOVIE: " + super.toString() + this.getColour().toString();
  }

  /**
   * Movie's constructor.
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
   * @param initGenre
   *          of the movie.
   * @param initDuration
   *          of the movie (minutes)
   * @param initColor
   *          true == color, false == b/w.
   */
  public Movie(final String initTitle, final int initReleaseYear, final String initPublisher,
              final String initAuthor, final Language initCurrentLanguage,
              final ItemGenre initGenre, final Integer initDuration, final TypeColor initColor) {
    super(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage, initGenre);
    this.duration = initDuration;
    this.color = initColor;
  }

  /**
   *
   * @return movie's duration.
   */
  @Override
  public Integer getDuration() {
    return this.duration;
  }

  /**
   *
   * @return true == color, false == b/w.
   */
  @Override
  public TypeColor getColour() {
    return this.color;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    return super.equals(o);
  }

  @Override
  public Optional<Integer> getNumRelease() {
    return Optional.absent();
  }

  @Override
  public String getIsbn() {
    return "";
  }
}
