package it.trashwarecesena.trustalodesktopclient.repository.query;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Implements the behaviour expected from a SingleRequest, letting subclasses
 * narrowing the type of the request.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractSingleRequest implements SingleRequest {
    private final Object request;

    /**
     * Initialize the {@link SingleRequest} to have the passed in Object as
     * parameter.
     * 
     * @param fragmented
     *            a non-nullable Object to be the payload of the request.
     */
    public AbstractSingleRequest(final Object fragmented) {
        Objects.requireNonNull(fragmented, "The Object carried by a SingleRequest" + ErrorString.NO_NULL);
        this.request = fragmented;
    }

    @Override
    public final Class<?> getDesiredHandler() {
        return request.getClass();
    }

    @Override
    public final Object getPayload() {
        return this.request;
    }

}
