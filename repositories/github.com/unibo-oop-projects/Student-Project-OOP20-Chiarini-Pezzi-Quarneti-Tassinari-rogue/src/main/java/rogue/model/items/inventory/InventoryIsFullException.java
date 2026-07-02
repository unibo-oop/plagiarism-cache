package rogue.model.items.inventory;

/**
 * Exception for when the InventoryIsFull.
 *
 */
public class InventoryIsFullException extends Exception {

    private static final long serialVersionUID = 7054497161949466512L;

    InventoryIsFullException(final String message) {
        super(message);
    }

}
