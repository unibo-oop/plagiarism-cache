package it.trashwarecesena.trustalodesktopclient.model.devices;

/**
 * A common ancestor for all data storage devices.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface Memory {

    /**
     * Retrieve the {@link GenericDevice} this Memory refers to.
     * 
     * @return a {@link GenericDevice} with all the the generic informations about
     *         this device.
     */
    GenericDevice getGenericDevice();

    /**
     * Retrieve the capacity of this HardDiskDrive.
     * 
     * @return an Integer expressing how much data this disk is able of handling.
     */
    Integer getCapacity();

    /**
     * Retrieve the unit of measurement of the disk capacity.
     * 
     * @return a {@link DigitalInformationUnit} expressing how the
     *         {@code getCapacity()} result should be interpreted.
     */
    DigitalInformationUnit getCapacityUnit();
}
