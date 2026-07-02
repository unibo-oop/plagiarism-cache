package startmenu.controller;

import java.util.List;

import javafx.util.Pair;
import model.gamerules.GameRules;
import startmenu.model.ModelController;
import startmenu.view.ViewController;
import util.WindowLauncherUtils;

/**
 * Handles the interactions between the view and the model.
 */
public class MainControllerImpl implements MainController {

    private final ViewController view;
    private final ModelController model;

    /**
     * 
     * @param view  the view where it will get the input
     * 
     * @param model the model that handles the response on relatives actions
     */
    public MainControllerImpl(final ViewController view, final ModelController model) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    /**{@inheritDoc}**/@Override
    public void callGameMenu(final double height, final double width) {
        try {
            WindowLauncherUtils.playerSelectMenuLauncher(new Pair<>(height, width), this.model.getChosenGameMode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public void showMenu() {
        this.view.draw();
    }

    /** {@inheritDoc} **/
    @Override
    public List<GameRules> getGameModes() {
        return this.model.getGameMode();
    }

    /** {@inheritDoc} **/
    @Override
    public void setGameMode(final int index) {
        this.model.setGameMode(index);
    }

}
