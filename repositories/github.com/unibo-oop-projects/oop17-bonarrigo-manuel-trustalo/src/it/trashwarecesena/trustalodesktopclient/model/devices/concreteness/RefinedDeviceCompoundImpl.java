package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompound;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Implementation of the {@link RefinedDeviceCompound} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RefinedDeviceCompoundImpl implements RefinedDeviceCompound {

    private final RefinedDevice compound;
    private final RefinedDevice component;

    /**
     * Constructs a {@link RefinedDeviceCompound} over the given
     * {@link RefinedDevice}s.
     * 
     * @param compound
     *            the RefinedDevice ought to be the main component in the compound
     * @param component
     *            the RefinedDevice ought to be the minor component in the compound
     * 
     */
    public RefinedDeviceCompoundImpl(final RefinedDevice compound, final RefinedDevice component) {
        this.compound = Objects.requireNonNull(compound, "The RefinedDevice compound" + ErrorString.CUSTOM_NULL);
        this.component = Objects.requireNonNull(component, "The RefinedDevice component" + ErrorString.CUSTOM_NULL);
    }

    @Override
    public RefinedDevice getCompound() {
        return this.compound;
    }

    @Override
    public RefinedDevice getComponent() {
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
        final RefinedDeviceCompoundImpl other = (RefinedDeviceCompoundImpl) obj;
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
        return "RefinedDeviceCompoundImpl [compound=" + compound + ", component=" + component + "]";
    }

}
