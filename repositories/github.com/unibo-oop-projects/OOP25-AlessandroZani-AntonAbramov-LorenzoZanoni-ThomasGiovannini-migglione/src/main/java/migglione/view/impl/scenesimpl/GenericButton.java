package migglione.view.impl.scenesimpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Class to design the graphic and specifics of the most simple button.
 * Its major use is seen in the Menu, and is designed to change position while resizing the window.
 */
public final class GenericButton extends JButton {

    private static final long serialVersionUID = 9879879871L;
    private static final String FONT_NAME = "Times New Roman";
    private static final int WIDTH = 250;
    private static final int HEIGHT = 50;
    private static final int FONT_SIZE = 26;

    /**
     * Constructor used to initialize the button.
     * Additionally, it will remain around the same size when changing size.
     * of the window, and the text will be displayed at the center.
     * 
     * @param text is the text displayed in the button, while
     * @param action is the action performed when pressed
     */
    public GenericButton(final String text, final ActionListener action) {
        this.setText(text);
        this.addActionListener(action);
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        this.setForeground(Color.YELLOW);
        this.setBackground(Color.BLACK);
        this.setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                if (isVisible()) {
                    setBackground(Color.YELLOW);
                    setForeground(Color.BLACK);
                    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                setBackground(Color.BLACK);
                setForeground(Color.YELLOW);
                setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
            }
        });
    }
}
