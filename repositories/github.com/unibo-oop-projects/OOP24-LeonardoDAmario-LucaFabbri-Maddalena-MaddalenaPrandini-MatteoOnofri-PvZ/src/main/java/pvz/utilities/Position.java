package pvz.utilities;

/**
 * Represents a two-dimensional position in the game world, typically used
 * to place entities on the grid or to track their movement.
 *
 * @param x the horizontal coordinate
 * @param y the vertical coordinate
 */
public record Position(double x, double y) {
}
