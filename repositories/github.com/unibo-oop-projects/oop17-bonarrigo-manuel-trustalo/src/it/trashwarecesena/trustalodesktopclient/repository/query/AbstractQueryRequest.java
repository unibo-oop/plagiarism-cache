package it.trashwarecesena.trustalodesktopclient.repository.query;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Implements the behaviour expected from a QueryRequest, letting subclasses
 * narrowing the type of the request.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractQueryRequest implements QueryRequest {

    private final QueryObject query;

    /**
     * Instantiate a QueryRequest over the {@link QueryObject} passed as parameter.
     * 
     * @param query
     *            a QueryObject to express the condition to retrieve information
     *            with.
     */
    public AbstractQueryRequest(final QueryObject query) {
        this.query = Objects.requireNonNull(query, "The QueryObject" + ErrorString.NO_NULL);
    }

    @Override
    public final Class<?> getQueryType() {
        return this.query.getDesiredHandler();
    }

    @Override
    public final QueryObject getQueryObject() {
        return this.query;
    }
}
