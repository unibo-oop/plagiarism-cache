package controller;

import java.util.List;
import java.util.Map;
import model.character.Stats;
import model.container.Box;
import model.ranking.AbstractCharacter;
import view.View;
import view.View.Frames;

/**
 * Inteface Controler.
 *
 */
public interface Controller {

    /**
     *
     * @param value 
     * @return .
     */
    boolean modAllStats(double value);

    /**
     * Stop Timer.
     */
    void stopTimer();

    /**
    * 
    */
    void update();

    /**
     * this method return the date.
     * 
     * @return date in sec
     */
    double getDate();

    /**
     * this function manage the situation of death's character.
     */
    void characterIsDead();

    /**
     * this method increase the age of character.
     */
    void ageUp();

    /**
     * Set the character name.
     * 
     * @param name
     *            Character name
     * @return true if the assegnation is confirmed else false
     */
    boolean setCharacterName(String name);

    /**
     * Set the url of the tamagotchi image.
     * 
     * @param newUrl
     *            is the url of the image
     */
    void setTamagotchiUrl(String newUrl);

    /**
     * @return the url of the tamagotchi image.
     */
    String getTamagotchiUrl();

    /**
     * after buy, this function set the mainItem.
     */
    void checkAndSetMainItem();

    /**
     * check the quantity and delete from inventory and mainItem.
     */
    void checkInventory();

    /**
     * 
     * @return the shop(and his products)
     */
    Map<String, List<Box>> getShop();

    /**
     * 
     * @return Balance
     */
    int getBalance();

    // void attachView(View newView);

    /**
     * Start the controller.
     */
    void start();

    /**
     * Start Timer.
     */
    void startTimer();

    /**
     * 
     * @return the invetory
     */
    Map<String, List<Box>> getInventory();

    /**
     * 
     * @return Ranking list
     */
    List<AbstractCharacter> getRanking();

    /**
     * This function set the main item.
     * 
     * @param category
     *            Category of item
     * @param item
     *            Selected item
     */
    void setMainItem(String category, String item);

    /**
     * This function return the prefer object.
     * 
     * @param category
     *            category
     * @return String value
     */
    String getMainItem(String category);

    /**
     * 
     * @return List of stats
     */
    List<Stats> getStats();

    /**
     * Modify a single stats.
     * 
     * @param category
     *            category
     * @return true if mod stas work else false
     */
    boolean modStat(String category);

    /**
     * Used to buy some item.
     * 
     * @param item
     *            Item
     * @return true if the action is completed. else false
     */

    boolean buy(String item);

    /**
     */
    void saveFile();

    /**
     * 
     */
    void loadRanking();

    /**
     * 
     * @param frames
     *            setu the next ViewFrame
     */
    void frame(Frames frames);

    /**
     * this method add the character in the ranking.
     */
    void addRanking();

    /**
     * this method modify the age of Character at the restart game.
     * 
     * @param time
     *            is the time pass to the closure of application
     */
    void setAge(double time);

    /**
     * 
     * @return the caracr Name
     */
    String getCharacterName();

    /**
     * Set up the view in the ViewManager.
     * 
     * @param newView 
     */
    void setViewManager(View newView);

    /**
     * this method is lunched to setup the initial configuration.
     */
    void loadStartInformation();

    /**
     * Save the Character in Ranking.
     */
    void saveRanking();

    /**
     * 
     * @return viewManager
     */
    ViewManager getViewManager();

    /**
     * This method is used to load the character, if character is dead or non exist
     * call the method loadStartInfo().
     */
    void loadInfoCharacter();

    /**
     * 
     * @param amount
     *            is a number, it is used to modify the Balance.
     */
    void setCoin(int amount);

    /**
     * Update the amount of money showed in the View.
     */
    void updateMoney();

    /**
     * 
     * @param value 
     */
    void setAmount(int value);

    /**
     * 
     * @param d 
     */
    void setDecrementFactor(double d);

    /**
     * 
     * @return age
     */
    double getAge();

    /**
     * 
     */
    void updateAge();
}
