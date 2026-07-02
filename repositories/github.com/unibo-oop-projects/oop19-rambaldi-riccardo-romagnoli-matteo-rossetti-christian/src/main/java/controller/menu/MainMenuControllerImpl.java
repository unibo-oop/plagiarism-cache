package controller.menu;

import controller.base.BaseController;

/**
 * This is the implementation of {@link MainMenuController} interface.
 */
public class MainMenuControllerImpl implements MainMenuController {

    private final BaseController baseController;

    /**
     * MainMenuController's constructor.
     * @param baseController
     *      The instance of {@link BaseController}.
     */
    public MainMenuControllerImpl(final BaseController baseController) {
        this.baseController = baseController;
    }

    @Override
    public final void playHit() {
        this.baseController.startChoiceMenu();
    }

    @Override
    public final void leaderboardHit() {
        this.baseController.startLeaderboard();
    }

}
