package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Extends the functionality of a {@link Person} to those of a juridical one,
 * namely an organization, or a business, by the means of creating the type to
 * be referred to.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "People")
public interface JuridicalPerson extends Person, Comparable<JuridicalPerson> {

}
