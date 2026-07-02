package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * Skeletal implementation of the {@link PersistenceAdapter}, made so that
 * concrete implementors only have to add behavior for the <i>connect</i>
 * method.
 * <p>
 * The class dispatches all the requests it receives to a
 * {@link PersistenceMapper}, querying it for the criteria regarding the
 * requests satisfaction.
 * <p>
 * If the criteria don't match those needed, the request is dispatched to
 * another handler through a chain of responsibility.
 * 
 * @author Manuel Bonarrigo
 */
public abstract class AbstractPersistenceAdapter implements PersistenceAdapter {

    private static final String A_REQUEST = "A request";
    private static final String NO_RESOLUTION = "No adapter found to resolve a ";

    private final String name;
    private final List<PersistenceMapper> mapper;
    private Optional<PersistenceAdapter> nextHandler;

    /**
     * Constructs a new PersistenceAdapter linked to the given
     * {@link PersistenceMapper} to handle the requests it will be dispatched to.
     * 
     * @param name
     *            a name for the PersistentAdapter
     * @throws IllegalArgumentException
     *             if the name is an empty string, or the mapper is a null reference
     */
    public AbstractPersistenceAdapter(final String name) throws IllegalArgumentException {
        super();
        this.name = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(name));
        this.mapper = new ArrayList<>();
        nextHandler = Optional.empty();
    }

    private boolean canHandleRequest(final Class<?> request) {
        return mapper.stream().anyMatch(map -> map.canHandleRequest(request));
    }

    @Override
    public final boolean isLastLink() {
        return !nextHandler.isPresent();
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void dispatchNewLink(final PersistenceAdapter newLink) throws IllegalArgumentException {
        Objects.requireNonNull(newLink, "A persistence adapter" + ErrorString.CUSTOM_NULL);

        if (!newLink.isLastLink()) {
            throw new IllegalArgumentException(
                    "A new link in the chain of responsibility must have no succeeding available handler");
        }
        if (this.getName().equals(newLink.getName())) {
            throw new IllegalArgumentException("A link in the chain must have a unique name");
        }
        if (this.isLastLink()) {
            this.nextHandler = Optional.of(newLink);
        } else {
            this.nextHandler.get().dispatchNewLink(newLink);
        }
    }

    @Override
    public final Optional<PersistenceAdapter> deleteLink(final String deletee) {
        Objects.requireNonNull(deletee, ErrorString.STRING_NULL);

        if (this.name.equals(deletee)) {
            return nextHandler;
        } else {
            this.nextHandler = nextHandler.orElseThrow(() -> new IllegalStateException("Persistence adapter not found"))
                    .deleteLink(deletee);
            return Optional.of(this);
        }
    }

    private PersistenceMapper filterMapper(final SingleRequest request) {
        Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL);
        return mapper.stream().filter(mapper -> mapper.canHandleRequest(request.getDesiredHandler()))
                .collect(Collectors.toList()).get(0);
    }

    private PersistenceMapper filterMapper(final BiRequest request) {
        Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL);
        return mapper.stream().filter(mapper -> mapper.canHandleRequest(request.getDesiredHandler()))
                .collect(Collectors.toList()).get(0);
    }

    private PersistenceMapper filterMapper(final QueryRequest request) {
        Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL);
        return mapper.stream().filter(mapper -> mapper.canHandleRequest(request.getQueryType())
                && mapper.canHandleRequest(request.getQueryType())).collect(Collectors.toList()).get(0);
    }

    @Override
    public final void dispatchCreateRequest(final SingleRequest request) {
        Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL);
        if (canHandleRequest(request.getDesiredHandler())) {
            filterMapper(request).dispatchCreateRequest(request);
            return;
        }
        nextHandler.orElseThrow(
                    () -> new IllegalStateException(NO_RESOLUTION + request.getDesiredHandler()))
        .dispatchCreateRequest(request);
    }

    @Override
    public final FragmentedSet dispatchReadRequest(final QueryRequest request) {
        Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL);
        return this.canHandleRequest(request.getQueryType()) && this.canHandleRequest(request.getQueryType())
                ? filterMapper(request).dispatchReadRequest(request)
                : nextHandler
                        .orElseThrow(
                                () -> new IllegalStateException(NO_RESOLUTION + request.getQueryType()))
                        .dispatchReadRequest(request);
    }

    @Override
    public final void dispatchUpdateRequest(final BiRequest biRequest) {
        Objects.requireNonNull(biRequest, A_REQUEST + ErrorString.CUSTOM_NULL);
        if (canHandleRequest(biRequest.getDesiredHandler())) {
            filterMapper(biRequest).dispatchUpdateRequest(biRequest);
            return;
        }
        nextHandler.orElseThrow(
                    () -> new IllegalStateException(NO_RESOLUTION + biRequest.getDesiredHandler()))
        .dispatchUpdateRequest(biRequest);
    }

    @Override
    public final void dispatchDeleteRequest(final SingleRequest request) {
        Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL);
        if (canHandleRequest(request.getDesiredHandler())) {
            filterMapper(request).dispatchDeleteRequest(request);
            return;
        }
        nextHandler.orElseThrow(
                    () -> new IllegalStateException(NO_RESOLUTION + request.getDesiredHandler()))
        .dispatchDeleteRequest(request);
    }

    @Override
    public final void addPersistenceMapper(final String persistenceAdapter, final PersistenceMapper mapper) {
        Objects.requireNonNull(persistenceAdapter, "A persistence adapter" + ErrorString.CUSTOM_NULL);
        Objects.requireNonNull(mapper, "A persistence mapper" + ErrorString.CUSTOM_NULL);
        if (this.name.equals(persistenceAdapter)) {
            this.mapper.add(mapper);
        } else {
            nextHandler.orElseThrow(
                    () -> new IllegalStateException("No persistence adapter found to deliver the mapper"))
                        .addPersistenceMapper(persistenceAdapter, mapper);
        }
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractPersistenceAdapter other = (AbstractPersistenceAdapter) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
