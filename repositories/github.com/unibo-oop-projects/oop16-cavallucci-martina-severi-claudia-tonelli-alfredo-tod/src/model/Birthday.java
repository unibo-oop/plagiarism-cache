package model;

import java.io.Serializable;

/**
 * This interface represents the birthday event of a contact inside the address
 * book.
 */
public interface Birthday extends Serializable, AbstractEvent {

    /**
     * Gets the celebrated contact.
     * 
     * @return celebrated contact
     */
    Contact getCelebratedContact();
}
