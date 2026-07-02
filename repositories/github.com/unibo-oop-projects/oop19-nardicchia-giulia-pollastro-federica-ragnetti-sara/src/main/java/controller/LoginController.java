package controller;

import model.UserPlayer;

public interface LoginController {

    /**
     * If user exists in data storage, simulate login.
     * 
     * @param player
     *          the player to check
     */
    void loginUser(UserPlayer player);

    /**
     * If the player is correctly registered, set the player.
     * 
     * @param player 
     *          the player to check
     */
    void registerUser(UserPlayer player);

}
