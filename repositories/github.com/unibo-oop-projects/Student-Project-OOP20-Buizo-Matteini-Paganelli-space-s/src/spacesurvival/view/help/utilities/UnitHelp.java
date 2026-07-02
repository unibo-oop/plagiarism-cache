package spacesurvival.view.help.utilities;

import spacesurvival.model.EngineImage;
import spacesurvival.view.utilities.JImage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implements the creation of a help unit with images and title.
 */
public class UnitHelp extends JPanel {
    private static final long serialVersionUID = -4410620162507419830L;
    private final JLabel lbTitle;
    private final List<JImage> listIconCommand;

    /**
     * Create a unit help with flowLayout.
     */
    public UnitHelp() {
        super(new FlowLayout());
        super.setOpaque(false);
        this.lbTitle = new JLabel();
        this.listIconCommand = new ArrayList<>();
        super.add(this.lbTitle);
    }

    /**
     * Get title unit help.
     * 
     * @return title's unit help.
     */
    public String getTitleUnit() {
        return this.lbTitle.getText();
    }

    /**
     * Set title a unit help.
     * 
     * @param title for unit help.
     */
    public void setTitleUnit(final String title) {
        this.lbTitle.setText(title);
    }

    /**
     * Set font title of unit help.
     * 
     * @param font for set title.
     */
    public void setFontTitleUnit(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * Set color for foreground of help unit.
     * @param color for set foreground.
     */
    public void setForegroundUnit(final Color color) {
        this.lbTitle.setForeground(color);
    }

    /**
     * Add new image to help unit.
     * @param engineImage for create model image.
     */
    public void addIconUnit(final EngineImage engineImage) {
        final JImage pnImage = new JImage(engineImage.getPath(), engineImage.getSize());
        this.listIconCommand.add(pnImage);
        super.add(pnImage);
    }

    /**
     * Descriptions of unit help.
     */
    @Override
    public String toString() {
        return "PanelHelp{" 
                + "lbTitle=" + lbTitle 
                + ", listCommandImages=" + listIconCommand + '}';
    }
}
