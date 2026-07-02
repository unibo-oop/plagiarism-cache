package aboidsim.util;

/**
 * This class represent a point in a cartesian plane.
 *
 *
 */
public class Vector {

	private double x;
	private double y;

	/**
	 * Public Constructor.
	 *
	 * @param xx
	 *            the position in the x-axis
	 * @param yy
	 *            the position in the y-axis.
	 */
	public Vector(final double xx, final double yy) {
		this.x = xx;
		this.y = yy;
	}

	/**
	 * Public Constructor.
	 *
	 * @param v
	 *            the vector wanted to be copied
	 */
	public Vector(final Vector v) {
		this.x = v.getX();
		this.y = v.getY();
	}

	/**
	 * Getter.
	 *
	 * @return the value of x
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Getter.
	 *
	 * @return the value of y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Setter.
	 *
	 * @param xVal
	 *            the new value of the x coordinate
	 */
	public void setX(final double xVal) {
		this.x = xVal;
	}

	/**
	 * Setter.
	 *
	 * @param yVal
	 *            the new value of the y coordinate
	 */
	public void setY(final double yVal) {
		this.y = yVal;
	}

	/**
	 * Basic addition.
	 *
	 * @param v
	 *            the vector wanted to be added
	 */
	public void add(final Vector v) {
		this.x += v.getX();
		this.y += v.getY();
	}

	/**
	 * Basic substraction.
	 *
	 * @param v
	 *            the vector wanted to be substracted
	 */
	public void sub(final Vector v) {
		this.x -= v.getX();
		this.y -= v.getY();
	}

	/**
	 * Scalar multiplication of a single number.
	 *
	 * @param z
	 *            the scalar you want each coordinate to be multiplied by
	 */
	public void mul(final double z) {
		this.x *= z;
		this.y *= z;
	}

	/**
	 * Scalar multiplication of two numbers.
	 *
	 * @param z1
	 *            the scalar you want the x-coordinate to be multiplied by
	 * @param z2
	 *            the scalar you want the y-coordinate to be multiplied by
	 */
	public void mul(final double z1, final double z2) {
		this.x *= z1;
		this.y *= z2;
	}

	/**
	 * Scalar division of a single number.
	 *
	 * @param z
	 *            the scalar you want each coordinate to be divided by*
	 * @throws IllegalArgumentException
	 *             if z equals 0
	 */
	public void div(final double z) throws IllegalArgumentException {
		if (z == 0) {
			throw new IllegalArgumentException("You cannot divide by zero.");
		} else {
			this.x /= z;
			this.y /= z;
		}
	}

	/**
	 * Scalar division of two numbers.
	 *
	 * @param z1
	 *            the scalar you want the x-coordinate to be divided by
	 * @param z2
	 *            the scalar you want the y-coordinate to be divided by
	 * @throws IllegalArgumentException
	 *             if z1 or z2 equal 0
	 */
	public void div(final double z1, final double z2) throws IllegalArgumentException {
		if ((z1 == 0) || (z2 == 0)) {
			throw new IllegalArgumentException("You cannot divide by zero");
		} else {
			this.x /= z1;
			this.y /= z2;
		}

	}

	/**
	 * The magnitude (or modulus) of the vector (|v|).
	 *
	 * @return the magnitude of the vector
	 */
	public double magnitude() {
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}

	/**
	 * The magnitude of the vector, but at the power of two. Use this method to
	 * speed up calculations. See magnitude() for details.
	 *
	 * @return the magnitude of the vector
	 */
	public double magnitudeSquared() {
		return (this.x * this.x) + (this.y * this.y);
	}

	/**
	 * The norm of a the vector. Each coordinate is scaled proportionally to its
	 * size in order to get a magnitude of 1.
	 */
	public void norm() {
		final double magnitude = this.magnitude();
		if (magnitude != 0) {
			this.div(magnitude);
		}
	}

	/**
	 * This method scale the magnitude of a Vector to maxValue.
	 *
	 * @param value
	 *            the value of the desired magnitude
	 */
	public void scaleTo(final double value) {
		if (value == 0) {
			this.x = 0.0;
			this.y = 0.0;
		} else {
			final double divFactor = this.magnitude() / value;
			this.x = this.x / divFactor;
			this.y = this.y / divFactor;
		}
	}

	/**
	 * This method scale the magnitude of a Vector to maxValue. If the magnitude
	 * of the vector is bigger than the input value the vector get reduced.
	 *
	 * @param maxValue
	 *            the value of the desired magnitude
	 */
	public void limitTo(final double maxValue) {
		if (this.magnitude() > maxValue) {
			this.scaleTo(maxValue);
		}
	}

	/**
	 * The distance between two vectors.
	 *
	 * @param v
	 *            the other vector
	 * @return the distance
	 */
	public double dist(final Vector v) {
		return Math.sqrt(Math.pow((v.getX() - this.x), 2) + Math.pow((v.getY() - this.y), 2));
	}

	/**
	 * Static method. Basic addition between two vectors.
	 *
	 * @param v1
	 *            the first vector
	 * @param v2
	 *            the second vector
	 * @return the new vector
	 */
	public static Vector add(final Vector v1, final Vector v2) {
		final Vector sum = new Vector(v1);
		sum.add(v2);
		return sum;
	}

	/**
	 * Static method. Basic substraction between two vectors.
	 *
	 * @param v1
	 *            the first vector
	 * @param v2
	 *            the second vector
	 * @return the new vector
	 */
	public static Vector sub(final Vector v1, final Vector v2) {
		final Vector diff = new Vector(v1);
		diff.sub(v2);
		return diff;
	}

	/**
	 * Utility method that prints the value of the coordinates.
	 */
	public void print() {
		System.out.println("X: " + this.x + " Y: " + this.y);
	}

	@Override
	public String toString() {
		final String str = new String("X: " + this.x + " | " + "Y: " + this.y);
		return str;
	}

}
