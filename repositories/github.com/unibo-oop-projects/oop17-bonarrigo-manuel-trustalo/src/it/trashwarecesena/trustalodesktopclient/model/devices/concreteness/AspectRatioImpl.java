package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * Implementation of the {@link AspectRatio} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class AspectRatioImpl implements AspectRatio {

    private final String aspectRatio;

    /**
     * Constructs an AspectRatio upon the given width and height.
     * 
     * @param horizontalRatio
     *            the horizontal proportion. Must be a positive number.
     * @param verticalRatio
     *            the vertical proportion. Must be a positive number.
     * @throws NullPointerException
     *             if any of the arguments is found to be {@code null}
     * @throws IllegalArgumentException
     *             if any of the arguments is found to be non positive
     */
    public AspectRatioImpl(final Integer horizontalRatio, final Integer verticalRatio) {
        this.aspectRatio = 
                ExtendedObjects.requirePositive(
                        Objects.requireNonNull(horizontalRatio, "The Integer" + ErrorString.CUSTOM_NULL), "Th Integer" 
                        + ErrorString.ONLY_POSITIVE) 
                + ":" 
                + ExtendedObjects.requirePositive(
                        Objects.requireNonNull(verticalRatio, "The Integer" + ErrorString.CUSTOM_NULL), "The Integer" 
                        + ErrorString.ONLY_POSITIVE);
    }

    @Override
    public String getScreenRatio() {
        return this.aspectRatio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aspectRatio == null) ? 0 : aspectRatio.hashCode());
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
        final AspectRatioImpl other = (AspectRatioImpl) obj;
        if (aspectRatio == null) {
            if (other.aspectRatio != null) {
                return false;
            }
        } else if (!aspectRatio.equals(other.aspectRatio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AspectRatioImpl [aspectRatio=" + aspectRatio + "]";
    }
}
