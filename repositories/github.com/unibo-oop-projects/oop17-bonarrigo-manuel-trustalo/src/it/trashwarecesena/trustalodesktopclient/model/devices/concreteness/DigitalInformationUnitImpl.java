package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link DigitalInformationUnit} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class DigitalInformationUnitImpl implements DigitalInformationUnit {

    private final String unitOfMeasure;

    /**
     * Constructs a {@link DigitalInformationUnit} over the given unit of measure.
     * 
     * @param unitOfMeasure
     *            a String containing the unit of measure.
     * @throws NullPointerException
     *             if the unitOfMeasure is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the unitOfMeasure is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public DigitalInformationUnitImpl(final String unitOfMeasure) {
        this.unitOfMeasure = ExtendedObjects.requireNonEmpty(
                Objects.requireNonNull(unitOfMeasure, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((unitOfMeasure == null) ? 0 : unitOfMeasure.hashCode());
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
        final DigitalInformationUnitImpl other = (DigitalInformationUnitImpl) obj;
        if (unitOfMeasure == null) {
            if (other.unitOfMeasure != null) {
                return false;
            }
        } else if (!unitOfMeasure.equals(other.unitOfMeasure)) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return this.unitOfMeasure;
    }

    @Override
    public String toString() {
        return "DigitalInformationUnitImpl [unitOfMeasure=" + unitOfMeasure + "]";
    }

}
