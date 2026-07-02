package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.item.Archive;
import model.item.ArchiveImpl;
import model.item.ItemFactory;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.item.ReviewImpl;
import model.user.ArchiveUser;
import model.user.ArchiveUserImpl;
import model.user.UserImpl;
import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItem;
import utils.TypeItemInfo;
import utils.UserInfo;

/**
 * Main class of the model. It is Serializable and it has two main field, the
 * first to save item and its info and the second to save the user and its info.
 *
 * @author Edoardo
 *
 */
public class ModelImpl implements Serializable, Model {

  private static final long serialVersionUID = -8370710936091204583L;
  private static final int MAX_DAY = 60;
  private Archive archiveItem;
  private ArchiveUser archiveUser;
  private StudyRoom studyRoom;
  private String systemPassword = "mediateca";

  /**
   * Empty constructor.
   */
  public ModelImpl() {
    this.archiveItem = ArchiveImpl.getArchiveImpl();
    this.archiveUser = ArchiveUserImpl.getArchiveImpl();
    this.studyRoom = new StudyRoomImpl();
  }

  /**
   * Constructor that must be used in the case which there is already archive
   * files saved.
   *
   * @param initItemArchive
   *          Item archive saved in the file in order to be deserialized.
   * @param initArchiveUser
   *          User archive saved in the file in order to be deserialized.
   * @param initStudyRoom
   *          StudyRoom saved in the file in order to be deserialized.
   * @throws Exception
   *           in the case which singleton already exist.
   */
  public ModelImpl(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive,
              final Map<Integer, UserImpl> initArchiveUser,
              final Map<GregorianCalendar, ArrayList<Integer>> initStudyRoom) throws Exception {
    this.archiveItem = ArchiveImpl.getArchiveImpl();
    this.archiveUser = ArchiveUserImpl.getArchiveImpl();
    this.setItemArchive(initItemArchive);
    this.setUserArchive(initArchiveUser);
    this.studyRoom = new StudyRoomImpl(initStudyRoom);
  }

  @Override
  public Map<Integer, Pair<ItemImpl, ItemInfo>> getItemArchive() {
    return this.archiveItem.getItemArchive();
  }

  /**
   * This method set the item archive in order to be (de)serialized.
   *
   * @param initItemArchive
   *          item's archive.
   * @throws Exception
   *           in the case which the archive is been already initialized.
   */
  private void setItemArchive(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive)
              throws Exception {
    this.archiveItem.setArchiveItemImpl(initItemArchive);
  }

  @Override
  public Map<Integer, UserImpl> getUserArchive() {
    return this.archiveUser.getUserArchive();
  }

  /**
   * This method set the user archive in order to be (de)serialized.
   *
   * @param initArchiveUser
   *          the archiveUser to set
   *
   * @throws Exception
   *           in the case which the archive is been already initialized.
   */
  private void setUserArchive(final Map<Integer, UserImpl> initArchiveUser) throws Exception {
    this.archiveUser.setArchiveUserImpl(initArchiveUser);
  }

  @Override
  public void registerUser(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref)
              throws Exception, UserException {
    if (!this.checkUsername(initUsername)) {
      UserImpl u = new UserImpl(initName, initSurname, initBirthdate, initUsername, initPassword,
                  initEmail, initTelephoneNumber, initBookPref, initMoviePref);
      this.archiveUser.addUser(u);
      this.setReccomandedList(u.getIdUser());
    } else {
      throw new UserException("Can not add user " + initUsername
                  + " into the archive becouse there is already a user with that username.");
    }
  }

  /**
   *
   * @param initUsername
   *          username to find in the archive.
   * @return true if username is in the archive, false if username is not in the
   *         archive.
   */
  private boolean checkUsername(final String initUsername) {
    return this.archiveUser.getAllUsername().contains(initUsername);
  }

