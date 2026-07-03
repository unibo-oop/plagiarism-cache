package zengine.core;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.Random;

import zengine.interfaces.GameFunctionsUtilities;

final class ZengineUtilities implements GameFunctionsUtilities {

    private static ZengineUtilities utilities = new ZengineUtilities();;

    private final Random rnd = new Random();

    private ZengineUtilities() {
    }

    public static ZengineUtilities getUtilities() {
        return utilities;
    }

    // it is safe to use @Safevarargs since nothing wheird is done with args
    @Override
    @SafeVarargs
    public final <T> T choose(final T... args) {
        if (args.length > 0) {
            final int choice = rnd.nextInt(args.length);
            return args[choice];
        } else {
            return null;
        }
    }

    @Override
    public <T extends Comparable<T>> T max(final T a, final T b) {
        if (a == null) {
            if (b == null) {
                return a;
            } else {
                return b;
            }
        }
        if (b == null) {
            return a;
        }
        return a.compareTo(b) > 0 ? a : b;
    }

    @Override
    public <T extends Comparable<T>> T min(final T a, final T b) {
        if (a == null) {
            if (b == null) {
                return a;
            } else {
                return b;
            }
        }
        if (b == null) {
            return a;
        }
        return a.compareTo(b) < 0 ? a : b;
    }

    @Override
    public <T extends Comparable<T>> T clamp(final T value, final T x1, final T x2) {
        if (value == null) {
            return null;
        }
        return max(min(value, max(x1, x2)), min(x1, x2));
    }

    @Override
    public <T extends Comparable<T>> boolean valueInRange(final T value, final T x1, final T x2) {
        if (value == null || x1 == null || x2 == null) {
            return false;
        }
        return (value.compareTo(min(x1, x2)) >= 0 && value.compareTo(max(x1, x2)) <= 0);
    }

    @Override
    public <T extends Comparable<T>> boolean rangeOverlaps(final T x1, final T x2, final T y1, final T y2) {
        if (x1 == null || x2 == null || y1 == null || y2 == null) {
            return false;
        }

        // would have declared these at the initial point of the method, but pmd
        // complains
        final T max1 = max(x1, x2);
        final T min1 = min(x1, x2);
        final T max2 = max(y1, y2);
        final T min2 = min(y1, y2);

        return (max1.compareTo(min2) > 0 && min1.compareTo(max2) < 0);
    }

