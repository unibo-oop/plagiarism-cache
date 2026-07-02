package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * This interface describes the category of any technology used to contact a
 * {@link Person}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "ContactCategories")
public interface ContactCategory extends Comparable<ContactCategory> {

    /**
     * Retrieve the name of such a technology category.
     * 
     * @return a {@link String} with the name of the technology
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

}
