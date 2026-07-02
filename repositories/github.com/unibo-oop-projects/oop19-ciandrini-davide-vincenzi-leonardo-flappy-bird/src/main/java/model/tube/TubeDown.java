package model.tube;

public class TubeDown extends AbstractTube {
    /**
     * This is the distance between the two tubes.
     */
    private static final int DISTANCE = 105;


    public TubeDown(final String tubeImagePath) {
        super(tubeImagePath);
    }

    @Override
    public final TubeDown copy() {
        return new TubeDown(this.getTubeImagePath());
    }

    /**
     * Set the coordinateY of the bottom tube related to the top tube's coordinate Y.
     * @param y top tube's coordinateY
     */
    public void setY(final double y) {
        setPosY(y + DISTANCE);

    }

}