    @Override
    public double pointDistance(final double x1, final double y1, final double x2, final double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    @Override
    public double pointDirection(final double x1, final double y1, final double x2, final double y2) {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if (angle <= 0) {
            angle += 360;
        }
        return 360 - angle;
    }

    @Override
    public boolean pointInCircle(final double pointX, final double pointY, final double circleX, final double circleY,
            final double radius) {
        return (pointDistance(pointX, pointY, circleX, circleY) <= radius);
    }

    @Override
    public boolean pointInRectangle(final double pointX, final double pointY, final double x1, final double y1, final double x2,
            final double y2) {
        return (valueInRange(pointX, x1, x2) && valueInRange(pointY, y1, y2));
    }

    @Override
    public boolean rectangleOverlaps(final double ax1, final double ay1, final double ax2, final double ay2, final double bx1,
            final double by1, final double bx2, final double by2) {
        return (rangeOverlaps(ax1, ax2, bx1, bx2) && rangeOverlaps(ay1, ay2, by1, by2));
    }

    @Override
    public double lengthDirX(final double len, final double dir) {
        final double ang = Math.toRadians(-dir);
        return Math.cos(ang) * len;
    }

    @Override
    public double lengthDirY(final double len, final double dir) {
        final double ang = Math.toRadians(-dir);
        return Math.sin(ang) * len;
    }

    @Override
    public boolean isRunningInJar() {
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        return (jarFile.isFile());
    }

    @Override
    public boolean rectangleOverlapsCircle(final double x1, final double y1, final double x2, final double y2,
            final double circleX, final double circleY, final double radius) {
        // calculate where the circle is in relation to the rectangle
        int pos = -1;
        boolean colliding = false;

        // just to dont create an enum for this method only
        final int topleft = 0;
        final int topcent = 1;
        final int topright = 2;
        final int midleft = 3;
        final int midcent = 4;
        final int midright = 5;
        final int botleft = 6;
        final int botcent = 7;
        final int botright = 8;

        if (circleX < x1) {
            if (circleY < y1) {
                pos = topleft;
            } else {
                if (circleY > y2) {
                    pos = botleft;
                } else {
                    pos = midleft;
                }
            }
        } else {
            if (circleX > x2) {
                if (circleY < y1) {
                    pos = topright;
                } else {
                    if (circleY > y2) {
                        pos = botright;
                    } else {
                        pos = midright;
                    }
                }
            } else {
                if (circleY < y1) {
                    pos = topcent;
                } else {
                    if (circleY > y2) {
                        pos = botcent;
                    } else {
                        pos = midcent;
                    }
                }
            }
        }

        // based on where it is, calculate if they intersect
        if (pos == topcent || pos == midleft || pos == midcent || pos == midright || pos == botcent) {
            if ((circleX > x1 - radius && circleX < x2 + radius) && (circleY > y1 - radius && circleY < y2 + radius)) {
                colliding = true;
            }
        } else {
            if (pos == topleft && pointDistance(circleX, circleY, x1, y1) < radius) {
                colliding = true;
            }
            if (pos == topright && pointDistance(circleX, circleY, x2, y1) < radius) {
                colliding = true;
            }
            if (pos == botleft && pointDistance(circleX, circleY, x1, y2) < radius) {
                colliding = true;
            }
            if (pos == botright && pointDistance(circleX, circleY, x2, y2) < radius) {
                colliding = true;
            }
        }
        return colliding;
    }

    private double[] pointRotate(final double x, final double y, final double centerX, final double centerY, final double angle) {
        // it must not be final, sorry pmd
        double[] pt = { x, y }; // //
        AffineTransform.getRotateInstance(Math.toRadians(-angle), centerX, centerY).transform(pt, 0, pt, 0, 1);
        // specifying to use this double[] to hold coords
        return pt;
    }

    @Override
    public double pointXrotate(final double x, final double y, final double centerX, final double centerY, final double angle) {
        return pointRotate(x, y, centerX, centerY, angle)[0];
    }

    @Override
    public double pointYrotate(final double x, final double y, final double centerX, final double centerY, final double angle) {
        return pointRotate(x, y, centerX, centerY, angle)[1];
    }

    @Override
    public int abs(final int num) {
        return Math.abs(num);
    }

    @Override
    public double abs(final double num) {
        return Math.abs(num);
    }

    @Override
    public double random(final double num) {
        return rnd.nextDouble() * num;
    }

    @Override
    public double randomRange(final double num1, final double num2) {
        final double x1 = min(num1, num2);
        final double x2 = max(num1, num2);
        final double range = x2 - x1;
        return random(range) + x1;
    }

    @Override
    public double wrapToModulus(final double value, final int modulus) {
        if (modulus == 0) {
            return 0;
        } else {
            final double discard = value - (int) value;
            final int n = modulus;
            int r = (int) value % n;
            if (r < 0) {
                r += n;
            }
            return r + discard;
        }
    }

    @Override
    public double angleValue(final double angle) {
        return wrapToModulus(angle, 360);
    }

    @Override
    public double angleDifference(final double angle1, final double angle2) {
        final double a1 = angleValue(angle1);
        final double a2 = angleValue(angle2);
        double angle = (abs(a1 - a2)) % 360;

        if (angle > 180) {
            angle = 360 - angle;
        }
        return angle;
    }

    @Override
    public double angleShortestRotationVerse(final double angle1, final double angle2) {
        final double a1 = angleValue(angle1);
        final double a2 = angleValue(angle2);
        if (a1 < a2) {
            if (abs(a1 - a2) < 180) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (abs(a1 - a2) < 180) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public double angleRotate(final double currentAngle, final double destinationAngle, final double rotSpeed) {
        final double ca = angleValue(currentAngle);
        final double da = angleValue(destinationAngle);
        double rs = clamp(rotSpeed, 0d, 180d);
        double rotVerse = 0;
        final double eps = 1e-5;
        final double minRs = 0.0001;

        if (abs(ca - da) > eps) {
            rs = clamp(rs, minRs, angleDifference(ca, da));
            rotVerse = angleShortestRotationVerse(ca, da);
            return angleValue(ca + rs * rotVerse);
        } else {
            return ca;
        }
    }

    @Override
    public double angleRotateHyperbolic(final double currentAngle, final double destinationAngle, final double slownessFactor) {
        return angleRotateHyperbolic(currentAngle, destinationAngle, slownessFactor, 0, 180);
    }

    @Override
    public double angleRotateHyperbolic(final double currentAngle, final double destinationAngle, final double slownessFactor,
            final double minSpeed, final double maxSpeed) {
        final double ca = angleValue(currentAngle);
        final double da = angleValue(destinationAngle);
        final double sf = max(slownessFactor, 1d);
        final double minS = minSpeed;
        final double maxS = maxSpeed;
        return angleRotate(ca, da, clamp(angleDifference(ca, da) / sf, minS, maxS));
    }

    @Override
    public double lerp(final double a, final double b, final double amount) {
        return a + amount * (b - a);
    }

    @Override
    public double power(final double base, final double exponent) {
        return Math.pow(abs(base), exponent);
    }
}
