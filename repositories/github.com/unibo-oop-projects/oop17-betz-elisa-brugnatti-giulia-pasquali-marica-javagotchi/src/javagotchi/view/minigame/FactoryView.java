package javagotchi.view.minigame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;
import javagotchi.view.minigame.component.GameButton;
import javagotchi.view.minigame.mainview.MiniGameView;
import javagotchi.view.minigame.mainview.MiniGameViewImpl;
import javagotchi.view.minigame.menu.MenuMiniGame;
import javagotchi.view.minigame.menu.OptionsMenu;
import javagotchi.view.minigame.menu.PauseMenu;
import javagotchi.view.minigame.menu.optionframe.BestView;
import javagotchi.view.minigame.menu.optionframe.MiniTutorial;

/**
 * 
 * @author marica
 *
 */
public final class FactoryView {

    private FactoryView() {
    }

    /**
     * Create a main menu.
     * 
     * @return {@link MenuMiniGame}
     */
    public static MenuMiniGame createMenu() {
        return new MenuMiniGame();
    }

    /**
     * Create a options menu.
     * 
     * @return {@link OptionsMenu}
     */
    public static OptionsMenu createViewOptions() {
        return new OptionsMenu();
    }

    /**
     * Create pause menu.
     * 
     * @return {@link PauseMenu}
     */
    public static PauseMenu createViewPause() {
        return new PauseMenu();
    }

    /**
     * Create a game view with a list of game button.
     * 
     * @return {@link MiniGameView}
     */
    public static MiniGameView createViewMiniGame() {
        return new MiniGameViewImpl();
    }

    /**
     * Create a game view with a list of game button.
     * 
     * @param list
     *            {@link MiniGameView#getButtons()}
     * @return {@link MiniGameView}
     */
    public static MiniGameView createViewMiniGame(final List<GameButton> list) {
        return new MiniGameViewImpl(list);
    }

    /**
     * Create a view for classification of best score.
     * 
     * @return {@link BestView}
     */
    public static BestView createBestView() {
        return new BestView();
    }

    /**
     * Create a windows dialog to indicate the game is over.
     */
    public static void createDialogGameOver() {
        final String mex = MiniGame.getFactoryController().getControllerMiniGame().getModel().getScore()
                .getStringBestScore() + "\n"
                + MiniGame.getFactoryController().getControllerMiniGame().getModel().getScore().getStringScore();
        final List<Object> options = new ArrayList<>();
        final JButton back = new JButton("Back");
        final JButton replay = new JButton("Replay");
        options.addAll(Arrays.asList(back, replay));
        ((AbstractButton) options.get(0)).addActionListener(e -> {
            Utility.log("Click Back ...");
            MiniGame.getFactoryController().getControllerMiniGame().backToMenu();
        });

        ((AbstractButton) options.get(1)).addActionListener(e -> {
            Utility.log("Click Replay ...");
            MiniGame.getFactoryController().getControllerMiniGame().resetGame();
        });

        JOptionPane.showOptionDialog(Stream.of(JFrame.getFrames()).filter(f -> f.isVisible()).findFirst().get(), mex,
                "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options.toArray(),
                options.stream().findFirst().get());
    }

    /**
     * Create a view for Minitutorial of MiniGame.
     */
    public static void createMiniTutorial() {
        MiniTutorial.create().load();
    }

}
