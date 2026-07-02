package controller.base;

import view.base.BaseView;

/**
 * This is the implementation of {@link BaseController} interface.
 */
public class BaseControllerImpl implements BaseController {

    private final BaseView baseView;

    /**
     * BaseController's constructor.
     * @param baseView
     *          The instance of {@link BaseView}.
     */
    public BaseControllerImpl(final BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public final void startProgram() {
        this.baseView.startMainMenu();
    }

    @Override
    public final void startChoiceMenu() {
        this.baseView.showChoiceMenu();
    }

    @Override
    public final void startGame(final String mapChosen, final String name, final String characterChosen) {
        this.baseView.displayGame(mapChosen, name, characterChosen);
    }

    @Override
    public final void startLeaderboard() {
        this.baseView.showLeaderboard();
    }

    @Override
    public final void closeProgram() {
        System.exit(0);
    }
}
