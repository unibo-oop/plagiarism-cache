package oop.raccoonhome.device;

/**
 * 
 *
 */
public interface OpenCloseDevice extends Device {
    /**
     * Open the object by calling switchOn function. switchOn isn't enough to
     * open this object type.
     */
    void open();

    /**
     * Close the object by calling switchOn function. switchOn isn't enough to
     * open this object type.
     */
    void close();

    /**
     * @return true if the device is open otherwise return false
     */
    boolean isOpen();

}
