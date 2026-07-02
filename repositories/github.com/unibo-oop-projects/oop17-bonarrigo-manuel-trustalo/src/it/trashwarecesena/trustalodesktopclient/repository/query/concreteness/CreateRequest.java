package it.trashwarecesena.trustalodesktopclient.repository.query.concreteness;

import it.trashwarecesena.trustalodesktopclient.repository.query.AbstractSingleRequest;

/**
 * A narrow type of
 * {@link it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest
 * SingleRequest} used to express a creation request.
 *
 * @author Manuel Bonarrigo
 */
public final class CreateRequest extends AbstractSingleRequest {

    /**
     * Constructs a CreateRequest upon the specified object. No check is performed
     * upon the belonging to the domain model, so a check must be performed by
     * anyone in need for a CreateRequest, since the exceptions raised will be
     * unpredictable otherwise
     * 
     * @param createe
     *            Any domain related object, even if no check is performed against
     *            its validity.
     */
    public CreateRequest(final Object createe) {
        super(createe);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDesiredHandler() == null) ? 0 : getDesiredHandler().hashCode());
        result = prime * result + ((getPayload() == null) ? 0 : getPayload().hashCode());
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
        final CreateRequest other = (CreateRequest) obj;
        if (getDesiredHandler() == null) {
            if (other.getDesiredHandler() != null) {
                return false;
            }
        } else if (!getDesiredHandler().equals(other.getDesiredHandler())) {
            return false;
        }
        if (getPayload() == null) {
            if (other.getPayload() != null) {
                return false;
            }
        } else if (!getPayload().equals(other.getPayload())) {
            return false;
        }
        return true;
    }

}
