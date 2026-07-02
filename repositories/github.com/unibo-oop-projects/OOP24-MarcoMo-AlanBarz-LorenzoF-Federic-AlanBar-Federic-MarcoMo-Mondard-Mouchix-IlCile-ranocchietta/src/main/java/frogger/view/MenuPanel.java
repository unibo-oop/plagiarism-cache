package frogger.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.controller.MenuControllerImpl;

/**
 * The {@code MenuPanel} class represents the main menu panel in the Frogger game.
 * It extends {@link AbstractPanel} and is responsible for rendering the menu background,
 * the back button, and the menu buttons. It also sets up mouse input listeners for
 * user interaction.
 * 
 * @see AbstractPanel
 * @see MenuControllerImpl
 */
public class MenuPanel extends AbstractPanel<MenuControllerImpl> {
    /** Added because the class JPanel implements Serializable. */
    private static final long serialVersionUID = 1L;

    private transient BufferedImage buttonBack = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

    /**
     * Constructs a new ShopPanel and sets its properties.
     */
    public MenuPanel() {
        super.setFocusable(true);
        super.setPanelSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setInputListener() {
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintBackground(g);
        paintButtonBack(g);
        paintMenu(g);
    }

    /**
     * Paint the mini-background for the button.
     * 
     * @param g the Graphics context to use for painting
     */
    private void paintButtonBack(final Graphics g) {
        final int offset = 30;
        g.drawImage(buttonBack, Constants.FRAME_WIDTH / 2 - (buttonBack.getWidth() + offset) / 2,
        Constants.FRAME_HEIGHT / 2 - (buttonBack.getHeight() + offset) / 2, buttonBack.getWidth() + offset,
        buttonBack.getHeight() + offset, null);
    }

    /**
     * Paint the buttons.
     * 
     * @param g the Graphics context to use for painting
     */
    private void paintMenu(final Graphics g) {
        this.getController().getMenu().getButtonList().forEach(button -> {
            g.drawImage(button.getCurrentImg(), button.getXPos(), button.getYPos(),
            Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT, null);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void importImg() {
        buttonBack = LoadSave.getSprite("menu_background.png");
        this.setBackgroundImage(LoadSave.getSprite("background.png"));
    }
}
