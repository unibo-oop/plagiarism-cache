package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link Request}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRequest {
    /**
     * The CRUD operation of proposing a new {@link Request} to be created.
     * 
     * @param request
     *            the Request to be created.
     * @throws DuplicateKeyValueException
     *             if the Request to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(Request request) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link Request} filtered by
     * the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the Request objects matched against the filter
     */
    Set<Request> readRequest(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link Request} to be updated with the
     * value of a new one.
     * 
     * @param oldRequest
     *            the Request actually stored.
     * @param newRequest
     *            the Request with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the Request update transforms the entity in an already present
     *             one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(Request oldRequest, Request newRequest) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link Request} for deletion.
     * 
     * @param request
     *            the Request to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(Request request) throws NonExistentReferenceException, BoundedReferenceException;

}
