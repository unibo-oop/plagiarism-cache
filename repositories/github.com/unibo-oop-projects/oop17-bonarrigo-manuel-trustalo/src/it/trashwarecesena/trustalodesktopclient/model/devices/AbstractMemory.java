package it.trashwarecesena.trustalodesktopclient.model.devices;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An abstract implementation of the common functionalities of a data storage
 * device.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractMemory implements Memory {

    private final GenericDevice device;
    private final Integer capacity;
    private final DigitalInformationUnit capacityUnit;

    /**
     * @param device
     *            a GenericDevice which has all the generic information about this
     *            Memory.
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
    public AbstractMemory(final GenericDevice device, final Integer capacity, final DigitalInformationUnit capacityUnit) {
        this.device = Objects.requireNonNull(device);
        this.capacity = ExtendedObjects.requirePositive(Objects.requireNonNull(capacity));
        this.capacityUnit = Objects.requireNonNull(capacityUnit);
    }

    @Override
    public final GenericDevice getGenericDevice() {
        return this.device;
    }

    @Override
    public final Integer getCapacity() {
        return this.capacity;
    }

    @Override
    public final DigitalInformationUnit getCapacityUnit() {
        return this.capacityUnit;
    }

}
