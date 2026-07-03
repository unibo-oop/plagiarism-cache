package utility;
/**
 * Interface to manage the position of a car.
 *
 */
public interface Position {

    /**
     * Move the car replacing the coordinate.
     * @param x coordinate (horizontal)
     * @param y coordinate (vertical)
     */
    void move(int x, int y);

    /**
     * Change the track where the car is located.
     * @param trackNumber the new track
     */
    void changeTrack(int trackNumber);

    /**
     * 
     * @return x coordinate
     */
    int getX();

    /**
     * 
     * @return y coordinate
     */
    int getY();

    /**
     * 
     * @return track position
     */
    int getTrackNumber();
}