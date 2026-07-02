package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.LegalCategoryCompound;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link LegalCategoryCompound}.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class LegalCategoryCompoundImpl implements LegalCategoryCompound {

    private final DeviceCategory compound;
    private final DeviceCategory component;

    /**
     * Constructs a {@link LegalCategoryCompound} over the given arguments.
     * 
     * @param compound
     *            the {@link DeviceCategory} supposed to contain the compound.
     * @param component
     *            the {@link DeviceCategory} supposed to be contained by the
     *            compound.
     * @throws NullPointerException
     *             if any of the parameters is found to be null.
     */
    public LegalCategoryCompoundImpl(final DeviceCategory compound, final DeviceCategory component) {
        this.compound = Objects.requireNonNull(compound, "The DeviceCategory compound" + ErrorString.CUSTOM_NULL);
        this.component = Objects.requireNonNull(component, "The DeviceCategory component" + ErrorString.CUSTOM_NULL);
    }

    @Override
    public DeviceCategory getCompound() {
        return this.compound;
    }

    @Override
    public DeviceCategory getComponent() {
        return this.component;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((component == null) ? 0 : component.hashCode());
        result = prime * result + ((compound == null) ? 0 : compound.hashCode());
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
        final LegalCategoryCompoundImpl other = (LegalCategoryCompoundImpl) obj;
        if (component == null) {
            if (other.component != null) {
                return false;
            }
        } else if (!component.equals(other.component)) {
            return false;
        }
        if (compound == null) {
            if (other.compound != null) {
                return false;
            }
        } else if (!compound.equals(other.compound)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LegalCategoryCompoundImpl [compound=" + compound + ", component=" + component + "]";
    }

}
