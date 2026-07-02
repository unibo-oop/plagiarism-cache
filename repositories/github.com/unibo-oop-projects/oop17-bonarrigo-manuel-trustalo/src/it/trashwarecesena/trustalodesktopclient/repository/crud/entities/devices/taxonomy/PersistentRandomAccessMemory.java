package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.RandomAccessMemory;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link RandomAccessMemory}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentRandomAccessMemory {
    /**
     * The CRUD operation of proposing a new {@link RandomAccessMemory} to be
     * created.
     * 
     * @param ram
     *            the RandomAccessMemory to be created.
     * @throws DuplicateKeyValueException
     *             if the RandomAccessMemory to be created is already present, and
     *             the persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(RandomAccessMemory ram) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link RandomAccessMemory}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the RandomAccessMemory objects matched against
     *         the filter
     */
    Set<RandomAccessMemory> readRandomAccessMemory(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link RandomAccessMemory} to be updated
     * with the value of a new one.
     * 
     * @param oldRam
     *            the RandomAccessMemory actually stored.
     * @param newRam
     *            the RandomAccessMemory with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the RandomAccessMemory update transforms the entity in an
     *             already present one, and the persistence storage does not
     *             tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(RandomAccessMemory oldRam, RandomAccessMemory newRam) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link RandomAccessMemory} for deletion.
     * 
     * @param ram
     *            the RandomAccessMemory to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(RandomAccessMemory ram) throws NonExistentReferenceException, BoundedReferenceException;

}
