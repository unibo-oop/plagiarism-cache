package menu.components;

import javax.swing.Icon;

/**
 * This class extends {@link AbstractGameButton}, and must be implemented to standardize
 * the game design. This class allows easy management of the common basic
 * settings of the game button. For further information see {@link JButton}
 * super class.
 */
public class GameButtonImpl extends AbstractGameButton {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a button with no set text or icon.
     */
    public GameButtonImpl() {
        super();
    }

    /**
     * Creates a button with initial text.
     * 
     * @param textButton button with text.
     */
    public GameButtonImpl(final String textButton) {
        super(textButton);
    }

    /**
     * Creates a button with an icon.
     * 
     * @param icon the Icon image to display on the button.
     */
    public GameButtonImpl(final Icon icon) {
        super(icon);
    }
}
