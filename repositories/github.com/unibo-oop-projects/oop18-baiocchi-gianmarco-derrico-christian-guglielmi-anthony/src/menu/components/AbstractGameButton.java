package menu.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * This abstract class extends {@link JButton}, and must be implemented to
 * standardize the game design. This class allows easy management of the common
 * basic settings of the game button. For further information see {@link JButton} super class.
 */
public abstract class AbstractGameButton extends JButton {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int FONT_SCALE = 30; 
    private static final int FONT_SIZE = (int) (SCREEN_SIZE.getHeight() / FONT_SCALE);
//    private static final Color BACKGROUND = new Color(81, 81, 81);
    private static final Color BACKGROUND_COLOR = Color.BLACK;
//    private static final Color MY_MAGENTA = new Color(241, 80, 158);
    private static final Color TEXT_BUTTON_COLOR = new Color(255, 255, 0);

    /**
     * Creates a button with no set text or icon.
     */
    public AbstractGameButton() {
        super();
        super.setFont(new GameFont().getFont().deriveFont(Font.BOLD, FONT_SIZE));
        super.setForeground(TEXT_BUTTON_COLOR);
        super.setBackground(BACKGROUND_COLOR);
        super.setBorder(null);
        super.setBorderPainted(false);
    }

    /**
     * Creates a button with initial text.
     * 
     * @param textButton button with text.
     */
    public AbstractGameButton(final String textButton) {
        this();
        super.setText(textButton);
    }

    /**
     * Creates a button with an icon.
     * 
     * @param icon the Icon image to display on the button.
     */
    public AbstractGameButton(final Icon icon) {
        this();
        super.setIcon(icon);
    }
}
