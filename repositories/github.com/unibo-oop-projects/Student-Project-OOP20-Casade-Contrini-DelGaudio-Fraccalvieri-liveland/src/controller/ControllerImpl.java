package controller;

import view.menu.GraphicalUserInterfaceImpl;

public final class ControllerImpl implements Controller {

    private EnvironmentControllerImpl controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView() {
        new GraphicalUserInterfaceImpl(this.controller).display();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnvironmentController() {
        this.controller = new EnvironmentControllerImpl();
    }

}
