package spacesurvival.view.game.utilities.logiccolor;

import java.awt.Color;

/**
 * Logic color for life's boss.
 */
public class LogicColorBoss implements LogicColor {
    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor(final int maxLife, final int value) {
        return Color.RED;
    }
}
