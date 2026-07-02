package it.trashwarecesena.trustalodesktopclient.repository.query;

/**
 * A single parameter wrapper of a request to the persistence module.
 * 
 * @author Manuel Bonarrigo
 */

public interface SingleRequest {

    /**
     * Retrieve which {@link Class} this SingleRequest is expected to operate onto.
     * @return A Class which will never be null.
     */
    Class<?> getDesiredHandler();

    /**
     * Retrieve the payload carried by this SingleRequest.
     * 
     * @return an Object, which will never be null, expressing whatever this request
     *         will be implemented to represent
     */
    Object getPayload();

}
