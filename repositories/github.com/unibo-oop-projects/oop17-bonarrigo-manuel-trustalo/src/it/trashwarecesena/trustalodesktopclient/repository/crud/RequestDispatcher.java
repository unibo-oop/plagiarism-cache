package it.trashwarecesena.trustalodesktopclient.repository.crud;

import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;

/**
 * Set of operation that makes the implementor able to handle and dispatching
 * the requests for CRUD operations.
 *
 * @author Manuel Bonarrigo
 */
public interface RequestDispatcher {
    /**
     * Dispatch a {@link SingleRequest} representing a creational CRUD operation to
     * a more appropriate handler.
     * 
     * @param request
     *            A SingleRequest carrying the information the client wants to be
     *            created
     */
    void dispatchCreateRequest(SingleRequest request);

    /**
     * Dispatch a {@link QueryRequest} representing a read CRUD operation to a more
     * appropriate handler.
     * 
     * @param request
     *            A QueryRequest carrying the filter the client wants the persistent
     *            data to be matched against
     * @return a {@link FragmentedSet} containing the results of applying the
     *         filter.
     */
    FragmentedSet dispatchReadRequest(QueryRequest request);

    /**
     * Dispatch a {@link BiRequest} representing an update CRUD operation to a more
     * appropriate handler.
     * 
     * @param biRequest
     *            A BiRequest carrying the information of what the client wants to
     *            be substituted by another piece of information
     */
    void dispatchUpdateRequest(BiRequest biRequest);

    /**
     * Dispatch a {@link SingleRequest} representing a deletion CRUD operation to a
     * more appropriate handler.
     * 
     * @param request
     *            A SingleRequest carrying the information of what the client wants
     *            to be deleted
     */
    void dispatchDeleteRequest(SingleRequest request);

}
