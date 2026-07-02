package model.common;

public final class MovementImpl implements Movement {

    private static final double DEFAULT_POSITION = 0;
    private double xMovement;
    private double yMovement;

    public MovementImpl(final double xMovement, final double yMovement) {
        super();
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }

    public MovementImpl() {
        this(DEFAULT_POSITION, DEFAULT_POSITION);
    }

    @Override
    public void setMovement(final double xMovement, final double yMovement) {
        this.xMovement = xMovement;
        this.yMovement = yMovement;

    }

    @Override
    public double getXMovement() {
        return this.xMovement;
    }

    @Override
    public double getYMovement() {
        return this.yMovement;
    }

    @Override
    public void mul(final double speed) {
        this.xMovement = this.xMovement * speed;
        this.yMovement = this.yMovement * speed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(xMovement);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yMovement);
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
        final MovementImpl other = (MovementImpl) obj;
        if (Double.doubleToLongBits(xMovement) != Double.doubleToLongBits(other.xMovement))
            return false;
        if (Double.doubleToLongBits(yMovement) != Double.doubleToLongBits(other.yMovement))
            return false;
        return true;
    }

}
