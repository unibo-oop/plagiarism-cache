package it.trashwarecesena.trustalodesktopclient.repository.query;

import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * A carrier to deliver query request over the persistence module.
 * @author Manuel Bonarrigo
 *
 */
public interface QueryRequest {

    /**
     * Retrieve which {@link Class} this QueryRequest is expected to fetch.
     * @return A Class which will never be null.
     */
    Class<?> getQueryType();

    /**
     * Retrieve the {@link QueryObject} this QueryRequest has been initialized with.
     * @return a QueryObject which will never be null.
     */
    QueryObject getQueryObject();
}
