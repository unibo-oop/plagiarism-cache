package it.trashwarecesena.trustalodesktopclient.model.requests.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link RequestDetail} interface.
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
public final class RequestDetailImpl implements RequestDetail {

    private final Optional<Integer> identifier;
    private final Optional<Request> associatedRequest;
    private final DeviceCategory deviceCategory;
    private final Optional<String> annotations;
    private final Integer requestedQuantity;
    private final Optional<RequestDetail> compositeRequestDetail;

    private RequestDetailImpl(final Integer identifier, final Request associatedRequest,
            final DeviceCategory deviceCategory, final String annotations, final Integer requestedQuantity,
            final RequestDetail compositeRequestDetail) {
        this.identifier = Optional.ofNullable(ExtendedObjects.requirePositive(
                                                                        identifier, 
                                                                        "The identifier" + ErrorString.ONLY_POSITIVE));
        this.associatedRequest = Optional.ofNullable(associatedRequest);
        this.deviceCategory = Objects.requireNonNull(deviceCategory, "The deviceCategory" + ErrorString.CUSTOM_NULL);
        this.annotations = Optional.ofNullable(ExtendedObjects.requireNonEmpty(annotations, ErrorString.EMPTY_STRING));
        this.requestedQuantity = ExtendedObjects.requireNonNegative(Objects.requireNonNull(requestedQuantity, 
                                                                    "The Integer" + ErrorString.CUSTOM_NULL), 
                                                                    "The quantity" + ErrorString.NO_NEGATIVE);
        this.compositeRequestDetail = Optional.ofNullable(compositeRequestDetail);
        if (this.associatedRequest.isPresent() == this.compositeRequestDetail.isPresent()) {
            throw new IllegalArgumentException("The request reference and the composite request detail can not both be"
                                                + "absent, neither both be present");
        }
    }

    @Override
    public Optional<Integer> getNumericIdentifier() {
        return this.identifier;
    }

    @Override
    public Optional<Request> getAssociatedRequest() {
        return this.associatedRequest;
    }

    @Override
    public DeviceCategory getDeviceCategory() {
        return this.deviceCategory;
    }

    @Override
    public Optional<String> getAnnotations() {
        return this.annotations;
    }

    @Override
    public Integer getRequestedQuantity() {
        return this.requestedQuantity;
    }

    @Override
    public Optional<RequestDetail> getCompositeRequestDetail() {
        return this.compositeRequestDetail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + identifier.hashCode();
        if (identifier.isPresent()) {
            result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
            result = prime * result + ((associatedRequest == null) ? 0 : associatedRequest.hashCode());
            result = prime * result + ((compositeRequestDetail == null) ? 0 : compositeRequestDetail.hashCode());
            result = prime * result + ((deviceCategory == null) ? 0 : deviceCategory.hashCode());
            result = prime * result + ((requestedQuantity == null) ? 0 : requestedQuantity.hashCode());
        }
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RequestDetailImpl other = (RequestDetailImpl) obj;
        if (!(getNumericIdentifier().isPresent() && (other.getNumericIdentifier().isPresent()))) {
            return false;
        }
        if (!(getNumericIdentifier().get().equals(other.getNumericIdentifier().get()))) {
            return false;
        }
        if (!(associatedRequest.equals(other.associatedRequest))) {
            return false;
        }
        if (!(annotations.equals(other.annotations))) {
            return false;
        }
        if (!(compositeRequestDetail.equals(other.compositeRequestDetail))) {
            return false;
        }
        if (!(deviceCategory.equals(other.deviceCategory))) {
            return false;
        }
        if (!(requestedQuantity.equals(other.requestedQuantity))) {         // NOPMD by Manuel Bonarrigo on 7/5/18 
                                                                            //02:57 PM This is a subtle equals, and I 
                                                                            //need and want it as much
                                                                            // readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequestDetailImpl [identifier=" + identifier + ", associatedRequest=" + associatedRequest
                + ", deviceCategory=" + deviceCategory + ", annotations=" + annotations + ", requestedQuantity="
                + requestedQuantity + ", compositeRequestDetail=" + compositeRequestDetail + "]";
    }

    /**
     * A builder to instantiate a RequestDetail through fluent and atomic setting of
     * the parameters.
     * <p>
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {

        private Integer identifierBuilder;
        private Request associatedRequestBuilder;
        private DeviceCategory categoryBuilder;
        private String annotationsBuilder;
        private Integer quantityBuilder;
        private RequestDetail compositeRequestBuilder;

        /**
         * Initialize the identifier field of a RequestDetail.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the RequestDetail class part
         * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param identifier
         *            an {@link Integer} for this instance of RequestDetail to be
         *            identified with.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder identifier(final Integer identifier) {
            this.identifierBuilder = identifier;
            return this;
        }

        /**
         * Initialize the associated request field of a RequestDetail.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param request
         *            a {@link Request} containing the related Request for this
         *            RequestDetail
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder associatedRequest(final Request request) {
            this.associatedRequestBuilder = request;
            return this;
        }

        /**
         * Initialize the category field of a RequestDetail.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param category
         *            a {@link DeviceCategory} containing a device category
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder category(final DeviceCategory category) {
            this.categoryBuilder = category;
            return this;
        }

        /**
         * Initialize the annotations field of a RequestDetail.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param annotations
         *            a {@link String} containing any off-the-schema informations about
         *            the RequestDetail
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder annotations(final String annotations) {
            this.annotationsBuilder = annotations;
            return this;
        }

        /**
         * Initialize the quantity field of a RequestDetail.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param quantity
         *            a {@link Integer} containing the quantity of ordered devices
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder quantity(final Integer quantity) {
            this.quantityBuilder = quantity;
            return this;
        }

        /**
         * Initialize the associated request detail field of a RequestDetail.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param requestDetail
         *            a {@link RequestDetail} containing the related RequestDetail for
         *            this RequestDetail
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder compositeRequestDetail(final RequestDetail requestDetail) {
            this.compositeRequestBuilder = requestDetail;
            return this;
        }

        /**
         * Instantiate and return a RequestDetail with the dynamically set values.
         * 
         * @return a fully instantiated RequestDetail
         * @throws NullPointerException
         *             if any of the mandatory parameter is {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be
         *             <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public RequestDetailImpl build() {
            return new RequestDetailImpl(identifierBuilder, associatedRequestBuilder, categoryBuilder, 
                                        annotationsBuilder, quantityBuilder, compositeRequestBuilder);
        }
    }
}