  @Override
  public void deleteUser(final int userId) throws Exception, UserException {
    if (this.archiveUser.contains(userId)) {
      this.archiveUser.removeUser(userId);
    } else {
      throw new UserException(
                  "User: " + userId + " not contained into the archive.Can not remove it");
    }

  }

  @Override
  public void registerBook(final String initTitle, final int initReleaseYear,
              final String initAuthor, final Language initCurrentLanguage, final String initISBN,
              final ItemGenre initGenre, final String initPublisher, final Integer initNumRelease,
              final Integer initNumCopy) throws Exception, ItemException {

    ItemImpl b = ItemFactory.getNewBook(initTitle, initReleaseYear, initAuthor, initCurrentLanguage,
                initISBN, initGenre, initPublisher, initNumRelease);
    if (!this.archiveItem.containsItem(b.getiD())) {
      this.archiveItem.addItem(b, initNumCopy);
    } else {
      this.archiveItem.changeAmount(b.getiD(), initNumCopy);
    }
  }

  @Override
  public void registerMovie(final String initTitle, final int initReleaseYear,
              final String initPublisher, final String initAuthor,
              final Language initCurrentLanguage, final ItemGenre initGenre,
              final Integer initDuration, final TypeColor initColor, final Integer initNumCopy)
              throws Exception, ItemException {
    ItemImpl m = ItemFactory.getNewMovie(initTitle, initReleaseYear, initPublisher, initAuthor,
                initCurrentLanguage, initGenre, initDuration, initColor);
    if (!this.archiveItem.containsItem(m.getiD())) {
      this.archiveItem.addItem(m, initNumCopy);
    } else {
      this.archiveItem.changeAmount(m.getiD(), initNumCopy);
    }
  }

  @Override
  public void deleteItem(final int itemId) throws Exception, ItemException {
    if (this.archiveItem.containsItem(itemId)) {
      this.archiveItem.removeItem(itemId);
    } else {
      throw new ItemException("Item: " + itemId + " is not into the archive.");
    }

  }

