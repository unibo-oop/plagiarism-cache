package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompoundWithGeneric;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link RefinedDeviceCompoundWithGeneric} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RefinedDeviceCompoundWithGenericImpl implements RefinedDeviceCompoundWithGeneric {

    private final RefinedDevice compound;
    private final GenericDevice component;
    private final Integer quantity;

    /**
     * Constructs a RefinedDeviceCompound over the given informations.
     * 
     * @param compound
     *            the main component
     * @param component
     *            the lesser component
     * @param quantity
     *            the quantity of {@link GenericDevice} in the compound.
     * @throws NullPointerException
     *             if any of the parameter is {@code null}
     */
    public RefinedDeviceCompoundWithGenericImpl(final RefinedDevice compound, final GenericDevice component, 
                                                final Integer quantity) {
        this.compound = Objects.requireNonNull(compound, "The RefinedDevice" + ErrorString.CUSTOM_NULL);
        this.component = Objects.requireNonNull(component, "The GenericDevice" + ErrorString.CUSTOM_NULL);
        this.quantity = ExtendedObjects.requireNonNegative(quantity, "The quantity" + ErrorString.NO_NEGATIVE);
    }

    @Override
    public RefinedDevice getCompound() {
        return this.compound;
    }

    @Override
    public GenericDevice getComponent() {
        return this.component;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
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
        final RefinedDeviceCompoundWithGenericImpl other = (RefinedDeviceCompoundWithGenericImpl) obj;
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
        return "RefinedDeviceCompoundWithGenericImpl [compound=" + compound + ", component=" + component + ", quantity="
                + quantity + "]";
    }

}
