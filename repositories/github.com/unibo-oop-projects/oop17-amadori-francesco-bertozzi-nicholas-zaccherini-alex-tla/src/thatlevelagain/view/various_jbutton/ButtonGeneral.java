package thatlevelagain.view.various_jbutton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * GeneralButton class.
 *
 */
public class ButtonGeneral extends JButton {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int DUE = 2;
    private static final int CINQUE = 5;

    /**
     * constructor.
     * @param name
     *         name
     */
    public ButtonGeneral(final String name) {
        super();
        this.setPreferredSize(new Dimension(GamePanel.BLOCK_WIDTH * CINQUE, GamePanel.BLOCK_HEIGHT * DUE));
        this.setText(name);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setFont(new Font("Comic Sans", Font.BOLD, GamePanel.BLOCK_WIDTH / CINQUE * DUE));
        this.setContentAreaFilled(false);
        this.setForeground(Color.WHITE);
    }
}
