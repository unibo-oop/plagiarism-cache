package it.trashwarecesena.trustalodesktopclient.model.immutable;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a certified source of information related to the Intel processors.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "Processors", identifierName = "")
public interface ImmutableIntelProcessor {

    /**
     * Retrieve the Intel identifier for the model.
     * 
     * @return a String representing the identifier given by Intel to this
     *         processor.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "ProductId")
    Optional<Integer> getProductIdentifier();

    /**
     * Retrieve the Intel processor number for the model.
     * 
     * @return a String representing the alphanumeric name given by Intel to this
     *         processor.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "ProcessorNumber")
    Optional<String> getProcessorNumber();

    /**
     * Retrieve the clock speed of this processor.
     * 
     * @return an Integer representing the clock speed of the Intel processor.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "ClockSpeedMhz")
    Optional<Integer> getFrequencyInMHz();

    /**
     * Retrieve the instruction set architecture this processor was built on.
     * 
     * @return an {@link String} containing the instruction set architecture
     *         information about this processor.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "InstructionSet")
    Optional<String> getInstructionSet();

    /**
     * Retrieve the type of cache this processor is given to, if any.
     * 
     * @return a Optional containing a String representing the type of cache
     *         available on this processor, or Optional.Empty if it is not
     *         present.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "CacheType")
    Optional<String> getCacheType();

    /**
     * Retrieve the amount of cache this processor is given to, if any.
     * 
     * @return an Optional containing an Integer representing the amount of cache
     *         amount available to this processor expressed in KB, or Optional.Empty if it is not
     *         present.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "CacheKB")
    Optional<Integer> getCacheAmountInKb();

    /**
     * Retrieve an unstructured form of information regarding this processor cache,
     * if it is present.
     * 
     * @return an Optional containing a {@link String} to be interpreted to gain
     *         more insight about the cache of this processor, or Optional.empty if
     *         no information is present.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Cache")
    Optional<String> getCacheUnstructuredInformation();

    /**
     * Retrieve the complete name as promoted by Intel of the processor.
     * 
     * @return a String containing the whole name of the processor.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "ProductName")
    Optional<String> getCompleteProductName();

}
