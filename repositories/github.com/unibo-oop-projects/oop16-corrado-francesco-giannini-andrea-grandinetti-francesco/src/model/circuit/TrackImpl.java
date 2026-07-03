package model.circuit;

import java.util.ArrayList;
import java.util.List;

import utility.Driver;
/**
 * Track Interface implementation.
 */
public class TrackImpl implements Track {
    private final List<Driver> driversInTheTrack;
    private final int lenght;
    private final boolean isTurn;
    private final int width;
    /**
     * Constructor.
     * @param lenght of a piece of track
     * @param width  of a piece of track
     * @param isTurn if it's a turn or not
     */
    public TrackImpl(final int lenght, final int width, final boolean isTurn) {
        this.lenght = lenght;
        this.width = width;
        this.isTurn = isTurn;
        this.driversInTheTrack = new ArrayList<>();
    }

    @Override
    public int getLength() {
        return this.lenght;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public boolean isATurn() {
        return this.isTurn;
    }

    @Override
    public List<Driver> listDriverInTheTrack() {
        return this.driversInTheTrack;
    }

}
