package menu.buttons;

import menu.components.GameButtonImpl;

/**
 * This class extends {@link GameButtonImpl}. It allows easy management
 * of the commons behaviors and settings of the Exit button in case of one or
 * multiple uses within its own program. For further information see
 * {@link JButton} super class.
 */
public class ExitButton extends GameButtonImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String EXIT = "EXIT";

    /**
     * Creates a exit game button.
     */
    public ExitButton() {
        super(EXIT);
        super.addActionListener(l -> System.exit(0));
    }
}
