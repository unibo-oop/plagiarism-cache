package model.user;

import java.io.Serializable;
import java.util.Collections;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

import model.ItemException;
import model.Pair;
import utils.ItemGenre;

/**
 * User is the Mediateca client. This class saves in addition to general
 * information also preferences info, wishlist and recommended list of item.
 *
 * @author Edoardo
 *
 */
public class UserImpl implements Serializable, User {

  private static final long serialVersionUID = 2261594876176760469L;
  private String name;
  private String surname;
  private GregorianCalendar birthdate;
  private String username;
  private String password;
  private Integer idUser;
  private String email;
  private String telephoneNumber;
  // Map<itemId, Pair<Restituito, reviewId>>
  private Map<Integer, Pair<Boolean, Optional<Integer>>> loanArchive = new HashMap<>();
  private Set<Integer> wishList = new HashSet<>();
  private List<Integer> recommendedList = new LinkedList<>();
  private List<ItemGenre> bookPreferences = new LinkedList<>();
  private List<ItemGenre> moviePreferences = new LinkedList<>();

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    Formatter formatter = new Formatter(stringBuilder);
    String template = "%15s | %15s | %15s | %30s | %10s ";
    formatter.format(template, this.getName(), this.getSurname(), this.getUsername(),
                this.getEmail(), this.getTelephoneNumber());
    formatter.close();
    return stringBuilder.toString();
  }

  /**
   * User constructor.
   *
   * @param initName
   *          User's name.
   * @param initSurname
   *          User's surname.
   * @param initBirthdate
   *          User's day of birth.
   * @param initUsername
   *          User's username.
   * @param initPassword
   *          User's password.
   * @param initEmail
   *          User's email.
   * @param initTelephoneNumber
   *          User's telephone Number.
   * @param initBookPref
   *          User's preferences.
   * @param initMoviePref
   *          User's preferences.
   */
  public UserImpl(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref) {
    this.name = initName.toUpperCase();
    this.surname = initSurname.toUpperCase();
    this.birthdate = initBirthdate;
    this.username = initUsername;
    this.password = initPassword;
    this.idUser = this.hashCode();
    this.email = initEmail.toLowerCase();
    this.telephoneNumber = initTelephoneNumber;
    this.bookPreferences = initBookPref;
    this.moviePreferences = initMoviePref;
  }

  @Override
  public Map<Integer, Pair<Boolean, Optional<Integer>>> getLoanArchive() {
    return Collections.unmodifiableMap(this.loanArchive);
  }

  /**
   * @return the wishList.
   */
  @Override
  public Set<Integer> getWishlist() {
    return Collections.unmodifiableSet(this.wishList);
  }

  @Override
  public void addToWishList(final Integer itemId) {
    this.wishList.add(itemId);
    System.out.println("UserId: " + this.idUser + " adds to its wishlist itemId: " + itemId);
  }

  @Override
  public void removeFromWishList(final Integer itemId) {
    if (this.wishList.contains(itemId)) {
      this.wishList.remove(itemId);
    }
  }

  /**
   * @return the bookPreferences
   */
  @Override
  public List<ItemGenre> getBookPreferences() {
    return Collections.unmodifiableList(this.bookPreferences);
  }

  /**
   * @param initBookPreferences
   *          the bookPreferences to set
   */
  @Override
  public void setBookPreferences(final List<ItemGenre> initBookPreferences) {
    this.bookPreferences = initBookPreferences;
  }

  /**
   * @return the moviePreferences
   */
  @Override
  public List<ItemGenre> getMoviePreferences() {
    return Collections.unmodifiableList(this.moviePreferences);
  }

  /**
   * @param initMoviePreferences
   *          the moviePreferences to set
   */
  @Override
  public void setMoviePreferences(final List<ItemGenre> initMoviePreferences) {
    this.moviePreferences = initMoviePreferences;
  }

  /**
   *
   * @return user's name.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   *
   * @return user's surname.
   */
  @Override
  public String getSurname() {
    return this.surname;
  }

  /**
   *
   * @return user's birthdate.
   */
  @Override
  public GregorianCalendar getBirthdate() {
    return this.birthdate;
  }

  /**
   *
   * @return user's username.
   */
  @Override
  public String getUsername() {
    return this.username;
  }

  /**
   *
   * @return user's password.
   */
  @Override
  public String getPassword() {
    return this.password;
  }

  /**
   *
   * @return user's id.
   */
  @Override
  public Integer getIdUser() {
    return this.idUser;
  }

  /**
   *
   * @return user's email.
   */
  @Override
  public String getEmail() {
    return this.email;
  }

  /**
   *
   * @return user's telephone number.
   */
  @Override
  public String getTelephoneNumber() {
    return this.telephoneNumber;
  }

  /**
   *
   * @return user's recommended list.
   */
  @Override
  public List<Integer> getRecommendedList() {
    return Collections.unmodifiableList(this.recommendedList);
  }

  /**
   * This method sets user's recommended list.
   *
   * @param initRecommendedList
   *          of itemId to set.
   */
  @Override
  public void setRecommendedList(final List<Integer> initRecommendedList) {
    this.recommendedList = initRecommendedList;
  }

  /**
   * @param initName
   *          the name to set.
   */
  @Override
  public void setName(final String initName) {
    this.name = initName.toUpperCase();
  }

  /**
   * @param initSurname
   *          the surname to set
   */
  @Override
  public void setSurname(final String initSurname) {
    this.surname = initSurname.toUpperCase();
  }

  /**
   * @param initBirthdate
   *          the birthdate to set
   */
  @Override
  public void setBirthdate(final GregorianCalendar initBirthdate) {
    this.birthdate = initBirthdate;
  }

  /**
   * @param initUsername
   *          the username to set
   */
  @Override
  public void setUsername(final String initUsername) {
    this.username = initUsername;
  }

  /**
   * @param initPassword
   *          the password to set
   */
  @Override
  public void setPassword(final String initPassword) {
    this.password = initPassword;
  }

  /**
   * @param initEmail
   *          the email to set
   */
  @Override
  public void setEmail(final String initEmail) {
    this.email = initEmail.toLowerCase();
  }

  /**
   * @param initTelephoneNumber
   *          the telephoneNumber to set
   */
  @Override
  public void setTelephoneNumber(final String initTelephoneNumber) {
    this.telephoneNumber = initTelephoneNumber;
  }

  @Override
  public int hashCode() {
    return com.google.common.base.Objects.hashCode(this.name, this.surname, this.birthdate,
                this.username);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof UserImpl)) {
      return false;
    }
    final UserImpl temp = (UserImpl) obj;
    return Objects.equal(this.name, temp.name) && Objects.equal(this.surname, temp.surname)
                && Objects.equal(this.birthdate, temp.birthdate)
                && Objects.equal(this.username, temp.username);
  }

  @Override
  public void addItem(final Integer itemId) {
    this.loanArchive.put(itemId, new Pair<>(false, null));
  }

  @Override
  public void removeItem(final Integer itemId) throws Exception {
    if (this.loanArchive.containsKey(itemId)) {
      this.loanArchive.put(itemId, new Pair<>(true, null));
    } else {
      throw new ItemException("ItemId" + itemId + " is not in the archive.");
    }
  }

  @Override
  public Set<Integer> getNowOnLoan() {
    Set<Integer> l = new HashSet<>();
    for (Entry<Integer, Pair<Boolean, Optional<Integer>>> i : this.loanArchive.entrySet()) {
      if (!i.getValue().getFirst()) {
        l.add(i.getKey());
      }
    }
    return Collections.unmodifiableSet(l);
  }

  @Override
  public void setItemReview(final Integer itemId, final Integer reviewId) {
    this.loanArchive.put(itemId, new Pair<Boolean, Optional<Integer>>(
                this.loanArchive.get(itemId).getFirst(), Optional.of(reviewId)));
  }

  @Override
  public boolean itWasReturned(final Integer itemId) {
    return this.loanArchive.get(itemId).getFirst();
  }

  @Override
  public Optional<Integer> getListReview(final Integer itemId) {
    return this.loanArchive.get(itemId).getSecond();
  }

}
