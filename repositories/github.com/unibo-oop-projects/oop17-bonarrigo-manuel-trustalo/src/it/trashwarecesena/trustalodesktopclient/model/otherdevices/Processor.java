package it.trashwarecesena.trustalodesktopclient.model.otherdevices;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents the few informations necessary to the Trashware Project of a processor.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "Processors", identifierName = "p:DeviceModel")
public interface Processor {

    /**
     * Retrieve the {@link GenericDevice} this Processor refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "p:DeviceModel")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the clock speed of this processor.
     * 
     * @return a Float representing the clock speed of the processor.
     */
    @InterfaceMethodToSchemaField(returnType = Float.class, schemaField = "p:Frequency")
    Float getFrequency();

    /**
     * Retrieve the unit of measure to read the clock speed with.
     * 
     * @return a {@link FrequencyUnit} representing the unit of measure of the clock
     *         speed.
     */
    @InterfaceMethodToSchemaField(returnType = FrequencyUnit.class, schemaField = "p:FrequencyUnit")
    FrequencyUnit getFrequencyUnit();

    /**
     * Retrieve the instruction set architecture this processor was built on.
     * 
     * @return an {@link InstructionSet} containing the instruction set architecture
     *         information about this processor.
     */
    @InterfaceMethodToSchemaField(returnType = InstructionSet.class, schemaField = "p:InstructionSet")
    InstructionSet getInstructionSet();

    /**
     * Retrieve the amount of L3 cache this processor is given to, if any.
     * 
     * @return an Optional containing an Integer representing the amount of L3 cache
     *         amount available to this processor, or Optional.Empty if it is not
     *         present.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "p:L3CacheAmount")
    Optional<Integer> getL3CacheAmount();

    /**
     * Retrieve the unit of measure of the amount of the L3 cache, if it is present.
     * 
     * @return an Optional containing a {@link DigitalInformationUnit} to interpret
     *         the amount of L3 cache amount with, or Optional.empty if no L3 cache
     *         is present.
     */
    @InterfaceMethodToSchemaField(returnType = DigitalInformationUnit.class, schemaField = "p:L3CacheInformationUnit")
    Optional<DigitalInformationUnit> getL3CacheInformationUnit();
}
