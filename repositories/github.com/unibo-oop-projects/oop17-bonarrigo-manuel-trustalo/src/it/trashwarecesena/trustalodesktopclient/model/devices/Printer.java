package it.trashwarecesena.trustalodesktopclient.model.devices;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a printing device.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "PrinterModels", identifierName = "DeviceModel")
public interface Printer {

    /**
     * Retrieve the {@link GenericDevice} this Case refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "DeviceModel")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the category of this {@link Printer}.
     * 
     * @return a {@link PrinterCategory} with all the information about this Printer
     *         category.
     */
    @InterfaceMethodToSchemaField(returnType = PrinterCategory.class, schemaField = "Tecnology")
    PrinterCategory getPrinterCategory();

    /**
     * The resolution (also known as ppi) which this {@link Printer} is able to
     * reach during its printing.
     * 
     * @return an {@link Optional} containing an Integer representing the resolution
     *         of the printer, or Optional.empty if this value is not available
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Resolution")
    Optional<Integer> getResolution();
}
