package model.item;

import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;

/**
 * Item is the center interface of Book and Movie.
 *
 * @author Edoardo
 *
 */
public interface Item {

  /**
   *
   * @return the item's publisher.
   */
  String getPublisher();

  /**
   *
   * @return the item's language.
   */
  Language getCurrentLanguage();

  /**
   *
   * @return the item's identifier.
   */
  int getiD();

  /**
   *
   * @return the item's title.
   */
  String getTitle();

  /**
   *
   * @return the item's release year.
   */
  int getReleaseYear();

  /**
   *
   * @return the item's author.
   */
  String getAuthor();

  /**
   *
   * @return the item's set Review.
   */
  List<ReviewImpl> getSetReview();

  /**
   *
   * @return the item's num of like.
   */
  int getLike();

  /**
   *
   * @return the set of user that put like on that item.
   */
  Set<Integer> getLikeUser();

  /**
   *
   * @return the item's average vote.
   */
  float getAverageVote();

  /**
   *
   * @return the item's genre.
   */
  ItemGenre getGenre();

  /**
   * This method set the item's title.
   *
   * @param initTitle
   *          item's title.
   */
  void setTitle(final String initTitle);

  /**
   * This method set the item's release year.
   *
   * @param initReleaseYear
   *          item's release year.
   */
  void setReleaseYear(final int initReleaseYear);

  /**
   * This method set the item's publisher.
   *
   * @param initPublisher
   *          item's publisher.
   */
  void setPublisher(final String initPublisher);

  /**
   * This method set the item's author.
   *
   * @param initAuthor
   *          item's author.
   */
  void setAuthor(final String initAuthor);

  /**
   * This method set the item's language.
   *
   * @param initCurrentLanguage
   *          item's langauige.
   */
  void setCurrentLanguage(final Language initCurrentLanguage);

  /**
   * This method set the item's genre.
   *
   * @param initGenre
   *          item's genre.
   */
  void setGenre(final ItemGenre initGenre);

  /**
   * hashCode function uses the Objects.hashCode( field1, field2, .. ) taken
   * from Google Guava.
   */
  @Override
  int hashCode();

  /**
   * equals function uses Object.equal(obj1, obj2) taken from Google Guava.
   *
   * @param obj
   * @return
   */
  @Override
  boolean equals(final Object obj);

  /**
   * Add a review to the Item.
   *
   * @param rev
   *          in order to add this review to the item's set
   */
  void addReview(ReviewImpl rev);

  /**
   * Add userId from the set which contains the user that likes it.
   *
   * @param userId
   *          user's identifier that likes this item.
   */
  void addLike(final Integer userId);

  /**
   * Remove userId from the set which contains the user that likes it.
   *
   * @param userId
   *          user's identifier.
   */
  void removeLike(final Integer userId);

  /**
   *
   * @return num of release.
   */
  Optional<Integer> getNumRelease();

  /**
   *
   * @return ISBN code.
   */
  String getIsbn();

  /**
   *
   * @return movie's duration.
   */
  Integer getDuration();

  /**
   *
   * @return the enum typecolor.
   */
  TypeColor getColour();

}
