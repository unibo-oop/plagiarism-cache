package menu.components;

import java.awt.LayoutManager;

/**
 * This class extends {@link AbstractGameFrame}, and must be implemented to standardize
 * the game design. This class allows easy management of the common basic
 * settings of the game frame. For further information see {@link JFrame} super
 * class.
 */
public class GameFrameImpl extends AbstractGameFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new game frame with default settings.
     */
    public GameFrameImpl() {
        super();
    }

    /**
     * Creates a new game frame with default settings and layout manager.
     * 
     * @param manager the LayoutManager
     */
    public GameFrameImpl(final LayoutManager manager) {
        super(manager);
    }
}
