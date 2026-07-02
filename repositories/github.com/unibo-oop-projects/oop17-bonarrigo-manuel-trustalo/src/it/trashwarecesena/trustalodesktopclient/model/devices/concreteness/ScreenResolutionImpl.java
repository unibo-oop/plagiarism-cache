package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link ScreenResolution} interface.
 * <p>
 * Construction is obtained with a Builder, instantiable through the invocation
 * of {@code new ScreenResolutionImpl.Builder()}
 * <p>
 * It is <b>mandatory</b> to understand that being the ScreenResolutionImpl class
 * part of the
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} family, strict rules do exists about the flow of the
 * information. The Identifiable page of this Javadoc expresses all the required
 * rules.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ScreenResolutionImpl implements ScreenResolution {

    private final Optional<Integer> identifier;
    private final Integer width;
    private final Integer height;
    private final AspectRatio ratio;

    private ScreenResolutionImpl(final Integer identifier, final Integer width, final Integer height,
            final AspectRatio ratio) {
        this.identifier = Optional.ofNullable(
                ExtendedObjects.requirePositive(identifier, "This Integer" + ErrorString.ONLY_POSITIVE));
        this.width = ExtendedObjects.requireNonNegative(
                Objects.requireNonNull(
                        width, "The Integer" + ErrorString.NO_NULL), "The Integer" + ErrorString.NO_NEGATIVE);
        this.height = ExtendedObjects.requireNonNegative(
                Objects.requireNonNull(
                        height, "This Integer" + ErrorString.NO_NULL), "The Integer" + ErrorString.NO_NEGATIVE);
        this.ratio = Objects.requireNonNull(ratio, "The AspectRatio" + ErrorString.CUSTOM_NULL);
    }

    @Override
    public Optional<Integer> getNumericIdentifier() {
        return this.identifier;
    }

    @Override
    public Integer getWidth() {
        return this.width;
    }

    @Override
    public Integer getHeight() {
        return this.height;
    }

    @Override
    public AspectRatio getAspectRatio() {
        return this.ratio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  identifier.hashCode();
        if (identifier.isPresent()) {
            result = prime * result + ((height == null) ? 0 : height.hashCode());
            result = prime * result + ((ratio == null) ? 0 : ratio.hashCode());
            result = prime * result + ((width == null) ? 0 : width.hashCode());
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
        final ScreenResolutionImpl other = (ScreenResolutionImpl) obj;
        if (!(getNumericIdentifier().isPresent() && (other.getNumericIdentifier().isPresent()))) {
            return false;
        }
        if (!(getNumericIdentifier().get().equals(other.getNumericIdentifier().get()))) {
            return false;
        }
        if (!(width.equals(other.width))) {
            return false;
        }
        if (!(height.equals(other.height))) {
            return false;
        }
        if (!(ratio.equals(other.ratio))) { // NOPMD by Manuel Bonarrigo on 7/5/18 02:57 PM This is a subtle equals, and
                                            //I need and want it as much readable as I possibly can.
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScreenResolutionImpl [identifier=" + identifier.orElse(null) + ", width=" + width + ", height=" + height + ", ratio="
                + ratio + "]";
    }

    /**
     * A builder to instantiate a ScreenResolutionImpl through fluent and atomic setting of
     * the parameters.
     * <p>
     * It is <b>mandatory</b> to understand that being the ScreenResolutionImpl class part
     * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
     * Identifiable} family, strict rules do exists about the flow of the
     * information. The Identifiable page of this Javadoc expresses all the required
     * rules.
     * 
     * @author Manuel Bonarrigo
     *
     */
    public static final class Builder {
        private Integer identifierBuilder;
        private Integer widthBuilder;
        private Integer heightBuilder;
        private AspectRatio ratioBuilder;


        /**
         * Initialize the identifier field of a ScreenResolution.
         * <p>
         * The usage of this method is optional to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * <p>
         * It is <b>mandatory</b> to understand that being the ScreenResolution class part
         * of the {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @param identifier
         *            an {@link Integer} for this instance of ScreenResolution to be
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
         * Initialize the width field of a ScreeResolution.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param width
         *            an {@link Integer} which express the width component of the
         *            ScreenResolution.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder width(final Integer width) {
            this.widthBuilder = width;
            return this;
        }

        /**
         * Initialize the height field of a ScreeResolution.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param height
         *            an {@link Integer} which express the height component of the
         *            ScreenResolution.
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder height(final Integer height) {
            this.heightBuilder = height;
            return this;
        }

        /**
         * Initialize the aspectRatio field of a ScreeResolution.
         * <p>
         * The usage of this method is mandatory to the means of the instantiation.
         * <p>
         * Notice that the real instantiation and consequent setting of the field won't
         * happen until the call of the {@code build()} method from this same class
         * 
         * @param ratio
         *            an {@link AspectRatio} which express the ratio between the width
         *            and the height
         * @return the instance of this same Builder, to achieve fluence.
         */
        public Builder aspectRatio(final AspectRatio ratio) {
            this.ratioBuilder = ratio;
            return this;
        }

        /**
         * Instantiate and return a ScreenResolution with the dynamically set values.
         * <p>
         * It is <b>mandatory</b> to understand that being the ScreenResolution class
         * part of the
         * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
         * Identifiable} family, strict rules do exists about the flow of the
         * information. The Identifiable page of this Javadoc expresses all the required
         * rules.
         * 
         * @return a fully instantiated ScreenResolution
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
        public ScreenResolutionImpl build() {
            return new ScreenResolutionImpl(identifierBuilder, widthBuilder, heightBuilder, ratioBuilder);
        }
    }

}
