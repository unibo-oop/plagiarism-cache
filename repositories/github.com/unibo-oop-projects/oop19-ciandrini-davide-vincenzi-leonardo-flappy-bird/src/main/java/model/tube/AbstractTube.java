package model.tube;

/**
 * This is the abstract class of the tubes that manage the same methods and variables they share.
 * This class was created to implement the Prototype Pattern.
 */
public abstract class AbstractTube {

    private static final double HEIGHT = 175;
    private static final double WIDTH = 55;
    private static final double POS_X = 600;
    private final String tubeImagePath;
    private double posY;

    /**
     * This is the method constructor which initialize the image path of the object.
     * @param tubeImagePath Image Path of the tube
     */
    public AbstractTube(final String tubeImagePath) {
        this.tubeImagePath = tubeImagePath;
    }

    /**
     * Method created to implement the Prototype Pattern.
     * @return a new Object which is only a copy of the first object created
     */
    public abstract AbstractTube copy();

    /**
     *
     * @return the initial coordinateX of the tube
     */
    public double getPosX() {
        return POS_X;
    }

    /**
     *
     * @return the initial coordinateY of the tube
     */
    public double getPosY() {
        return posY;
    }

    /**
     *
     * @return the width of the tube
     */
    public double getWidth() {
        return WIDTH;
    }

    /**
     *
     * @return the height of the tube
     */
    public double getHeight() {
        return HEIGHT;
    }

    /**
     *
     * @return the image path of the tube
     */
    public String getTubeImagePath() {
        return tubeImagePath;
    }

    /**
     * Set the new coordinate Y.
     * @param y Y coordinate
     */
    public void setPosY(final double y) {
        posY = y;
    }
}
