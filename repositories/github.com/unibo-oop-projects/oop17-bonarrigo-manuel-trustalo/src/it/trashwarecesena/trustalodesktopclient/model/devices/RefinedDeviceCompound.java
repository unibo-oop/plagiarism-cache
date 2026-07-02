package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Express the composition of two {@link RefinedDevice}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DevicesWithIDComponentOfDeviceWithID")
public interface RefinedDeviceCompound {

    /**
     * Retrieve the {@link RefinedDevice} which is supposed to be the main component
     * of the compound.
     * 
     * @return a {@link RefinedDevice} ought to be the main component.
     */
    @InterfaceMethodToSchemaField(returnType = RefinedDevice.class, schemaField = "Compound")
    RefinedDevice getCompound();

    /**
     * Retrieve the {@link RefinedDevice} which is supposed to be the minor component
     * of the compound.
     * 
     * @return a {@link RefinedDevice} ought to be the minor component.
     */
    @InterfaceMethodToSchemaField(returnType = RefinedDevice.class, schemaField = "Component")
    RefinedDevice getComponent();

}
