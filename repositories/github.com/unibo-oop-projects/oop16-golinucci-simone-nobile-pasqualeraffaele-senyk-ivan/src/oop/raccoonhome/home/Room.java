package oop.raccoonhome.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import oop.raccoonhome.device.Device;

/**
 * 
 *
 */
public final class Room implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 0000000001L;

    private ERoom roomType = ERoom.MULTIUSO;

    private final ArrayList<Device> roomDeviceList = new ArrayList<Device>();

    /**
     * @param roomType
     *            define the type of room that refer this class
     */
    public Room(final ERoom roomType) {
        this.roomType = roomType;
    }

    /*---------------------SWITCH/OTHER-----------------------------*/

    /**
     * @param device
     *            add new device at room
     */
    public void addDevice(final Device device) {
        roomDeviceList.add(device);
    }

    /**
     * @param device
     *          list of devices
     */
    public void addDevices(final List<Device> device) {
        roomDeviceList.addAll(device);
    }

    /**
     * @param device
     *            device that you want put ON
     */
    public void switchOn(final Device device) {
        device.switchOn();
    }

    /**
     * 
     */
    public void switchAllOn() {
        for (Device devices : roomDeviceList) {
            this.switchOn(devices);
        }
    }

    /**
     * @param device
     *            device that you want put OFF
     */
    public void switchOff(final Device device) {
        device.switchOff();
    }

    /**
     * 
     */
    public void switchAllOff() {
        for (Device devices : roomDeviceList) {
            this.switchOff(devices);
        }
    }

    /*------------------------------GET/SET------------------------*/

    /**
     * @return All list of device
     */
    public List<Device> getDevice() {
        return roomDeviceList;
    }

    /**
     * @param index
     *            position of device to selects
     * @return return the device whit position equals index
     */
    public Device getDevice(final int index) {
        // TODO controls for manage Exception
        return roomDeviceList.get(index);
    }

    /**
     * @return Type of room
     */
    public ERoom getRoom() {
        return this.roomType;
    }
}
