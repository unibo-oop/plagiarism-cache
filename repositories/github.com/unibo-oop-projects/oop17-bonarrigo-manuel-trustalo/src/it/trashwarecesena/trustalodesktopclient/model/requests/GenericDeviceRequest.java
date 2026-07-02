package it.trashwarecesena.trustalodesktopclient.model.requests;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a request about a {@link GenericDevice}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DeviceRequestsDeviceModels")
public interface GenericDeviceRequest {

    /**
     * Retrieve the GenericDevice bound to a Request.
     * 
     * @return a {@link GenericDevice} associated to the {@link RequestDetail} obtainable
     *         by the method {@code getRequestDetail()}
     */
    @InterfaceMethodToSchemaField(returnType = GenericDevice.class, schemaField = "DeviceModel")
    GenericDevice getDeviceRequested();

    /**
     * Retrieve the Request this class is all about.
     * 
     * @return a {@link RequestDetail}
     */
    @InterfaceMethodToSchemaField(returnType = RequestDetail.class, schemaField = "RequestDevice")
    RequestDetail getRequestDetail();

    /**
     * Retrieve the quantity of {@link GenericDevice} requested by this request.
     * 
     * @return an Integer representing the quantity of GenericDevice requested.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Quantity")
    Integer getQuantityRequested();

}
