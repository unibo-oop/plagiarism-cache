package spacesurvival.controller.gui.logicswitch;

import spacesurvival.controller.gui.ControllerGUI;
import spacesurvival.controller.gui.ManagerControllerGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;

/**
 * Implement the different logics for changing GUI.
 */
public class ManagerVisibility implements LogicSwitchGUI {
    private final ManagerControllerGUI managerControllerGUI;

    /**
     * Initialize focus from ManagerControllerGUI.
     * @param managerControllerGUI is a ManagerControllerGUI.
     */
    public ManagerVisibility(final ManagerControllerGUI managerControllerGUI) {
        this.managerControllerGUI = managerControllerGUI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void algorithmSwitchNormal(final LinkActionGUI actionCurrent, final LinkActionGUI actionNext) {
        switch (actionNext) {
        case LINK_MENU:
        case LINK_GAME:
            this.managerControllerGUI.getChronology().add(actionNext);
            this.managerControllerGUI.getManagerGui().get(actionNext).turn(Visibility.VISIBLE);
            this.managerControllerGUI.getChronology().remove(actionCurrent);
            this.managerControllerGUI.getManagerGui().get(actionCurrent).turn(Visibility.HIDDEN);
            break;
        case LINK_BACK:
            this.managerControllerGUI.getManagerGui().get(actionCurrent).turn(Visibility.HIDDEN);
            this.managerControllerGUI.getCurrentGUI()
            .ifPresent(link -> this.managerControllerGUI.getChronology().remove(link));
            break;
        case LINK_QUIT:
            this.managerControllerGUI.getManagerGui().values().forEach(ControllerGUI::closeGUI);
            break;
        default:
            this.managerControllerGUI.getChronology().add(actionNext);
            this.managerControllerGUI.getManagerGui().get(actionNext).turn(Visibility.VISIBLE);
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void algorithmSwitchGame(final LinkActionGUI actionCurrent, final LinkActionGUI actionNext) {
        switch (actionNext) {
        case LINK_PAUSE:
            if (!this.managerControllerGUI.getCurrentGUI().get().equals(LinkActionGUI.LINK_PAUSE)) {
                this.managerControllerGUI.getChronology().add(actionNext);
            } else {
                this.managerControllerGUI.getChronology().remove(actionNext);
            }

            this.managerControllerGUI.getManagerGui().get(actionNext).changeVisibility(); break;

        case LINK_BACK:
            this.managerControllerGUI.getManagerGui().get(actionCurrent).turn(Visibility.HIDDEN);
            this.managerControllerGUI.getCurrentGUI()
                .ifPresent(link -> this.managerControllerGUI.getChronology().remove(link));
            this.managerControllerGUI.getCurrentGUI()
                .ifPresent(link -> this.managerControllerGUI.getManagerGui().get(link).turn(Visibility.VISIBLE));
            break;
        case LINK_QUIT:
            this.managerControllerGUI.getManagerGui().values().forEach(ControllerGUI::closeGUI);
            break;
        default:
            this.managerControllerGUI.getChronology().add(actionNext);
            this.managerControllerGUI.getManagerGui().get(actionNext).turn(Visibility.VISIBLE);
            this.managerControllerGUI.getManagerGui().get(actionCurrent).turn(Visibility.HIDDEN);
            break;
        }

    }
}
