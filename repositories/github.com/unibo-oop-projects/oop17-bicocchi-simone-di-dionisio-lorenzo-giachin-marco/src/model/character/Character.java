package model.character;

import java.util.List;
import java.util.Map;

import model.container.Box;

/**
 * 
 * interface character.
 *
 */
public interface Character {

    /**
     * this static method is used when, at new game, old instance must be
     * reinitializate. This method is same to singleton pattern, ad-hoc for this
     * Tamagotchi.
     */

    /**
     * 
     */
    void ageUp();

    /**
     * shows the inventory map.
     * 
     * @return the itemmap
     */
    Map<String, List<Box>> getItemMap();

    /**
     * @param category
     *            is the category of item
     * @return map of inventory
     */
    Box getMainItem(String category);

    /**
     * 
     * @param category
     *            is the category of item
     * @param item
     *            is the new main item for this category
     */
    void setMainItem(String category, Box item);

    /**
     * get the main item map of inventory.
     * 
     * @return the mainItemMap of inventory
     */
    Map<String, Box> getMainItemMap();

    /**
     * 
     * @param item
     *            is the new item for this category
     * @param category
     *            is the category
     */
    void addToInventory(Box item, String category);

    /**
     * initialize inventory.
     * 
     * @param newMap
     *            is the list of categories for inventory
     */
    void initialize(Map<String, List<Box>> newMap);

    /**
     * 
     * @return balance
     */
    int getBalance();

    /**
     * @param amount
     *            is the amount
     */
    void setBalance(int amount);

    /**
     * 
     * @param category
     *            is the category of mainitem
     */
    void setDefaultMainItem(String category);

    /**
     * 
     * @param newUrl
     *            is the new url for the image of this character
     */
    void setUrl(String newUrl);

    /**
     * 
     * @return Url for the image of this character
     */
    String getUrl();

    /**
     * 
     * @return string contained name
     */
    String getName();

    /**
     * 
     * @return decrement factor
     */
    double getFactor();

    /**
     * 
     * @param newFactor
     *            is the new decrementfactor
     */
    void setFactor(double newFactor);

    /**
     * 
     * @param name
     *            will be the new name
     */
    void setName(String name);

    /**
     * 
     * @return age
     */
    float getAge();

    /**
     * 
     * @param age
     *            is the new age
     */
    void setAge(double age);

    /**
     * 
     * @return list of stats
     */
    List<Stats> getList();

    /**
     * 
     * @param newList
     *            is the new List
     */
    void setList(List<Stats> newList);

    /**
     * 
     * @return list of stats in string
     */
    String getListToString();

    /**
     * @param amount
     *            is the credit to add into balance
     */
    void addMoney(int amount);

}
