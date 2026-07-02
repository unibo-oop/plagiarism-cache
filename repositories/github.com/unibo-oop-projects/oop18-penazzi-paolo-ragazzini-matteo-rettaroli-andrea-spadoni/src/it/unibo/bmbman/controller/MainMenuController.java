package it.unibo.bmbman.controller;

import it.unibo.bmbman.controller.game.GameController;
import it.unibo.bmbman.controller.game.GameControllerImpl;
import it.unibo.bmbman.model.leaderboard.ScoreHandler;
import it.unibo.bmbman.view.HelpView;
import it.unibo.bmbman.view.LeaderboardView;
import it.unibo.bmbman.view.MainMenuView;
import it.unibo.bmbman.view.OptionsView;
/**
 *the implementation of MainMenuController interface.
 *
 */
public class MainMenuController implements MenuController<MainMenuList> {

    private final MenuController<OptionsMenuList> opt;
    private final MainMenuView mainMenuView;
    /**
     * Construct a {@link MainMenuController}.
     * @param mainMenuView the view of main manù
     * @param opt {@link OptionMenuController}
     */
    public MainMenuController(final MainMenuView mainMenuView, final MenuController<OptionsMenuList> opt) {
        super();
        this.mainMenuView = mainMenuView;
        this.opt = opt;
        ScoreHandler.checkAndRead();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setOptionSelected(final MainMenuList optionSelected) {
        switch (optionSelected) {
        case SINGLE_PLAYER:
            final GameController game = new GameControllerImpl(mainMenuView);
            game.startGame();
            break;
        case LEADERBOARD:
            final LeaderboardView lv = new LeaderboardView(mainMenuView);
            lv.getFrame().setVisible(true);
            break;
        case SETTINGS:
            final OptionsView ov = new OptionsView(mainMenuView, opt);
            ov.getFrame().setVisible(true);
            break;
        case HELP:
            final HelpView hv = new HelpView(mainMenuView);
            hv.getFrame().setVisible(true);
            break;
        default:
            break;
        }
    }

}

