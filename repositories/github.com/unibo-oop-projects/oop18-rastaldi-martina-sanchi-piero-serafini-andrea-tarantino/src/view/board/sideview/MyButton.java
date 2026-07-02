package view.board.sideview;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 *
 * Piero Sanchi. This class extends a JButton.
 *
 */
public class MyButton extends JButton {

    private static final long serialVersionUID = 1L;
    private static final int TITLEFONT = 17;
    private final Color color = Color.BLACK;

    /**
     * Constructor of MyButton.
     *
     * @param text
     *            the text of the button.
     */
    public MyButton(final String text) {
        super();
        this.setText(text);
        this.setFont(new Font("Tahoma", Font.BOLD, TITLEFONT));
        this.setBackground(this.color);
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
    }

    /**
     *
     * @return the standard color for MyButtons.
     */
    public Color getColor() {
        return this.color;
    }

}
