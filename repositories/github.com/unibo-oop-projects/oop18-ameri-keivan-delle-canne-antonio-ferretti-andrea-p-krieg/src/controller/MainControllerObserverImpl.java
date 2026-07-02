package controller;

import java.util.ArrayList;
import java.util.List;
import view.MainView;

/**
 * MainControllerObserverImpl that implements the MainControllerObserver interface.
 */
public class MainControllerObserverImpl implements MainControllerObserver {

    private final MainView mainView;
    private final List<SecondaryController> secondaryControllers;

    /**
     * @param mainView the main view to be updated after each SecondaryCOntrollerUpdate
     */
    public MainControllerObserverImpl(final MainView mainView) {
        this.secondaryControllers = new ArrayList<>();
        this.mainView = mainView;
    }

    /**{@inheritDoc}**/@Override
    public void update() {
        secondaryControllers.forEach(c -> c.update());
        mainView.update();
    }

    /**{@inheritDoc}**/@Override
    public void addController(final SecondaryController controller) {
        this.secondaryControllers.add(controller);
    }

}
