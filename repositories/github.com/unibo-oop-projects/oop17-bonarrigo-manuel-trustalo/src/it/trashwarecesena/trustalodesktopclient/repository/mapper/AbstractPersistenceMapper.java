package it.trashwarecesena.trustalodesktopclient.repository.mapper;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Skeletal implementation of a {@link PersistenceMapper} which lacks a proper
 * domain to operate onto.
 * <p>
 * This needs to be provided by concrete implementors.
 * 
 * @author Manuel Bonarrigo
 */
public abstract class AbstractPersistenceMapper implements PersistenceMapper {

    private final Set<Class<?>> availableHandlers;

    /**
     * Constructs a PersistenceMapper able of handling all these requests.
     * 
     * @param availableHandlers
     *            a Set of Class<?> hinting at the class capabilities.
     */
    public AbstractPersistenceMapper(final Set<Class<?>> availableHandlers) {
        super();
        Objects.requireNonNull(availableHandlers, "The set of class" + ErrorString.CUSTOM_NULL);
        this.availableHandlers = availableHandlers;
    }

    @Override
    public final boolean canHandleRequest(final Class<?> request) {
        Objects.requireNonNull(request, "The class" + ErrorString.CUSTOM_NULL);
        return availableHandlers.stream().anyMatch(klass -> klass.isAssignableFrom(request));
    }

    @Override
    public final Set<Class<?>> getHandlersSet() {
        return new HashSet<>(availableHandlers);
    }

}
