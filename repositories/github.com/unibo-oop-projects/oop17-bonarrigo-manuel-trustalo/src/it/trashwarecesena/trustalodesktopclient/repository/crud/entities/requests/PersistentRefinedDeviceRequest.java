package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.requests.RefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link RefinedDeviceRequest}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRefinedDeviceRequest {
    /**
     * The CRUD operation of proposing a new {@link RefinedDeviceRequest} to be
     * created.
     * 
     * @param request
     *            the RefinedDeviceRequest to be created.
     * @throws DuplicateKeyValueException
     *             if the RefinedDeviceRequest to be created is already present, and
     *             the persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(RefinedDeviceRequest request) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of
     * {@link RefinedDeviceRequest} filtered by the conditions of the
     * {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the RefinedDeviceRequest objects matched against
     *         the filter
     */
    Set<RefinedDeviceRequest> readRefinedDeviceRequest(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link RefinedDeviceRequest} to be updated
     * with the value of a new one.
     * 
     * @param oldRequest
     *            the RefinedDeviceRequest actually stored.
     * @param newRequest
     *            the RefinedDeviceRequest with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the RefinedDeviceRequest update transforms the entity in an
     *             already present one, and the persistence storage does not
     *             tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(RefinedDeviceRequest oldRequest, RefinedDeviceRequest newRequest) 
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link RefinedDeviceRequest} for deletion.
     * 
     * @param request
     *            the RefinedDeviceRequest to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(RefinedDeviceRequest request) throws NonExistentReferenceException, BoundedReferenceException;

}
