package it.trashwarecesena.trustalodesktopclient.repository.mapper.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.PeopleDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.AbstractPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A
 * {@link it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper
 * PersistentMapper} ought to manage the people subdomain.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class PeoplePersistenceMapper extends AbstractPersistenceMapper {

    private static final String A_REQUEST = "A request";

    private final PeopleDomain people;

    /**
     * Constructs a PeoplePersistenceMapper over the given {@link PeopleDomain}.
     * 
     * @param people
     *            the PeopleDomain to be wrapped by this class.
     */
    public PeoplePersistenceMapper(final PeopleDomain people) {
        super(Arrays.stream(people.getClass().getDeclaredMethods())
                    .filter(method -> (method.getName().startsWith("create") 
                            || method.getName().startsWith("update") 
                            || method.getName().startsWith("delete")))
                    .flatMap(method -> Arrays.stream(method.getParameterTypes()))
                    .collect(Collectors.toSet()));
        this.people = people;
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        people.dispatchCreateRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        return people.dispatchReadRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        people.dispatchUpdateRequest(Objects.requireNonNull(biRequest, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        people.dispatchDeleteRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

}
