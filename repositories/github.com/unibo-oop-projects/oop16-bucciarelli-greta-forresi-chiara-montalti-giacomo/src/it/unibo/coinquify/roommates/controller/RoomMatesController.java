package it.unibo.coinquify.roommates.controller;

import javax.naming.OperationNotSupportedException;

import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * Room mates controller interface.
 */
public interface RoomMatesController {

    /**
     * 
     * @param rm
     *            to delete
     * @throws OperationNotSupportedException
     *             can't delete last room mate
     */
    void remove(RoomMate rm) throws OperationNotSupportedException;

    /**
     * Try to add new room mate.
     * 
     * @param newRoomMate
     *            new room mate
     * @throws IllegalArgumentException
     *             if args aren't ok
     * @throws PhoneNumberPresentException
     *             if the phone number is equal to another inserted phone number
     */
    void add(RoomMate newRoomMate) throws IllegalArgumentException, PhoneNumberPresentException;

    /**
     * 
     * @param roomMate
     *            to update
     * @param newRoomMate
     *            with new fields uptated
     * @throws IllegalArgumentException
     *             if args aren't ok
     * @throws PhoneNumberPresentException
     *             if the phone number is equal to another inserted phone number
     */
    void update(RoomMate roomMate, RoomMate newRoomMate) throws IllegalArgumentException, PhoneNumberPresentException;

}