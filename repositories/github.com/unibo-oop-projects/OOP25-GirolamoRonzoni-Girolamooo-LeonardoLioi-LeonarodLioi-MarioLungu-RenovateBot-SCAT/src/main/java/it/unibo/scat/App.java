package it.unibo.scat;

import it.unibo.scat.common.Observer;
import it.unibo.scat.control.Control;
import it.unibo.scat.model.Model;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.model.api.ModelState;
import it.unibo.scat.view.View;
import it.unibo.scat.view.api.ViewInterface;

/**
 * Main application class for OOP25-SCAT.
 */
public final class App {

    private App() {
    }

    /**
     * Application entry point.
     * 
     * @param args command line arguments
     *
     */
    public static void main(final String[] args) {
        final Model model = new Model();

        final ModelInterface modelInterface = model;
        final ModelState modelObs = model;

        final View view = new View();
        final ViewInterface viewInterface = view;
        final Observer viewObserver = view;

        final Control controller = new Control(viewInterface, modelInterface);

        view.setControlInterface(controller);
        view.setModelState(modelObs);
        model.setObserver(viewObserver);

        controller.init();
    }
}
