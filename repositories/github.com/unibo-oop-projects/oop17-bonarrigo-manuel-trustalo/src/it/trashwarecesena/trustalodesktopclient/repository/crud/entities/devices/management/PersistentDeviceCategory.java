package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link DeviceCategory}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentDeviceCategory {
    /**
     * The CRUD operation of proposing a new {@link DeviceCategory} to be created.
     * 
     * @param category
     *            the DeviceCategory to be created.
     * @throws DuplicateKeyValueException
     *             if the DeviceCategory to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(DeviceCategory category) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link DeviceCategory}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the DeviceCategory objects matched against the
     *         filter
     */
    Set<DeviceCategory> readDeviceCategory(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link DeviceCategory} to be updated with
     * the value of a new one.
     * 
     * @param oldCategory
     *            the DeviceCategory actually stored.
     * @param newCategory
     *            the DeviceCategory with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the DeviceCategory update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(DeviceCategory oldCategory, DeviceCategory newCategory) throws NonExistentReferenceException, 
        DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link DeviceCategory} for deletion.
     * 
     * @param category
     *            the DeviceCategory to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     * 
     */
    void deleteEntry(DeviceCategory category) throws NonExistentReferenceException, BoundedReferenceException;

}
