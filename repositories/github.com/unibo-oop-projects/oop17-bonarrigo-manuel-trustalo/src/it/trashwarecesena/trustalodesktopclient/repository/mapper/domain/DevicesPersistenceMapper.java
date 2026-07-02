package it.trashwarecesena.trustalodesktopclient.repository.mapper.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.DeviceDomain;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.FragmentedSet;
import it.trashwarecesena.trustalodesktopclient.repository.mapper.AbstractPersistenceMapper;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A
 * {@link it.trashwarecesena.trustalodesktopclient.repository.mapper.PersistenceMapper
 * PersistentMapper} ought to manage the devices subdomain.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class DevicesPersistenceMapper extends AbstractPersistenceMapper {
    private static final String A_REQUEST = "A request";

    private final DeviceDomain devices;

    /**
     * Constructs a DevicesPersistenceMapper over the given {@link DeviceDomain}.
     * 
     * @param devices
     *            the DeviceDomain to be wrapped by this class.
     */
    public DevicesPersistenceMapper(final DeviceDomain devices) {
        super(Arrays.stream(devices.getClass().getDeclaredMethods())
                    .filter(method -> (method.getName().startsWith("create") 
                            || method.getName().startsWith("update") 
                            || method.getName().startsWith("delete")))
                    .flatMap(method -> Arrays.stream(method.getParameterTypes()))
                    .collect(Collectors.toSet()));
        this.devices = devices;
    }

    @Override
    public void dispatchCreateRequest(final SingleRequest request) {
        devices.dispatchCreateRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public FragmentedSet dispatchReadRequest(final QueryRequest request) {
        return devices.dispatchReadRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchUpdateRequest(final BiRequest biRequest) {
        devices.dispatchUpdateRequest(Objects.requireNonNull(biRequest, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

    @Override
    public void dispatchDeleteRequest(final SingleRequest request) {
        devices.dispatchDeleteRequest(Objects.requireNonNull(request, A_REQUEST + ErrorString.CUSTOM_NULL));
    }

}
