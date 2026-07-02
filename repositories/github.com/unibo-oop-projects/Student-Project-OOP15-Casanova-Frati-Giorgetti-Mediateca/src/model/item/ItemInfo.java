package model.item;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

/**
 * This class saves the info about the ItemImpl into the archive. It has two
 * fields, the first is the total number of ItemImpl contained into the archive.
 * The second is a Map of the user that have loan ItemImpl from the archive, the
 * key is ItemImpl's identifier, the values is the date when the ItemImpl was
 * taken from the archive.
 *
 * @author Edoardo
 *
 */
public class ItemInfo implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7549869898253407439L;
  private Integer quantity;
  private Map<Integer, GregorianCalendar> userList;

  /**
   * ItemInfo Constructor that set the field quantity with the parameter passed
   * and with a empty HashMap.
   *
   * @param initQuantity
   *          quantity of ItemImpl to add into the archive.
   */
  public ItemInfo(final int initQuantity) {
    this.quantity = initQuantity;
    this.userList = new HashMap<>();
  }

  /**
   * This method get the total quantity of ItemImpl in the archive.
   *
   * @return total quantity of ItemImpl available and not into the archive.
   */
  public Integer getQuantity() {
    return this.quantity;
  }

  /**
   * This method add a required quantity to an ItemImpl.
   *
   * @param initQuantity
   *          quantity of ItemImpl to add into the archive.
   */
  public void addQuantity(final Integer initQuantity) {
    this.quantity = this.quantity + initQuantity;
  }

  /**
   * This method get the UserMap that have loan this ItemImpl.
   *
   * @return a Map with key the UserId and value the Date when the user took
   *         this ItemImpl.
   */
  public Map<Integer, GregorianCalendar> getUserList() {
    return this.userList;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.quantity, this.userList);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof ItemInfo)) {
      return false;
    }
    final ItemInfo item = (ItemInfo) obj;
    return Objects.equal(this.quantity, item.quantity)
                && Objects.equal(this.userList, item.userList);
  }

  /**
   * This method check if an ItemImpl is available or not.
   *
   * @return true if it is available, false if it is not available.
   */
  public boolean isAvailable() {
    return (this.quantity - this.userList.size()) > 0;
  }
}
