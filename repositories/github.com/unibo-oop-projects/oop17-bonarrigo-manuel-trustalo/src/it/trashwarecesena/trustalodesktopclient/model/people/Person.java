package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

import java.util.Optional;

/**
 * Person is the base interface for person-related functionalities, gliding over
 * what type of <i>real</i> person is being manipulated.
 * <p>
 * A person, in the domain model, is an entity able to pursue device requests to
 * the Trashware project.
 * <p>
 * This interface is part of the {@link Identifiable} family.
 *
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "People")
public interface Person extends Identifiable {

    /**
     * Retrieves the name associated to this Person.
     *
     * @return a {@link String} containing such a name
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

    /**
     * Retrieve the category this Person is associated to.
     *
     * @return a {@link PersonCategory} holding all the informations about the
     *         category of the Person
     */
    @InterfaceMethodToSchemaField(returnType = PersonCategory.class, schemaField = "Category")
    PersonCategory getCategory();

    /**
     * Retrieves the code assigned by the laws of the Italian state to every person,
     * it being both a physical or juridical person. Since the people interacting
     * with the Trashware project are not merely Italian, this code can occasionally
     * not be granted, whence the optionality.
     *
     * @return an {@link Optional}<{@link String}> containing such a code.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "FiscalCode")
    Optional<String> getFiscalCode();

    /**
     * Retrieves any unrelated out-of-the-schema information about this person.
     *
     * @return an {@link Optional}<{@link String}> containing the informations.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Annotations")
    Optional<String> getAnnotations();

}
