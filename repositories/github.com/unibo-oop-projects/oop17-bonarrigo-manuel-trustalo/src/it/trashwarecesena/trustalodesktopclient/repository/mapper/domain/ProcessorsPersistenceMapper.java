package it.trashwarecesena.trustalodesktopclient.repository.mapper.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.ProcessorsDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.AbstractPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A
 * {@link it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper
 * PersistentMapper} ought to manage the processors subdomain.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ProcessorsPersistenceMapper extends AbstractPersistenceMapper {

    private static final String A_REQUEST = "A request";

    private final ProcessorsDomain domain;

    /**
     * Constructs a ProcessorsPersistenceMapper over the given {@link ProcessorsDomain}.
     * 
     * @param domain
     *            the ProcessorsDomain to be wrapped by this class.
     */
    public ProcessorsPersistenceMapper(final ProcessorsDomain domain) {
        super(Arrays.stream(domain.getClass().getDeclaredMethods())
                    .filter(method -> (method.getName().startsWith("create") 
                            || method.getName().startsWith("update") 
                            || method.getName().startsWith("delete")))
                    .flatMap(method -> Arrays.stream(method.getParameterTypes()))
                    .collect(Collectors.toSet()));
        this.domain = domain;
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        this.domain.dispatchCreateRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        return this.domain.dispatchReadRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        this.domain.dispatchUpdateRequest(Objects.requireNonNull(biRequest, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        this.domain.dispatchDeleteRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

}
