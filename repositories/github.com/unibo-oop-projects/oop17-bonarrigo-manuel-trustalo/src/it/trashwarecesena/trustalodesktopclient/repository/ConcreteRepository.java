package it.trashwarecesena.trustalodesktopclient.repository;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter;

/**
 * A concretion of the {@link Repository}, extending its behaviour from
 * {@link AbstractRepository}.
 * 
 * @author Manuel Bonarrigo
 *
 */
public class ConcreteRepository extends AbstractRepository {

    /**
     * Constructs a ConcreteRepository based on a single starting
     * {@link PersistenceAdapter}.
     * 
     * @param persistenceAdapter
     *            a fully instantiated PersistenceAdapter.
     * @throws NullPointerException
     *            if the persistenceAdapter parameter is found to be null
     */
    public ConcreteRepository(final PersistenceAdapter persistenceAdapter) {
        super(persistenceAdapter);
    }

}
