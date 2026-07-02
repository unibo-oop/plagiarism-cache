package spacesurvival.view.game.utilities.logiccolor;
import java.awt.Color;

public interface LogicColor {
    /**
     * Get the logic of color from life.
     * @param maxLife of gameObject.
     * @param value of life.
     * @return color.
     */
    Color getColor(int maxLife, int value);
}
