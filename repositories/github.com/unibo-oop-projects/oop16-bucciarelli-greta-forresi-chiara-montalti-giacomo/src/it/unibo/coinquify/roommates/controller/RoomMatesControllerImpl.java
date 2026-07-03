package it.unibo.coinquify.roommates.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import it.unibo.coinquify.home.Home;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.utils.Constants;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * Room mate controller implementation.
 */
public class RoomMatesControllerImpl implements RoomMatesController {

    private final Home home;
    private final List<Contact> contacts;

    /**
     * 
     * @param home
     *            instance
     * @param contacts
     *            to control, if there're contact duplicate
     */
    public RoomMatesControllerImpl(final Home home, final List<Contact> contacts) {
        this.home = home;
        this.contacts = contacts;
    }

    @Override
    public void remove(final RoomMate rm) throws OperationNotSupportedException {
        if (this.home.getRoomMates().size() <= 1) {
            throw new OperationNotSupportedException();
        }
        this.home.deleteRoomMate(rm);
    }

    @Override
    public void add(final RoomMate newRoomMate) throws IllegalArgumentException, PhoneNumberPresentException {
        try {
            this.controlFields(newRoomMate, Optional.empty());
        } catch (final PhoneNumberPresentException e) {
            finallyAdd(newRoomMate);
            throw e;
        }
        finallyAdd(newRoomMate);
    }

    /**
     * @param newRoomMate
     */
    private void finallyAdd(final RoomMate newRoomMate) {
        this.home.addRoomMate(newRoomMate);
    }

    private void controlFields(final RoomMate roomMate, final Optional<RoomMate> roomMateToUpdate)
            throws IllegalArgumentException, PhoneNumberPresentException {
        if (roomMate.getName().isEmpty() || roomMate.getSurname().isEmpty() || roomMate.getFiscalCode().isEmpty()
                || roomMate.getFiscalCode().length() != Constants.FISCALCODE_LENGTH
                || roomMate.getPhoneNumber().length() > Constants.PHONENUMBER_MAX_LENGTH
                || roomMate.getPhoneNumber().length() < Constants.PHONENUMBER_MIN_LENGTH
                || roomMate.getBirthDay().after(new Date())
                || roomMate.getPhoneNumber().equals(Constants.EMPTY_NUMBER)) {
            throw new IllegalArgumentException();
        }

        // if there's already an equals room mate
        if (this.home.getRoomMates().contains(roomMate)) {
            if (roomMateToUpdate.isPresent()) {
                if (!roomMate.equals(roomMateToUpdate.get())) {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        // control in the list of all contacts it there's a duplicate
        for (final Contact c : this.contacts) {
            if (c.getPhoneNumber().equals(roomMate.getPhoneNumber())
                    || c.getHomePhoneNumber().equals(roomMate.getPhoneNumber())
                    || c.getWorkPhoneNumber().equals(roomMate.getPhoneNumber())) {
                throw new PhoneNumberPresentException();
            }
        }

        for (final RoomMate rm : this.home.getRoomMates()) {
            if (rm.getPhoneNumber().equals(roomMate.getPhoneNumber()) && !rm.equals(roomMate)
                    && (roomMateToUpdate.isPresent() && !rm.equals(roomMateToUpdate.get()))) {
                throw new PhoneNumberPresentException();
            }
        }
    }

    @Override
    public void update(final RoomMate roomMate, final RoomMate newRoomMate)
            throws IllegalArgumentException, PhoneNumberPresentException {
        try {
            this.controlFields(newRoomMate, Optional.of(roomMate));
        } catch (final PhoneNumberPresentException e) {
            finallyUpdate(roomMate, newRoomMate);
            throw e;
        }
        finallyUpdate(roomMate, newRoomMate);
    }

    /**
     * @param roomMate
     * @param newRoomMate
     */
    private void finallyUpdate(final RoomMate roomMate, final RoomMate newRoomMate) {
        if (!roomMate.getName().equals(newRoomMate.getName())
                || !roomMate.getSurname().equals(newRoomMate.getSurname())) {
            this.home.refreshMansionsBtn(roomMate, newRoomMate);
        }
        this.home.updateRoomMate(roomMate, newRoomMate);
    }
}
