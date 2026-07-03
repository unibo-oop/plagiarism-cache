package oop.raccoonhome.device;

import oop.raccoonhome.util.Log;

/**
 * You can use this class for represent a door.
 *
 */
public final class Door implements OpenCloseDevice {

    private boolean status = false;
    private boolean opened = false;
    private final EDevice propriety = EDevice.DOOR;
    @Override
    public void switchOn() {
        if (this.isOpen()) {
            this.status = true;
            Log.getIstance().write("Door Opened");
        }
    }

    @Override
    public void switchOff() {
        this.status = false;
        Log.getIstance().write("Door Closed");
    }

    @Override
    public boolean isOn() {
        return this.status;
    }

    @Override
    public void open() {
        this.opened = true;
        Log.getIstance().write("Door Unlocked");
    }

    @Override
    public void close() {
        if (!this.isOn()) {
            this.opened = false;
            Log.getIstance().write("Door Locked");
        }
    }

    @Override
    public boolean isOpen() {
        return this.opened;
    }

    @Override
    public EDevice getPropriety() {
        return this.propriety;
    }
}