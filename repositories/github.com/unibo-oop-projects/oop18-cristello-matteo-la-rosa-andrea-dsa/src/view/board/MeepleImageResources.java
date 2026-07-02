package view.board;

import javax.swing.JComponent;

import controller.PlayerColor;
import model.utils.Direction;
/**
 * 
 * This class is the image supplier for player rapresentation on table (meeple).
 *
 */
public class MeepleImageResources extends JComponent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String imageAddress;
    /**
     * 
     * @param direction The player direction.
     * @param playerColor The player color.
     */
    public MeepleImageResources(final Direction direction, final PlayerColor playerColor) {
        super();
        this.imageAddress = "/" + direction.toString() + playerColor.toString() + ".png";
    }
    /**
     * 
     * @return The address of image.
     */
    public String getImageAddress() {
        return this.imageAddress;
    }

}
