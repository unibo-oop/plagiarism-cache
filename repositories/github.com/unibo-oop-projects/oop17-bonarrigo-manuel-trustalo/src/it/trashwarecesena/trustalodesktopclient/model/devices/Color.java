package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * A color in the RGB specter, expressed as a six-digits hexadecimal value.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "Colors")
public interface Color {

    /**
     * Retrieve the hexadecimal value.
     * 
     * @return a String expressing the hexadecimal value. The String will always
     *         have six digits, and the characters will always be in lower case.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "HexCode")
    String getColor();

}
