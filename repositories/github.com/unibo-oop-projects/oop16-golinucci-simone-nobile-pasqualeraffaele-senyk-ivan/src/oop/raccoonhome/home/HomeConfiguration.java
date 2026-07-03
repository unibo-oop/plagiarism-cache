package oop.raccoonhome.home;

import java.util.Arrays;

import oop.raccoonhome.device.Door;
import oop.raccoonhome.device.Lamp;
import oop.raccoonhome.device.Shutters;

/**
 * 
 *
 */
public final class HomeConfiguration {

    private boolean isSimulationActive = false;

    private final House house = new House();

    private static class ConfigurationHolder {
        private static final HomeConfiguration SINGLETON = new HomeConfiguration();
    }

    private HomeConfiguration() {
        this.makeRoom();
        this.makeDevice();
    }

    private void makeRoom() {
        final Room r1 = new Room(ERoom.BAGNO);
        final Room r2 = new Room(ERoom.CAMERADALETTO);
        final Room r3 = new Room(ERoom.SALOTTO);
        final Room r4 = new Room(ERoom.GARAGE);

        house.addRooms(Arrays.asList(r1, r2, r3, r4));
    }

    private void makeDevice() {
        final Lamp l1 = Lamp.getRightLamp();
        final Lamp l2 = Lamp.getLeftLamp();
        final Lamp l3 = Lamp.getLamp();

        final Shutters s1 = new Shutters();

        house.getRoom(ERoom.CAMERADALETTO).get(0).addDevices(Arrays.asList(l1, l2, l3, s1));

        final Lamp l4 = Lamp.getLamp();
        final Lamp l5 = Lamp.getTVLamp();
        final Lamp l6 = Lamp.getLeftLamp(); // Luce cucina
        final Shutters s2 = new Shutters();

        house.getRoom(ERoom.SALOTTO).get(0).addDevices(Arrays.asList(l4, l5, l6, s2));

        final Lamp l7 = Lamp.getMirrorLamp();
        final Lamp l8 = Lamp.getLamp();
        final Shutters s3 = new Shutters();

        house.getRoom(ERoom.BAGNO).get(0).addDevices(Arrays.asList(l7, l8, s3));

        final Lamp l9 = Lamp.getLamp();
        final Door d1 = new Door();

        house.getRoom(ERoom.GARAGE).get(0).addDevices(Arrays.asList(l9, d1));

    }

    /**
     * @return constructor
     * 
     */
    public static HomeConfiguration getIstance() {
        return ConfigurationHolder.SINGLETON;
    }

    /**
     * @return house
     */
    public House getHosue() {
        return this.house;
    }

    /**
     * @return true if isSimulationActive is true
     */
    public boolean isSimulationActive() {
        return this.isSimulationActive;
    }

    /**
     * @param isSimulationActive
     *            for disable/enable simulatiuon button
     */
    public void setSimActive(final boolean isSimulationActive) {
        this.isSimulationActive = isSimulationActive;
    }

}
