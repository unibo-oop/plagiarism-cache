package model;

import utility.Position;

/**
 * Class to manage the position of every car.
 *
 */
public class PositionImpl implements Position {
    private int x;
    private int y;
    private int trackNumber;
    /**
     * Constructor.
     * @param x horizontal position
     * @param y vertical position
     * @param trackNumber track number
     */
    public PositionImpl(final int x, final int y, final int trackNumber) {
        this.x = x;
        this.y = y;
        this.trackNumber = trackNumber;
    }
    /**
     * Constructor.
     */
    public PositionImpl() {
        this(0, 0, 0);
    }

    /**
     * Constructor.
     * @param pos the position to copy
     */
    public PositionImpl(final Position pos) {
        this(pos.getX(), pos.getY(), pos.getTrackNumber());
    }

    @Override
    public void move(final int x, final int y) { // il controllo del cambio tracciato viene fatto prima
        this.x = x;
        this.y = y;
    } 

    @Override
    public void changeTrack(final int trackNumber) {
        this.trackNumber = trackNumber;
        this.x = 0;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getTrackNumber() {
        return this.trackNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + trackNumber;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (trackNumber != other.getTrackNumber()) {
            return false;
        }
        if (x != other.getX()) {
            return false;
        }
        return y == other.getY();
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.trackNumber + ")";
    }
}
