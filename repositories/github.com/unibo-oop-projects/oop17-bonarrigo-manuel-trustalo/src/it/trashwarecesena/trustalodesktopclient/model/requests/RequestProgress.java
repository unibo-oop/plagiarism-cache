package it.trashwarecesena.trustalodesktopclient.model.requests;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Express the accomplishment progress of the request.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "RequestStates")
public interface RequestProgress {

    /**
     * Retrieve the name of the state of the progress.
     * 
     * @return a String expressing the state of the progress.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

    /**
     * Retrieve the description of the state, if any.
     * 
     * @return an {@link Optional} containing a String which express the description
     *         of the state, or Optional.empty if there is none.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Description")
    Optional<String> getDescription();

}
