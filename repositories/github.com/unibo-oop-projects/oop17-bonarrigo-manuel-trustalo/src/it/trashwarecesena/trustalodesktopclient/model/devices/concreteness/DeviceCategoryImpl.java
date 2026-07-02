package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Locale;
import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * A simple implementation of the {@link DeviceCategory} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class DeviceCategoryImpl implements DeviceCategory {

    private final String name;
    private final String acronym;
    private final boolean multipleCompound;

    /**
     * Constructs a DeviceCategoryImpl with the given name and acronym.
     * 
     * @param name
     *            A {@link String} with the name to be given to the
     *            DeviceCategoryImpl.
     * @param acronym
     *            A {@link String} with the acronym to be given to the
     *            DeviceCategoryImpl.
     * @param multipleCompound
     *            a boolean expressing if the device category is suitable for
     *            expressing compounds
     * @throws NullPointerException
     *             if any parameter is found to be {@code null}
     * @throws IllegalArgumentException
     *             if any string parameter is found to be not null and <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public DeviceCategoryImpl(final String name, final String acronym, final boolean multipleCompound) {
        super();
        this.name = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(name, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
        this.acronym = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(acronym, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING).toUpperCase(new Locale("it"));
        this.multipleCompound = Objects.requireNonNull(multipleCompound);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAcronym() {
        return this.acronym;
    }

    @Override
    public boolean isMultipleCompoundAllowed() {
        return this.multipleCompound;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((acronym == null) ? 0 : acronym.hashCode());
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
        final DeviceCategoryImpl other = (DeviceCategoryImpl) obj;
        if (acronym == null) {
            if (other.acronym != null) {
                return false;
            }
        } else if (!getAcronym().equals(other.getAcronym())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DeviceCategoryImpl [name=" + name + ", acronym=" + acronym + ", multipleCompound=" + multipleCompound
                + "]";
    }

}
