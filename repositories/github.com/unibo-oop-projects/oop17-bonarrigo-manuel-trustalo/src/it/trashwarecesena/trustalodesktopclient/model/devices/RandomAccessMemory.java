package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a random access memory device.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "RAMModels", identifierName = "DeviceModel")
public interface RandomAccessMemory {

    /**
     * Retrieve the {@link GenericDevice} this RandomAccessMemory refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "DeviceModel")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the capacity of this RandomAccessMemory.
     * 
     * @return an Integer expressing how much data this disk is able of handling.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Capacity")
    Integer getCapacity();

    /**
     * Retrieve the unit of measurement of the memory capacity.
     * 
     * @return a {@link DigitalInformationUnit} expressing how the
     *         {@code getCapacity()} result should be interpreted.
     */
    @InterfaceMethodToSchemaField(returnType = DigitalInformationUnit.class, schemaField = "CapacityUnit")
    DigitalInformationUnit getCapacityUnit();

}
