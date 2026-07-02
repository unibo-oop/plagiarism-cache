package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link RequestProgress}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRequestProgress {

    /**
     * The CRUD operation of proposing a new {@link RequestProgress} to be created.
     * 
     * @param progress
     *            the RequestProgress to be created.
     * @throws DuplicateKeyValueException
     *             if the RequestProgress to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(RequestProgress progress) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link RequestProgress}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the RequestProgress objects matched against the
     *         filter
     */
    Set<RequestProgress> readRequestProgress(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link RequestProgress} to be updated with
     * the value of a new one.
     * 
     * @param oldProgress
     *            the RequestProgress actually stored.
     * @param newProgress
     *            the RequestProgress with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the RequestProgress update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void updateEntry(RequestProgress oldProgress, RequestProgress newProgress) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link RequestProgress} for deletion.
     * 
     * @param progress
     *            the RequestProgress to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(RequestProgress progress) throws NonExistentReferenceException, BoundedReferenceException;

}
