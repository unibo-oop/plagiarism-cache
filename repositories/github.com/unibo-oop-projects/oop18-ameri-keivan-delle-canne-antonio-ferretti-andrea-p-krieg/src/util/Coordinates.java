package util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *a class representing 2d coordinates.
 */
public class Coordinates {

    private final int x;
    private final int y;

    /**
     * 
     * @param x the x
     * @param y the y
     */
    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * 
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /**
     * {@inheritDoc}
     */
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
        final Coordinates other = (Coordinates) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CoordinatesImpl [x=" + x + ", y=" + y + "]";
    }

    /**
     * @param c1 first coordinate
     * @param c2 second coordinate
     * @return the distance between the passed coordinates
     */
    public static int distance(final Coordinates c1, final Coordinates c2) {
        return (int) Math.max(Math.abs(c1.getX() - c2.getX()), Math.abs(c1.getY() - c2.getY()));
    }

    /**
     * @param c1 first coordinate
     * @param c2 second coordinate
     * @return the sum of the two coordinates
     */
    public static Coordinates sum(final Coordinates c1, final Coordinates c2) {
        return new Coordinates(c1.getX() + c2.getX(), c1.getY() + c2.getY());
    }

    /**
     * @param c1 first coordinate
     * @param c2 second coordinate
     * @return the first minus the second coordinate
     * @throws IllegalArgumentException if c1 < c2
     */

    public static Coordinates subtract(final Coordinates c1, final Coordinates c2) {
        return sum(c1, new Coordinates(-c2.getX(), -c2.getY()));
    }

    /**
     * 
     * @param cords    the coordinates to start with
     * @param distance the distance from the initial coordinates
     * @param limitX   the x limit (min is 0)
     * @param limitY   the y limit (min is 0)
     * @return the set of coordinates in the given range from cords, excluding cords
     */
    public static Set<Coordinates> getCoordinatesRange(final Coordinates cords, final int distance, final int limitX,
            final int limitY) {
        final Set<Coordinates> result = new HashSet<Coordinates>();
        for (int i = 1; i <= distance; i++) {
            for (int j = 1; j <= distance; j++) {
                result.add(new Coordinates(cords.getX() + j, cords.getY()));
                result.add(new Coordinates(cords.getX() - j, cords.getY()));
                result.add(new Coordinates(cords.getX(), cords.getY() - i));
                result.add(new Coordinates(cords.getX(), cords.getY() + i));
                result.add(new Coordinates(cords.getX() + j, cords.getY() + i));
                result.add(new Coordinates(cords.getX() - j, cords.getY() + i));
                result.add(new Coordinates(cords.getX() + j, cords.getY() - i));
                result.add(new Coordinates(cords.getX() - j, cords.getY() - i));
            }
        }
        return result.stream().filter(c -> limitX > c.getX() && c.getX() >= 0 && limitY > c.getY() && c.getY() >= 0)
                .collect(Collectors.toSet());
    }
}
