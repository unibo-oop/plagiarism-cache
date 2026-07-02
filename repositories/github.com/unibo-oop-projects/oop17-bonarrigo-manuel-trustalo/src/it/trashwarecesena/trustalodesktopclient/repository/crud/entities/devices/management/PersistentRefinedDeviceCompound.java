package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompound;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link RefinedDeviceCompound}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRefinedDeviceCompound {
    /**
     * The CRUD operation of proposing a new {@link RefinedDeviceCompound} to be
     * created.
     * 
     * @param compound
     *            the RefinedDeviceCompound to be created.
     * @throws DuplicateKeyValueException
     *             if the RefinedDeviceCompound to be created is already present,
     *             and the persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(RefinedDeviceCompound compound) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of
     * {@link RefinedDeviceCompound} filtered by the conditions of the
     * {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the RefinedDeviceCompound objects matched
     *         against the filter
     */
    Set<RefinedDeviceCompound> readRefinedDeviceCompound(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link RefinedDeviceCompound} to be updated
     * with the value of a new one.
     * 
     * @param oldCompound
     *            the RefinedDeviceCompound actually stored.
     * @param newCompound
     *            the RefinedDeviceCompound with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the RefinedDeviceCompound update transforms the entity in an
     *             already present one, and the persistence storage does not
     *             tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(RefinedDeviceCompound oldCompound, RefinedDeviceCompound newCompound) throws 
            NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link RefinedDeviceCompound} for deletion.
     * 
     * @param compound
     *            the RefinedDeviceCompound to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(RefinedDeviceCompound compound) throws NonExistentReferenceException, BoundedReferenceException;

}
