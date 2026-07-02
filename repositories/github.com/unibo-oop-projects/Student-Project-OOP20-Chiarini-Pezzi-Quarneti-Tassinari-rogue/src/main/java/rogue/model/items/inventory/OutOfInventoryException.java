package rogue.model.items.inventory;

/**
 * Exception for when the given index is out of the inventory.
 *
 */
public class OutOfInventoryException extends Exception {

    private static final long serialVersionUID = -4154450610441652376L;

    OutOfInventoryException(final String message) {
        super(message);
    }

}
