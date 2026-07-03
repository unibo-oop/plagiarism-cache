package model.circuit;

import java.util.List;

import utility.Driver;
/**
 *Interface to manage the dimension of a piece of track and if it's a curve.
 * The interface also control which driver is in the piece.
 */
public interface Track {
    /**
     * Determinate the driver in the piece of track.
     * @return a list of the driver 
     */
    List<Driver> listDriverInTheTrack();
    /**
     * The length of a piece of track.
     * @return the length
     */
    int getLength();
    /**
     * Determinate if a piece is a turn or not.
     * @return if its a turn
     */
    boolean isATurn();
    /**
     * Getter.
     * @return the track's width
     */
    int getWidth();
}
