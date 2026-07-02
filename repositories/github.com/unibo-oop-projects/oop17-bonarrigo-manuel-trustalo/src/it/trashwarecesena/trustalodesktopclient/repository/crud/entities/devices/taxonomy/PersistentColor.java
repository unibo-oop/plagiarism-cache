package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link Color}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentColor {
    /**
     * The CRUD operation of proposing a new {@link Color} to be created.
     * 
     * @param color
     *            the Color to be created.
     * @throws DuplicateKeyValueException
     *             if the Color to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(Color color) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link Color} filtered by
     * the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the Color objects matched against the filter
     */
    Set<Color> readColor(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link Color} to be updated with the value
     * of a new one.
     * 
     * @param oldColor
     *            the Color actually stored.
     * @param newColor
     *            the Color with the informations to be fetched for update.
     * @throws DuplicateKeyValueException
     *             if the Color update transforms the entity in an already present
     *             one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(Color oldColor, Color newColor) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link Color} for deletion.
     * 
     * @param color
     *            the Color to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(Color color) throws NonExistentReferenceException, BoundedReferenceException;

}
