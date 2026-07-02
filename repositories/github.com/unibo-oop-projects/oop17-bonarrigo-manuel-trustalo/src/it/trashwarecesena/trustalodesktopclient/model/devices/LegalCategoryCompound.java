package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents the allowed compositions of {@link DeviceCategory}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "DeviceCategoryAllowedComponents")
public interface LegalCategoryCompound {

    /**
     * Retrieve the holding part of the compound.
     * 
     * @return a {@link DeviceCategory} supposed to be the main part of the
     *         compound.
     */
    @InterfaceMethodToSchemaField(returnType = DeviceCategory.class, schemaField = "CompoundCategory")
    DeviceCategory getCompound();

    /**
     * Retrieve the minor part of the compound.
     * 
     * @return a {@link DeviceCategory} supposed to be the contained part.
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "ComponentCategory")
    DeviceCategory getComponent();

}
