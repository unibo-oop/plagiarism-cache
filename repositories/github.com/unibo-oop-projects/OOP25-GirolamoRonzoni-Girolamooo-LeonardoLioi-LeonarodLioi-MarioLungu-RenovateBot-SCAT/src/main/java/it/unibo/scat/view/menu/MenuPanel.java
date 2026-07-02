package it.unibo.scat.view.menu;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.menu.api.MenuPanelInterface;
import it.unibo.scat.view.menu.usernamepanel.UsernamePanel;

/**
 * Main menu panel. Manages different screens using {@link CardLayout}.
 */
@SuppressFBWarnings(value = { "SE_TRANSIENT_FIELD_NOT_RESTORED",
        "EI_EXPOSE_REP2" }, justification = "Component not intended for serialization;Reference intentionally shared")
public final class MenuPanel extends JPanel implements MenuPanelInterface {
    private static final long serialVersionUID = 1L;

    private static final String CARD_SETTINGS = "SETTINGS";
    private static final String CARD_USERNAME = "USERNAME";
    private static final String CARD_LEADERBOARD = "LEADERBOARD";
    private static final String CARD_CREDITS = "CREDITS";

    private final transient ViewActionsInterface viewInterface;
    private transient BufferedImage currentBackground;
    private transient BufferedImage background1;
    private transient BufferedImage background2;

    private final transient CardLayout cardLayout = new CardLayout();

    /**
     * Creates the menu panel and initializes backgrounds and sub-panels.
     * 
     * @param viewInterface interface used to delegate menu actions.
     * 
     */
    public MenuPanel(final ViewActionsInterface viewInterface) {
        this.viewInterface = viewInterface;
        setLayout(cardLayout);

        initBackground();
        initPanels();

        showSettingsPanel();
    }

    /**
     * Loads the menu background images from resources.
     * 
     * @throws IllegalStateException if loading fails
     */
    private void initBackground() {
        try {
            background1 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(UIConstants.MENU_BACKGROUND1_PATH)));
            background2 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(UIConstants.MENU_BACKGROUND2_PATH)));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load menu background", e);
        }
    }

    /**
     * Initializes and registers all menu panels in the {@link CardLayout}.
     */
    private void initPanels() {
        final SettingsPanel settingsPanel = new SettingsPanel(viewInterface, this);
        final UsernamePanel usernamePanel = new UsernamePanel(viewInterface);
        final LeaderboardPanel leaderboardPanel = new LeaderboardPanel(this, viewInterface);
        final CreditsPanel creditsPanel = new CreditsPanel(this);

        settingsPanel.setOpaque(false);

        final double ratio = 0.8;

        add(percentCenteredCard(settingsPanel, 1, ratio), CARD_SETTINGS);
        add(percentCenteredCard(usernamePanel, ratio, ratio), CARD_USERNAME);
        add(percentCenteredCard(leaderboardPanel, ratio, ratio), CARD_LEADERBOARD);
        add(percentCenteredCard(creditsPanel, ratio, ratio), CARD_CREDITS);
    }

    /**
     * Creates a wrapper panel that centers the given component
     * and scales it according to the provided width and height ratios.
     *
     * @param content     component to center
     * @param widthRatio  percentage of the container width
     * @param heightRatio percentage of the container height
     * @return wrapper panel with custom layout
     */
    private static JPanel percentCenteredCard(final JComponent content, final double widthRatio,
            final double heightRatio) {
        final JPanel wrapper = new JPanel(null) {
            private static final long serialVersionUID = 1L;

            @Override
            public void doLayout() {
                final int w = getWidth();
                final int h = getHeight();

                final int cw = Math.max(1, (int) Math.round(w * widthRatio));
                final int ch = Math.max(1, (int) Math.round(h * heightRatio));

                content.setBounds((w - cw) / 2, (h - ch) / 2, cw, ch);
            }
        };

        wrapper.setOpaque(false);
        wrapper.add(content);
        return wrapper;
    }

    /**
     * Paints the current background image scaled to fully cover the panel area.
     *
     * @param g graphics context
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (currentBackground == null) {
            return;
        }

        final int panelW = getWidth();
        final int panelH = getHeight();
        final int imgW = currentBackground.getWidth();
        final int imgH = currentBackground.getHeight();

        final double scale = Math.max((double) panelW / imgW, (double) panelH / imgH);

        final int drawW = (int) Math.ceil(imgW * scale);
        final int drawH = (int) Math.ceil(imgH * scale);
        final int x = (panelW - drawW) / 2;
        final int y = (panelH - drawH) / 2;

        g.drawImage(currentBackground, x, y, drawW, drawH, null);
    }

    /**
     * Shows the leaderboard screen and sets the secondary background.
     */
    @Override
    public void showLeaderboardPanel() {
        cardLayout.show(this, CARD_LEADERBOARD);
        currentBackground = background2;
        revalidate();
        repaint();
    }

    /**
     * Shows the credits screen and sets the secondary background.
     */
    @Override
    public void showCreditsPanel() {
        cardLayout.show(this, CARD_CREDITS);
        currentBackground = background2;
        revalidate();
        repaint();
    }

    /**
     * Shows the settings screen and sets the primary background.
     */
    @Override
    public void showSettingsPanel() {
        cardLayout.show(this, CARD_SETTINGS);
        currentBackground = background1;
        revalidate();
        repaint();
    }

    /**
     * Shows the username screen and sets the secondary background.
     */
    @Override
    public void showUsernamePanel() {
        cardLayout.show(this, CARD_USERNAME);
        currentBackground = background2;
        revalidate();
        repaint();
    }

}
