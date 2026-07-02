package model.common;

public final class PositionImpl implements Position {

    private double xPosition;
    private double yPosition;

    public PositionImpl() {
        xPosition = 0;
        yPosition = 0;
    }

    public PositionImpl(final double x, final double y) {
        xPosition = x;
        yPosition = y;
    }

    public PositionImpl(final Position position) {
        this(position.getX(), position.getY());
    }

    @Override
    public double getX() {
        return this.xPosition;
    }

    @Override
    public double getY() {
        return this.yPosition;
    }

    @Override
    public void update(final Movement movement) {
        this.sum(movement.getXMovement(), movement.getYMovement());
    }

    private void sum(final double deltaX, final double deltaY) {
        this.xPosition = this.xPosition + deltaX;
        this.yPosition = this.yPosition + deltaY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(xPosition);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yPosition);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PositionImpl other = (PositionImpl) obj;
        if (Double.doubleToLongBits(xPosition) != Double.doubleToLongBits(other.xPosition))
            return false;
        if (Double.doubleToLongBits(yPosition) != Double.doubleToLongBits(other.yPosition))
            return false;
        return true;
    }

    @Override
    public Position getSumPosition(final double x, final double y) {
        return new PositionImpl(this.xPosition + x, this.yPosition + y);
    }

}
