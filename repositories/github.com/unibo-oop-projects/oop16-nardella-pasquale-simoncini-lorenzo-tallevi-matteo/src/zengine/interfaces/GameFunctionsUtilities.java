package zengine.interfaces;

/**
 * This interfaces represents the methods provided by the Utilities component of
 * the Zengine. These methods are used for generic purpose calculations, such as
 * vector computation, math, randomness and more.
 *
 */
public interface GameFunctionsUtilities {

    /**
     * Returns true if zengine is running from inside an executable jar or file.
     * 
     * @return true if zengine is running from inside an executable file
     */
    boolean isRunningInJar();

    /**
     * returns the absolute value of num.
     * 
     * @param num
     *            number to transform
     * @return the absolute value of num
     */
    int abs(int num);

    /**
     * returns the absolute value of num.
     * 
     * @param num
     *            number to transform
     * @return the absolute value of num
     */
    double abs(double num);

    /**
     * returns a random number between 0 and num.
     * 
     * @param num
     *            maximum number to be generated
     * @return the generated number
     */
    double random(double num);

    /**
     * returns a random number between num1 and num2.
     * 
     * @param num1
     *            first edge of the range of numbers
     * @param num2
     *            decond edge of the range of numbers
     * @return the generated number
     */
    double randomRange(double num1, double num2);

    /**
     * Returns only one of the given arguments, chosen at random.
     * 
     * @param <T>
     *            type of the arguments
     * @param args
     *            list of the entities to pick from
     * @return one the argument being picked
     */
    // @SafeVarargs would not be appliable here
    @SuppressWarnings("unchecked")
    <T> T choose(T... args);

    /**
     * Returns the biggest value between a and b.
     * 
     * @param <T>
     *            type of the comparables
     * @param a
     *            first comparable
     * @param b
     *            second comparable
     * @return the max between a and b
     */
    <T extends Comparable<T>> T max(T a, T b);

    /**
     * Returns the smallest value between a and b.
     * 
     * @param <T>
     *            type of the comparables
     * @param a
     *            first comparable
     * @param b
     *            second comparable
     * @return the min between a and b
     */
    <T extends Comparable<T>> T min(T a, T b);

    /**
     * Returns value clamped between x1 and x2. If value is smaller than
     * min(x1,x2), min(x1,x2) is returned. if value is bigger than max(x1,x2),
     * max(x1,x2) is returned. Otherwise, value is returned unchanged.
     * 
     * @param <T>
     *            type of the comparables
     * @param value
     *            value to clamp
     * @param x1
     *            first limit value
     * @param x2
     *            second limit value
     * @return value clamped between x1 and x2
     */
    <T extends Comparable<T>> T clamp(T value, T x1, T x2);

    /**
     * Returns wether value is included inside the range values of min(x1,x2) -
     * max(x1,x2).
     * 
     * @param <T>
     *            type of the comparables
     * @param value
     *            value to check
     * @param x1
     *            first limit value
     * @param x2
     *            second limit value
     * @return true if value falls inside range min(x1,x2) - max(x1,x2)
     */
    <T extends Comparable<T>> boolean valueInRange(T value, T x1, T x2);

    /**
     * Returns wether the two ranges of values specified by x1-x2, y1-y2 have at
     * least one element in common.
     * 
     * @param <T>
     *            type of the comparables
     * @param x1
     *            limit of the first range
     * @param x2
     *            limit of the first range
     * @param y1
     *            limit of the second range
     * @param y2
     *            limit of the second range
     * @return true if ranges described by x1-x2, y1-y2 have at leas one element
     *         in common
     */
    <T extends Comparable<T>> boolean rangeOverlaps(T x1, T x2, T y1, T y2);

    /**
     * Returns the distance between point (x1,y2) to the point (x2,y2).
     * 
     * @param x1
     *            x coordinate of the first point
     * @param y1
     *            y coordinate of the first point
     * @param x2
     *            x coordinate of the second point
     * @param y2
     *            y coordinate of the second point
     * @return distance between point (x1,y1) and point (x2,y2)
     */
    double pointDistance(double x1, double y1, double x2, double y2);

    /**
     * Returns the direction formed between point (x1,y2) to the point (x2,y2),
     * in degrees. For example, if point2 is at the right of point1, it returns
     * 0. If it's above, returns 90, if at left, 180 and so on.
     * 
     * @param x1
     *            x coordinate of the first point
     * @param y1
     *            y coordinate of the first point
     * @param x2
     *            x coordinate of the second point
     * @param y2
     *            y coordinate of the second point
     * @return direction in degrees from point (x1,y1) to point (x2,y2)
     */
    double pointDirection(double x1, double y1, double x2, double y2);

