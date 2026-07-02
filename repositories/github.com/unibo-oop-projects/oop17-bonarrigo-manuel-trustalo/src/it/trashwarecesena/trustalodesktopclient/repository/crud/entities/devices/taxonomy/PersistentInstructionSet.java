package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link InstructionSet}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentInstructionSet {
    /**
     * The CRUD operation of proposing a new {@link InstructionSet} to be created.
     * 
     * @param isa
     *            the InstructionSet to be created.
     * @throws DuplicateKeyValueException
     *             if the InstructionSet to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(InstructionSet isa) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link InstructionSet}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the InstructionSet objects matched against the
     *         filter
     */
    Set<InstructionSet> readInstructionSets(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link InstructionSet} to be updated with
     * the value of a new one.
     * 
     * @param oldIsa
     *            the InstructionSet actually stored.
     * @param newIsa
     *            the InstructionSet with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the InstructionSet update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(InstructionSet oldIsa, InstructionSet newIsa) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link InstructionSet} for deletion.
     * 
     * @param isa
     *            the InstructionSet to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(InstructionSet isa) throws NonExistentReferenceException, BoundedReferenceException;

}
