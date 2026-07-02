package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.Case;
import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link Case} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class CaseImpl implements Case {

    private final GenericDevice device;
    private final Color color;

    /**
     * Constructs a {@link Case} over the given {@link GenericDevice} and
     * {@link Color}.
     * 
     * @param device
     *            a GenericDevice which has all the generic information about this
     *            Case.
     * @param color
     *            the Color of the Case.
     * @throws NullPointerException
     *             if any of the parameter is found to be null.
     */
    public CaseImpl(final GenericDevice device, final Color color) {
        this.device = Objects.requireNonNull(device, "The GenericDevice" + ErrorString.CUSTOM_NULL);
        this.color = Objects.requireNonNull(color, "The Color" + ErrorString.CUSTOM_NULL);
    }

    @Override
    public GenericDevice getGenericDevice() {
        return this.device;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((device == null) ? 0 : device.hashCode());
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
        final CaseImpl other = (CaseImpl) obj;
        if (device == null) {
            if (other.device != null) {
                return false;
            }
        } else if (!device.equals(other.device)) {
            return false;
        }
        if (color == null) {
            if (other.color != null) {
                return false;
            }
        } else if (!color.equals(other.color)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "CaseImpl [device=" + device + ", color=" + color + "]";
    }
}
