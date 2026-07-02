package it.trashwarecesena.trustalodesktopclient.repository;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * A skeletal implementation of the {@link Repository} interface.
 * <p>
 * The main functionalities are already implemented, so that concrete
 * implementors only have to add the desired behavior about creation details.
 * <p>
 * Should be noted that the whole class is backed up by an {@link Optional} of
 * {@link PersistenceAdapter} without tolerance for null values, so that a
 * NullPointerException might be thrown at any point at construction time.
 * 
 * @author Manuel Bonarrigo
 * 
 */
public abstract class AbstractRepository implements Repository {
    private static final String THE_REQUEST = "The request";
    private static final String NO_ADAPTER = "No persistence adapter to query";

    private Optional<PersistenceAdapter> persistenceAdapter;

    /**
     * Constructs a Repository with the specified {@link PersistenceAdapter}.
     * 
     * @param persistenceAdapter
     *            the PersistenceAdapter the client desires.
     */
    public AbstractRepository(final PersistenceAdapter persistenceAdapter) {
        this.persistenceAdapter = Optional
                .of(Objects.requireNonNull(persistenceAdapter, "The persistence adapter" + ErrorString.CUSTOM_NULL));
    }

    private PersistenceAdapter getAdapter() {
        return this.persistenceAdapter.orElseThrow(() -> new IllegalStateException(NO_ADAPTER));
    }

    @Override
    public final void dispatchCreateRequest(final SingleRequest request) throws IllegalStateException {
        Objects.requireNonNull(request, THE_REQUEST + ErrorString.CUSTOM_NULL);
        getAdapter().dispatchCreateRequest(request);
    }

    @Override
    public final FragmentedSet dispatchReadRequest(final QueryRequest request) throws IllegalStateException {
        Objects.requireNonNull(request, THE_REQUEST + ErrorString.CUSTOM_NULL);
        return getAdapter().dispatchReadRequest(request);
    }

    @Override
    public final void dispatchUpdateRequest(final BiRequest biRequest) throws IllegalStateException {
        Objects.requireNonNull(biRequest, THE_REQUEST + ErrorString.CUSTOM_NULL);
        getAdapter().dispatchUpdateRequest(biRequest);
    }

    @Override
    public final void dispatchDeleteRequest(final SingleRequest request) throws IllegalStateException {
        Objects.requireNonNull(request, THE_REQUEST + ErrorString.CUSTOM_NULL);
        getAdapter().dispatchDeleteRequest(request);
    }

    @Override
    public final void addPersistenceAdapter(final PersistenceAdapter newLink) {
        Objects.requireNonNull(newLink, "A persistence adapter" + ErrorString.CUSTOM_NULL);
        if (persistenceAdapter.isPresent()) {
            persistenceAdapter.get().dispatchNewLink(newLink);
        } else {
            this.persistenceAdapter = Optional.of(newLink);
        }
    }

    @Override
    public final void removePersistenceAdapter(final String deletee) {
        this.persistenceAdapter = getAdapter().deleteLink(ExtendedObjects
                .requireNonEmpty(Objects.requireNonNull(deletee, ErrorString.STRING_NULL), ErrorString.EMPTY_STRING));
    }

    @Override
    public final void dispatchMapper(final String persistenceAdapterName, final PersistenceMapper domain) {
        getAdapter().addPersistenceMapper(
                ExtendedObjects.requireNonEmpty(Objects.requireNonNull(persistenceAdapterName, ErrorString.STRING_NULL),
                        ErrorString.EMPTY_STRING),
                Objects.requireNonNull(domain, "A PersistenceMapper" + ErrorString.CUSTOM_NULL));
    }

}
