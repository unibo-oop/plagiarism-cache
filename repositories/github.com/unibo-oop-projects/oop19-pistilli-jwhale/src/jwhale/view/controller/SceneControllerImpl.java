package jwhale.view.controller;

import jwhale.controller.Controller;
import jwhale.view.View;

public class SceneControllerImpl implements SceneController {
    private Controller controller;
    private View view;

    @Override
    public final void setController(final Controller controller) {
        this.controller = controller;
    }
    @Override
    public final void setView(final View view) {
        this.view = view;
    }
    /**
     * Get controller instance.
     * @return
     *          controller instance.
     */
    protected final Controller getController() {
        return controller;
    }
    /**
     * Get view instance.
     * @return
     *          view instance.
     */
    protected final View getView() {
        return view;
    }

}
