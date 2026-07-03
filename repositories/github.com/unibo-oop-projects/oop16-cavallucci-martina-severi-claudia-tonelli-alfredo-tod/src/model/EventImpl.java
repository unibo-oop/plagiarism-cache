package model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalTime;

import com.google.common.base.Optional;

/**
 * This class represents an appointment event of a user.
 */
public class EventImpl extends AbstractEventImpl implements Event {

    private static final long serialVersionUID = -1655396630875092247L;

    private final Optional<String> place;
    private final Optional<LocalTime> endTime;
    private final Optional<List<Contact>> contacts;

    /**
     * Builds a appointment event.
     * 
     * @param notify
     *            if the event is to be notified
     * @param description
     *            the description of the event
     * @param place
     *            the place where the event is located
     * @param startTime
     *            the start time of the event
     * @param endTime
     *            the end time of the event
     * @param contacts
     *            list of contacts participating at the event
     */
    EventImpl(final boolean notify, final String description, final String place, final LocalTime startTime,
            final LocalTime endTime, final List<Contact> contacts) {
        super(description, startTime, notify);
        this.place = Optional.fromNullable(place);
        this.endTime = Optional.fromNullable(endTime);
        this.contacts = Optional.fromNullable(contacts);
    }

    @Override
    public String getPlace() {
        return this.place.or("");
    }

    @Override
    public String getEndTime() {
        if (this.endTime.isPresent()) {
            return this.endTime.get().toString();
        } else {
            return "";
        }
    }

    @Override
    public List<Contact> getContacts() {
        return this.contacts.or(Collections.emptyList());
    }

    /**
     * This inner class is the Builder of EventImpl.
     */
    public static class Builder {
        private Boolean builderNotify;
        private String builderDescription;
        private LocalTime builderStartTime;
        private String builderPlace;
        private LocalTime builderEndTime;
        private List<Contact> builderContacts;

        /**
         * @param notify
         *            if the event is to be notified
         * @return true if the event is to be notified
         */
        public Builder notify(final boolean notify) {
            this.builderNotify = Objects.requireNonNull(notify);
            return this;
        }

        /**
         * @param description
         *            the description of the event
         * @return the description of the event
         */
        public Builder description(final String description) {
            this.builderDescription = Objects.requireNonNull(description);
            return this;
        }

        /**
         * @param startTime
         *            the start time of the event
         * @return the start time of the event
         */
        public Builder startTime(final LocalTime startTime) {
            this.builderStartTime = Objects.requireNonNull(startTime);
            return this;
        }

        /**
         * @param place
         *            the place where the event is located
         * @return the place where the event is located
         */
        public Builder place(final String place) {
            this.builderPlace = place;
            return this;
        }

        /**
         * @param endTime
         *            the end time of the event
         * @return the end time of the event
         */
        public Builder endTime(final LocalTime endTime) {
            this.builderEndTime = endTime;
            return this;
        }

        /**
         * @param contacts
         *            list of contacts participating at the event
         * @return list of contacts participating at the event
         */
        public Builder contacts(final List<Contact> contacts) {
            this.builderContacts = contacts;
            return this;
        }

        /**
         * Builds a EventImpl.
         * 
         * @return a new EventImpl
         * @throws IllegalArgumentException
         *             if notify, description and start time fields are empty
         */
        public EventImpl build() throws IllegalArgumentException {
            if (this.builderNotify == null || this.builderDescription == null || this.builderStartTime == null) {
                throw new IllegalArgumentException("The notify, description and start time fields are required");
            }
            return new EventImpl(this.builderNotify, this.builderDescription, this.builderPlace, this.builderStartTime,
                    this.builderEndTime, this.builderContacts);
        }
    }
}
