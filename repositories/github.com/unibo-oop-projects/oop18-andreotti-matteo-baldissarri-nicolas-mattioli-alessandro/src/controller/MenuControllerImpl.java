package controller;

import view.AudioPlayer;
import view.CreditsView;
import view.LeaderboardView;
import view.MainMenuView;
import view.OptionsView;
import view.View;

/**
 * 
 * Implementation of Menu Controller.
 *
 */
public final class MenuControllerImpl implements MenuController {

    private final MainController controller;
    private final View view;
    private final AudioPlayer player;
    private final Leaderboard leaderboard;

    /**
     * 
     * @param controller  the main controller.
     * @param view        the application view.
     * @param player      the application audio.
     * @param leaderboard the game leaderboard.
     */
    public MenuControllerImpl(final View view, final MainController controller, final AudioPlayer player,
            final Leaderboard leaderboard) {
        this.controller = controller;
        this.leaderboard = leaderboard;
        this.view = view;
        this.player = player;
        goToMainMenu();
    }

    @Override
    public void goToMainMenu() {
        view.setMenu(new MainMenuView(this, view.getScene()));
    }

    @Override
    public void goToNewGame() {
        controller.newGame();

    }

    @Override
    public void goToLeaderboards() {
        view.setMenu(new LeaderboardView(this, view.getScene(), leaderboard));
    }

    @Override
    public void goToOptions() {
        view.setMenu(new OptionsView(this, view.getScene(), player.getMusicVol(), player.getSfxVol()));
    }

    @Override
    public void goToCredits() {
        view.setMenu(new CreditsView(this, view.getScene()));

    }

    @Override
    public void sfxVolumeChanger(final double volume) {
        player.setSfxVol(volume);
    }

    @Override
    public void musicVolumeChanger(final double volume) {
        player.setMusicVol(volume);
    }

    @Override
    public void quit() {
        controller.exit();

    }

}
