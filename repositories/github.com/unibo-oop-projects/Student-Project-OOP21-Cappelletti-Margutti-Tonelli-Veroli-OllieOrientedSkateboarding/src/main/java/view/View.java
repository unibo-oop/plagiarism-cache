package view;

import controller.Controller;

/**
 * 
 * The view of the application.
 *
 */
public interface View {

    /**
     * Creates and renders the StartMenuView of the application.
     * 
     */
    void startMenu();

    /**
     * Creates and renders the GameView of the application.
     * 
     */
    void game();

    /**
     * Creates and renders the GameOverView of the application.
     * 
     */
    void gameOver();

    /**
     * Creates and renders the ShopView of the application.
     * 
     */
    void shop();

    /**
     * Renders the GameView during the game loop.
     */
    void render();

    /**
     * Gets the {@link Controller} of the application.
     * @return the {@link Controller} of the application.
     */
    Controller getController();

}
