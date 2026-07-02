package it.trashwarecesena.trustalodesktopclient.repository;

import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.CrudDevices;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.CrudIntel;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.CrudPeople;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.CrudProcessors;
import it.trashwarecesena.trustalodesktopclient.repository.crud.domain.CrudRequests;

/**
 * The interface for the client-based boundary of the persistence module.
 * <p>
 * Through the usage of this interface any client can have access to the
 * capability of making any domain related objects persistent in some way,
 * which the client should not care about, since it is pursued automatically by
 * the module.
 * 
 * @author Manuel Bonarrigo
 *
 */

public interface Persistence extends CrudPeople, CrudDevices, CrudRequests, CrudIntel, CrudProcessors {

}
