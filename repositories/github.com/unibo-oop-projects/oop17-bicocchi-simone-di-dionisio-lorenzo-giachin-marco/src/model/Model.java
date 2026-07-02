package model;

import java.util.List;
import java.util.Map;

import model.character.Stats;
import model.container.Box;

import model.container.Shop;
import model.ranking.AbstractCharacter;

/**
 * 
 * interface model.
 *
 */
public interface Model {

    /**
     * shows the inventory map.
     * 
     * @return inventory.map
     */
    Map<String, List<Box>> getItemMap();

    /**
     * 
     * @param newDifficulty 
     */
    void setDifficulty(int newDifficulty);

    /**
     * 
     * @return Difficulty 
     */
    int getDifficulty();
    /**
     * 
     */
    void ageUp();

    /**
     * 
     * @return age of character
     */
    double getAge();

    /**
     * 
     * @return name of this character
     */
    String getName();

    /**
     * @param name
     *            is the name of character.
     */
    void setCharacterName(String name);

    /**
     * 
     * @param newShop
     *            is the new shop
     */
    void setShop(Shop newShop);

    /**
     * 
     * @param category
     *            is the category for this item
     * @param item
     *            is the new mainitem.
     * @return true if the main item is not null
     */
    boolean setMainItem(String category, String item);

    /**
     * 
     * @param category
     *            is the category of mainitem
     * @return the url of this item
     */
    String getMainItem(String category);

    /**
     * 
     * @param time
     *            is the time to add at the age of character
     */
    void setAge(double time);

    /**
     * 
     * @param newFactor
     *            is the new decrementfactor
     */
    void setDecrementFactor(double newFactor);

    /**
     * initialize with list, shop and inventory.
     * 
     * @param map
     *            is the list of categories
     */
    void initializeContainers(Map<String, List<Box>> map);

    /**
     * character buy item to the shop, and put there in inventory.
     * 
     * @param item
     *            is the new item bought
     * @return true if purchase had success
     */
    boolean buy(String item);

    /**
     * used to modify all stats.
     * 
     * @param value
     *            is the value that will use to modify all stats
     * @return true if character is not death
     */
    boolean modAllStats(double value);

    /**
     * used to modify a single stat.
     * 
     * @param category
     *            is the category of the stats
     * @return true if character is not death
     */
    boolean modStat(String category);

    /**
     * 
     * @return list of all stats.
     */
    List<Stats> getListOfStats();

    /**
     */
    void addRanking();

    /**
     * 
     * @return all ranking
     */
    List<AbstractCharacter> getRanking();

    /**
     * 
     * @return shop map
     */
    Map<String, List<Box>> getShopMap();

    /**
     * 
     * @return date modified
     */
    double getDate();

    /**
     * 
     * @return current balance
     */
    int getBalance();

    /**
     * check if main item is already setted, else it will be set.
     */
    void checkAndSetMainItem();

    /**
     * 
     * @return Url for the image of this character
     */
    String getCharacterUrl();

    /**
     * 
     * @param newUrl
     *            is the new url for the image of this character
     */
    void setCharacterUrl(String newUrl);

    /**
     * @param amount
     *            is the credit to add into balance
     */
    void addMoney(int amount);

}
