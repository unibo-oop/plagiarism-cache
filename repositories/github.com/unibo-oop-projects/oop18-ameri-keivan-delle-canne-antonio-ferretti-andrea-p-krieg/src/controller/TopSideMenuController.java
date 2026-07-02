package controller;

import java.util.List;

import javafx.util.Pair;
import model.skilltree.SkillTreeAttribute;
import util.MenuVariablesUtils;
import util.WindowLauncherUtils;
import controller.selection.GameCommandsUsingSelection;
import view.MainView;
import view.OverlayView;
import view.UpdatableView;

/**
 * The controller of the top side of the side menu.
 */
public class TopSideMenuController extends AbstractSecondaryController {

    private final GameCommandsUsingSelection model;
    private final OverlayView overlayView;
    private final MainView mainView;

    /**
     * @param view        the view related to this controller
     * 
     * @param model       the model of the game
     * 
     * @param overlayView the overlay view
     * 
     * @param main        the global view
     */
    public TopSideMenuController(final UpdatableView view, final GameCommandsUsingSelection model,
            final OverlayView overlayView, final MainView main) {
        super(view);
        this.model = model;
        this.overlayView = overlayView;
        this.mainView = main;
    }

    /**
     * @return the string representation of the current turn player
     */
    public String getPlayerInfo() {
        return this.model.getPlayerInfo();
    }

    /**
     * @return the name of the current turn player
     */
    public String getPlayerName() {
        return this.model.getCurrentPlayer().getName();
    }

    /**
     * Tells the OverlayViewController to show the dialog pane for the winner.
     */
    public void endGame() {
        this.overlayView.endGameDialog(getPlayerName());
        this.mainView.exit();
    }

    /**
     * Resets the game starting from the first menu back again.
     */
    public void exit() {
        this.mainView.exit();
        WindowLauncherUtils.mainMenu(new Pair<Double, Double>(MenuVariablesUtils.HEIGHT_DEFAULT, MenuVariablesUtils.WIDTH_DEFAULT));
    }

    /**
     * Ameri.
     * 
     * @return a list of skilltree attributes.
     */
    public List<SkillTreeAttribute> getSkillTreeUpgradableAttributes() {
        return model.getSkillTreeUpgradableAttribute();
    }

    /**
     * Ameri.
     * 
     * @return the Skilltree attributes list size.
     */
    public int getSkillTreeUpgradableAttributesSize() {
        return model.getSkillTreeUpgradableAttribute().size();
    }

    /**
     * AMERI.
     * 
     * @param index of the attribute.
     * @return the attribute name.
     * @throws IllegalStateException if the attributes list size is less than equal
     *                               to index.
     */
    public String getAttributeName(final int index) {
        return getSkillTreeAttribute(index).getAttributeName();
    }

    /**
     * AMERI.
     * 
     * @param index of the attribute.
     * @return the cost to upgrade the attribute.
     * @throws IllegalStateException if the attributes list size is less than equal
     *                               to index.
     */
    public String getAttributeCostToString(final int index) {
        return getSkillTreeAttribute(index).getCostToString();
    }

    /**
     * AMERI.
     * 
     * @param index of the attribute.
     * @throws IllegalStateException if the attributes list size is less than equal
     *                               to index.
     */
    public void upgradeAttribute(final int index) {
        model.upgradeAttribute(getSkillTreeAttribute(index));
        super.notifyObserver();
    }

    /**
     * AMERI.
     * 
     * @param index of the attribute.
     * @return true if there are enough resources to upgrade the attribute.
     * @throws IllegalStateException if the attributes list size is less than equal
     *                               to index.
     */
    public boolean canUpgradeAttribute(final int index) {
        return model.canUpgradeAttribute(getSkillTreeAttribute(index));
    }

    /** {@inheritDoc} **/
    @Override
    public void notifyObserver() {
        if (this.model.getWinnerPlayer().isPresent()) {
            this.endGame();
        } else {
            this.model.nextTurn();
            super.notifyObserver();
        }
    }

    private SkillTreeAttribute getSkillTreeAttribute(final int index) {
        if (getSkillTreeUpgradableAttributesSize() <= index) {
            throw new IllegalStateException();
        } else {
            return getSkillTreeUpgradableAttributes().get(index);
        }
    }
}
