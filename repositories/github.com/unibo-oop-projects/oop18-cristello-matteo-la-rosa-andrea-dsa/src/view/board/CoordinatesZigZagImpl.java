package view.board;

import view.DimensionUtils;

/**
 * This class implement the CoordinatesTemplate, giving to table a ZigZag shape.
 */
public class CoordinatesZigZagImpl extends AbstractCoordinates  {

    private static final Integer NOFPEAK = 4;
    private static final Integer XSPACE = 25;
    private static final Integer YSPACE = 30;
    private static final Integer XDIMENSION = 50;
    private static final Integer YDIMENSION = 50; // Dimension giver


    /**
     * Class constructor.
     * 
     * @param lenght
     *                   The number of point 2d needed.
     */
    public CoordinatesZigZagImpl(final Integer lenght) {
        super(lenght);
    }

    /**
     * This method populate of 2d point the entire ArrayList.
     * 
     * @param lenght
     *                   The number of point 2d to populate.
     */
    public void populateCoordinates(final Integer lenght) {
        int pX = DimensionUtils.xForScreenDimension(XDIMENSION);
        int pY = DimensionUtils.yForScreenDimension(YDIMENSION);
        this.setBoatCoordinates(new Point2DImpl(0, 0));

        for (int i = 0; i < lenght; i++) {
            if (i % (lenght / (NOFPEAK)) <= (lenght / (NOFPEAK * 2))) {
                pX += DimensionUtils.xForScreenDimension(XSPACE);
                pY += DimensionUtils.yForScreenDimension(YSPACE);
            } else {
                pX += DimensionUtils.xForScreenDimension(XSPACE);
                pY -= DimensionUtils.yForScreenDimension(YSPACE);
            }
            this.getCoordinates().add(new Point2DImpl(pX, pY));
        }
    }

}
