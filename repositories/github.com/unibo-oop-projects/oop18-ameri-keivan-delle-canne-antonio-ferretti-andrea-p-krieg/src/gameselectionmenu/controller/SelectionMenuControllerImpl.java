package gameselectionmenu.controller;

import java.util.List;

import gameselectionmenu.model.SelectionMenuModel;
import gameselectionmenu.view.SelectionMenuView;
import javafx.util.Pair;
import util.WindowLauncherUtils;

/**
 * SelectionMenuControllerImpl is a class that implements the interface
 * SelectionMenuController. This class represents the controller part of the
 * player selection menu. If all conditions are met, the user can start the
 * game.
 */
public class SelectionMenuControllerImpl implements SelectionMenuController {

    private final SelectionMenuView view;
    private final SelectionMenuModel model;

    /**
     * Constructor.
     * 
     * @param view  the view where it will get the input.
     * @param model the model that handles the response on relatives actions.
     */
    public SelectionMenuControllerImpl(final SelectionMenuView view, final SelectionMenuModel model) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    /** {@inheritDoc} **/
    @Override
    public void showMenu() {
        this.view.draw();
    }

    /** {@inheritDoc} **/
    @Override
    public void startGame(final double height, final double width) {
        if (this.canStartGame()) {
            try {
                WindowLauncherUtils.gameWithSideMenu(new Pair<>(height, width), this.model.getPlayers(), this.model.getSelectedGameMode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public int getMinimumPlayers() {
        return model.getMinimumPlayers();
    }

    /** {@inheritDoc} **/
    @Override
    public int getMaximumPlayers() {
        return model.getMaximumPlayers();
    }

    /** {@inheritDoc} **/
    @Override
    public void setCurrentPlayers(final int currentPlayers) {
        model.setCurrentPlayers(currentPlayers);
    }

    /** {@inheritDoc} **/
    @Override
    public int getCurrentPlayers() {
        return model.getPlayerNumber();
    }

    /** {@inheritDoc} **/
    @Override
    public List<String> getRaceNameList() {
        return model.getRaceNameList();
    }

    /** {@inheritDoc} **/
    @Override
    public boolean verify(final int id, final String name, final String raceName) {
        return (!checkEmptyOrNullStr(name) && !checkEmptyOrNullStr(raceName))
                ? model.canAddThePlayer(id, name, raceName)
                : false;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canStartGame() {
        return model.canStart();
    }

    private boolean checkEmptyOrNullStr(final String string) {
        return string == null || string.isEmpty();
    }

}
