package model.utilities;

/**
 * 
 * Represents the initialization of the ball and paddle objects.
 *
 */
public enum ObjectInit {

    /**
     * default states.
     */
    PADDLE(new Position(290, 540), 78, 20),

    /**
     * default states.
     */
    BALL(new Position(330, 530), 10, 10);

    private final Position pos;
    private final int initHeight;
    private final int initWidth;

    ObjectInit(final Position position, final int initWidth, final int initHeight) {
        this.initHeight = initHeight;
        this.pos = position;
        this.initWidth = initWidth;
    }

    /**
     * 
     * @return start position
     */
    public Position getStartPos() {
        return pos;
    }

    /**
     * 
     * @return start height
     */
    public int getInitHeight() {
        return initHeight;
    }

    /**
     * 
     * @return start width
     */
    public int getInitWidth() {
        return initWidth;
    }
}
