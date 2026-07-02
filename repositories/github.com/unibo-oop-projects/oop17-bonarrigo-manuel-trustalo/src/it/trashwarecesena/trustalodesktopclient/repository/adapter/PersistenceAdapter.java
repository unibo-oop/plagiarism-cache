package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import it.trashwarecesena.trustalodesktopclient.repository.crud.RequestDispatcher;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResource;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper;

/**
 * The abstraction over the management part of a persistence storage (namely,
 * connecting to it).
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface PersistenceAdapter extends RequestDispatcher, ChainOfResponsibilityUtils<PersistenceAdapter, String> {

    /**
     * Retrieve the name of the PersistenceAdapter.
     * 
     * @return a string representing the name of the PersistenceAdapter
     */
    String getName();

    /**
     * Retrieve a {@link ConnectionResource} carrying information about what
     * connection system is used, and a reference to the connection resource itself.
     * 
     * @return a FragmentedConnection holding the connection resource.
     */
    ConnectionResource<?> getConnection();

    /**
     * Adds a {@link PersistenceMapper} to this PersistenceAdapter knowledge-base.
     * 
     * @param persistenceAdapter
     *            the String a PersistenceAdapter is identified with.
     * @param mapper
     *            the mapper to be added.
     */
    void addPersistenceMapper(String persistenceAdapter, PersistenceMapper mapper);

}
