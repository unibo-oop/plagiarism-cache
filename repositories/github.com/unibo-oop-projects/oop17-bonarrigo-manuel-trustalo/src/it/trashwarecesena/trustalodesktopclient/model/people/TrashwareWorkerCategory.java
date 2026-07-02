package it.trashwarecesena.trustalodesktopclient.model.people;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * This interface describes the category assignable to a {@link TrashwareWorker}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "WorkerCategories")
public interface TrashwareWorkerCategory extends Comparable<TrashwareWorkerCategory> {

    /**
     * Retrieve the name of this category, an information expressing what kind of job the worker does.
     * 
     * @return a {@link String} with the name of the category.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

}