  @Override
  public void borrowItem(final int itemId, final int userId)
              throws Exception, ItemException, UserException {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      if (this.getRequiredUser(userId).getNowOnLoan().contains(itemId)
                  || this.archiveItem.getUserList(itemId).contains(userId)) {
        throw new UserException("UserId " + userId + " have already taken itemId " + itemId);
      }
      if (this.archiveItem.getItemInfo(itemId).isAvailable()) {
        this.archiveItem.addUser(itemId, userId);
        this.getRequiredUser(userId).addItem(itemId);
      } else {
        throw new ItemException(itemId + " not available.");
      }
    } else if (!this.archiveItem.containsItem(itemId)) {
      throw new ItemException("ItemId: " + itemId + "are not contained into the archive");
    } else if (!this.archiveUser.contains(userId)) {
      throw new UserException("UserId" + userId + "are not contained into the archive");
    }
  }

  @Override
  public void returnItem(final int itemId, final int userId) throws Exception, ItemException {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      if (!this.getRequiredUser(userId).itWasReturned(itemId)) {
        this.archiveItem.removeUser(itemId, userId);
        this.getRequiredUser(userId).removeItem(itemId);
      } else {
        throw new UserException("User " + userId + " has already return this item: " + itemId);
      }
    } else {
      throw new ItemException("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive");
    }

  }

  @Override
  public void addLike(final int itemId, final int userId)
              throws UserException, ItemException, Exception {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      this.getRequiredItem(itemId).addLike(userId);
      this.getRequiredUser(userId).addToWishList(itemId);
    } else if (!this.archiveItem.containsItem(itemId)) {
      throw new ItemException("ItemId: " + itemId + "are not contained into the archive");
    } else if (!this.archiveUser.contains(userId)) {
      throw new UserException("UserId" + userId + "are not contained into the archive");
    }
  }

  @Override
  public void removeLike(final Integer itemId, final Integer userId)
              throws Exception, UserException, ItemException {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      if (this.getRequiredUser(userId).getWishlist().contains(itemId)) {
        this.getRequiredItem(itemId).removeLike(userId);
        this.getRequiredUser(userId).removeFromWishList(itemId);
      } else {
        throw new UserException(
                    "ItemId: " + itemId + " not contained into the userId" + userId + "wishlist.");
      }
    } else if (!this.archiveItem.containsItem(itemId)) {
      throw new ItemException("ItemId: " + itemId + "are not contained into the archive");
    } else if (!this.archiveUser.contains(userId)) {
      throw new UserException("UserId" + userId + "are not contained into the archive");
    }

  }

  @Override
  public void addReview(final Integer itemId, final Integer userId, final Integer vote,
              final String note) throws Exception, ItemException, UserException {
    ReviewImpl rev = new ReviewImpl(vote, note);
    if (this.archiveUser.contains(userId) && this.archiveItem.containsItem(itemId)) {
      if (this.getRequiredUser(userId).getLoanArchive().containsKey(itemId)) {
        this.getRequiredUser(userId).setItemReview(itemId, (int) rev.getId());
        this.getRequiredItem(itemId).addReview(rev);
      } else {
        throw new UserException("ItemId: " + itemId + " not loaned to " + userId + " userId\n");
      }
    } else if (!this.archiveItem.containsItem(itemId)) {
      throw new ItemException("ItemId: " + itemId + "are not contained into the archive");
    } else if (!this.archiveUser.contains(userId)) {
      throw new UserException("UserId" + userId + "are not contained into the archive");
    }
  }

  @Override
  public List<ReviewImpl> getAllItemReview(final Integer itemId) throws Exception {
    return this.getRequiredItem(itemId).getSetReview();
  }

  @Override
  public ItemImpl getRequiredItem(final Integer itemId) throws Exception, ItemException {
    if (this.archiveItem.containsItem(itemId)) {
      return (ItemImpl) this.archiveItem.getItem(itemId);
    } else {
      throw new ItemException("ItemId: " + itemId + " not contained into the archive\n");
    }
  }

  @Override
  public UserImpl getRequiredUser(final Integer userId) throws Exception, UserException {
    if (this.archiveUser.contains(userId)) {
      return this.archiveUser.getUser(userId);
    } else {
      throw new UserException("UserId: " + userId + " not contained into the archive\n");
    }
  }

  @Override
  public Set<Integer> getAllItemId(final TypeItem t) {
    return this.archiveItem.getItemId(t);
  }

  @Override
  public Set<Integer> getAllUserId() {
    return this.archiveUser.getUserId();
  }

  @Override
  public Map<Integer, Double> checkDeadlineas(final Integer userId)
              throws Exception, UserException {
    Map<Integer, Double> mmap = new HashMap<>();
    if (this.archiveUser.contains(userId)) {
      for (Integer i : this.getRequiredUser(userId).getLoanArchive().keySet()) {
        if (!this.getRequiredUser(userId).itWasReturned(i)) {
          mmap.put(i, this.archiveItem.calculateDifferenceDays(i, userId));
        }
      }
      return mmap;
    } else {
      throw new UserException("UserId: " + userId + "is not in the archive.");
    }
  }

  @Override
  public boolean blockUser(final Integer userId) throws Exception {
    Map<Integer, Double> mmap = this.checkDeadlineas(userId);
    for (Double d : mmap.values()) {
      if (d > ModelImpl.MAX_DAY) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Set<Integer> filtersItem(final Set<Integer> set, final TypeItemInfo ts, final String param)
              throws Exception {
    if ((!ts.equals(TypeItemInfo.AUTHOR)) && (!ts.equals(TypeItemInfo.TITLE))
                && (!ts.equals(TypeItemInfo.PRODUCER)) && (!ts.equals(TypeItemInfo.RELEASE_YEAR))
                && (!ts.equals(TypeItemInfo.LANGUAGE)) && (!ts.equals(TypeItemInfo.GENRE))) {
      throw new Exception("TypeSearch " + ts + "not valid");
    }
    Set<Integer> r = new HashSet<>();
    for (Integer i : set) {
      if (ts.equals(TypeItemInfo.TITLE)) {
        if (param.equals("")) {
          return set;
        }
        if (((ItemImpl) this.archiveItem.getItem(i)).getTitle().equals(param.toUpperCase())) {
          r.add(i);
        }
      } else if (ts.equals(TypeItemInfo.AUTHOR)) {
        if (param.equals("")) {
          return set;
        }
        if (((ItemImpl) this.archiveItem.getItem(i)).getAuthor().equals(param.toUpperCase())) {
          r.add(i);
        }
      } else if (ts.equals(TypeItemInfo.PRODUCER)) {
        if (param.equals("")) {
          return set;
        }
        if (((ItemImpl) this.archiveItem.getItem(i)).getPublisher().equals(param.toUpperCase())) {
          r.add(i);
        }
      } else if (ts.equals(TypeItemInfo.RELEASE_YEAR)) {
        if (param.equals("")) {
          return set;
        }
        if (((ItemImpl) this.archiveItem.getItem(i)).getReleaseYear() == Integer.parseInt(param)) {
          r.add(i);
        }
      } else if (ts.equals(TypeItemInfo.LANGUAGE)) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getCurrentLanguage().toString()
                    .equals(param)) {
          r.add(i);
        }
      } else if (ts.equals(TypeItemInfo.GENRE)) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getGenre().toString().equals(param)) {
          r.add(i);
        }
      }

    }
    return r;
  }

  @Override
  public void changeItem(final TypeItemInfo ts, final Integer itemId, final Object param)
              throws Exception, ItemException {
    if ((!ts.equals(TypeItemInfo.AUTHOR)) && (!ts.equals(TypeItemInfo.TITLE))
                && (!ts.equals(TypeItemInfo.PRODUCER)) && (!ts.equals(TypeItemInfo.RELEASE_YEAR))
                && (!ts.equals(TypeItemInfo.LANGUAGE)) && (!ts.equals(TypeItemInfo.GENRE))) {
      throw new Exception("TypeSearch " + ts + "not valid to change on item");
    }
    if (!this.archiveItem.containsItem(itemId)) {
      throw new ItemException("ItemId" + itemId + " not contained into the archive.");
    }
    if (ts.equals(TypeItemInfo.TITLE)) {
      this.getRequiredItem(itemId).setTitle((String) param);
    } else if (ts.equals(TypeItemInfo.AUTHOR)) {
      this.getRequiredItem(itemId).setAuthor((String) param);
    } else if (ts.equals(TypeItemInfo.PRODUCER)) {
      this.getRequiredItem(itemId).setPublisher((String) param);
    } else if (ts.equals(TypeItemInfo.RELEASE_YEAR)) {
      this.getRequiredItem(itemId).setReleaseYear((int) param);
    } else if (ts.equals(TypeItemInfo.LANGUAGE)) {
      this.getRequiredItem(itemId).setCurrentLanguage((Language) param);
    } else if (ts.equals(TypeItemInfo.GENRE)) {
      this.getRequiredItem(itemId).setGenre((ItemGenre) param);
    }
  }

  @Override
  public void changeUser(final UserInfo ts, final Integer userId, final Object param)
              throws Exception, UserException {
    if ((!ts.equals(UserInfo.NAME)) && (!ts.equals(UserInfo.SURNAME))
                && (!ts.equals(UserInfo.BIRTHDATE)) && (!ts.equals(UserInfo.USERNAME))
                && (!ts.equals(UserInfo.PASSWORD)) && (!ts.equals(UserInfo.EMAIL))
                && (!ts.equals(UserInfo.TELEPHONE_NUMBER))) {
      throw new Exception("TypeSearch " + ts + "not valid to change on User");
    }
    if (!this.archiveUser.contains(userId)) {
      throw new UserException("UserId" + userId + " not contained into the archive.");
    }
    if (ts.equals(UserInfo.NAME)) {
      this.getRequiredUser(userId).setName((String) param);
    } else if (ts.equals(UserInfo.SURNAME)) {
      this.getRequiredUser(userId).setSurname((String) param);
    } else if (ts.equals(UserInfo.BIRTHDATE)) {
      this.getRequiredUser(userId).setBirthdate((GregorianCalendar) param);
    } else if (ts.equals(UserInfo.USERNAME)) {
      this.getRequiredUser(userId).setUsername((String) param);
    } else if (ts.equals(UserInfo.PASSWORD)) {
      this.getRequiredUser(userId).setPassword((String) param);
    } else if (ts.equals(UserInfo.EMAIL)) {
      this.getRequiredUser(userId).setEmail((String) param);
    } else if (ts.equals(UserInfo.TELEPHONE_NUMBER)) {
      this.getRequiredUser(userId).setTelephoneNumber((String) param);
    }
  }

  @Override
  public void setReccomandedList(final Integer userId) throws Exception, UserException {
    if (!this.archiveUser.contains(userId)) {
      throw new UserException("User " + userId + " not contained into the archive.");
    } else {
      Set<Integer> all;
      List<Integer> toAdd = new LinkedList<Integer>();
      for (ItemGenre im : this.getRequiredUser(userId).getMoviePreferences()) {
        all = this.filtersItem(this.getAllItemId(TypeItem.MOVIE), TypeItemInfo.GENRE,
                    im.toString());
        if (all.size() != 0) {
          Integer start = 0;
          Integer best = 0;
          for (Integer v : all) {
            if (this.getRequiredItem(v).getAverageVote() >= start) {
              start = (int) this.getRequiredItem(v).getAverageVote();
              best = v;
            }
          }
          toAdd.add(best);
        }
      }
      for (ItemGenre ig : this.getRequiredUser(userId).getBookPreferences()) {
        all = this.filtersItem(this.getAllItemId(TypeItem.BOOK), TypeItemInfo.GENRE, ig.toString());
        if (all.size() != 0) {
          Integer start = 0;
          Integer best = 0;
          for (Integer v : all) {
            if (this.getRequiredItem(v).getAverageVote() >= start) {
              start = (int) (this.getRequiredItem(v)).getAverageVote();
              best = v;
            }
          }
          toAdd.add(best);
        }
      }
      this.getRequiredUser(userId).setRecommendedList(toAdd);
    }
  }

  @Override
  public void refreshRecommendedList() throws UserException, Exception {
    for (Integer i : this.getAllUserId()) {
      this.setReccomandedList(i);
    }
  }

  @Override
  public void bookSit(final GregorianCalendar initDay, final Integer initSit,
              final Integer initUserId) throws UserException, Exception {
    if (this.archiveUser.contains(initUserId)) {
      this.studyRoom.takeSit(initDay, initSit, initUserId);
    } else {
      throw new UserException("UserId: " + initUserId + " not in the archive.");
    }
  }

  @Override
  public void cancelSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception {
    this.studyRoom.cancelSit(day, sit, userId);
  }

  @Override
  public List<Integer> getAllUserSit(final GregorianCalendar day) {
    return this.studyRoom.getAllSit(day);
  }

  @Override
  public String getSystemPassword() {
    String s = this.systemPassword;
    return s;
  }

  @Override
  public void setSystemPassword(final String initSystemPassword) {
    this.systemPassword = initSystemPassword;
  }

  @Override
  public Map<GregorianCalendar, ArrayList<Integer>> getStudyRoom() {
    return this.studyRoom.getStudyRoom();
  }

  @Override
  public Set<Integer> getItemBorrowed(final Integer userId) throws Exception {
    return Collections.unmodifiableSet(this.getRequiredUser(userId).getLoanArchive().keySet());
  }

  @Override
  public int getStudyRoomSit() {
    return this.studyRoom.getNumSit();
  }
}
