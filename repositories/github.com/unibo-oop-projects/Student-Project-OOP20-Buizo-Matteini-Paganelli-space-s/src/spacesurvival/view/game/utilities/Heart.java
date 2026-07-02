package spacesurvival.view.game.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.path.Icon;
import spacesurvival.view.utilities.JImage;

/**
 * Implement a component view for heart of spaceShip.
 */
public class Heart extends JPanel {
    private static final long serialVersionUID = -4710411924530543464L;
    private final JLabel nHeart;

    /**
     * Initialize and create all graphics components.
     */
    public Heart() {
        super(new FlowLayout());
        super.setOpaque(false);
        final Dimension dimension = EngineImage.getSizeImageFromScale(
                Icon.HEART, ScaleOf.ICON_FULL, Screen.WIDTH_FULLSCREEN);

        final JImage iconHeart = new JImage(Icon.HEART, dimension);
        this.nHeart = new JLabel();
        this.add(iconHeart);
        this.add(this.nHeart);
    }

    /**
     * Set font all components.
     * @param font for all components.
     */
    public void setFontAll(final Font font) {
        this.nHeart.setFont(font);
    }

    /**
     * Set Color foreground of all components.
     * @param color foreground for all components.
     */
    public void setForegroundAll(final Color color) {
        this.nHeart.setForeground(color);
    }

    /**
     * Set n° heart of ship.
     * 
     * @param nHeart of ship.
     */
    public void setnHeart(final int nHeart) {
        this.nHeart.setText("x" + nHeart);
    }

    /**
     * Set n° heart of ship.
     * 
     * @param nHeart of ship.
     */
    public void setnHeart(final String nHeart) {
        this.nHeart.setText("x" + nHeart);
    }

}
