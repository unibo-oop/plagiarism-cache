package it.trashwarecesena.trustalodesktopclient.model.requests.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.requests.RefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link RefinedDeviceRequest} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RefinedRequestImpl implements RefinedDeviceRequest {

    private final RefinedDevice refined;
    private final RequestDetail request;

    /**
     * Constructs a RefinedDevice upon the given parameters.
     * 
     * @param request
     *            a {@link RequestDetail} to be associated with a {@link RefinedDevice}
     * @param refined
     *            the {@link RefinedDevice} to be associated to the Request
     * @throws NullPointerException
     *             if any of the arguments is found to be {@code null}
     * @throws IllegalArgumentException
     *             if any of the arguments is found to be non positive
     */
    public RefinedRequestImpl(final RefinedDevice refined, final RequestDetail request) {
        this.refined = Objects.requireNonNull(refined, "The RefinedDevice" + ErrorString.CUSTOM_NULL);
        this.request = Objects.requireNonNull(request, "The Request" + ErrorString.CUSTOM_NULL);
    }

    @Override
    public RefinedDevice getDeviceRequested() {
        return this.refined;
    }

    @Override
    public RequestDetail getRequestDetail() {
        return this.request;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((refined == null) ? 0 : refined.hashCode());
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
        final RefinedRequestImpl other = (RefinedRequestImpl) obj;
        if (refined == null) {
            if (other.refined != null) {
                return false;
            }
        } else if (!refined.equals(other.refined)) {
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
        return "RefinedRequestimpl [refined=" + refined + ", request=" + request + "]";
    }

}
