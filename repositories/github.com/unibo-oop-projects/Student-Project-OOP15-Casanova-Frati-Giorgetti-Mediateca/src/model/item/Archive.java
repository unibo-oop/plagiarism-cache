package model.item;

import java.util.Map;
import java.util.Set;

import model.Pair;
import utils.TypeItem;

/**
 * This interface contains methods to communicate with the item archives.
 *
 * @author Edoardo
 *
 */
public interface Archive {

  /**
   * This method must be called only at the start of the program, if it had
   * already file config saved. With this method you set the main field
   * itemArchive.
   *
   * @param initItemArchive
   *          saved item's archive.
   * @throws Exception
   *           in the case which itemArchive is already initialized.
   */
  void setArchiveItemImpl(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive)
              throws Exception;

  /**
   * This method @return the itemArchive.
   */
  Map<Integer, Pair<ItemImpl, ItemInfo>> getItemArchive();

  /**
   * This function adds an ItemImpl to the archive.
   *
   * @param i
   *          Book or Movie to add to the archive.
   * @param initNumCopy
   *          Book or Movie number copies
   * @throws Exception
   *           in the case which initNumCopy < 0.
   *
   */
  void addItem(final ItemImpl i, final Integer initNumCopy) throws Exception;

  /**
   * This method is used to change item's setting.
   *
   * @param itemId
   *          ItemImpl's identifier.
   * @param initAmount
   *          amount of copy to add to the archive
   * @throws Exception
   *           in the case which the item is not in the archive.
   */
  void changeAmount(final Integer itemId, final Integer initAmount) throws Exception;

  /**
   * This method return an ItemImpl identified by id.
   *
   * @param itemId
   *          ItemImpl identifier.
   *
   * @return ItemImpl with id code.
   *
   * @throws Exception
   *           in the case which the item is not in the archive.
   */
  Item getItem(final Integer itemId) throws Exception;

  /**
   * This method return the info about item's required.
   *
   * @param itemId
   *          item's identifier.
   * @return item's info.
   * @throws Exception
   *           in the case which item is not into the archive.
   *
   */
  ItemInfo getItemInfo(final Integer itemId) throws Exception;

  /**
   * This method removes a ItemImpl from the archive.
   *
   * @param id
   *          ItemImpl's identifier.
   * @throws Exception
   *           in the case which the item is not in the archive.
   */
  void removeItem(final Integer id) throws Exception;

  /**
   * This method apply dayBetweenDates to the required ItemImpl's UserImpl with
   * identifier id.
   *
   * @param itemId
   *          ItemImpl's identifier.
   * @param userId
   *          UserImpl's Identifier.
   * @return the number of days elapsed
   * @throws Exception
   *           in the case which the item is not in the archive or the user is
   *           not in the item's list.
   *
   */
  double calculateDifferenceDays(final Integer itemId, final Integer userId) throws Exception;

  /**
   * This method adds the user (userId) to the list of ItemImpl's loans.
   *
   * @param itemId
   *          ItemImpl's identifier.
   * @param userId
   *          UserImpl's identifier.
   * @throws Exception
   *           in the case which the ItemImpl is not in the archive.
   */
  void addUser(final Integer itemId, final Integer userId) throws Exception;

  /**
   * This method removes the user (userId) to the list of item's loans.
   *
   * @param itemId
   *          ItemImpl's identifier.
   * @param userId
   *          UserImpl's identifier.
   * @throws Exception
   *           in the case which the ItemImpl is not in the archive or the user
   *           is not in the item's list.
   */
  void removeUser(final Integer itemId, final Integer userId) throws Exception;

  /**
   * This method check the availability of the ItemImpl in the archive.
   *
   * @param itemId
   *          ItemImpl's identifier.
   * @return true if ItemImpl is available else return false.
   * @throws Exception
   *           in the case which the ItemImpl required is not present in the
   *           archive.
   */
  boolean checkAvailability(final Integer itemId) throws Exception;

  /**
   * This method return a list of UserID that have taken a required ItemImpl
   * from the archives.
   *
   * @param itemId
   *          ItemImpl's identifier.
   * @return the Set of users identifier that have taken a required ItemImpl
   *         from the archives.
   * @throws Exception
   *           in the case which the item required is not present in the
   *           archive.
   */
  Set<Integer> getUserList(final Integer itemId) throws Exception;

  /**
   * This method return true if itemId is a key into the map, it return false if
   * itemId is not a key into the map.
   *
   * @param itemId
   *          item's identifier.
   * @return true if item is into the map, else false.
   */
  boolean containsItem(final Integer itemId);

  /**
   * This method returns a set of integer which refer to every item contained
   * into the archive. If you pass TypeItem.BOOK it return a set of books
   * identifier. If you pass TypeItem.MOVIE it return a set of movie identifier.
   *
   * @param t
   *          type of item.
   * @return a set of item identifier.
   */
  Set<Integer> getItemId(final TypeItem t);

}
