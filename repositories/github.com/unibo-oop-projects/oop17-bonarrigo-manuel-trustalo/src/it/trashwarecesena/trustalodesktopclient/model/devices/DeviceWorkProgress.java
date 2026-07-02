package it.trashwarecesena.trustalodesktopclient.model.devices;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Express the refinement progress of a
 * {@link it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice
 * RefinedDevice}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "DeviceStates")
public interface DeviceWorkProgress {

    /**
     * Retrieve the name of the state of the progress.
     * 
     * @return a {@link String} containing the progress name.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

    /**
     * Retrieve the description of the state of the progress.
     * 
     * @return an {@link Optional} containing the informations, or Optional.empty if
     *         they are unknown.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Description")
    Optional<String> getDescription();

}
