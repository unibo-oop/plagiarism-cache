package spacesurvival.view.game.utilities.logiccolor;

import java.awt.Color;

/**
 * Logic color for life's spaceShip.
 */
public class LogicColorShip implements LogicColor {
    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(final int maxLife, final int value) {
        final double maxColValue = 255;

        final double redValue = (value > ((double) maxLife / 2) ? 1 - 2 * (value - ((double) maxLife / 2)) / maxLife : 1.0) * maxColValue;
        final double greenValue = (value > ((double) maxLife / 2) ? 1.0 : 2 * value / (double) maxLife) * maxColValue;
        final double blueValue = 0;

        return value <= 0 ? Color.RED : value >= maxLife ? Color.GREEN : new Color((int) redValue, (int) greenValue, (int) blueValue);
    }

}
