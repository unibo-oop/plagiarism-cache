package model.shop;

import java.io.IOException;
import java.util.List;

public interface ShopModel {

    /**
     * If the player has enough money, update the player properties.
     * @param selectedItem the item chosen by the player.
     */
    void shopItemPayment(ShopItem selectedItem);

    /**
     * If the player has enough money. 
     * @return the result string.
     */
    String misteryBoxPayment(); 

    /**
     * Gets all of the items in the shop.
     * @return the items of the shop.
     */
    List<ShopItem> getItems();

    /**
     * Gets all of the purchased items.
     * @return the purchased items. 
     */
    List<ShopItem> getPurchasedItems(); 

    /**
     * Saves the Shop Items on file.
     * @throws IOException if occurs problem during the writing.
     */
    void saveShopItem() throws IOException;

    /**
     * Gets the number of the total coins from the statistics.
     * @return the total coins.
     */
    int getTotalCoins();

    /**
     * Checks if the player can buy a MysteryBox. 
     * @param box the MysteryBox. 
     * @param coins the total coins of the player. 
     * @return if the MysteryBox can be bought or not. 
     */
    boolean checkMystery(MysteryBox box, int coins);

    /**
     * Checks if the player can buy a ShopItem. 
     * @param selectedItem the item selected by the player.
     * @param coins the total coins of the player.
     * @return if the ShopItem can be bought or not.
     */
    boolean checkPayment(ShopItem selectedItem, int coins);

    /**
     * Writes the skin's name on file. 
     * @throws IOException if occurs problem during the writing.
     */
    void writeSkinOnFile() throws IOException;

    /**
     * Checks if the current item is selected for the next game. 
     * @param name the name of the selected item by the player.
     * @return if the selected item is selected or not. 
     */
    boolean isSelected(String name);

    /**
     * Sets the selected item for the next game. 
     * @param name the name of the selected item by the player. 
     */
    void setSelected(String name);


}
