package it.trashwarecesena.trustalodesktopclient.model.devices;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * A GenericDevice is any kind of device or part of a device which can be
 * deliberately stocked without any further manufacturing except for picking it
 * up from a donor or extracting it from another broken device.
 * <p>
 * It is <b>mandatory</b> to understand that being the GenericDevice interface
 * part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "DeviceModels")
public interface GenericDevice extends Identifiable {

    /**
     * Retrieve the category of a device.
     * 
     * @return a {@link DeviceCategory} containing all the informations about his
     *         device category.
     */
    @InterfaceMethodToSchemaField(returnType = DeviceCategory.class, schemaField = "Category")
    DeviceCategory getDeviceCategory();

    /**
     * Tells if this device is available to be donated.
     * 
     * @return true if it can be donated, false otherwise.
     */
    boolean isAvailable();

    /**
     * Retrieve the available amount of this GenericDevice in the inventory.
     *
     * @return an {@link Integer} representing the amount of available devices.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "DevicesWithoutID")
    Integer getNumberOfAvailableDevices();

    /**
     * Retrieve the formal description of this GenericDevice, if any.
     * 
     * @return a {@link Optional} containing the formal description of this device,
     *         or Optional.empty, if there is none.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Description")
    Optional<String> getDeviceDescription();

    /**
     * Retrieve the serial number imposed by the producer of the device, if any.
     * 
     * @return a {@link Optional} containing the original serial number of this
     *         device, or Optional.empty, if there is none.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "VendorModelNumber")
    Optional<String> getVendorModelIdentifier();

    /**
     * Retrieve the manufacturer which produced this GenericDevice, if is known.
     * 
     * @return a {@link Optional} containing the {@link Vendor} of this device, or
     *         Optional.empty, if it is unknown.
     */
    @InterfaceMethodToSchemaField(returnType = Vendor.class, schemaField = "Vendor")
    Optional<Vendor> getVendor();

}