    /**
     * Returns whether the point (pointX,pointY) is inside the circle centered
     * in (circleX,circleY) with radius radius.
     * 
     * @param pointX
     *            x coordinate of the point to check
     * @param pointY
     *            y coordinate of the point to check
     * @param circleX
     *            x coordinate of the center of the circle
     * @param circleY
     *            y coordinate of the center of the circle
     * @param radius
     *            radius of the circle
     * @return true if (pointX,pointY) falls inside the circle
     */
    boolean pointInCircle(double pointX, double pointY, double circleX, double circleY, double radius);

    /**
     * Returns whether the point pointX-pointY is inside the rectangle whose
     * top-left corner is x1-y1 and bot-right corner is x2-y2.
     * 
     * @param pointX
     *            x coordinate of the point to check
     * @param pointY
     *            y coordinate of the point to check
     * @param x1
     *            x coordinate of the top-left corner of the rectangle
     * @param y1
     *            y coordinate of the top-left corner of the rectangle
     * @param x2
     *            x coordinate of the bot-right corner of the rectangle
     * @param y2
     *            y coordinate of the bot-right corner of the rectangle
     * @return true if (pointX,pointY) falls inside the rectangle
     */
    boolean pointInRectangle(double pointX, double pointY, double x1, double y1, double x2, double y2);

    /**
     * Returns whether the given rectangles touch at least in one point.
     * Rectangles are defined from their top-left corner to their bot-right
     * corner
     * 
     * @param ax1
     *            x coordinate of the top-left corner of the first rectangle
     * @param ay1
     *            y coordinate of the top-left corner of the first rectangle
     * @param ax2
     *            x coordinate of the bot-right corner of the first rectangle
     * @param ay2
     *            y coordinate of the bot-right corner of the first rectangle
     * @param bx1
     *            x coordinate of the top-left corner of the second rectangle
     * @param by1
     *            y coordinate of the top-left corner of the second rectangle
     * @param bx2
     *            x coordinate of the bot-right corner of the second rectangle
     * @param by2
     *            y coordinate of the bot-right corner of the second rectangle
     * @return true if the two rectangles are touching
     */
    boolean rectangleOverlaps(double ax1, double ay1, double ax2, double ay2, double bx1, double by1, double bx2, double by2);

    /**
     * returns true if given rectangle is touching the given circle. The
     * rectangle is described by its top-left and bot-right corner, while the
     * circle is described by its center and radius.
     * 
     * @param x1
     *            x coordinate of the top-left corner of the rectangle
     * @param y1
     *            y coordinate of the top-left corner of the rectangle
     * @param x2
     *            x coordinate of the bot-right corner of the rectangle
     * @param y2
     *            y coordinate of the bot-right corner of the rectangle
     * @param circleX
     *            x coordinate of the center of the circle
     * @param circleY
     *            y coordinate of the center of the circle
     * @param radius
     *            radius of the circle
     * @return true if given rectangle is touching the given circle
     */
    boolean rectangleOverlapsCircle(double x1, double y1, double x2, double y2, double circleX, double circleY, double radius);

    /**
     * Returns the x component of the vector described by length and direction
     * (the horizontal cathetus of hypotenuse defined by length and direction).
     * 
     * @param len
     *            length of the vector
     * @param dir
     *            direction of the vector, in degrees
     * @return the length of the x coordinate of the vector
     */
    double lengthDirX(double len, double dir);

    /**
     * returns the y component of the vector described by length and direction
     * (the vertical cathetus of hypotenuse defined by length and direction).
     * 
     * @param len
     *            length of the vector
     * @param dir
     *            direction of the vector, in degrees
     * @return the length of the y coordinate of the vector
     */
    double lengthDirY(double len, double dir);

    /**
     * returns the x coordinate of the point (x,y) rotated around
     * (centerX,centerY) by the given angle.
     * 
     * @param x
     *            x coordinate of the point to rotate
     * @param y
     *            y coordinate of the point to rotate
     * @param centerX
     *            x coordinate of the center of the rotation
     * @param centerY
     *            y coordinate of the center of the rotation
     * @param angle
     *            rotation angle
     * @return x coordinate of the point after being rotated
     */
    double pointXrotate(double x, double y, double centerX, double centerY, double angle);

    /**
     * returns the y coordinate of the point (x,y) rotated around
     * (centerX,centerY) by the given angle.
     * 
     * @param x
     *            x coordinate of the point to rotate
     * @param y
     *            y coordinate of the point to rotate
     * @param centerX
     *            x coordinate of the center of the rotation
     * @param centerY
     *            y coordinate of the center of the rotation
     * @param angle
     *            rotation angle
     * @return y coordinate of the point after being rotated
     */
    double pointYrotate(double x, double y, double centerX, double centerY, double angle);

