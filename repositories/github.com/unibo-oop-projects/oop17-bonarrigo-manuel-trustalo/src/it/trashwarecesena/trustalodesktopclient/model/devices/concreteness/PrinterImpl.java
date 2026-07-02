package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.Printer;
import it.trashwarecesena.trustalodesktopclient.model.devices.PrinterCategory;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link Printer} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class PrinterImpl implements Printer {

    private final GenericDevice genericDevice;
    private final PrinterCategory category;
    private final Optional<Integer> resolution;

    /**
     * Constructs a {@link Printer} over the given informations.
     * 
     * @param genericDevice
     *            a GenericDevice which has all the generic information about this
     *            Printer.
     * @param category
     *            a {@link PrinterCategory} which define the category of this
     *            Printer
     * @param resolution
     *            the resolution of the printer, expressed in ppi. Can be
     *            {@code null}
     */
    public PrinterImpl(final GenericDevice genericDevice, final PrinterCategory category, final Integer resolution) {
        super();
        this.genericDevice = Objects.requireNonNull(genericDevice, "A GenericDevice" + ErrorString.CUSTOM_NULL);
        this.category = Objects.requireNonNull(category, "A PrinterCategory" + ErrorString.CUSTOM_NULL);
        this.resolution = 
            Optional.ofNullable(ExtendedObjects.requirePositive(
                    resolution,  "The resolution" + ErrorString.ONLY_POSITIVE));
    }

    @Override
    public GenericDevice getGenericDevice() {
        return this.genericDevice;
    }

    @Override
    public PrinterCategory getPrinterCategory() {
        return this.category;
    }

    @Override
    public Optional<Integer> getResolution() {
        return this.resolution;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((genericDevice == null) ? 0 : genericDevice.hashCode());
        result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
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
        final PrinterImpl other = (PrinterImpl) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (genericDevice == null) {
            if (other.genericDevice != null) {
                return false;
            }
        } else if (!genericDevice.equals(other.genericDevice)) {
            return false;
        }
        if (resolution == null) {
            if (other.resolution != null) {
                return false;
            }
        } else if (!resolution.equals(other.resolution)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrinterImpl [genericDevice=" + genericDevice + ", category=" + category + ", resolution=" 
                + resolution.orElse(null) + "]";
    }

}
