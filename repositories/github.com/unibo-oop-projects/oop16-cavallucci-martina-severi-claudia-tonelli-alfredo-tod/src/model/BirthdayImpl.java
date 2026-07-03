package model;

import org.joda.time.LocalTime;

/**
 * This class represents the birthday event of a contact inside the address
 * book.
 */
public class BirthdayImpl extends AbstractEventImpl implements Birthday {

    private static final long serialVersionUID = 4774488570008435078L;
    private final Contact celebratedContact;

    /**
     * Builds a birthday event.
     * 
     * @param notify
     *            if the event is to be notified
     * @param description
     *            the description of the event
     * @param celebratedContact
     *            person celebrating birthday
     * @param startTime
     *            the start time of the event, used only for notification
     */
    public BirthdayImpl(final boolean notify, final String description, final Contact celebratedContact,
            final LocalTime startTime) {
        super(description, startTime, notify);
        this.celebratedContact = celebratedContact;
    }

    @Override
    public Contact getCelebratedContact() {
        return this.celebratedContact;
    }

}
