package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link TrashwareWorkerCategory}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentTrashwareWorkerCategory {

    /**
     * The CRUD operation of proposing a new {@link TrashwareWorkerCategory} to be
     * created.
     * 
     * @param category
     *            the TrashwareWorkerCategory to be created
     * @throws DuplicateKeyValueException
     *             if the TrashwareWorkerCategory to be created is already present,
     *             and the persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void createEntry(TrashwareWorkerCategory category) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of
     * {@link TrashwareWorkerCategory} filtered by the conditions of the
     * {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the TrashwareWorkerCategory objects matched
     *         against the filter
     */
    Set<TrashwareWorkerCategory> readTrashwareWorkerCategories(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link TrashwareWorkerCategory} to be
     * updated with the value of a new one.
     * 
     * @param oldCategory
     *            the TrashwareWorkerCategory actually stored.
     * @param newCategory
     *            the TrashwareWorkerCategory with the informations to be fetched
     *            for update.
     * @throws DuplicateKeyValueException
     *             if the TrashwareWorkerCategory update transforms the entity in an
     *             already present one, and the persistence storage does not
     *             tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void updateEntry(TrashwareWorkerCategory oldCategory, TrashwareWorkerCategory newCategory) 
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link TrashwareWorkerCategory} for
     * deletion.
     * 
     * @param category
     *            the TrashwareWorkerCategory to be deleted from the persistent
     *            data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(TrashwareWorkerCategory category) throws NonExistentReferenceException, BoundedReferenceException;
}
