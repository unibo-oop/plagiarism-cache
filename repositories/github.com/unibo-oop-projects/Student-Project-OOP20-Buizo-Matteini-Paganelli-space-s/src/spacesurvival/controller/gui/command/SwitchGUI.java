package spacesurvival.controller.gui.command;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.view.GUI;

/**
 * Implements a command caller for the GUI engines and executes them.
 */
public class SwitchGUI {
    private final CmdEngine onCmdEngine;
    private final CmdEngine offCmdEngine;

    private GUI gui;
    private EngineGUI engine;

    /**
     * Initialize the commands to set the visibility status.
     */
    public SwitchGUI() {
        this.onCmdEngine = new CmdON();
        this.offCmdEngine = new CmdOFF();
    }

    /**
     * Initialize the commands to set the status of the visibility and receive the objects to which to set the status.
     * @param engine for set state of visibility.
     * @param gui for set state of visibility from engine.
     */
    public SwitchGUI(final EngineGUI engine, final GUI gui) {
        this();
        this.engine = engine;
        this.gui = gui;
    }

    /**
     * Execute state of visibility a depending on the parameter.
     * @param visibility for set state of visibility.
     */
    public void turn(final Visibility visibility) {
        switch (visibility) {
            case HIDDEN: this.offCmdEngine.execute(engine).execute(gui); break;
            case VISIBLE: this.onCmdEngine.execute(engine).execute(gui); break;
            default: break;
        }
    }

    /**
     * Change state of visibility.
     */
    public void changeVisibility() {
        if (this.engine.isVisible()) {
            this.offCmdEngine.execute(engine).execute(gui);
        } else {
            this.onCmdEngine.execute(engine).execute(gui);
        }
    }
}
