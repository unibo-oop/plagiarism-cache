package view.board;



import view.DimensionUtils;

/**
 * This class implement the CoordinatesTemplate, giving to table a circular shape.
 */
public class CoordinatesCircularImpl extends AbstractCoordinates {

    private static final Integer WIDE = 230;
    private static final Integer XCENTERGAP = 200;
    private static final Integer YCENTERGAP = 25;
    private static final Integer CIRCLE = 360;

    /**
     * The class constructor.
     * 
     * @param lenght
     *                   The lenght of tiles line.
     */
    public CoordinatesCircularImpl(final Integer lenght) {
        super(lenght);
    };
    /**
     * This method populate of 2d point the entire ArrayList.
     * 
     * @param lenght
     *                   The number of point 2d to populate.
     */
    public void populateCoordinates(final Integer lenght) {

        final double angle = Math.toRadians(CIRCLE / lenght);
        final int w = DimensionUtils.xForScreenDimension(WIDE);
        final int center = w;
        this.setBoatCoordinates(new Point2DImpl(w + DimensionUtils.xForScreenDimension(XCENTERGAP), w));

        for (int i = 0; i < lenght; i++) {

            final double ia = i * angle;
            final int x2 = center + (int) (Math.sin(ia) * (w)) + DimensionUtils.xForScreenDimension(XCENTERGAP);
            final int y2 = center - (int) (Math.cos(ia) * (w)) + DimensionUtils.yForScreenDimension(YCENTERGAP);
            this.getCoordinates().add(new Point2DImpl(x2, y2));
        }
    }

}
