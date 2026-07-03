package view;

public interface ViewInterface {
    /**
     * It starts the application and shows the main menu.
     */
    void startView();

    /**
     * getter for actual gameScreen
     * 
     * @return The GameScreen
     */
    public GameScreenImpl getGameScreen();

}