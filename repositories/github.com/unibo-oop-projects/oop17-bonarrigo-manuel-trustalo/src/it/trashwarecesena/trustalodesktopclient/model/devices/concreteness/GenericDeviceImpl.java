package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.Vendor;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * This class extends the behaviour of a {@link GenericDevice}.
 * <p>
 * Construction is obtained with a Builder, instantiable through the invocation
 * of {@code new GenericDevice.Builder()}
 * <p>
 * It is <b>mandatory</b> to understand that being the GenericDeviceImpl class
 * part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class GenericDeviceImpl implements GenericDevice {

    private final Optional<Integer> identifier;
    private final DeviceCategory category;
    private final Optional<Vendor> vendor;
    private final Optional<String> vendorModelIdentifier;
    private final Integer availableDevices;
    private final Optional<String> description;

    private GenericDeviceImpl(final Integer identifier, final DeviceCategory category, final Vendor vendor,
            final String vendorModelIdentifier, final Integer availableDevices, final String description) {

        this.category = Objects.requireNonNull(category, "The DeviceCategory " + ErrorString.CUSTOM_NULL);
        this.identifier = Optional.ofNullable(ExtendedObjects.requirePositive(identifier, ErrorString.ONLY_POSITIVE));
        this.vendor = Optional.ofNullable(vendor);
        this.vendorModelIdentifier = 
                Optional.ofNullable(ExtendedObjects.requireNonEmpty(vendorModelIdentifier, ErrorString.EMPTY_STRING));
        this.availableDevices = ExtendedObjects.requireNonNegative(availableDevices, ErrorString.NO_NEGATIVE);
        this.description = Optional.ofNullable(ExtendedObjects.requireNonEmpty(description, ErrorString.EMPTY_STRING));
    }

    @Override
    public boolean isAvailable() {
        return availableDevices > 0;
    }

    @Override
    public Optional<Integer> getNumericIdentifier() {
        return this.identifier;
    }

    @Override
    public Optional<Vendor> getVendor() {
        return this.vendor;
    }

    @Override
    public Optional<String> getVendorModelIdentifier() {
        return this.vendorModelIdentifier;
    }

    @Override
    public Integer getNumberOfAvailableDevices() {
        return this.availableDevices;
    }

    @Override
    public Optional<String> getDeviceDescription() {
        return this.description;
    }

    @Override
    public DeviceCategory getDeviceCategory() {
        return this.category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getNumericIdentifier().hashCode();
        if (getNumericIdentifier().isPresent()) {
            result = prime * result + getVendor().hashCode();
            result = prime * result + getVendorModelIdentifier().hashCode();
            result = prime * result + getNumberOfAvailableDevices().hashCode();
            result = prime * result + getDeviceCategory().hashCode();
            result = prime * result + getDeviceDescription().hashCode();
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
        final GenericDeviceImpl other = (GenericDeviceImpl) obj;
        if (!(getNumericIdentifier().isPresent() && (other.getNumericIdentifier().isPresent()))) {
            return false;
        }
        if (!(getNumericIdentifier().get().equals(other.getNumericIdentifier().get()))) {
            return false;
        }
        if (!(getVendor().equals(other.getVendor()))) {
            return false;
        }
        if (!(getVendorModelIdentifier().equals(other.getVendorModelIdentifier()))) {
            return false;
        }
        if (!(getNumberOfAvailableDevices().equals(other.getNumberOfAvailableDevices()))) {
            return false;
        }
        if (!(getDeviceCategory()).equals(other.getDeviceCategory())) {
            return false;
        }
        if (!(getDeviceDescription().equals(other.getDeviceDescription()))) { // NOPMD by Manuel Bonarrigo on 7/5/18 
                                                                              //02:57 PM This is a subtle equals, and I 
                                                                              //need and want it as much
                                                                              // readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GenericDeviceImpl [identifier=" + identifier.orElse(null) + ", vendor=" + vendor.orElse(null) 
                + ", vendorModelIdentifier=" + vendorModelIdentifier.orElse(null) + ", availableDevices=" 
                + availableDevices + ", description=" + description.orElse(null) + "]";
    }

    /**
     * A builder to instantiate a GenericDevice through fluent and atomic setting of
     * the parameters.
     * <p>
     * It is <b>mandatory</b> to understand that being the GenericDeviceImpl class part
     * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
     * Identifiable} family, strict rules do exists about the flow of the
     * information. The Identifiable page of this Javadoc expresses all the required
     * rules.
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {

        private Integer builderIdentifier;
        private DeviceCategory builderDeviceCategory;
        private Vendor builderVendor;
        private String builderVendorModelIdentifier;
        private Integer builderAvailableDevices;
        private String builderDescription;

        /**
         * Default constructor.
         */
        public Builder() {
            this.builderAvailableDevices = Integer.valueOf(0);
        }

        /**
         * Initialize the identifier field of a GenericDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the GenericDeviceImpl class part
         * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param identifier
         *            an {@link Integer} for this instance of GenericDevice to be
         *            identified with.
         * @return the instance of this same Builder, to achieve fluence.
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         */
        public Builder identifier(final Integer identifier) {
            this.builderIdentifier = identifier;
            return this;
        }

        /**
         * Initialize the deviceCategory field of a GenericDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param deviceCategory
         *            an {@link DeviceCategory} which express what kind of device is
         *            being managed.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder deviceCategory(final DeviceCategory deviceCategory) {
            this.builderDeviceCategory = deviceCategory;
            return this;
        }

        /**
         * Initialize the vendor field of a GenericDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param vendor
         *            the {@link Vendor} which produced such a GenericDevice
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder vendor(final Vendor vendor) {
            this.builderVendor = vendor;
            return this;
        }

        /**
         * Initialize the vendorModelIdentifier field of a GenericDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param vendorModelIdentifier
         *            a {@link String} containing the original serial number imposed by
         *            the vendor which produced the GenericDevice
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder vendorModelIdentifier(final String vendorModelIdentifier) {
            this.builderVendorModelIdentifier = vendorModelIdentifier;
            return this;
        }

        /**
         * Initialize the availableDevices field of a GenericDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation, since
         * the field will be initialized to {@code 0} if not set. This only means that
         * no error will be raised at construction time.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param availableDevices
         *            an {@link Integer} specifying the number of GenericDevice actually
         *            in the inventory
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder available(final Integer availableDevices) {
            this.builderAvailableDevices = availableDevices;
            return this;
        }

        /**
         * Initialize the description field of a GenericDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param description
         *            a {@link String} containing any off-the-schema description about
         *            the GenericDevice
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder description(final String description) {
            this.builderDescription = description;
            return this;
        }

        /**
         * Instantiate and return a GenericDevice with the dynamically set values.
         * <p>
         * It is <b>mandatory</b> to understand that being the GenericDevice class part
         * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @return a fully instantiated GenericDevice
         * @throws NullPointerException
         *             if any of the mandatory parameter is {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be
         *             <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         *      Identifiable}
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public GenericDeviceImpl build() {
            return new GenericDeviceImpl(builderIdentifier, builderDeviceCategory, builderVendor,
                    builderVendorModelIdentifier, builderAvailableDevices, builderDescription);
        }
    }
}
