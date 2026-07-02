package it.trashwarecesena.trustalodesktopclient.repository.crud.entities.intel;

import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.model.immutable.ImmutableIntelProcessor;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;

/**
 * The interface modeling the four CRUD operations for the domain entity of
 * {@link ImmutableIntelProcessor}.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistentIntelProcessor {
    /**
     * The CRUD operation of proposing a new {@link ImmutableIntelProcessor} to be
     * created.
     * 
     * @param processor
     *            the ImmutableIntelProcessor to be created.
     * @throws UnsupportedOperationException
     *             since this is a well-known read-only domain, no addition is
     *             possible
     */
    void createEntry(ImmutableIntelProcessor processor);

    /**
     * The CRUD operation of requesting a {@link Set} of {@link ImmutableIntelProcessor}
     * filtered by the conditions of the {@link QueryObject}.
     * 
     * @param filter
     *            the filter that will determine which results are going to be
     *            fetched
     * @return A Set containing all the ImmutableIntelProcessor objects matched against the
     *         filter
     */
    Set<ImmutableIntelProcessor> readIntelProcessors(QueryObject filter);

    /**
     * The CRUD operation of proposing a {@link ImmutableIntelProcessor} to be updated with
     * the value of a new one.
     * 
     * @param oldProcessor
     *            the ImmutableIntelProcessor actually stored.
     * @param newProcessor
     *            the ImmutableIntelProcessor with the informations to be fetched for
     *            update.
     * @throws UnsupportedOperationException
     *             since this is a well-known read-only domain, no update is
     *             possible
     */
    void updateEntry(ImmutableIntelProcessor oldProcessor, ImmutableIntelProcessor newProcessor);

    /**
     * The CRUD operation of proposing a {@link ImmutableIntelProcessor} for deletion.
     * 
     * @param processor
     *            the ImmutableIntelProcessor to be deleted from the persistent data.
     * @throws UnsupportedOperationException
     *             since this is a well-known read-only domain, no delete is
     *             possible
     * 
     */
    void deleteEntry(ImmutableIntelProcessor processor);

}
