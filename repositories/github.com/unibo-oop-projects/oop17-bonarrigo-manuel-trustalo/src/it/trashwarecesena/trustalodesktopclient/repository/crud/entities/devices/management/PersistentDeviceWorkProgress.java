package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link DeviceWorkProgress}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentDeviceWorkProgress {
    /**
     * The CRUD operation of proposing a new {@link DeviceWorkProgress} to be
     * created.
     * 
     * @param progress
     *            the DeviceWorkProgress to be created.
     * @throws DuplicateKeyValueException
     *             if the DeviceWorkProgress to be created is already present, and
     *             the persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(DeviceWorkProgress progress) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link DeviceWorkProgress}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the DeviceWorkProgress objects matched against
     *         the filter
     */
    Set<DeviceWorkProgress> readDeviceWorkProgress(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link DeviceWorkProgress} to be updated
     * with the value of a new one.
     * 
     * @param oldProgress
     *            the DeviceWorkProgress actually stored.
     * @param newProgress
     *            the DeviceWorkProgress with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the DeviceWorkProgress update transforms the entity in an
     *             already present one, and the persistence storage does not
     *             tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(DeviceWorkProgress oldProgress, DeviceWorkProgress newProgress) 
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link DeviceWorkProgress} for deletion.
     * 
     * @param progress
     *            the DeviceWorkProgress to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(DeviceWorkProgress progress) throws NonExistentReferenceException, BoundedReferenceException;

}
