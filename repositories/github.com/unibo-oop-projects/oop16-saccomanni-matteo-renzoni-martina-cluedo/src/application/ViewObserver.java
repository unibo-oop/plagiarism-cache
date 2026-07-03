package application;

import java.util.Map;

import utilities.enumerations.CharacterCard;
import utilities.enumerations.PlayerType;
import utilities.enumerations.RoomCard;
import utilities.enumerations.WeaponCard;

/**
 * Controller Interface.
 */
public interface ViewObserver {

    /**
     * Method to update the player position after the user selection.
     * 
     * @param selectedRoom
     *            the room where you want to go or the one you want to approach.
     */
    void chooseRoom(RoomCard selectedRoom);

    /**
     * Quit game and return to the menu scene.
     */
    void quit();

    /**
     * End the turn of a player and set the next one.
     */
    void endTurn();

    /**
     * Method which initialize the game with the settings from view.
     * 
     * @param m
     *            list of players selected from the view.
     */
    void setupNewGame(Map<CharacterCard, PlayerType> m);

    /**
     * If a player choose to give up the game, the others can continue playing.
     */
    void giveUp();

    /**
     * Save a game if it's not finished.
     */
    void save();

    /**
     * Load a game choose from view.
     * 
     * @param name
     *            the selected game.
     */
    void resumeSelectedGame(String name);

    /**
     * Method which create a dialog to show the cards of the current player.
     */
    void showCards();

    /**
     * Method which open a dialog to choose which game you want to resume.
     */
    void upload();

    /**
     * Method to control if you can do an hypothesis and open the dialog to do
     * it if possible.
     */
    void guessClick();

    /**
     * Method to control if you can do an accuse and open the dialog to do it if
     * possible.
     */
    void accuseClick();

    /**
     * Method which switch the scene to starting settings for a new game.
     */
    void newGame();

    /**
     * Method to go back to the menu scene.
     */
    void backToMenu();

    /**
     * This method check if the card given for the formal accuse are corrects
     * and show response in view.
     * 
     * @param room
     *            figure the Room where is the Character in the moment of the
     *            accuse which is supposed to be the solution.
     * @param weapon
     *            figure the Weapon which is supposed to be the solution.
     * @param character
     *            figure the Character which is supposed to be the killer.
     */
    void accuse(RoomCard room, WeaponCard weapon, CharacterCard character);

    /**
     * This method check if the card given for the hypothesis are corrects, show
     * response in view and update history for all the players..
     * 
     * @param s
     *            figure the Room where is the Character in the moment of the
     *            hypothesis.
     * @param a
     *            figure the Weapon on which you want to investigate.
     * @param p
     *            figure the Character on which you want to investigate.
     */
    void guess(RoomCard s, WeaponCard a, CharacterCard p);
}