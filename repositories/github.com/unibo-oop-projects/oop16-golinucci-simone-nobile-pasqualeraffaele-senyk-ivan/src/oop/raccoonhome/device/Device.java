package oop.raccoonhome.device;

/**
 * 
 *
 */
public interface Device {

    /**
     * Active selected object. If the object is OpenCloseDevice, this function
     * active a subpart
     */
    void switchOn();

    /**
     * De-active selected object. If the object is OpenCloseDevice, this
     * function active a subpart
     */
    void switchOff();

    /**
     * @return true if the device is on otherwise return off
     */
    boolean isOn();

    /**
     * @return the propriety of devices
     * 
     */
    EDevice getPropriety();
}
