package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link PhysicalPerson}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentPhysicalPerson {
    /**
     * The CRUD operation of proposing a new {@link PhysicalPerson} to be created.
     * 
     * @param person
     *            the PhysicalPerson to be created
     * @throws DuplicateKeyValueException
     *             if the PhysicalPerson to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void createEntry(PhysicalPerson person) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link PhysicalPerson}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the PhysicalPerson objects matched against the
     *         filter
     */
    Set<PhysicalPerson> readPeople(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link PhysicalPerson} to be updated with
     * the value of a new one.
     * 
     * @param oldPerson
     *            the PhysicalPerson actually stored.
     * @param newPerson
     *            the PhysicalPerson with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the PhysicalPerson update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void updateEntry(PhysicalPerson oldPerson, PhysicalPerson newPerson) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link PhysicalPerson} for deletion.
     * 
     * @param person
     *            the PhysicalPerson to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(PhysicalPerson person) throws NonExistentReferenceException, BoundedReferenceException;
}
