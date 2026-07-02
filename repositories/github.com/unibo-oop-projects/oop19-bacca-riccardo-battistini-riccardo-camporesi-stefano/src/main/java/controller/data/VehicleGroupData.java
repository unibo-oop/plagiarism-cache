package controller.data;

import constraints.DirOfMovement;

/**
 * This class holds all the parameters needed to create a vehicle.
 */
public final class VehicleGroupData {

    private DirOfMovement sense;
    private int number;
    private int maxVel;

    public VehicleGroupData(final DirOfMovement sense, final int number, final int maxVel) {
        this.sense = sense;
        this.number = number;
        this.maxVel = maxVel;
    }

    public DirOfMovement getSense() {
        return sense;
    }

    public void setSense(final DirOfMovement sense) {
        this.sense = sense;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    /**
     * @return the maxVel
     */
    public int getMaxVel() {
        return maxVel;
    }

    /**
     * @param maxVel the maxVel to set
     */
    public void setMaxVel(final int maxVel) {
        this.maxVel = maxVel;
    }

    @Override
    public String toString() {
        return "VehicleData [sense=" + sense + ", number=" + number + "]";
    }
}
