package it.unibo.coinquify.home;

import it.unibo.coinquify.utils.Messages;

/**
 * Room enumeration.
 */
public enum Room {
    /**
     * room A.
     */
    ROOM_A,
    /**
     * room B.
     */
    ROOM_B,
    /**
     * room C.
     */
    ROOM_C,
    /**
     * others room.
     */
    OTHERS_ROOMS;

    @Override
    public String toString() {
        final String string = super.toString();
        return Messages.getMessages().getString(string);
    }
}
