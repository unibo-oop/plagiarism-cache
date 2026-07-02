package it.trashwarecesena.trustalodesktopclient.repository.query.concreteness;

import it.trashwarecesena.trustalodesktopclient.repository.query.AbstractQueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * A narrow type of
 * {@link it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest
 * QueryRequest} used to express a read request.
 *
 * @author Manuel Bonarrigo
 */

public final class ReadRequest extends AbstractQueryRequest {

    /**
     * Constructs a ReadRequest upon the {@link QueryObject} passed in as parameter.
     * 
     * @param query
     *            A QueryObject containing all the informations about what is needed
     *            to be fetched.
     */
    public ReadRequest(final QueryObject query) {
        super(query);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQueryType() == null) ? 0 : getQueryType().hashCode());
        result = prime * result + ((getQueryObject() == null) ? 0 : getQueryObject().hashCode());
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
        final ReadRequest other = (ReadRequest) obj;
        if (getQueryType() == null) {
            if (other.getQueryType() != null) {
                return false;
            }
        } else if (!getQueryType().equals(other.getQueryType())) {
            return false;
        }
        if (getQueryObject() == null) {
            if (other.getQueryObject() != null) {
                return false;
            }
        } else if (!getQueryObject().equals(other.getQueryObject())) {
            return false;
        }
        return true;
    }
}
