package it.trashwarecesena.trustalodesktopclient.repository;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.crud.RequestDispatcher;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper;

/**
 * The entry point for any client who wants to see their persistence requests
 * fulfilled.
 * <p>
 * The repository acts like a Facade to all the persistence services the system
 * has to offer, de-coupling their specifics and implementation complexities
 * from clients who want to make their information persistent.
 * <p>
 * Clients who use a Repository are prevented from knowing where their
 * informations are stored, or what technology is being used to store them.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface Repository extends RequestDispatcher {

    /**
     * Adds a {@link PersistenceAdapter} to the dispatch-base of the Repository.
     * 
     * @param newLink
     *            The PersistenceAdapter to be added.
     */
    void addPersistenceAdapter(PersistenceAdapter newLink);

    /**
     * Remove a {@link PersistenceAdapter} from the Repository dispatch-base.
     * 
     * @param deletee
     *            The {@link String} the {@link PersistenceAdapter} is identified
     *            with.
     */
    void removePersistenceAdapter(String deletee);

    /**
     * 
     * Dispatching a mapper to a selected {@link PersistenceAdapter}, pursuing to
     * link the two's.
     * 
     * @param persistenceAdapter
     *            the PersistenceAdapter to be given the
     * @param domain
     *            the {@link PersistenceMapper} to be linked.
     */
    void dispatchMapper(String persistenceAdapter, PersistenceMapper domain);

}
