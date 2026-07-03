package model;

import org.joda.time.LocalTime;

/**
 * This abstract class represents a event of the user calendar.
 */
public abstract class AbstractEventImpl implements AbstractEvent {

    private static final long serialVersionUID = -3681260733200766604L;
    private final String description;
    private final LocalTime startTime;
    private boolean notify;

    AbstractEventImpl(final String description, final LocalTime startTime, final boolean notify) {
        this.description = description;
        this.startTime = startTime;
        this.notify = notify;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEventImpl other = (AbstractEventImpl) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "-" + startTime.toString("HH:mm") + " " + description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalTime getStartTime() {
        return this.startTime;
    }

    @Override
    public boolean isNotify() {
        return notify;
    }

    @Override
    public void setNotified() {
        this.notify = false;
    }

}
