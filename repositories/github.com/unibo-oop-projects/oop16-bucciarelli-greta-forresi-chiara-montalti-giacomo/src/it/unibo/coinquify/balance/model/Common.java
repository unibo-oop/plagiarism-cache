package it.unibo.coinquify.balance.model;

import it.unibo.coinquify.utils.Messages;

/**
 *
 *
 */
public enum Common {
        /**
         * room A.
         */
        HOUSE_DEBT,
        /**
         * room B.
         */
        HOUSE_LENDING;

        @Override
        public String toString() {
            final String string = super.toString();
            return Messages.getMessages().getString(string);
        }
    }
