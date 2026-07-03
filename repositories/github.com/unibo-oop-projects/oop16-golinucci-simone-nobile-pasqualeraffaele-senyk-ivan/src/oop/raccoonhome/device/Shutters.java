package oop.raccoonhome.device;

import oop.raccoonhome.util.Log;

/**
 * 
 *
 */
public final class Shutters implements OpenCloseDevice {

    // indica se i motori per apertura/chiusura tapparella sono attivi
    private boolean status = false;
    // indica se la finestra è aperta o meno
    private boolean statusOpened = false;

    private EDevice propriety = EDevice.SHUTTER;

    @Override
    public void switchOn() {
        this.status = true;
        Log.getIstance().write("Shutter motor start");
    }

    @Override
    public void switchOff() {
        this.status = false;
        Log.getIstance().write("Shutter motor stop");

    }

    @Override
    public boolean isOn() {
        return this.status;
    }

    @Override
    public void open() {
        // Simuliamo l'accensione dei motori per apertura taparella
        this.switchOn();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.switchOff();
        Log.getIstance().write("Shutter opended");
        this.statusOpened = true;
    }

    @Override
    public void close() {
        // Simuliamo l'accensione dei motori per apertura taparella
        this.switchOn();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.switchOff();
        this.statusOpened = false;
        Log.getIstance().write("Shutter closed");
    }

    @Override
    public boolean isOpen() {
        return this.statusOpened;
    }

    @Override
    public EDevice getPropriety() {
        return this.propriety;
    }
}