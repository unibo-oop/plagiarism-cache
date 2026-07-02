package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link RequestDetail}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRequestDetail {
    /**
     * The CRUD operation of proposing a new {@link RequestDetail} to be created.
     * 
     * @param detail
     *            the RequestDetail to be created.
     * @throws DuplicateKeyValueException
     *             if the RequestDetail to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(RequestDetail detail) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link RequestDetail}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the RequestDetail objects matched against the
     *         filter
     */
    Set<RequestDetail> readRequestDetail(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link RequestDetail} to be updated with
     * the value of a new one.
     * 
     * @param oldDetail
     *            the RequestDetail actually stored.
     * @param newDetail
     *            the RequestDetail with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the RequestDetail update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(RequestDetail oldDetail, RequestDetail newDetail) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link RequestDetail} for deletion.
     * 
     * @param detail
     *            the RequestDetail to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(RequestDetail detail) throws NonExistentReferenceException, BoundedReferenceException;

}
