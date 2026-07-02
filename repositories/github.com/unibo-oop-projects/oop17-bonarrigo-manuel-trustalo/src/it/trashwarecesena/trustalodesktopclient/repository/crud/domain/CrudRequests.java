package it.trashwarecesena.trustalodesktopclient.repository.crud.domain;

import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests.PersistentGenericDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests.PersistentRefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests.PersistentRequest;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests.PersistentRequestDetail;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.requests.PersistentRequestProgress;

/**
 * A marker interface constituted by the extension of all the interfaces which
 * semantically compose the Request domain.
 * <p>
 * The implementor of this class <i> should </i> have direct access to the
 * persistence media, as the returning values are expected to be the result of a
 * data query.
 * 
 * @author Manuel Bonarrigo
 */
public interface CrudRequests extends PersistentGenericDeviceRequest, PersistentRequest, PersistentRequestDetail,
        PersistentRequestProgress, PersistentRefinedDeviceRequest {

}
