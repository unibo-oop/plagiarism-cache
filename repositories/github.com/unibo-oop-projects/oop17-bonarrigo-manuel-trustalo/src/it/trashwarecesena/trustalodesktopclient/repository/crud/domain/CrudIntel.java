package it.trashwarecesena.trustalodesktopclient.repository.crud.domain;

import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.intel.PersistentIntelProcessor;

/**
 * A marker interface constituted by the extension of all the interfaces which
 * semantically compose the Intel domain.
 * <p>
 * The implementor of this class <i> should </i> have direct access to the
 * persistence media, as the returning values are expected to be the result of a
 * data query.
 * 
 * @author Manuel Bonarrigo
 */

public interface CrudIntel extends PersistentIntelProcessor {

}
