package view;

import java.util.List;
import model.player.PlayerInfo;
import utilities.enumerations.IconType;

/**
 * View Interface.
 */
public interface View {

    /**
     * Show a notification on screen.
     * 
     * @param str
     *            string of what have to show.
     * @param icon
     *            icon to add in the window.
     */
    void drawNotification(String str, IconType icon);

    /**
     * Show on screen the list of rooms to choose where you want to move.
     * 
     * @param d
     *            dice result.
     */
    void showRooms(int d);

    /**
     * Change position of a player if he decided to move.
     * 
     * @param p
     *            player to update the position.
     */
    void updatePlayerPosition(PlayerInfo p);

    /**
     * Method that Launch the view application.
     * 
     * @param args
     *            default parameters.
     */
    void init(String... args);

    /**
     * Switch the scene to menu or to the settings.
     * 
     * @param newScene
     *            the new scene to show.
     */
    void switchScene(String newScene);

    /**
     * Switch the scene to board scene.
     * 
     * @param list
     *            list of players
     */
    void switchBoardScene(List<PlayerInfo> list);

    /**
     * Update character image, notes, clues and history in the view for the
     * player who's turn is it.
     * 
     * @param pAttual
     *            the player which is the turn to update.
     */
    void updateNextPlayerView(PlayerInfo pAttual);

    /**
     * Update history with an event during a turn.
     * 
     * @param s
     *            string to add.
     */
    void updateHistoryCurrentPlayer(String s);

    /**
     * Remove pawn of the current player.
     * 
     * @param playerInfo
     *            current player to remove his pawn.
     */
    void removePawnPlayer(PlayerInfo playerInfo);
}