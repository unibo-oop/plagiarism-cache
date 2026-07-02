package it.trashwarecesena.trustalodesktopclient.repository.mapper.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.RequestsDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.AbstractPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A
 * {@link it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper
 * PersistentMapper} ought to manage the requests subdomain.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RequestsPersistenceMapper extends AbstractPersistenceMapper {

    private static final String A_REQUEST = "A request";

    private final RequestsDomain requests;

    /**
     * Constructs a RequestsPersistenceMapper over the given {@link RequestsDomain}.
     * 
     * @param requests
     *            the RequestsDomain to be wrapped by this class.
     */
    public RequestsPersistenceMapper(final RequestsDomain requests) {
        super(Arrays.stream(requests.getClass().getDeclaredMethods())
                    .filter(method -> (method.getName().startsWith("create") 
                            || method.getName().startsWith("update") 
                            || method.getName().startsWith("delete")))
                    .flatMap(method -> Arrays.stream(method.getParameterTypes()))
                    .collect(Collectors.toSet()));
        this.requests = requests;
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        requests.dispatchCreateRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        return requests.dispatchReadRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        requests.dispatchUpdateRequest(Objects.requireNonNull(biRequest, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        requests.dispatchDeleteRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

}
