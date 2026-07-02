package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * A concrete implementation of a {@link RefinedDevice}.
 * <p>
 * Construction is obtained with a Builder, instantiable through the invocation
 * of {@code new RefinedDeviceImpl.Builder()}
 * <p>
 * It is <b>mandatory</b> to understand that being the RefinedDevice class part
 * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RefinedDeviceImpl implements RefinedDevice {

    private final Optional<Integer> identifier;
    private final Integer categoryDeviceId;
    private final DeviceCategory category;
    private final GenericDevice refined;
    private final DeviceWorkProgress progress;
    private final boolean available;
    private final TrashwareWorker lastCommitter;
    private final Date lastUpdate;
    private final Optional<String> annotations;

    private RefinedDeviceImpl(final Integer identifier, final Integer categoryDeviceId, final DeviceCategory category,
            final GenericDevice refined, final DeviceWorkProgress progress, final boolean available,
            final TrashwareWorker lastCommitter, final Date lastUpdate, final String annotations) {
        this.identifier = Optional.ofNullable(ExtendedObjects.requirePositive(identifier, ErrorString.ONLY_POSITIVE));
        this.category = Objects.requireNonNull(category, "The DeviceCategory" + ErrorString.CUSTOM_NULL);
        this.categoryDeviceId = 
                ExtendedObjects.requirePositive(Objects.requireNonNull(categoryDeviceId), ErrorString.ONLY_POSITIVE);
        this.refined = Objects.requireNonNull(refined, "The GenericDevice" + ErrorString.CUSTOM_NULL);
        this.progress = Objects.requireNonNull(progress, "The DeviceWorkProgress" + ErrorString.CUSTOM_NULL);
        this.available = available;
        this.lastCommitter = Objects.requireNonNull(lastCommitter, "The TrashwareWorker" + ErrorString.CUSTOM_NULL);
        this.lastUpdate = Objects.requireNonNull(lastUpdate, "The Date" + ErrorString.CUSTOM_NULL);
        this.annotations = Optional.ofNullable(ExtendedObjects.requireNonEmpty(annotations, ErrorString.EMPTY_STRING));
    }

    @Override
    public Optional<Integer> getNumericIdentifier() {
        return this.identifier;
    }

    @Override
    public DeviceCategory getDeviceCategory() {
        return this.category;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public GenericDevice getGenericDevice() {
        return this.refined;
    }

    @Override
    public Integer getCategoryDeviceId() {
        return this.categoryDeviceId;
    }

    @Override
    public DeviceWorkProgress getWorkProgress() {
        return this.progress;
    }

    @Override
    public TrashwareWorker getLastChangeCommitter() {
        return this.lastCommitter;
    }

    @Override
    public Date getLastChangeDate() {
        return this.lastUpdate;
    }

    @Override
    public Optional<String> getAnnotations() {
        return this.annotations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getNumericIdentifier().hashCode();
        if (getNumericIdentifier().isPresent()) {
            result = prime * result + refined.hashCode();
            result = prime * result + category.hashCode();
            result = prime * result + progress.hashCode();
            result = prime * result + lastCommitter.hashCode();
            result = prime * result + lastUpdate.hashCode();
            result = prime * result + annotations.hashCode();
            result = prime * result + categoryDeviceId.hashCode();
            result = prime * result + Boolean.valueOf(available).hashCode();
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
        final RefinedDeviceImpl other = (RefinedDeviceImpl) obj;
        if (!(getNumericIdentifier().isPresent() && (other.getNumericIdentifier().isPresent()))) {
            return false;
        }
        if (!(getNumericIdentifier().get().equals(other.getNumericIdentifier().get()))) {
            return false;
        }
        if (!(this.refined.equals(other.getGenericDevice()))) {
            return false;
        }
        if (!(this.category.equals(other.getDeviceCategory()))) {
            return false;
        }
        if (!(this.categoryDeviceId.equals(other.getCategoryDeviceId()))) {
            return false;
        }
        if (!(this.progress.equals(other.getWorkProgress()))) {
            return false;
        }
        if (!(this.lastCommitter.equals(other.getLastChangeCommitter()))) {
            return false;
        }
        if (!(this.lastUpdate.equals(other.getLastChangeDate()))) {
            return false;
        }
        if (!(this.annotations.equals(other.getAnnotations()))) {
            return false;
        }
        if ((this.available != other.isAvailable())) { //NOPMD by Manuel Bonarrigo: This is a subtle equals, and I need
                                                       //and want it as much readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RefinedDeviceImpl [categoryDeviceId=" + categoryDeviceId + ", refined=" + refined + ", progress="
                + progress + ", available=" + available + ", lastCommitter=" + lastCommitter + ", lastUpdate="
                + lastUpdate + ", annotations=" + annotations.orElse(null) + "]";
    }

    /**
     * A builder to instantiate a RefinedDevice through fluent and atomic setting of
     * the parameters.
     * <p>
     * All the building methods but annotations() are mandatory.
     * 
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {

        private Integer builderIdentifier;
        private DeviceCategory builderDeviceCategory;
        private Integer builderCategoryDeviceId;
        private GenericDevice builderRefined;
        private DeviceWorkProgress builderProgress;
        private boolean builderAvailable;
        private TrashwareWorker builderLastCommitter;
        private Date builderLastUpdate;
        private String builderAnnotations;

        /**
         * Initialize the identifier field of a RefinedDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the RefinedDeviceImpl class part
         * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param identifier
         *            an {@link Integer} for this instance of RefinedDevice to be
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
         * Initialize the categoryDeviceId field of a RefinedDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param categoryDeviceId
         *            an {@link Integer} to use and consumption of TrashwareWorker to
         *            impose a known identifier over the device.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder categoryDeviceId(final Integer categoryDeviceId) {
            this.builderCategoryDeviceId = categoryDeviceId;
            return this;
        }

        /**
         * Initialize the deviceCategory field of a RefinedDevice.
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
         * Initialize the refined field of a RefinedDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the GenericDevice interface part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param refined
         *            the {@link GenericDevice} being refined by a Trashware project
         *            member, which will be contained inside the new RefinedDevice
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder refining(final GenericDevice refined) {
            this.builderRefined = refined;
            return this;
        }

        /**
         * Initialize the progress field of a RefinedDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param progress
         *            the {@link DeviceWorkProgress} the RefinedDevice currently has.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder progress(final DeviceWorkProgress progress) {
            this.builderProgress = progress;
            return this;
        }

        /**
         * Initialize the available field of a RefinedDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation. The
         * field will be initialized as {@code false} if not set.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param available
         *            a boolean specifying if the RefinedDevice is available for
         *            donation
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder available(final boolean available) {
            this.builderAvailable = available;
            return this;
        }

        /**
         * Initialize the lastCommitter field of a RefinedDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param lastCommitterr
         *            a {@link TrashwareWorker} specifying the last worker which applied
         *            any kind of manufacturing to the device
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder lastCommitter(final TrashwareWorker lastCommitterr) {
            this.builderLastCommitter = lastCommitterr;
            return this;
        }

        /**
         * Initialize the lastUpdate field of a RefinedDevice.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation. The
         * field will be initialized as {@code false} if not set.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param lastUpdate
         *            a {@link Date} specifying the date of the last update
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder lastUpdate(final Date lastUpdate) {
            this.builderLastUpdate = lastUpdate;
            return this;
        }

        /**
         * Initialize the annotations field of a RefinedDevice.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param annotations
         *            a {@link String} containing any off-the-schema informations about
         *            the RefinedDevice
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder annotations(final String annotations) {
            this.builderAnnotations = annotations;
            return this;
        }

        /**
         * Instantiate and return a RefinedDevice with the dynamically set values.
         * 
         * @return a fully instantiated RefinedDevice
         * @throws NullPointerException
         *             if any of the mandatory parameter is {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be
         *             <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public RefinedDeviceImpl build() {
            return new RefinedDeviceImpl(builderIdentifier, builderCategoryDeviceId, builderDeviceCategory,
                    builderRefined, builderProgress, builderAvailable, builderLastCommitter, builderLastUpdate,
                    builderAnnotations);
        }
    }
}
