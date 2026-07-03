package it.unibo.coinquify.mansionsmng.controller;

import java.util.Set;

import javax.naming.OperationNotSupportedException;

import it.unibo.coinquify.mansionsmng.model.Mansion;
import it.unibo.coinquify.mansionsmng.model.MansionType;
import it.unibo.coinquify.roommates.model.RoomMate;

/**
 * Mansion mng controller class.
 */
public class MansionManagerControllerImpl implements MansionMangerController {

    private final Set<RoomMate> roomMates;

    /**
     * Controller constructor.
     * 
     * @param roomMates
     *            of home
     */
    public MansionManagerControllerImpl(final Set<RoomMate> roomMates) {
        this.roomMates = roomMates;
    }

    @Override
    public Mansion addMansion(final Mansion mansion, final RoomMate roomMate)
            throws IllegalArgumentException, OperationNotSupportedException {
        this.controlFields(mansion, roomMate);
        roomMate.addMansion(mansion);
        return mansion;
    }

    private void controlFields(final Mansion mansionToControl, final RoomMate roomMate)
            throws IllegalArgumentException, OperationNotSupportedException {

        if (!mansionToControl.getEndTime().after(mansionToControl.getStartTime()) || roomMate == null) {
            throw new IllegalArgumentException();
        }
        // don't work if room mate is already done something in that day and
        // hours
        for (final Mansion mansion : roomMate.getMansions()) {
            if (mansion.getDayOfWeek().equals(mansionToControl.getDayOfWeek())
                    && ((mansionToControl.getEndTime().after(mansion.getStartTime())
                            && mansionToControl.getEndTime().before(mansion.getEndTime())
                            || (mansionToControl.getEndTime().after(mansion.getStartTime())
                                    && mansionToControl.getEndTime().equals(mansion.getEndTime()))
                            || (mansionToControl.getStartTime().after(mansion.getStartTime())
                                    && mansionToControl.getStartTime().before(mansion.getEndTime()))
                            || mansionToControl.getStartTime().equals(mansion.getStartTime())))) {
                throw new OperationNotSupportedException();
            }
        }
        // don't work if other room mate is doing that mansion it that day (not
        // for OTHERS mansion)
        if (mansionToControl.getName() != MansionType.OTHERS_MANSIONS) {
            for (final RoomMate rm : this.roomMates) {
                for (final Mansion mansion : rm.getMansions()) {
                    if (mansion.getDayOfWeek().equals(mansionToControl.getDayOfWeek())
                            && mansion.getName().equals(mansionToControl.getName())) {
                        throw new OperationNotSupportedException();
                    }
                }
            }
        }
    }

    @Override
    public void deleteMansion(final Mansion mansion, final RoomMate rm) {
        rm.deleteMansion(mansion);
    }
}
