package frogger.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.controller.GameController;
import frogger.controller.PauseController;

/**
 * The panel that is drawn when the game is paused.
 */
public class PausePanel extends AbstractPanel<PauseController> {
    /*added because the class JPanel implements Serializable. */
    private static final long serialVersionUID = 1L;
    private transient BufferedImage background;

    /**
     * Just set some basic parameters.
     */
    public PausePanel() {
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
    protected void importImg() {
        background = LoadSave.getSprite("pause_background.jpg");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        paintBackground(g);
        paintMenu(g);
    }

    /**
     * Paint the menu.
     * @param g the Graphics
     */
    private void paintMenu(final Graphics g) {
        this.getController().getMenu().getButtonList().forEach((button) -> {
            g.drawImage(button.getCurrentImg(), button.getXPos(), button.getYPos(),
            Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT, null);
        });
    }

    /**
     * paint the background.
     * @param g the Graphics
     */
    @Override
    protected void paintBackground(final Graphics g) {
        final LevelPainter p = new LevelPainter((GameController) getController().getGameController());
        p.importImg();
        p.paintLevel(g);
        g.drawImage(background, (int) getController().getXinPixel(Constants.PAUSE_MENU_X_GRID_UNITS),
        (int) getController().getYinPixel(Constants.PAUSE_MENU_Y_GRID_UNITS), 
        Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT / 2, null);
    }

}
