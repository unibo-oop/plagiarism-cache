package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import it.trashwarecesena.trustalodesktopclient.model.devices.AbstractMemory;
import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.HardDiskDrive;

/**
 * An implementation of the {@link HardDiskDrive} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class HardDiskDriveImpl extends AbstractMemory implements HardDiskDrive {

    /**
     * Constructs a {@link HardDiskDrive} over the given informations.
     * 
     * @param device
     *            a GenericDevice which has all the generic information about this
     *            HardDiskDrive.
     * @param capacity
     *            the amount of information this drive is able to hold. Must be a
     *            positive number.
     * @param capacityUnit
     *            the unit of measure the capacity is measured with.
     * @throws NullPointerException
     *             if any of the parameter is null.
     * @throws IllegalArgumentException
     *             if the capacity parameter is not positive.
     */
    public HardDiskDriveImpl(final GenericDevice device, final Integer capacity, 
            final DigitalInformationUnit capacityUnit) {
        super(device, capacity, capacityUnit);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCapacity() == null) ? 0 : getCapacity().hashCode());
        result = prime * result + ((getCapacityUnit() == null) ? 0 : getCapacityUnit().hashCode());
        result = prime * result + ((getGenericDevice() == null) ? 0 : getGenericDevice().hashCode());
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
        final HardDiskDriveImpl other = (HardDiskDriveImpl) obj;
        if (getCapacity() == null) {
            if (other.getCapacity() != null) {
                return false;
            }
        } else if (!getCapacity().equals(other.getCapacity())) {
            return false;
        }
        if (getCapacityUnit() == null) {
            if (other.getCapacityUnit() != null) {
                return false;
            }
        } else if (!getCapacityUnit().equals(other.getCapacityUnit())) {
            return false;
        }
        if (getGenericDevice() == null) {
            if (other.getGenericDevice() != null) {
                return false;
            }
        } else if (!getGenericDevice().equals(other.getGenericDevice())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HardDiskDriveImpl [getGenericDevice()=" + getGenericDevice() + ", getCapacity()=" + getCapacity() + ", getCapacityUnit()=" 
                + getCapacityUnit() + "]";
    }

}
