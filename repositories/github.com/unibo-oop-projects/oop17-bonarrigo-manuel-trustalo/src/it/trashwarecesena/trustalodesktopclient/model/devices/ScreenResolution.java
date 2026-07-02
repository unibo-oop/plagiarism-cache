package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents the resolution of a {@link Screen}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "ScreenResolutions")
public interface ScreenResolution extends Identifiable {

    /**
     * Retrieve the width of the Screen.
     * 
     * @return a non-negative Integer which represents the width of the Screen.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Width")
    Integer getWidth();

    /**
     * Retrieve the height of the Screen.
     * 
     * @return a non-negative Integer which represents the height of the Screen.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Height")
    Integer getHeight();

    /**
     * Retrieve the ratio between the components of the Screen.
     * 
     * @return an
     *         {@link it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio
     *         ApectRatio} describing the ratio between the components of the
     *         Screen.
     */
    @InterfaceMethodToSchemaField(returnType = AspectRatio.class, schemaField = "AspectRatio")
    AspectRatio getAspectRatio();
}
