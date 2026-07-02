package it.trashwarecesena.trustalodesktopclient.model.otherdevices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents the processor's clock speed unit of measure.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "FrequencyUnits", identifierName = "Name")
public interface FrequencyUnit {

    /**
     * Retrieve the name of the processor clock speed unit of measure.
     * 
     * @return a String representing the name of the unit of measure.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

}
