package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link AspectRatio}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentAspectRatio {
    /**
     * The CRUD operation of proposing a new {@link AspectRatio} to be created.
     * 
     * @param ratio
     *            the AspectRatio to be created.
     * @throws DuplicateKeyValueException
     *             if the AspectRatio to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(AspectRatio ratio) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link AspectRatio}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the AspectRatio objects matched against the
     *         filter
     */
    Set<AspectRatio> readAspectRatio(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link AspectRatio} to be updated with the
     * value of a new one.
     * 
     * @param oldRatio
     *            the AspectRatio actually stored.
     * @param newRatio
     *            the AspectRatio with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the AspectRatio update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(AspectRatio oldRatio, AspectRatio newRatio) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link AspectRatio} for deletion.
     * 
     * @param ratio
     *            the AspectRatio to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(AspectRatio ratio) throws NonExistentReferenceException, BoundedReferenceException;

}
