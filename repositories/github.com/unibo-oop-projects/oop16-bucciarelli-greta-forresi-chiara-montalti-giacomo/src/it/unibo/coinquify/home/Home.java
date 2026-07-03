package it.unibo.coinquify.home;

import java.util.Set;

import it.unibo.coinquify.mansionsmng.view.MansionManagerGUI;
import it.unibo.coinquify.roommates.model.RoomMate;

/**
 * Principal home inferface.
 */
public interface Home {
    /**
     * 
     * @return the list of all room mates
     */
    Set<RoomMate> getRoomMates();

    /**
     * Delete a room mate from home.
     * 
     * @param roomMate
     *            to delete
     * @return true if the cancellation has done
     */
    boolean deleteRoomMate(RoomMate roomMate);

    /**
     * Add a room mate from home.
     * 
     * @param roomMate
     *            to add
     */
    void addRoomMate(RoomMate roomMate);

    /**
     * Set home mansion manager gui.
     * 
     * @param mansionManager
     *            to set.
     */
    void setMansionManagerGUI(MansionManagerGUI mansionManager);

    /**
     * 
     * @param roomMate
     *            to update
     * @param newRoomMate
     *            new room mate
     */
    void updateRoomMate(RoomMate roomMate, RoomMate newRoomMate);

    /**
     * Refresh mansion's buttons of room mate.
     * 
     * @param roomMate
     *            specifys
     * @param newRoomMate
     *            informations
     */
    void refreshMansionsBtn(RoomMate roomMate, RoomMate newRoomMate);
}
