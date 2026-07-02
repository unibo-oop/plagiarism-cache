package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents an hard disk drive device.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "HDDModels")
public interface HardDiskDrive {

    /**
     * Retrieve the {@link GenericDevice} this HardDiskDrive refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "ID")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the capacity of this HardDiskDrive.
     * 
     * @return an Integer expressing how much data this disk is able of handling.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Capacity")
    Integer getCapacity();

    /**
     * Retrieve the unit of measurement of the disk capacity.
     * 
     * @return a {@link DigitalInformationUnit} expressing how the
     *         {@code getCapacity()} result should be interpreted.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "CapacityUnit")
    DigitalInformationUnit getCapacityUnit();

}
