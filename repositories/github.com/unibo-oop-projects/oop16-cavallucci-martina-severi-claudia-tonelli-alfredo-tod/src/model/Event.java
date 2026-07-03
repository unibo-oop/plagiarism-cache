package model;

import java.io.Serializable;
import java.util.List;

/**
 * This interface represents an appointment event of a user.
 */
public interface Event extends Serializable, AbstractEvent {

    /**
     * @return the place where the appointment will be held
     */
    String getPlace();

    /**
     * @return a string with end time of the appointment
     */
    String getEndTime();

    /**
     * @return a list with all event's contacts
     */
    List<Contact> getContacts();
}