    /**
     * returns the value wrapped inside a range described the given modulus (the
     * modulus is like the period of a periodic function). For example,
     * wrapToModulus(375,360) returns 15, wrapToModulus(720,360) returns 0 and
     * wrapToModulus(-90, 360) returns 270.
     * 
     * @param value
     *            value to be wrapped
     * @param modulus
     *            width of the modulus to wrap the value
     * @return the wrapped value
     */
    double wrapToModulus(double value, int modulus);

    /**
     * returns an angle normalized between 0 and 360 degrees.
     * 
     * @param angle
     *            angle to be normalized
     * @return the angle normalized between 0 and 360 degrees
     */
    double angleValue(double angle);

    /**
     * returns the smallest angle difference between angle1 and angle2.
     * 
     * @param angle1
     *            first angle to be compared
     * @param angle2
     *            second angle to compare
     * @return the smallest angle difference between two angles
     */
    double angleDifference(double angle1, double angle2);

    /**
     * calculates the shortest rotation verse (clockwise or counter-clockwise)
     * to go from angle 1 to angle2.
     * 
     * @param angle1
     *            starting angle
     * @param angle2
     *            ending angle
     * @return 1 if the shortest path is counter-clockwise, -1 if clockwise
     */
    double angleShortestRotationVerse(double angle1, double angle2);

    /**
     * rotates an angle toward a destinationAngle, following the shortest
     * rotation verse, by rotSpeed degrees. Returns the rotated angle.
     * 
     * @param currentAngle
     *            angle to be rotated
     * @param destinationAngle
     *            destination of the rotation
     * @param rotSpeed
     *            how much degrees currentAngle will be rotated toward
     *            destinationAngle
     * @return the rotated angle
     */
    double angleRotate(double currentAngle, double destinationAngle, double rotSpeed);

    /**
     * rotates an angle toward a target angle, following the shortest rotation
     * verse, according to the given slowness factor. The rotationSpeed is
     * directly proportional to the angular distance between the currentAngle
     * and the destinationAngle, this means that if the rotation speed will be
     * maximum at the beginning and will slow down until it reaches the target
     * angle.
     * 
     * @param currentAngle
     *            angle to be rotated
     * @param destinationAngle
     *            destination of the rotation
     * @param slownessFactor
     *            how slow the rotation is (1 or less=instantaneous, 2=very
     *            fast, 3=fast, ... 10=slow etc)
     * @return the rotated angle
     */
    double angleRotateHyperbolic(double currentAngle, double destinationAngle, double slownessFactor);

    /**
     * rotates an angle toward a target angle, following the shortest rotation
     * verse, according to the given slowness factor. The rotationSpeed is
     * directly proportional to the angular distance between the currentAngle
     * and the destinationAngle, this means that if the rotation speed will be
     * maximum at the beginning and will slow down until it reaches the target
     * angle. You can also specify a minimum and maximum rotation speed.
     * 
     * @param currentAngle
     *            angle to be rotated
     * @param destinationAngle
     *            destination of the rotation
     * @param slownessFactor
     *            how slow the rotation is (1 or less=instantaneous, 2=very
     *            fast, 3=fast, ... 10=slow etc)
     * @param minSpeed
     *            minimum rotation speed, in degrees
     * @param maxSpeed
     *            maximum rotation speed, in degrees
     * @return the rotated angle
     */
    double angleRotateHyperbolic(double currentAngle, double destinationAngle, double slownessFactor, double minSpeed,
            double maxSpeed);

    /**
     * returns the value that equates to the position between two other values
     * for a given percentage. So if you do, for example: lerp(0, 10, 0.5) you
     * would get the return value of 5, which is 50% of the input values. You
     * can extrapolate using this function too, by supplying a positive or
     * negative value for the interpolation amount so that doing something like:
     * lerp(0, 10, 2) will return a value of 20.
     * 
     * @param a
     *            first value
     * @param b
     *            second value
     * @param amount
     *            amount to interpolate
     * @return interpolated value
     */
    double lerp(double a, double b, double amount);

    /**
     * returns the value of a number multiplied by itself "n" number of times.
     * For example, power(5,3) will multiply 5 by itself 3 times and return 125,
     * which is the same as saying 5*5*5=125. Please note that the "base" value
     * (the number to change) is taken as absolute value.
     * 
     * @param base
     *            base of the power
     * @param exponent
     *            exponent of the power
     * @return abs(base)^exponent
     */
    double power(double base, double exponent);
}
