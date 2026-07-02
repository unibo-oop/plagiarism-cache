package spacesurvival.view.game.utilities;

import spacesurvival.view.utilities.GraphicsLayoutUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import spacesurvival.view.utilities.FactoryGUIs;

/**
 * Implement a component view for round and timer counting.
 */
public class RoundTimer extends JPanel {
    private static final long serialVersionUID = 2358772098881553L;
    private final JLabel timer;
    private final JLabel stringRound;
    private final JLabel round;

    /**
     * Initialize and create all graphics components.
     */
    public RoundTimer() {
        super();
        super.setOpaque(false);

        this.timer = new JLabel(GraphicsLayoutUtils.INIT_TIMER_STRING);
        this.stringRound = new JLabel(GraphicsLayoutUtils.ROUND_STRING);
        this.round = new JLabel();

        super.add(FactoryGUIs.encapsulatesInPanelFlow(this.timer));
        super.add(FactoryGUIs.createPanelFlowUnionComponents(java.util.List.of(this.stringRound, this.round)));
        super.setLayout(new GridLayout(0, super.getComponentCount() - 1));
    }

    /**
     * Set the font to all the HUD.
     * 
     * @param font to set
     */
    public void setFontAll(final Font font) {
        this.timer.setFont(font);
        this.stringRound.setFont(font);
        this.round.setFont(font);
    }

    /**
     * Set the foreground color of the text to all the HUD.
     * 
     * @param color to set
     */
    public void setForegroundAll(final Color color) {
        this.timer.setForeground(color);
        this.stringRound.setForeground(color);
        this.round.setForeground(color);
    }

    /**
     * Set text round.
     * @param round is a int.
     */
    public void setRound(final int round) {
        this.round.setText(String.valueOf(round));
    }

    /**
     * Set text round.
     * @param round is a string.
     */
    public void setRound(final String round) {
        this.round.setText(round);
    }

    /**
     * Set text timer timer. 
     * @param timer is text.
     */
    public void setTimer(final String timer) {
        this.timer.setText(timer);
    }
}
