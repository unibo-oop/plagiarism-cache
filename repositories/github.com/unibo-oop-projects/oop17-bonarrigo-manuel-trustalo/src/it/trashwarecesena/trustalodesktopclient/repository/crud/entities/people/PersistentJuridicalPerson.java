package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link JuridicalPerson}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentJuridicalPerson {

    /**
     * The CRUD operation of proposing a new {@link JuridicalPerson} to be created.
     * 
     * @param juridicalPerson
     *            the new JuridicalPerson to be created
     * @throws DuplicateKeyValueException
     *             if the JuridicalPerson to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void createEntry(JuridicalPerson juridicalPerson) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link JuridicalPerson}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the JuridicalPerson objects matched against the
     *         filter
     */
    Set<JuridicalPerson> readJuridicalPeople(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link JuridicalPerson} to be updated with
     * the value of a new one.
     * 
     * @param oldJuridicalPerson
     *            the JuridicalPerson actually stored.
     * @param newJuridicalPerson
     *            the JuridicalPerson with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the JuridicalPerson update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void updateEntry(JuridicalPerson oldJuridicalPerson, JuridicalPerson newJuridicalPerson) 
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link JuridicalPerson} for deletion.
     * 
     * @param juridicalPerson
     *            the JuridicalPerson to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(JuridicalPerson juridicalPerson) throws NonExistentReferenceException, BoundedReferenceException;
}
