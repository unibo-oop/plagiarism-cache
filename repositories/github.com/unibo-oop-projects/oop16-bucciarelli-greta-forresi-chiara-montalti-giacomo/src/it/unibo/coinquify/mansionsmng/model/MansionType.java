package it.unibo.coinquify.mansionsmng.model;

import it.unibo.coinquify.utils.Messages;

/**
 * MansionType of home.
 */
public enum MansionType {
    /**
     * bathroom(s) clean.
     */
    BATHROOM_CLEAN,
    /**
     * kitchen clean.
     */
    KITCHEN_CLEAN,
    /**
     * living room clean.
     */
    LIVING_ROOM_CLEAN,
    /**
     * shopping.
     */
    SHOPPING,
    /**
     * bills.
     */
    BILLS,
    /**
     * others.
     */
    OTHERS_MANSIONS;

    @Override
    public String toString() {
        final String string = super.toString();
        return Messages.getMessages().getString(string);
    }
}
