package view.gamescreen;

import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

/**
 * 
 * Tank icon that gives info about the number of available tanks in a state and its owner.
 *
 */
public class TankIcon extends SVGPath {

    /**
     * 
     * Class constructor.
     * 
     * @param content
     *              Svg content to be drawn.
     */
    public TankIcon(final String content) {
        this.setContent(content);
    }

    /**
     * 
     * Update the color of the icon.
     * 
     * @param fill
     *              Paint value.
     * 
     */
    public void setColor(final Paint fill) {
        this.setFill(fill);
    }

}
