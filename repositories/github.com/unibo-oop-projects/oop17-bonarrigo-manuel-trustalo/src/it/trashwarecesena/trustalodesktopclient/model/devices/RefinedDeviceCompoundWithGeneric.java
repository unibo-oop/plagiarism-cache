package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Express the composition of a {@link RefinedDevice} and a
 * {@link GenericDevice}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DevicesWithoutIDComponentOfDeviceWithID")
public interface RefinedDeviceCompoundWithGeneric {

    /**
     * Retrieve the {@link RefinedDevice} which is supposed to be the main component
     * of the compound.
     * 
     * @return a {@link RefinedDevice} ought to be the main component.
     */
    @InterfaceMethodToSchemaField(returnType = RefinedDevice.class, schemaField = "Compound")
    RefinedDevice getCompound();

    /**
     * Retrieve the {@link GenericDevice} which is supposed to be the minor component
     * of the compound.
     * 
     * @return a {@link GenericDevice} ought to be the minor component of the compound.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "Component")
    GenericDevice getComponent();

    /**
     * The quantity of {@link GenericDevice} in the compound.
     * 
     * @return an Integer representing such a quantity.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Quantity")
    Integer getQuantity();

}
