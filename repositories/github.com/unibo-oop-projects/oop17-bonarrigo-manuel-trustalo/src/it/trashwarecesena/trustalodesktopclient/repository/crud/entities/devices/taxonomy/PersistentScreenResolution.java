package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link ScreenResolution}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentScreenResolution {
    /**
     * The CRUD operation of proposing a new {@link ScreenResolution} to be created.
     * 
     * @param screenResolution
     *            the ScreenResolution to be created.
     * @throws DuplicateKeyValueException
     *             if the ScreenResolution to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(ScreenResolution screenResolution) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link ScreenResolution}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the ScreenResolution objects matched against the
     *         filter
     */
    Set<ScreenResolution> readScreenResolution(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link ScreenResolution} to be updated with
     * the value of a new one.
     * 
     * @param oldScreenResolution
     *            the ScreenResolution actually stored.
     * @param newScreenResolution
     *            the ScreenResolution with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the ScreenResolution update transforms the entity in an
     *             already present one, and the persistence storage does not
     *             tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(ScreenResolution oldScreenResolution, ScreenResolution newScreenResolution) 
            throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link ScreenResolution} for deletion.
     * 
     * @param screenResolution
     *            the ScreenResolution to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(ScreenResolution screenResolution) throws NonExistentReferenceException, BoundedReferenceException;

}
