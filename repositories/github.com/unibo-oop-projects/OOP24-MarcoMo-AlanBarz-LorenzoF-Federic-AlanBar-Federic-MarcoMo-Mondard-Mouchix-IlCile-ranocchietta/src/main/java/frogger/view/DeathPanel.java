package frogger.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.common.Position;
import frogger.controller.DeathController;

/**
 * A panel displayed when the player dies.
 * Shows the final score and a background image, and allows mouse interaction for restarting or exiting.
 */
public class DeathPanel extends AbstractPanel<DeathController> {
    /*added because the class JPanel implements Serializable. */
    private static final long serialVersionUID = 1L;
    /*Position on screen where the score will be drawn.*/
    private static final Position FONT_POS = new Position(-4, 2);

    private final int score;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT);

    /**
     * Constructs a DeathPanel with the given score.
     *
     * @param score the final score to be displayed
     */
    public DeathPanel(final int score) {
        super.setFocusable(true);
        super.setPanelSize();
        super.setBackground(Color.BLACK);
        super.setOpaque(false);
        this.score = score;
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
        paintBackground(g);
        paintScore(g);
        paintMenu(g);
    }

    private void paintMenu(final Graphics g) {
        this.getController().getMenu().getButtonList().forEach((button) -> {
            g.drawImage(button.getCurrentImg(), button.getXPos(), button.getYPos(),
            Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT, null);
        });
    }

    /**
     * Paints the player's final score on the screen.
     *
     * @param g the graphics context used for drawing
     */
    public void paintScore(final Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(myFont);
        g.drawString("YOUR SCORE: " + this.score, 
        (int) this.getController().getXinPixel(FONT_POS.x()), (int) this.getController().getYinPixel(FONT_POS.y()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void importImg() {
        this.setBackgroundImage(LoadSave.getSprite("death_background.png"));
    }

}
