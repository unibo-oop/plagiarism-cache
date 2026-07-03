package oop.raccoonhome.controller;

import oop.raccoonhome.device.Device;
import oop.raccoonhome.device.Lamp;
import oop.raccoonhome.device.OpenCloseDevice;
import oop.raccoonhome.util.Log;

/**
 * 
 *
 */
public class AllController {

    private static String on = "Lamp is on";
    private static String alreadyOn = "Lamp is already on";
    private static String off = "Lamp is off";
    private static String alreadyOff = "Lamp is already off";
    private static String open = "Shutter is open";
    private static String alreadyOpen = "Shutter is already Open";
    private static String close = "Shutter is close";
    private static String alreadyClose = "Shuter is already close";
    private static final int MAX_INTENSITY = 100;
    private static final int MIN_INTENSITY = 0;

    /**
     * 
     * @param device
     *            variable for check if lamp is On or not
     */
    public void turnOnLamp(final Device device) {

        if (device.isOn()) {
            Log.getIstance().write(alreadyOn);
        } else {
            device.switchOn();
            Log.getIstance().write(on);
            ((Lamp) device).setIntensity(MAX_INTENSITY);
        }
    }

    /**
     * 
     * @param device
     *            variable for check if lamp is On or not
     */
    public void turnOffLamp(final Device device) {

        if (device.isOn()) {
            device.switchOff();
            ((Lamp) device).setIntensity(MIN_INTENSITY);
            Log.getIstance().write(off);
        } else {
            Log.getIstance().write(alreadyOff);
        }
    }

    /**
     * 
     * @param device
     *            variable for check if lamp is On or not
     */
    public void openShutter(final OpenCloseDevice device) {
        if (device.isOpen()) {
            Log.getIstance().write(alreadyOpen);
        } else {
            device.open();
            Log.getIstance().write(open);
        }
    }

    /**
     * 
     * @param device
     *            variable for check if lamp is On or not
     */
    public void closeShutter(final OpenCloseDevice device) {
        if (device.isOpen()) {
            device.close();
            Log.getIstance().write(close);
        } else {
            Log.getIstance().write(alreadyClose);
        }
    }

    /**
     * @param device
     *            open the door if it's unlocked
     */
    public void openDoor(final OpenCloseDevice device) {
        device.switchOn();
    }

    /**
     * @param device
     *            close the door
     */
    public void closeDoor(final OpenCloseDevice device) {
        device.switchOff();
    }

    /**
     * 
     * @param device
     *            pass the Device wich will call his metod for set intensity
     * @param intensity
     *            variable double for set intensity
     */
    public void setIntensiryDevice(final Lamp device, final double intensity) {
        device.setIntensity((int) intensity);
    }

    /**
     * 
     * @param password
     *            CheckPassword for ability button for open Garage
     * @return Return true or false
     */
    public boolean checkPassword(final String password) {
        return password.equals("RaccoonTeam");
    }
}
