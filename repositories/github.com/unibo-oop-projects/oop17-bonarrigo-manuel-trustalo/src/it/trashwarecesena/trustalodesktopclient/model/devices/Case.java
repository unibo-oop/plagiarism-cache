package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents the external shell of a Desktop PC.
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "CaseModels")
public interface Case {

    /**
     * Retrieve the {@link GenericDevice} this Case refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "ID")
    GenericDevice getGenericDevice();

    /**
     * Retrieve the {@link Color} of this Case.
     * 
     * @return the {@link Color} this case is painted with.
     */
    @InterfaceMethodToSchemaField(returnType = Color.class, schemaField = "Color")
    Color getColor();

}
