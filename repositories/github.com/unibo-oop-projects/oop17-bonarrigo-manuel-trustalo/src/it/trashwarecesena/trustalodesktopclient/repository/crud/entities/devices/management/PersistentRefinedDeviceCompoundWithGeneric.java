package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompoundWithGeneric;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link RefinedDeviceCompoundWithGeneric}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRefinedDeviceCompoundWithGeneric {
    /**
     * The CRUD operation of proposing a new
     * {@link RefinedDeviceCompoundWithGeneric} to be created.
     * 
     * @param compound
     *            the RefinedDeviceCompoundWithGeneric to be created.
     * @throws DuplicateKeyValueException
     *             if the RefinedDeviceCompoundWithGeneric to be created is already
     *             present, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(RefinedDeviceCompoundWithGeneric compound) throws NonExistentReferenceException, 
        DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of
     * {@link RefinedDeviceCompoundWithGeneric} filtered by the conditions of the
     * {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the RefinedDeviceCompoundWithGeneric objects
     *         matched against the filter
     */
    Set<RefinedDeviceCompoundWithGeneric> readRefinedDeviceCompoundWithGeneric(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link RefinedDeviceCompoundWithGeneric} to
     * be updated with the value of a new one.
     * 
     * @param oldCompound
     *            the RefinedDeviceCompoundWithGeneric actually stored.
     * @param newCompound
     *            the RefinedDeviceCompoundWithGeneric with the informations to be
     *            fetched for update.
     * @throws DuplicateKeyValueException
     *             if the RefinedDeviceCompoundWithGeneric update transforms the
     *             entity in an already present one, and the persistence storage
     *             does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(RefinedDeviceCompoundWithGeneric oldCompound, RefinedDeviceCompoundWithGeneric newCompound)
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link RefinedDeviceCompoundWithGeneric}
     * for deletion.
     * 
     * @param compound
     *            the RefinedDeviceCompoundWithGeneric to be deleted from the
     *            persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(RefinedDeviceCompoundWithGeneric compound) throws NonExistentReferenceException, 
            BoundedReferenceException;

}
