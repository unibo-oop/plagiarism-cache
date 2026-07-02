package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * This interface describes the category assignable to a
 * {@link JuridicalPerson}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "PersonCategories")
public interface PersonCategory extends Comparable<PersonCategory> {

    /**
     * Retrieve the name of such a category.
     * 
     * @return a {@link String} with the name of the category
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

}
