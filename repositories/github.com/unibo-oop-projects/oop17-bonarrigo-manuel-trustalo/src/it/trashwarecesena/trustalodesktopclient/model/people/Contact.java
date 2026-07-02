package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * This interface describes how to get in contact "in real life" with another
 * {@link Person}.
 *
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "Contacts")
public interface Contact extends Comparable<Contact> {

    /**
     * Retrieve the {@link ContactCategory} of such a Contact.
     *
     * @return a ContactType containing the informations about the contact type
     */
    @InterfaceMethodToSchemaField(returnType = ContactCategory.class, schemaField = "Category")
    ContactCategory getCategory();

    /**
     * Retrieve the actual way to communicate to the {@link Person} owning this
     * Contact.
     *
     * @return a {@link String} with the most sensible value to contact anyone,
     *         related to the declared {@link ContactCategory}
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Value")
    String getValue();

    /**
     * The {@link Person} owning such a contact.
     *
     * @return a Person containing all the known informations of the owner
     *
     */
    @InterfaceMethodToSchemaField(returnType = Person.class, schemaField = "Person")
   Person getOwner();

}
