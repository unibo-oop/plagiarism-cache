package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.management;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.LegalCategoryCompound;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link LegalCategoryCompound}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentLegalCategoryCompound {
    /**
     * The CRUD operation of proposing a new {@link LegalCategoryCompound} to be created.
     * 
     * @param categoryCompound
     *            the LegalCategoryCompound to be created.
     * @throws DuplicateKeyValueException
     *             if the LegalCategoryCompound to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(LegalCategoryCompound categoryCompound) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link LegalCategoryCompound}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the LegalCategoryCompound objects matched against the
     *         filter
     */
    Set<LegalCategoryCompound> readLegalCategoryCompound(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link LegalCategoryCompound} to be updated with
     * the value of a new one.
     * 
     * @param oldCategoryCompound
     *            the LegalCategoryCompound actually stored.
     * @param newCategoryCompound
     *            the LegalCategoryCompound with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the LegalCategoryCompound update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(LegalCategoryCompound oldCategoryCompound, LegalCategoryCompound newCategoryCompound)
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link LegalCategoryCompound} for deletion.
     * 
     * @param categoryCompound
     *            the LegalCategoryCompound to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(LegalCategoryCompound categoryCompound) throws NonExistentReferenceException, 
            BoundedReferenceException;

}
