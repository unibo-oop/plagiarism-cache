package model.tube;

public class TubeUp extends AbstractTube {

    private static final int MAX = 175; //Max height of the tubes
    private static final int MIN = 60; //Min height of the tubes

    public TubeUp(final String tubeImagePath) {
        super(tubeImagePath);
    }


    @Override
    public final TubeUp copy() {

        return new TubeUp(this.getTubeImagePath());
    }

    /**
     * Set the random coordinateY of the top tube.
     */
    public void setY() {
        setPosY(Math.random() * (MAX - MIN) + MIN);
    }
}
