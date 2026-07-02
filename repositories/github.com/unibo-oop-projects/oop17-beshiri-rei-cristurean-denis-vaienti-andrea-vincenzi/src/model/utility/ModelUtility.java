package model.utility;

import java.util.List;

import model.animated.Animated;
import model.hitbox.HitBox;
import model.room.Room;
import model.worldevent.WorldEvent;
import utility.Command;

/**
 * Utility used to obtain information of model.
 *
 */
public final class ModelUtility {

    private static final double WORLD_HEIGHT = 600;
    private static final double WORLD_WIDTH = 1100;
    private static final double WORLD_WIDTH_PROP = 11;
    private static final double WORLD_HEIGHT_PROP = 6;
    private static final double EPSILON = 0.001;

    private static int currentRound;
    private static boolean pauseBetweenRound;
    private static Animated player;
    private static Room room;
    private static List<Command> listMovementCommand; // The list of the movement of the player.
    private static List<Command> listShotsCommand;
    private static List<Animated> listAnimatedObj;
    private static List<WorldEvent> listWorldEvent; // Communicate with Controller.

    /**
     * Private Constructor for static class.
     */
    private ModelUtility() {
    }

    /**
     * @return player.
     */
    public static Animated getPlayer() {
        return player;
    }

    /**
     * @return true if the user was playing a round, false if he wasn't.
     */
    public static boolean pauseDuringRound() {
        return pauseBetweenRound;
    }

    /**
     * @return player.
     */
    public static double getEpsilon() {
        return EPSILON;
    }

    /**
     * @return list Game Object.
     */
    public static List<Animated> getAnimatedObjects() {
        return listAnimatedObj;
    }

    /**
     * @return player hitbox.
     */
    public static HitBox getPlayerHitBox() {
        return player.getHitBox();
    }

    /**
     * @return room.
     */
    public static Room getRoom() {
        return room;
    }

    /**
     * @return List of Command for moving.
     */
    public static List<Command> getListMovementCommand() {
        return listMovementCommand;
    }

    /**
     * @return List of Command for shots.
     */
    public static List<Command> getListShotsCommand() {
        return ModelUtility.listShotsCommand;
    }

    /**
     * Method to communicate with the Controller. the controller should only take
     * this list from the model.
     * 
     * @return the WorldEvent list.
     */
    public static List<WorldEvent> getWorldEventList() {
        return listWorldEvent;
    }

    /**
     * @return current Round.
     */
    public static int getCurrentRound() {
        return currentRound;
    }

    /**
     * @param player
     *            the new player to update.
     */
    public static void updatePlayerModelUtility(final Animated player) {
        ModelUtility.player = player;
    }

    /**
     * @param list
     *            the new list.
     */
    public static void updateListWorldEvent(final List<WorldEvent> list) {
        ModelUtility.listWorldEvent = list;
    }

    /**
     * @param room
     *            update room.
     */
    public static void updateRoomModelUtility(final Room room) {
        ModelUtility.room = room;
    }

    /**
     * @param pause
     *            true if the user was playing a round, false if he wasn't.
     */
    public static void updatePauseDuringRound(final boolean pause) {
        ModelUtility.pauseBetweenRound = pause;
    }

    /**
     * @param listMovementCommand
     *            the new list.
     */
    public static void updateListMovementCommand(final List<Command> listMovementCommand) {
        ModelUtility.listMovementCommand = listMovementCommand;
    }

    /**
     * @param listShotCommand
     *            the new shot dir.
     */
    public static void updateListShotCommand(final List<Command> listShotCommand) {
        ModelUtility.listShotsCommand = listShotCommand;
    }

    /**
     * @param newCurrent
     *            new current round to update.
     */
    public static void updateCurrentRound(final int newCurrent) {
        ModelUtility.currentRound = newCurrent;
    }

    /**
     * @param list
     *            game object. the new list.
     */
    public static void updateListAnimatedObject(final List<Animated> list) {
        ModelUtility.listAnimatedObj = list;
    }

    /**
     * World height.
     * 
     * @return world height (600).
     */
    public static double getWorldHeight() {
        return WORLD_HEIGHT;
    }

    /**
     * World width.
     * 
     * @return world width (1100).
     */
    public static double getWorldWidth() {
        return WORLD_WIDTH;
    }

    /**
     * World height proportion.
     * 
     * @return world height proportion.
     */
    public static double getWorldHeightProp() {
        return WORLD_HEIGHT_PROP;
    }

    /**
     * World width proportion.
     * 
     * @return world width proportion.
     */
    public static double getWorldWidthProp() {
        return WORLD_WIDTH_PROP;
    }
}
