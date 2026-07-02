package javagotchi.view.minigame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javagotchi.view.minigame.component.Components;
import javagotchi.view.minigame.component.MusicButton;

/**
 * 
 * @author marica
 *
 */
public abstract class MenuDefault extends AbstractFrameDefault {

    private static final long serialVersionUID = 514423340787686491L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final int GAPTOP = 5;
    private static final int GAPLEFT = 5;
    private static final int GAPBETWEENBUTTON = 20;
    private static final int GAPRIGHT = 5;
    private static final int SIZEFONT = 30;

    private final JPanel centerPanel = new JPanel(new GridBagLayout());
    private final MusicButton music = Components.createMusicButton();

    /**
     * Constructor for MenuDefault.
     */
    public MenuDefault() {
        super(WIDTH, HEIGHT);
        setCenterPanel();
    }

    /**
     * Constructor for MenuDefault with fixed width and variable height.
     * 
     * @param height
     *            height of windows
     */
    public MenuDefault(final int height) {
        super(WIDTH, height);
        setCenterPanel();
    }

    /**
     * Gets a instance of {@link AbstractFrameDefault.BackgroundImage}.
     * 
     * @param path
     *            path of image
     * @return {@link AbstractFrameDefault.BackgroundImage}.
     */
    protected JPanel getImagePanel(final String path) {
        final JPanel panelImage = new AbstractFrameDefault.BackgroundImage(path, this);
        this.add(panelImage, BorderLayout.CENTER);
        return panelImage;
    }

    /**
     * Gets the north panel.
     * 
     * @param name
     *            name of menu
     * @return panel north
     */
    protected JPanel getNorthPanel(final JLabel name) {
        final JPanel panNorth = new JPanel(new FlowLayout());
        panNorth.setOpaque(false);
        panNorth.add(name, BorderLayout.CENTER);
        name.setForeground(Color.yellow);
        name.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, SIZEFONT));
        return panNorth;
    }

    /**
     * Gets the center panel.
     * 
     * @return center panel
     */
    protected JPanel getCenterPanel() {
        return this.centerPanel;
    }

    private void setCenterPanel() {
        centerPanel.setPreferredSize(this.getSize());
        centerPanel.setOpaque(false);
    }

    /**
     * Gets the {@link GridBagConstraints}.
     * 
     * @return {@link GridBagConstraints}
     */
    protected GridBagConstraints getContainerCenterPanel() {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(GAPTOP, GAPLEFT, GAPBETWEENBUTTON, GAPRIGHT);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return constraints;
    }

    /**
     * Adds buttons in the center panel.
     * 
     * @param constraints
     *            {@link MenuDefault#getContainerCenterPanel()}
     */
    protected abstract void addButtonsInCenterPanel(GridBagConstraints constraints);

    /**
     * Gets {@link MenuDefault#music}, and sets icon.
     * 
     * @return {@link MenuDefault#music}
     */
    protected MusicButton getMusic() {
        music.setIconMusic();
        return music;
    }
}
