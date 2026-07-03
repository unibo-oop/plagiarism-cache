package view;

public interface GameScreen {
    /**
     * Method to update what is drawn on the screen.
     */
    public void updateScreen();

    /**
     * Getter for the drawable canvas
     * 
     * @return the canvas on which to draw
     */
    public DrawableCanvas getCanvas();

    /**
     * 
     * Getter for the Handler
     * 
     * @return handler of the game
     */
    public InputHandler getHandler();

}
