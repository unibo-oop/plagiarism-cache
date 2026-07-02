package it.trashwarecesena.trustalodesktopclient.repository.query.concreteness;

import it.trashwarecesena.trustalodesktopclient.repository.query.AbstractSingleRequest;

/**
 * A narrow type of
 * {@link it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest
 * SingleRequest} used to express a deletion request.
 *
 * @author Manuel Bonarrigo
 */
public final class DeleteRequest extends AbstractSingleRequest {
    /**
     * Constructs a DeleteRequest to be satisfied upon the object passed as
     * parameter.
     * 
     * @param fragmented
     *            The dynamic object to be deleted.
     */
    public DeleteRequest(final Object fragmented) {
        super(fragmented);
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
        final DeleteRequest other = (DeleteRequest) obj;
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
