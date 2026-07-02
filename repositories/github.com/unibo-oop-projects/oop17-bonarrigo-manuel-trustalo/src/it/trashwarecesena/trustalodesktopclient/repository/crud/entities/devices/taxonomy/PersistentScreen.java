package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.Screen;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link Screen}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentScreen {

    /**
     * The CRUD operation of proposing a new {@link Screen} to be created.
     * 
     * @param screen
     *            the Screen to be created.
     * @throws DuplicateKeyValueException
     *             if the Screen to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(Screen screen) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link Screen}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the Screen objects matched against the
     *         filter
     */
    Set<Screen> readScreen(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link Screen} to be updated with
     * the value of a new one.
     * 
     * @param oldScreen
     *            the Screen actually stored.
     * @param newScreen
     *            the Screen with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the Screen update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     */
    void updateEntry(Screen oldScreen, Screen newScreen) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link Screen} for deletion.
     * 
     * @param screen
     *            the Screen to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(Screen screen) throws NonExistentReferenceException, BoundedReferenceException;

}
