package spacesurvival.controller.gui.command;

import spacesurvival.model.gui.EngineGUI;

/**
 * Interface of command pattern for switch state visibility engine.
 */
public interface CmdEngine {

    /**
     * Execute command of engine and return command of GUI.
     * @param engineGUI is a parameter for command.
     * @return command for GUI.
     */
    CmdGUI execute(EngineGUI engineGUI);
}
