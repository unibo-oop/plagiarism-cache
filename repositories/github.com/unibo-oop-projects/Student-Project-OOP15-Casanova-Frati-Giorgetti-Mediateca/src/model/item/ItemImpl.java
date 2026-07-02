package model.item;

import java.io.Serializable;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.base.Objects;

import utils.ItemGenre;
import utils.Language;

/**
 * Item is the common part of book and movie. It implements its interface Item
 * and Serializable in order to save data.
 *
 * @author Edoardo
 *
 */
public abstract class ItemImpl implements Item, Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 7331548557628545541L;
  private final int iD;
  private String title;
  private int releaseYear;
  private String publisher;
  private String author;
  private Language currentLanguage;
  private List<ReviewImpl> setReview = new LinkedList<ReviewImpl>();
  private Set<Integer> like;
  private ItemGenre genre;
  private float averageVote;

  /**
   * Item's constructors whit starter initialization. In the field ID there is
   * the global item's IDenti
   *
   * @param initTitle
   *          of the general item
   * @param initReleaseYear
   *          of the general item
   * @param initPublisher
   *          of the general item
   * @param initAuthor
   *          author in the case of book, director in the case of movie
   * @param initCurrentLanguage
   *          of the general item contained in the archive
   * @param initGenre
   *          of the general item
   *
   */
  public ItemImpl(final String initTitle, final int initReleaseYear, final String initPublisher,
              final String initAuthor, final Language initCurrentLanguage,
              final ItemGenre initGenre) {
    this.title = initTitle.toUpperCase();
    this.releaseYear = initReleaseYear;
    this.publisher = initPublisher.toUpperCase();
    this.author = initAuthor.toUpperCase();
    this.currentLanguage = initCurrentLanguage;
    this.like = new HashSet<Integer>();
    this.averageVote = 0;
    this.iD = this.hashCode();
    this.genre = initGenre;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    Formatter formatter = new Formatter(stringBuilder);
    String template = "%20s | %20s | %20s |  %5s";
    formatter.format(template, this.getiD(), this.getTitle(), this.getAuthor(),
                this.getReleaseYear());
    formatter.close();
    return stringBuilder.toString();
  }

  @Override
  public String getPublisher() {
    return this.publisher;
  }

  @Override
  public Language getCurrentLanguage() {
    return this.currentLanguage;
  }

  @Override
  public int getiD() {
    return this.iD;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public int getReleaseYear() {
    return this.releaseYear;
  }

  @Override
  public String getAuthor() {
    return this.author;
  }

  @Override
  public List<ReviewImpl> getSetReview() {
    return Collections.unmodifiableList(this.setReview);
  }

  @Override
  public int getLike() {
    return this.like.size();
  }

  @Override
  public Set<Integer> getLikeUser() {
    return Collections.unmodifiableSet(this.like);
  }

  @Override
  public float getAverageVote() {
    return this.averageVote;
  }

  @Override
  public ItemGenre getGenre() {
    return this.genre;
  }

  @Override
  public void setTitle(final String initTitle) {
    this.title = initTitle.toUpperCase();
  }

  @Override
  public void setReleaseYear(final int initReleaseYear) {
    this.releaseYear = initReleaseYear;
  }

  @Override
  public void setPublisher(final String initPublisher) {
    this.publisher = initPublisher;
  }

  @Override
  public void setAuthor(final String initAuthor) {
    this.author = initAuthor.toUpperCase();
  }

  @Override
  public void setCurrentLanguage(final Language initCurrentLanguage) {
    this.currentLanguage = initCurrentLanguage;
  }

  @Override
  public void setGenre(final ItemGenre initGenre) {
    this.genre = initGenre;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.author, this.releaseYear, this.title, this.publisher,
                this.currentLanguage);
  }

  @Override
  public boolean equals(final Object object) {
    if (!(object instanceof ItemImpl)) {
      return false;
    }
    final ItemImpl item = (ItemImpl) object;
    return Objects.equal(this.author, item.author)
                && Objects.equal(this.releaseYear, item.releaseYear)
                && Objects.equal(this.title, item.title)
                && Objects.equal(this.publisher, item.publisher)
                && Objects.equal(this.currentLanguage, item.currentLanguage);
  }

  @Override
  public void addReview(final ReviewImpl rev) {
    this.setReview.add(rev);
    this.setAverageVote();
  }

  @Override
  public void addLike(final Integer userId) {
    if (!this.like.contains(userId)) {
      this.like.add(userId);
    }
  }

  @Override
  public void removeLike(final Integer userId) {
    if (this.like.contains(userId)) {
      this.like.remove(userId);
    }
  }

  /**
   * This method set the item's average vote.
   */
  public void setAverageVote() {
    int div = this.setReview.size();
    int total = 0;
    for (Review r : this.setReview) {
      total = total + r.getVote();
    }
    this.averageVote = (float) total / div;
  }

}
