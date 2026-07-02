package it.trashwarecesena.trustalodesktopclient.model.devices;

import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Express the internal categorization of any device for the Trashware
 * project.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@InterfaceToSchemaEntity(schemaEntity = "TrashwareDeviceCategories")
public interface DeviceCategory {

    /**
     * Retrieve the name of the category.
     * 
     * @return a {@link String} containing the name of the category.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Name")
    String getName();

    /**
     * Retrieve the unique acronym of the category. The format of the String will
     * always be in upper case.
     * 
     * @return a {@link String} containing the acronym.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Acronym")
    String getAcronym();

    /***
     * Tells if the category is thought to be associated with multiple sub-instances
     * of any device.
     * 
     * @return true if this behaviour is possible, false otherwise
     */
    @InterfaceMethodToSchemaField(returnType = Boolean.class, schemaField = "AllowsMultipleComponund")
    boolean isMultipleCompoundAllowed();

}
