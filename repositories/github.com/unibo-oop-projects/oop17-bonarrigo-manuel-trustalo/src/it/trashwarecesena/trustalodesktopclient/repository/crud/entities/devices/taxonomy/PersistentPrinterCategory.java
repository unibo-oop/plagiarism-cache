package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.devices.taxonomy;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link PrinterCategory}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentPrinterCategory {
    /**
     * The CRUD operation of proposing a new {@link PrinterCategory} to be created.
     * 
     * @param category
     *            the PrinterCategory to be created.
     * @throws DuplicateKeyValueException
     *             if the PrinterCategory to be created is already present, and the
     *             persistence storage does not tolerate value repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void createEntry(PrinterCategory category) throws NonExistentReferenceException, DuplicateKeyValueException;

    /**
     * The CRUD operation of requesting a {@link Set} of {@link PrinterCategory}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the PrinterCategory objects matched against the
     *         filter
     */
    Set<PrinterCategory> readPrinterCategory(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link PrinterCategory} to be updated with
     * the value of a new one.
     * 
     * @param oldCategory
     *            the PrinterCategory actually stored.
     * @param newCategory
     *            the PrinterCategory with the informations to be fetched for
     *            update.
     * @throws DuplicateKeyValueException
     *             if the PrinterCategory update transforms the entity in an already
     *             present one, and the persistence storage does not tolerate value
     *             repetition.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * 
     */
    void updateEntry(PrinterCategory oldCategory, PrinterCategory newCategory) throws NonExistentReferenceException, 
            DuplicateKeyValueException;

    /**
     * The CRUD operation of proposing a {@link PrinterCategory} for deletion.
     * 
     * @param category
     *            the PrinterCategory to be deleted from the persistent data.
     * @throws NonExistentReferenceException
     *             if any referenced value is absent or malformed, and the
     *             persistence storage was not able to precisely locate it.
     * @throws BoundedReferenceException
     *             if exists any entity actively referencing this one, making thus
     *             this one impossible to delete.
     */
    void deleteEntry(PrinterCategory category) throws NonExistentReferenceException, BoundedReferenceException;

}
