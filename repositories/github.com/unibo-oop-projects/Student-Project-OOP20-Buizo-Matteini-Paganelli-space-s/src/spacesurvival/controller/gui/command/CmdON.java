package spacesurvival.controller.gui.command;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;

/**
 * Command On for the GUI.
 */
public class CmdON implements CmdEngine {

    /**
     * {@inheritDoc}
     */
    @Override
    public CmdGUI execute(final EngineGUI engine) {
        engine.setVisibility(Visibility.VISIBLE);
        return gui -> gui.setVisible(engine.getVisibility().isVisible());
    }
}
