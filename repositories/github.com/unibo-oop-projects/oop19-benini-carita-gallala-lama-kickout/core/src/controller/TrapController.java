package controller;

import model.Trap;
import view.stage.TrapView;
/**
 *  Controls the Trap
 */
public class TrapController {
    private Trap trapModel;
    private TrapView view;
    /**
     * Links model and view
     * @param trapModel
     *          The trap's model
     * @param view
     *          The trap's view
     */
    public TrapController(final Trap trapModel, final TrapView view) {
        this.trapModel = trapModel;
        this.view = view;
        this.view.setModel(this.trapModel);
    }
}
