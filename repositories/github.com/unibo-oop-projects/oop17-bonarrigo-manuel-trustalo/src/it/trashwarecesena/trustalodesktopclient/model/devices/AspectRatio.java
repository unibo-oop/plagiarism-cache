package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * The aspect ratio describes the proportional relationship between a width and
 * a height. It is expressed as two numbers separated by a colon.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "AspectRatios")
public interface AspectRatio {

    /**
     * Retrieve the proportion between the size of the dimensions this AspectRatio
     * was built upon.
     * 
     * @return a String representing such a proportion, in the format {@code x:y}.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "RatioFactor")
    String getScreenRatio();

}
