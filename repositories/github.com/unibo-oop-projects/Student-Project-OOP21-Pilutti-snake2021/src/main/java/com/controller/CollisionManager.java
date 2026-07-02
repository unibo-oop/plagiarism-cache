package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.view.View;

/**
 * Interface that models a manager for the in-game collisions.
 *
 */
public interface CollisionManager {

    /**
     * Manages collisions between snake and the apple.
     * 
     * @param view  the game's view
     * @param model the game's model
     */
    void manageAppleCollision(View view, Model model);

    /**
     * Manages collisions between snake and the map bounds and between snake and its
     * own body.
     * 
     * @param view  the game's view
     * @param model the game's model
     */
    void manageWallOrBodyCollision(View view, Model model);
}
