package it.trashwarecesena.trustalodesktopclient.model.requests;

import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.Identifiable;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.Carrier;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.EntityInterface;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceMethodToSchemaField;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.annotations.InterfaceToSchemaEntity;

/**
 * Represents one of the multiple possible lines of a request. A peculiarity of
 * this class is that it can depends not only on a {@link Request}, but even on
 * a reference of this same class, obviously not at the same time.
 * <p>
 * It is <b>mandatory</b> to understand that being the RequestDetail interface
 * part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
@EntityInterface
@Carrier
@InterfaceToSchemaEntity(schemaEntity = "DeviceRequestDetails")
public interface RequestDetail extends Identifiable {

    /**
     * Retrieve the Request this RequestDetail is associated to, if it is not
     * associated to another RequestDetail.
     * 
     * @return an Optional containing a {@link Request} this RequestDetail is
     *         associated to, or Optional.empty if it is associated to another
     *         RequestDetail
     */
    @InterfaceMethodToSchemaField(returnType = Request.class, schemaField = "Request")
    Optional<Request> getAssociatedRequest();

    /**
     * Retrieve the DeviceCategory this RequestDetail is related to.
     * 
     * @return a {@link DeviceCategory}.
     */
    @InterfaceMethodToSchemaField(returnType = DeviceCategory.class, schemaField = "Category")
    DeviceCategory getDeviceCategory();

    /**
     * Retrieve any off-the-schema information this RequestDetail is associated to,
     * if any.
     * 
     * @return an {@link Optional} containing a String representing the annotations
     *         on this RequestDetail, or Optional.empty if none is present.
     */
    @InterfaceMethodToSchemaField(returnType = String.class, schemaField = "Annotations")
    Optional<String> getAnnotations();

    /**
     * Retrieve the quantity contained in this RequestDetail.
     * 
     * @return an Integer representing a quantity.
     */
    @InterfaceMethodToSchemaField(returnType = Integer.class, schemaField = "Quantity")
    Integer getRequestedQuantity();

    /**
     * Retrieve the RequestDetail this RequestDetail is associated to, if it is not
     * associated to a Request.
     * 
     * @return an Optional containing a {@link RequestDetail} this RequestDetail is
     *         associated to, or Optional.empty if it is associated to a Request.
     */
    @InterfaceMethodToSchemaField(returnType = RequestDetail.class, schemaField = "ComponentOfRequestDevice")
    Optional<RequestDetail> getCompositeRequestDetail();
}
