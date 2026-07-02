package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenCategory;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link ScreenCategory}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentScreenCategory {
    /**
     * The CRUD operation of proposing a new {@link ScreenCategory} to be created.
     * 
     * @param screenCategory
     *            the ScreenCategory to be created.
     * @throws DuplicateKeyValueException
     *             if the ScreenCategory to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(ScreenCategory screenCategory) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link ScreenCategory}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the ScreenCategory objects matched against the
     *         filter
     */
    Set<ScreenCategory> readScreenCategory(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link ScreenCategory} to be updated with
     * the value of a new one.
     * 
     * @param oldScreenCategory
     *            the ScreenCategory actually stored.
     * @param newScreenCategory
     *            the ScreenCategory with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the ScreenCategory update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(ScreenCategory oldScreenCategory, ScreenCategory newScreenCategory) 
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link ScreenCategory} for deletion.
     * 
     * @param screenCategory
     *            the ScreenCategory to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(ScreenCategory screenCategory) throws NonExistentReferenceException, BoundedReferenceException;

}
