package it.trashwarecesena.trustalodesktopclient.repository.mapper.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.IntelImmutableDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.AbstractPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A
 * {@link it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper
 * PersistentMapper} ought to manage the ImmutableIntel subdomain.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ImmutableIntelPersistenceMapper extends AbstractPersistenceMapper {

    private static final String A_REQUEST = "A request";
    private final IntelImmutableDomain domain;

    /**
     * Constructs a ImmutableIntelPersistenceMapper over the given {@link IntelImmutableDomain}.
     * 
     * @param domain
     *            the IntelImmutableDomain to be wrapped by this class.
     */
    public ImmutableIntelPersistenceMapper(final IntelImmutableDomain domain) {
        super(Arrays.stream(domain.getClass().getDeclaredMethods())
                .filter(method -> (method.getName().startsWith("create") 
                        || method.getName().startsWith("update") 
                        || method.getName().startsWith("delete")))
                .flatMap(method -> Arrays.stream(method.getParameterTypes()))
                .collect(Collectors.toSet()));
        this.domain = Objects.requireNonNull(domain, "An IntelImmutableDomain" + ErrorString.CUSTOM_NULL);
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        domain.dispatchCreateRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        return domain.dispatchReadRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        domain.dispatchUpdateRequest(Objects.requireNonNull(biRequest, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        domain.dispatchDeleteRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

}
