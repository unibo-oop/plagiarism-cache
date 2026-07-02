package controller.data;

import constraints.DirOfMovement;

/**
 * This class holds all the parameters needed to create a road.
 */
public final class RoadData {

    private String name;
    private int lanesNumber;
    private DirOfMovement sense;

    public RoadData(final String name, final int lanesNumber, final DirOfMovement sense) {
        this.name = name;
        this.lanesNumber = lanesNumber;
        this.sense = sense;
    }

    public String getName() {
        return name;
    }

    public int getLanesNumber() {
        return lanesNumber;
    }

    public DirOfMovement getSense() {
        return sense;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setLanesNumber(final int lanesNumber) {
        this.lanesNumber = lanesNumber;
    }

    public void setSense(final DirOfMovement sense) {
        this.sense = sense;
    }

    @Override
    public String toString() {
        return "RoadData [name=" + name + ", lanesNumber=" + lanesNumber + ", sense=" + sense + "]";
    }
}
