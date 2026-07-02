package controller;

import java.io.IOException;

import model.shop.ShopModel;

public interface ShopController {

    /**
     * Shows the shop.
     */
    void render();

    /**
     * Gets the ShopModel.
     * @return the model of the shop.
     */
    ShopModel getShopModel();

    /**
     * Gets the total coins. 
     * @return the total coins of the player. 
     */
    int getTotalCoins();

    /**
     * Increases the counter used by the ShopView to scroll the skins. 
     * @return the skins counter. 
     */
    int increaseSkinCounter();

    /**
     * Decreases the counter used by the ShopView to scroll the skins. 
     * @return the skins counter. 
     */
    int decreaseSkinCounter();

    /**
     * Saves everything on file. 
     * @throws IOException if occurs problem during the writing.
     */
    void close() throws IOException;

}
