package unibo.exiled.view;

import unibo.exiled.controller.MenuControllerImpl;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.FontManager;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.util.Optional;

/**
 * The new game selection menu view.
 */
public final class NewGameView {

    /**
     * The constructor of the new game selection view.
     */
    public NewGameView() {
        FontManager.loadFont();

        final JFrame mainFrame = new JFrame();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setTitle(ConstantsAndResourceLoader.GAME_NAME);
        mainFrame.setLocationByPlatform(true);
        mainFrame.setFocusable(true);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(new MenuView(new MenuControllerImpl().getNewGameMenuItems(), Optional.empty()),
                BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
