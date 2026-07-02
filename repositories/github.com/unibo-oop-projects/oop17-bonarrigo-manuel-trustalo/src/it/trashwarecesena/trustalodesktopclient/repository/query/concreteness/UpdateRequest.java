package it.trashwarecesena.trustalodesktopclient.repository.query.concreteness;

import it.trashwarecesena.trustalodesktopclient.repository.query.AbstractBiRequest;

/**
 * A narrow type of
 * {@link it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest
 * BiRequest} used to express an update request.
 * 
 * @author Manuel Bonarrigo
 */
public final class UpdateRequest extends AbstractBiRequest {

    /**
     * Constructs an UpdateRequest to be satisfied upon the objects passed as
     * parameters. The oldEntry parameter is valued more because is the one to be
     * searched on at the occurring of the update.
     * 
     * @param oldEntry
     *            The object to be updated
     * @param newEntry
     *            The object which contains the new data
     * 
     */
    public UpdateRequest(final Object oldEntry, final Object newEntry) {
        super(oldEntry, newEntry);
        if (!(oldEntry.getClass().equals(newEntry.getClass()))) {
            throw new IllegalStateException("The components of an update request are expected to be of the same type.");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDesiredHandler() == null) ? 0 : getDesiredHandler().hashCode());
        result = prime * result + ((getPayload() == null) ? 0 : getPayload().hashCode());
        result = prime * result + ((getSecondPayload() == null) ? 0 : getSecondPayload().hashCode());
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
        final UpdateRequest other = (UpdateRequest) obj;
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
        if (getSecondPayload() == null) {
            if (other.getSecondPayload() != null) {
                return false;
            }
        } else if (!getSecondPayload().equals(other.getSecondPayload())) {
            return false;
        }
        return true;
    }

}
