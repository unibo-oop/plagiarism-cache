package element;

public class Vector2DImpl implements Vector2D {

    private final double xComponent;
    private final double yComponent;

    public Vector2DImpl(final double xComponent, final double yComponent) {
        final int precision = 8;
        this.xComponent = Elements.round(xComponent, precision);
        this.yComponent = Elements.round(yComponent, precision);

    }

    private void nullVectorCheck() throws IllegalStateException {
        if (this.isNullVector()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public double getXComponent() {
        return this.xComponent;
    }

    @Override
    public double getYComponent() {
        return this.yComponent;
    }

    @Override
    public double getRadiansAngle() throws IllegalStateException {
        this.nullVectorCheck();
        return Math.atan2(this.getYComponent(), this.getXComponent());
    }

    @Override
    public double getDegreesAngle() throws IllegalStateException {
        return Math.toDegrees(this.getRadiansAngle());
    }

    @Override
    public boolean isNullVector() {
        return this.getXComponent() == 0 && this.getYComponent() == 0;
    }

    @Override
    public double getModulus() {
        return Math.sqrt(Math.pow(this.getXComponent(), 2) + Math.pow(this.getYComponent(), 2));
    }

    @Override
    public Vector2D getNormalizedVector() throws ArithmeticException {
        this.nullVectorCheck();
        double modulus = this.getModulus();
        return new Vector2DImpl(this.getXComponent() / modulus, this.getYComponent() / modulus);
    }

    @Override
    public Vector2D scalarMultiplication(final double scalar) {
        return new Vector2DImpl(this.getXComponent() * scalar, this.getYComponent() * scalar);
    }

    @Override
    public boolean hasSameNormalizedVector(final Vector2D vector2D) throws IllegalStateException {
        return this.hasSameNormalizedVector(vector2D, 4);
    }

    @Override
    public boolean hasSameNormalizedVector(final Vector2D vector2D, final int precision) throws IllegalStateException {
        double delta = Math.pow(10, precision);
        if (this.isNullVector() || vector2D.isNullVector()) {
            throw new IllegalStateException();
        }
        Vector2D v1 = this.getNormalizedVector();
        Vector2D v2 = vector2D.getNormalizedVector();
        return Math.abs(v1.getXComponent() - v2.getXComponent()) <= delta && Math.abs(v1.getYComponent() - v2.getYComponent()) <= delta;

    }

    @Override
    public String toString() {
        return "Vector2D ( x: " + this.xComponent + ", y :" + this.yComponent + " )";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2DImpl)) return false;

        final Vector2DImpl vector2D = (Vector2DImpl) o;

        if (Double.compare(vector2D.getXComponent(), this.getXComponent()) != 0) return false;
        return Double.compare(vector2D.getYComponent(), this.getYComponent()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.getXComponent());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.getYComponent());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
