package giocoscudetto.controller.impl;

import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.ViewManager;

/**
 * Starter implementation.
 */
public final class StarterImpl implements Starter {

    private final ViewManager viewManager;

    /**
     * Constructor for StarterImpl.
     * 
     * @param manager the view manager to use for the controller.
     */
    @SuppressFBWarnings
    public StarterImpl(final ViewManager manager) {
        this.viewManager = manager;
    }

    @Override
    public void startGame() {
        this.viewManager.showView("home");
    }

    @Override
    public void changeView(final String panelName) {
        SwingUtilities.invokeLater(() -> viewManager.showView(panelName));
        //System.out.println("controller");
    }

    @Override
    public void closeGame() {
        this.viewManager.quit();
    }

    /*@Override
    public void addObserver(final GameObserver ob) {
        this.observers.add(ob);
    }

    @Override
    public void removeObserver(final GameObserver ob) {
        this.observers.remove(ob);
    }

    @Override
    public void notifyViews() {
        for (GameObserver ob : observers) {
            ob.updateState();
        }
    }*/

}
