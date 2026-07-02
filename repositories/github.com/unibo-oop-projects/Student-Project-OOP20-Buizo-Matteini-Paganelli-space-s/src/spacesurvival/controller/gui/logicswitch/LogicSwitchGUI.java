package spacesurvival.controller.gui.logicswitch;

import spacesurvival.utilities.LinkActionGUI;

/**
 * Implement functions for GUI switching logic.
 */
public interface LogicSwitchGUI {
    /**
     * Function normal logic for change GUI.
     * 
     * @param actionCurrent is current GUI.
     * @param actionNext is next GUI.
     */
    void algorithmSwitchNormal(LinkActionGUI actionCurrent, LinkActionGUI actionNext);

    /**
     * Function game logic for change GUI.
     * 
     * @param actionCurrent is current GUI.
     * @param actionNext is next GUI.
     */
    void algorithmSwitchGame(LinkActionGUI actionCurrent, LinkActionGUI actionNext);
}
