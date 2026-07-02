package it.trashwarecesena.trustalodesktopclient.repository.crud.domain;

import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentContact;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentContactCategory;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentJuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentJuridicalPersonCategory;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentPhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentTrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.repository.crud.entities.people.PersistentTrashwareWorkerCategory;

/**
 * A marker interface constituted by the extension of all the interfaces which
 * semantically compose the people domain.
 * <p>
 * The implementor of this class <i> should </i> have direct access to the
 * persistence media, as the returning values are expected to be the result of a
 * data query.
 * 
 * @author Manuel Bonarrigo
 */
public interface CrudPeople extends PersistentContact, PersistentContactCategory, PersistentJuridicalPerson,
        PersistentJuridicalPersonCategory, PersistentPhysicalPerson, PersistentTrashwareWorker,
        PersistentTrashwareWorkerCategory {

}
