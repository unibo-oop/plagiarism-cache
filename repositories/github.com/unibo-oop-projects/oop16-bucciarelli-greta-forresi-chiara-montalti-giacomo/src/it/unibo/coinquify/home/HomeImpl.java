package it.unibo.coinquify.home;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.coinquify.file.RoomMatesFile;
import it.unibo.coinquify.mansionsmng.view.MansionManagerGUI;
import it.unibo.coinquify.roommates.model.RoomMate;

/**
 * Home implementation.
 */
public class HomeImpl implements Home {

    private final Set<RoomMate> roomMates;
    private MansionManagerGUI mansionManagerGUI;

    /**
     * create new home.
     * 
     * @throws IOException
     *             if there are errors in reading
     * @throws ClassNotFoundException
     *             if the class isn't found
     * @throws FileNotFoundException
     *             if the file isn't found
     */
    public HomeImpl() throws FileNotFoundException, ClassNotFoundException, IOException {
        this.roomMates = new HashSet<>();
        if (!RoomMatesFile.readRoomMates().isEmpty()) {
            this.roomMates.addAll(RoomMatesFile.readRoomMates());
        }
    }

    @Override
    public Set<RoomMate> getRoomMates() {
        return Collections.unmodifiableSet(this.roomMates);
    }

    @Override
    public boolean deleteRoomMate(final RoomMate roomMate) {
        if (!roomMate.getMansions().isEmpty()) {
            this.mansionManagerGUI.deleteMansionOfRoomMates(roomMate);
        }
        return this.roomMates.remove(roomMate);
    }

    @Override
    public void addRoomMate(final RoomMate roomMate) {
        this.roomMates.add(roomMate);
    }

    @Override
    public void setMansionManagerGUI(final MansionManagerGUI mansionManager) {
        this.mansionManagerGUI = mansionManager;
    }

    @Override
    public void updateRoomMate(final RoomMate roomMate, final RoomMate newRoomMate) {
        // change with setters roomMate fields
        roomMate.setName(newRoomMate.getName());
        roomMate.setSurname(newRoomMate.getSurname());
        roomMate.setBirthDay(newRoomMate.getBirthDay());
        roomMate.setFiscalCode(newRoomMate.getFiscalCode());
        roomMate.setRoom(newRoomMate.getRoom());
        roomMate.setPhoneNumber(newRoomMate.getPhoneNumber());
    }

    @Override
    public void refreshMansionsBtn(final RoomMate roomMate, final RoomMate newRoomMate) {
        this.mansionManagerGUI.refreshMansionsBtn(roomMate, newRoomMate);
    }
}
