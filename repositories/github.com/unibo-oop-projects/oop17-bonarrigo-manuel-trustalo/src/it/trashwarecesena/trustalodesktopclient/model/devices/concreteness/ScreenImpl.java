package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.Screen;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link Screen} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ScreenImpl implements Screen {

    private static final int TRUE_BOOLEAN_CLASSICAL_HASHCODE_VALUE = 1231;
    private static final int FALSE_BOOLEAN_CLASSICAL_HASHCODE_VALUE = 1237;

    private final GenericDevice device;
    private final ScreenCategory category;
    private final ScreenResolution resolution;
    private final Optional<Color> color;
    private final boolean frame;
    private final boolean vgaSocket;
    private final boolean dviSocket;
    private final boolean audioSpeakers;

    private ScreenImpl(final GenericDevice device, final ScreenCategory category, final ScreenResolution resolution, 
            final Color color, final boolean frame, final boolean vgaSocket, final boolean dviSocket, 
            final boolean audioSpeakers) {
        this.device = Objects.requireNonNull(device, "The GenericDevice" + ErrorString.CUSTOM_NULL);
        this.category = Objects.requireNonNull(category, "The ScreenCategory" + ErrorString.CUSTOM_NULL);
        this.resolution = Objects.requireNonNull(resolution, "The ScreenResolution" + ErrorString.CUSTOM_NULL);
        this.color = Optional.ofNullable(color);
        this.frame = frame;
        this.vgaSocket = vgaSocket;
        this.dviSocket = dviSocket;
        this.audioSpeakers = audioSpeakers;
    }

    @Override
    public GenericDevice getGenericDevice() {
        return this.device;
    }

    @Override
    public ScreenCategory getCategory() {
        return this.category;
    }

    @Override
    public ScreenResolution getMaximumResolution() {
        return this.resolution;
    }

    @Override
    public Optional<Color> getColor() {
        return this.color;
    }

    @Override
    public boolean isWithFrame() {
        return this.frame;
    }

    @Override
    public boolean isWithVgaSocket() {
        return this.vgaSocket;
    }

    @Override
    public boolean isWithDviSocket() {
        return this.dviSocket;
    }

    @Override
    public boolean isWithAudioSpeakers() {
        return this.audioSpeakers;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + (audioSpeakers ? TRUE_BOOLEAN_CLASSICAL_HASHCODE_VALUE : FALSE_BOOLEAN_CLASSICAL_HASHCODE_VALUE);
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((device == null) ? 0 : device.hashCode());
        result = prime * result 
                + (dviSocket ? TRUE_BOOLEAN_CLASSICAL_HASHCODE_VALUE : FALSE_BOOLEAN_CLASSICAL_HASHCODE_VALUE);
        result = prime * result 
                + (frame ? TRUE_BOOLEAN_CLASSICAL_HASHCODE_VALUE : FALSE_BOOLEAN_CLASSICAL_HASHCODE_VALUE);
        result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
        result = prime * result 
                + (vgaSocket ? TRUE_BOOLEAN_CLASSICAL_HASHCODE_VALUE : FALSE_BOOLEAN_CLASSICAL_HASHCODE_VALUE);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScreenImpl other = (ScreenImpl) obj;
        if (audioSpeakers != other.audioSpeakers) {
            return false;
        }
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (color == null) {
            if (other.color != null) {
                return false;
            }
        } else if (!color.equals(other.color)) {
            return false;
        }
        if (device == null) {
            if (other.device != null) {
                return false;
            }
        } else if (!device.equals(other.device)) {
            return false;
        }
        if (dviSocket != other.dviSocket) {
            return false;
        }
        if (frame != other.frame) {
            return false;
        }
        if (resolution == null) {
            if (other.resolution != null) {
                return false;
            }
        } else if (!resolution.equals(other.resolution)) {
            return false;
        }
        if (vgaSocket != other.vgaSocket) { //NOPMD by Manuel Bonarrigo: These are EQUALS, they need to be made this way
                                            //to be understood.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScreenImpl [device=" + device + ", category=" + category + ", resolution=" + resolution + ", color="
                + color.orElse(null) + ", frame=" + frame + ", vgaSocket=" + vgaSocket + ", dviSocket=" + dviSocket
                + ", audioSpeakers=" + audioSpeakers + "]";
    }

    /**
     * A builder to instantiate a Screen through fluent and atomic setting of the
     * parameters.
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {
        private GenericDevice deviceBuilder;
        private ScreenCategory categoryBuilder;
        private ScreenResolution resolutionBuilder;
        private Color colorBuilder;
        private boolean frameBuilder;
        private boolean vgaSocketBuilder;
        private boolean dviSocketBuilder;
        private boolean audioSpeakersBuilder;

        /**
         * Initialize the device field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the GenericDevice interface
         * part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param device
         *            the {@link GenericDevice} with more informations about this object
         *            as generic device.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder device(final GenericDevice device) {
            this.deviceBuilder = device;
            return this;
        }

        /**
         * Initialize the screenCategory field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param category
         *            a {@link ScreenCategory} which express which technology the
         *            Screen uses to show images.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder category(final ScreenCategory category) {
            this.categoryBuilder = category;
            return this;
        }

        /**
         * Initialize the resolution field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param resolution
         *            a {@link ScreenResolution} which express the maximum resolution of
         *            the Screen
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder resolution(final ScreenResolution resolution) {
            this.resolutionBuilder = resolution;
            return this;
        }

        /**
         * Initialize the color field of a Screen.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param color
         *            a {@link Color} which express what the color of the screen is
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder color(final Color color) {
            this.colorBuilder = color;
            return this;
        }

        /**
         * Initialize the frame field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param hasFrame
         *            a boolean which express if the Screen has a frame.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder hasFrame(final boolean hasFrame) {
            this.frameBuilder = hasFrame;
            return this;
        }

        /**
         * Initialize the vgaSocket field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param hasVgaSocket
         *            a boolean which express if the Screen has a VGA socket.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder hasVgaSocket(final boolean hasVgaSocket) {
            this.vgaSocketBuilder = hasVgaSocket;
            return this;
        }

        /**
         * Initialize the dviSocket field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param hasDviSocket
         *            a boolean which express if the Screen has a DVI socket.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder hasDviSocket(final boolean hasDviSocket) {
            this.dviSocketBuilder = hasDviSocket;
            return this;
        }

        /**
         * Initialize the audioSpeakers field of a Screen.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param hasAudioSpeakers
         *            a boolean which express if the Screen has audio speakers.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder hasAudioSpeakers(final boolean hasAudioSpeakers) {
            this.audioSpeakersBuilder = hasAudioSpeakers;
            return this;
        }

        /**
         * Instantiate and return a Screen with the dynamically set values.
         * <p>
         * @return a fully instantiated Screen
         * @throws NullPointerException
         *             if any of the mandatory parameter is {@code null}
         * @throws IllegalArgumentException
         *             if any of the string related parameters are found to be
         *             <i>empty</i>
         * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
         *      ExtendedObjects.requireNonEmpty(String, String)}
         */
        public ScreenImpl build() {
            return new ScreenImpl(deviceBuilder, categoryBuilder, resolutionBuilder, colorBuilder, frameBuilder,
                    vgaSocketBuilder, dviSocketBuilder, audioSpeakersBuilder);
        }
    }
}
