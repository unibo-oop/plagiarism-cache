package it.trashwarecesena.trustalodesktopclient.model.requests;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents a request about a {@link RefinedDevice}.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DeviceRequestsDevicesWithID")
public interface RefinedDeviceRequest {

    /**
     * Retrieve the RefinedDevice bound to a RequestDetail.
     * 
     * @return a {@link RefinedDevice} associated to the {@link RequestDetail} obtainable
     *         by the method {@code getRequestDetail()}
     */
    @InterfaceMethodToSchemaField(returnType = RefinedDevice.class, schemaField = "DeviceWithID")
    RefinedDevice getDeviceRequested();

    /**
     * Retrieve the RequestDetail this class is all about.
     * 
     * @return a {@link RequestDetail}
     */
    @InterfaceMethodToSchemaField(returnType = RequestDetail.class, schemaField = "RequestDevice")
    RequestDetail getRequestDetail();
}
