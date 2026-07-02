package it.trashwarecesena.trustalodesktopclient.model.people;

import java.sql.Date;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Extends the functionality of a {@link Person} to those of a physical one,
 * namely an human being.
 * <p>
 * This interface is part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "People")
public interface PhysicalPerson extends Person, Comparable<PhysicalPerson> {

    /**
     * Retrieve the date of birth of this person.
     * 
     * @return an {@link Optional}<{@link Date}> containing the birth date, or
     *         Optional.empty if this information is not available
     */
    @InterfaceMethodToSchemaField(returnType = Date.class, schemaField = "BirthDate")
    Optional<Date> getBirthDate();

    /**
     * Retrieve a location birth, as much more precise as the person was comfortable
     * to disclose.
     * 
     * @return an {@link Optional}<{@link String}> containing the birth location, or
     *         Optional.empty if this information is not available
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "BirthLocation")
    Optional<String> getBirthLocation();

}
