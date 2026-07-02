package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link GenericDevice}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentGenericDevice {
    /**
     * The CRUD operation of proposing a new {@link GenericDevice} to be created.
     * 
     * @param device
     *            the GenericDevice to be created.
     * @throws DuplicateKeyValueException
     *             if the GenericDevice to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(GenericDevice device) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link GenericDevice}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the GenericDevice objects matched against the
     *         filter
     */
    Set<GenericDevice> readGenericDevice(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link GenericDevice} to be updated with
     * the value of a new one.
     * 
     * @param oldDevice
     *            the GenericDevice actually stored.
     * @param newDevice
     *            the GenericDevice with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the GenericDevice update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(GenericDevice oldDevice, GenericDevice newDevice) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link GenericDevice} for deletion.
     * 
     * @param device
     *            the GenericDevice to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(GenericDevice device) throws NonExistentReferenceException, BoundedReferenceException;

}
