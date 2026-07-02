package it.trashwarecesena.trustalodesktopclient.model.otherdevices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Express the instruction set architecture used by a processor.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "InstructionSets", identifierName = "Name")
public interface InstructionSet {

    /**
     * Retrieve the name of the instruction set architecture.
     * 
     * @return a String containing the name of the instruction set architecture.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

}
