package it.trashwarecesena.trustalodesktopclient.repository.query;

/**
 * An extension of {@link SingleRequest} which needs an additional parameter to
 * be satisfied.
 * 
 * @author Manuel Bonarrigo
 */

public interface BiRequest extends SingleRequest {

    /**
     * Retrieve the additional payload this request carries.
     * 
     * @return an Object which will never be null.
     */
    Object getSecondPayload();

}
