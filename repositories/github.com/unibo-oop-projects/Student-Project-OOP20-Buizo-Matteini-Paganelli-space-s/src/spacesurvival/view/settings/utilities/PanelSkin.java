package spacesurvival.view.settings.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spacesurvival.model.EngineImage;
import spacesurvival.model.gui.settings.EngineSettings;
import spacesurvival.view.utilities.FactoryGUIs;
import spacesurvival.view.utilities.JImage;

/**
 * Implements a view component for skin switching.
 */
public class PanelSkin extends JPanel {
    private static final long serialVersionUID = 3916840550754860452L;
    private final JLabel lbTitle;
    private final JButton btSX;
    private final JButton btDX;

    private final JImage pnImage;

    /**
     * Initialize all skin components.
     */
    public PanelSkin() {
        super(new BorderLayout());
        super.setOpaque(false);

        this.lbTitle = new JLabel();
        this.btSX  = new JButton(EngineSettings.DIR_SX);
        this.btDX = new JButton(EngineSettings.DIR_DX);
        this.pnImage = new JImage();
        this.createGraphics();
    }

    /**
     * Create the graphic part of the component.
     */
    private void createGraphics() {
        this.add(FactoryGUIs.encapsulateInPanelVerticalCenter(this.btSX), BorderLayout.WEST);
        this.add(FactoryGUIs.encapsulatesInPanelFlow(this.lbTitle), BorderLayout.NORTH);
        this.add(this.pnImage, BorderLayout.CENTER);
        this.add(FactoryGUIs.encapsulateInPanelVerticalCenter(this.btDX), BorderLayout.EAST);
    }

    /**
     * Set title component Skin.
     * @param title for component.
     */
    public void setLabelTitle(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * Set font all buttons.
     * @param font for font's buttons.
     */
    public void setFontButtons(final Font font) {
        this.btSX.setFont(font);
        this.btDX.setFont(font);
    }

    /**
     * Set font title component skin.
     * @param font for title.
     */
    public void setFontLabelTitle(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * Set all foreground with color.
     * @param color for foreground.
     */
    public void setAllForeground(final Color color) {
        this.setForeground(color);
        this.btDX.setForeground(color);
        this.btSX.setForeground(color);
        this.lbTitle.setForeground(color);
    }

    /**
     * Set all background's button in transparent. 
     */
    public void setTransparentButton() {
        FactoryGUIs.setTransparentJButton(this.btSX);
        FactoryGUIs.setTransparentJButton(this.btDX);
    }

    /**
     * Set path image for skin.
     * @param pathImage for image.
     */
    public void setPnImage(final String pathImage) {
        this.pnImage.setImage(pathImage);
    }

    /**
     * 
     * @param rate
     * @param widthScreen
     */
    public void setRateImg(final int rate, final int widthScreen) {
         final Dimension dimension = EngineImage.getSizeImageFromScale(
                 this.pnImage.getPath(), rate, widthScreen);
        this.pnImage.setSize(dimension);
    }

    /**
     * Get left button.
     * @return JButton.
     */
    public JButton getBtSX() {
        return btSX;
    }

    /**
     * Get right button.
     * @return JButton.
     */
    public JButton getBtDX() {
        return btDX;
    }
}
