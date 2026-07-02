package spacesurvival.controller.gui.command;

import spacesurvival.view.GUI;

/**
 * Interface of command pattern for command GUI.
 */
public interface CmdGUI {

    /**
     * Execute the command of GUI.
     * @param gui
     */
    void execute(GUI gui);
}
