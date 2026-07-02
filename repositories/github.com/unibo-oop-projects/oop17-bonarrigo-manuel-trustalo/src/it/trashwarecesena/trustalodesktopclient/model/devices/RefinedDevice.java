package it.trashwarecesena.trustalodesktopclient.model.devices;

import java.sql.Date;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * A device which has received any kind of modification by the Trashware
 * project, spacing from the replacement of a broken hardware piece to the
 * installation of a more suitable driver.
 * <p>
 * It is <b>mandatory</b> to understand that being the RefinedDevice class
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
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DevicesWithID")
public interface RefinedDevice extends Identifiable {

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
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "Available")
    boolean isAvailable();

    /**
     * Retrieve the informations of the original {@link GenericDevice} before any
     * {@link TrashwareWorker} manufacturing.
     * 
     * @return a {@link GenericDevice} with such informations.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "DeviceModel")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the {@link TrashwareWorker} assigned identifier to this
     * RefinedDevice.
     * 
     * @return an {@link Integer} representing the internal categorization of the
     *         RefinedDevice
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "CategoryDeviceID")
    Integer getCategoryDeviceId();

    /**
     * Retrieve the state of the work upon this device.
     * 
     * @return a {@link DeviceWorkProgress} containing the state of the work.
     */
    @InterfaceMethodToSchemaField(returnType = DeviceWorkProgress.class, schemaField = "CurrentState")
    DeviceWorkProgress getWorkProgress();

    /**
     * Retrieve the last TrashwareWorker to alter the state of this RefinedDevice.
     * 
     * @return the {@link TrashwareWorker} which operated the last upon this
     *         RefinedDevice
     */

    @InterfaceMethodToSchemaField(returnType = TrashwareWorker.class, schemaField = "LastUpdateWorker")
    TrashwareWorker getLastChangeCommitter();

    /**
     * Retrieve the moment this RefinedDevice was altered the last time.
     * 
     * @return a {@link Date} expressing the moment this RefinedDevice was altered
     *         the last time.
     */
    @InterfaceMethodToSchemaField(returnType = Date.class, schemaField = "LastUpdateDate")
    Date getLastChangeDate();

    /**
     * Retrieve any off-the-schema information available about this RefinedDevice.
     * 
     * @return a {@link String} with any kind of information present about this
     *         RefinedDevice, or Optional.empty, if there is none.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Annotations")
    Optional<String> getAnnotations();

}
