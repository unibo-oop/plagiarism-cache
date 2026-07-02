package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.people.ContactCategory;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link ContactCategory}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentContactCategory {

    /**
     * The CRUD operation of proposing a new {@link ContactCategory} to be created.
     * 
     * @param category
     *            the ContactCategory to be created
     * @throws DuplicateKeyValueException
     *             if the ContactCategory to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void createEntry(ContactCategory category) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link ContactCategory}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the ContactCategory objects matched against the
     *         filter
     */
    Set<ContactCategory> readContactCategories(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link ContactCategory} to be updated with
     * the value of a new one.
     * 
     * @param oldType
     *            the ContactCategory actually stored.
     * @param newType
     *            the ContactCategory with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the ContactCategory update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void updateEntry(ContactCategory oldType, ContactCategory newType) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link ContactCategory} for deletion.
     * 
     * @param category
     *            the ContactCategory to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(ContactCategory category) throws NonExistentReferenceException, BoundedReferenceException;
}
