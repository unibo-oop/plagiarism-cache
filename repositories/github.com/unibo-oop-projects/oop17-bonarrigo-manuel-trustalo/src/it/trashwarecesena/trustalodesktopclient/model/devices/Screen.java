package it.trashwarecesena.trustalodesktopclient.model.devices;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a screen and its features.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "ScreenModels", identifierName = "DeviceModel")
public interface Screen {

    /**
     * Retrieve the {@link GenericDevice} this Screen refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "DeviceModel")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the category of the {@link Screen}.
     * 
     * @return a {@link ScreenCategory} which expresses the technology used by the
     *         screen to display images.
     */
    @InterfaceMethodToSchemaField(returnType = ScreenCategory.class, schemaField = "Tecnology")
    ScreenCategory getCategory();

    /**
     * Retrieve the maximum resolution of the {@link Screen}.
     * 
     * @return a {@link ScreenResolution} pointing which the maximum resolution of
     *         the Screen is.
     */
    @InterfaceMethodToSchemaField(returnType = ScreenResolution.class, schemaField = "MaximumResolution")
    ScreenResolution getMaximumResolution();

    /**
     * Retrieve the {@link Color} of the Screen, if any.
     * 
     * @return an {@link Optional} containing the Color of the Screen, or
     *         Optional.empty if this information is not available.
     */
    @InterfaceMethodToSchemaField(returnType = Color.class, schemaField = "Color")
    Optional<Color> getColor();

    /**
     * Tells if the Screen has a frame.
     * 
     * @return true if the Screen has a frame, false otherwise.
     */
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "WithFrame")
    boolean isWithFrame();

    /**
     * Tells if the Screen has a VGA socket.
     * 
     * @return true if the Screen has a VGA socket, false otherwise.
     */
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "HasVGASocket")
    boolean isWithVgaSocket();

    /**
     * Tells if the Screen has a DVI socket.
     * 
     * @return true if the Screen has a DVI socket, false otherwise.
     */
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "HasDVISocket")
    boolean isWithDviSocket();

    /**
     * Tells if the Screen has audio speakers.
     * 
     * @return true if the Screen has audio speakers, false otherwise.
     */
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "HasAudioSpeakers")
    boolean isWithAudioSpeakers();
}
