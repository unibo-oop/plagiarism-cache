package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.Case;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link Case}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentCase {
    /**
     * The CRUD operation of proposing a new {@link CaseModel} to be created.
     * 
     * @param caseModel
     *            the CaseModel to be created.
     * @throws DuplicateKeyValueException
     *             if the CaseModel to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(Case caseModel) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link Case}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the CaseModel objects matched against the
     *         filter
     */
    Set<Case> readCase(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link Case} to be updated with
     * the value of a new one.
     * 
     * @param oldCaseModel
     *            the CaseModel actually stored.
     * @param newCaseModel
     *            the CaseModel with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the CaseModel update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(Case oldCaseModel, Case newCaseModel) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link Case} for deletion.
     * 
     * @param caseModel
     *            the CaseModel to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(Case caseModel) throws NonExistentReferenceException, BoundedReferenceException;

}
