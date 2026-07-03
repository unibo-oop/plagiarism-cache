package it.unibo.coinquify.mansionsmng.controller;

import javax.naming.OperationNotSupportedException;

import it.unibo.coinquify.mansionsmng.model.Mansion;
import it.unibo.coinquify.roommates.model.RoomMate;

/**
 * Mansion manager controller interface.
 */
public interface MansionMangerController {

    /**
     * 
     * @param mansion
     *            to add
     * @param roomMate
     *            to do that
     * @return mansion
     * 
     * @throws IllegalArgumentException
     *             if the args are incorrected
     * @throws OperationNotSupportedException
     *             if it isn't possible to add that mansions
     */
    Mansion addMansion(Mansion mansion, RoomMate roomMate)
            throws IllegalArgumentException, OperationNotSupportedException;

    /**
     * 
     * @param mansion
     *            to delete
     * @param rm
     *            room mate of mansion
     */
    void deleteMansion(Mansion mansion, RoomMate rm);

}