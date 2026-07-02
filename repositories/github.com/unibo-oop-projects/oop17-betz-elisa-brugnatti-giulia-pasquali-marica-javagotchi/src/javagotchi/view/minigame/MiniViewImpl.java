package javagotchi.view.minigame;

import javax.swing.SwingUtilities;

import javagotchi.view.minigame.mainview.MiniGameView;
import javagotchi.view.minigame.menu.MenuMiniGame;

/**
 * 
 * @author marica
 *
 */
public class MiniViewImpl implements MiniView {

    /**
     * Constructor for ViewImpl.
     */
    private MenuMiniGame menu;
    private MiniGameView actualGame;

    @Override
    public final MenuMiniGame getMainMenu() {
        if (menu == null) {
            menu = FactoryView.createMenu();
        }
        return menu;
    }

    @Override
    public final MiniGameView getViewMiniGame() {
        return actualGame;
    }

    @Override
    public final void setViewMiniGame(final MiniGameView viewMiniGame) {
        actualGame = viewMiniGame;
    }

    @Override
    public final void updateTimeGui(final int sec) {
        if (this.isInUseViewGame()) {
            SwingUtilities.invokeLater(() -> actualGame.setTime(sec));
        }
    }

    @Override
    public final void updateScoreGui(final String textScore) {
        if (this.isInUseViewGame()) {
            SwingUtilities.invokeLater(() -> actualGame.setScore(textScore));
        }
    }

    private boolean isInUseViewGame() {
        return actualGame != null;
    }

}
