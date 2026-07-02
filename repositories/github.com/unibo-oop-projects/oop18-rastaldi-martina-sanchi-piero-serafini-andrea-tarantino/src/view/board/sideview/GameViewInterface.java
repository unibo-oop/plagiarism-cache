package view.board.sideview;

import utilities.Colors;

/**
 *
 * Piero Sanchi. Interface of GameView.
 *
 */
public interface GameViewInterface {

    /**
     *
     * @param avviso
     *            is a string that specifies what to write on instruction label.
     */
    void redrawLabel(String avviso);

    /**
     *
     * @param color
     *            the color with which repaint the turnPlayer label.
     */
    void repaintLabel(Colors color);

    /**
     *
     * @param text
     *            the string to set ad audio button text.
     */
    void setAudioText(String text);

    /**
     *
     * @param nSteps
     *            the number of steps to write in label AvailableSteps.
     */
    void setAvailableNStepsText(Integer nSteps);

    /**
     *
     * @param imagePath
     *            the path of the image to display on lbldice.
     */
    void setDiceImage(String imagePath);

    /**
     *
     * @param arrived
     *            the number of heroes arrived at the temple.
     * @param requested
     *            the number of arrived heroes requested to win the game.
     */
    void setHeroArrived(Integer arrived, Integer requested);

    /**
     *
     * @param state
     *            the state of the audio button.
     */
    void setState(AudioState state);

    /**
     *
     * @param player
     *            the string of the player to write on turnplayer label.
     */
    void setTurnPlayerText(String player);

    /**
     * sets everything for waiting the user to roll the dice.
     */
    void waitDice();

    /**
     * sets everything for waiting the user to move.
     */
    void waitMove();

    /**
     * sets everything for waiting the user to select an element.
     */
    void waitSelect();

}
