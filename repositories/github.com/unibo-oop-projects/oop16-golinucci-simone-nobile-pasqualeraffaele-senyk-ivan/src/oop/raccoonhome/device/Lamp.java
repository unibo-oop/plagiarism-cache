package oop.raccoonhome.device;

import oop.raccoonhome.util.Log;

/**
 * 
 *
 */
public final class Lamp implements Device {

    private static final double MAX_INTENSITY = 100;
    private static final double MIN_INTENSITY = 0;

    private boolean status = false;
    private EDevice propriety = EDevice.LAMP;
    private int intensity = 0;

    private Lamp(final EDevice propriety) {
        setPropriety(propriety);
    }

    /**
     * @return instance of lamp: type LAMP
     */
    public static Lamp getLamp() {
        return new Lamp(EDevice.LAMP);
    }

    /**
     * @return instance of left lamp: type LAMP_L
     */
    public static Lamp getLeftLamp() {
        return new Lamp(EDevice.LAMP_L);
    }

    /**
     * @return instance of right lamp: type LAMP_R
     */
    public static Lamp getRightLamp() {
        return new Lamp(EDevice.LAMP_R);
    }

    /**
     * @return instance of tv lamp: type LAMP_TV
     */
    public static Lamp getTVLamp() {
        return new Lamp(EDevice.LAMP_TV);
    }

    /**
     * @return instance of mirror lamp: type LAMP_MIRROR
     */
    public static Lamp getMirrorLamp() {
        return new Lamp(EDevice.LAMP_MIRROR);
    }

    @Override
    public void switchOn() {
        this.status = true;
        Log.getIstance().write("lamp is ON");
    }

    @Override
    public void switchOff() {
        this.status = false;
        Log.getIstance().write("lamp is OFF");

    }

    @Override
    public boolean isOn() {
        return this.status;
    }

    @Override
    public EDevice getPropriety() {
        return this.propriety;
    }

    /**
     * Set a new propriety, you cannot insert DOOR or SHUTTER propriety.
     * @param dev
     *            device types
     */
    public void setPropriety(final EDevice dev) {
        if (dev != EDevice.DOOR || dev != EDevice.SHUTTER) {
            this.propriety = dev;
        }
    }

    /**
     * @return value of lamp intensity
     */
    public int getIntensity() {
        return intensity;
    }

    /**
     * @param intensity
     *            of lamp
     * @return true if the value of intensity is right (0<intensity<100), else
     *         return false (0>intensity || intensity>100)
     */
    public boolean setIntensity(final int intensity) {
        if ((intensity <= MAX_INTENSITY) && (intensity >= MIN_INTENSITY)) {
            this.intensity = intensity;
            Log.getIstance().write("Intensity is:" + intensity);
            return true;
        }
        Log.getIstance().write("ERROR: Intensity is:" + intensity);
        return false;
    }
}
