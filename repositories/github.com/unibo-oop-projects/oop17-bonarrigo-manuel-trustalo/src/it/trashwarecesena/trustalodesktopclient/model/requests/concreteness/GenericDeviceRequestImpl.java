package it.trashwarecesena.trustalodesktopclient.model.requests.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.requests.GenericDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link GenericDeviceRequest} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class GenericDeviceRequestImpl implements GenericDeviceRequest {

    private final RequestDetail request;
    private final GenericDevice device;
    private final Integer quantity;

    /**
     * Constructs a GenericDeviceRequest upon the given parameters.
     * 
     * @param request
     *            a {@link RequestDetail} to be associated with a {@link GenericDevice}
     * @param device
     *            the {@link GenericDevice} to be associated to the Request
     * @param quantity
     *            an Integer representing the quantity of devices requested
     * @throws NullPointerException
     *             if any of the arguments is found to be {@code null}
     * @throws IllegalArgumentException
     *             if any of the arguments is found to be non positive
     */
    public GenericDeviceRequestImpl(final RequestDetail request, final GenericDevice device, final Integer quantity) {
        this.request = Objects.requireNonNull(request, "The request" + ErrorString.CUSTOM_NULL);
        this.device = Objects.requireNonNull(device, "The GenericDevice" + ErrorString.CUSTOM_NULL);
        this.quantity = ExtendedObjects.requireNonNegative(Objects.requireNonNull(quantity),
                "The quantity" + ErrorString.NO_NEGATIVE);
    }

    @Override
    public RequestDetail getRequestDetail() {
        return this.request;
    }

    @Override
    public GenericDevice getDeviceRequested() {
        return this.device;
    }

    @Override
    public Integer getQuantityRequested() {
        return this.quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((device == null) ? 0 : device.hashCode());
        result = prime * result + ((request == null) ? 0 : request.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericDeviceRequestImpl other = (GenericDeviceRequestImpl) obj;
        if (quantity == null) {
            if (other.quantity != null) {
                return false;
            }
        } else if (!quantity.equals(other.quantity)) {
            return false;
        }
        if (device == null) {
            if (other.device != null) {
                return false;
            }
        } else if (!device.equals(other.device)) {
            return false;
        }
        if (request == null) {
            if (other.request != null) {
                return false;
            }
        } else if (!request.equals(other.request)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GenericDeviceRequestImpl [request=" + request + ", refined=" + device + ", quantity=" + quantity + "]";
    }
}
