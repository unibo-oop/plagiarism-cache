package it.unibo.monoopoly.utils.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.DefaultInteractivePanel;

/**
 * This class is used to set up an interactive panel with a listener attached,
 * for
 * the user, to press a button to roll the dices implementing
 * {@link ActionListener}
 * interface.
 */
public class RollDicesListener implements ActionListener {

    private final MainView mainView;

    /**
     * initialize field of class in constructor.
     * 
     * @param mainView the main view of the application
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public RollDicesListener(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.mainView.setInteractivePanel(new DefaultInteractivePanel(this.mainView.getMainFrame().getHeight()));
        this.mainView.getControllerState().closeControllerState(
                    new DataBuilderOutputImpl().build());
    }
}
