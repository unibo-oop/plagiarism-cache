package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * This interface defines the operation required to obtain the status of a
 * {@link PhysicalPerson} working for the Trashware project.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "Workers", identifierName = "Person")
public interface TrashwareWorker extends Comparable<TrashwareWorker> {

    /**
     * Retrieve the {@link PhysicalPerson} this instance actually express.
     * 
     * @return the PhysicalPerson associated to be working for Trashware project.
     */
    @InterfaceMethodToSchemaField(returnType = PhysicalPerson.class, schemaField = "Person")
    PhysicalPerson getPerson();

    /**
     * Retrieve the category which express what kind of TrashwareWorker is being
     * dealt with.
     * 
     * @return a {@link TrashwareWorkerCategory} containing all the informations
     *         about this TrashwareWorker category
     */
    @InterfaceMethodToSchemaField(returnType = TrashwareWorkerCategory.class, schemaField = "Category")
    TrashwareWorkerCategory getCategory();

    /**
     * Tells if the TrashwareWorker is currently enrolled at the Trashware project.
     * 
     * @return true if the worker is enrolled, false otherwise
     */
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "CurrentlyWorking")
    boolean isCurrentlyWorking();

}
