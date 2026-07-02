package it.unibo.burraco.view.table;

import javax.swing.JFrame;

/**
 * Exposes Swing-specific access needed only during wiring in BurracoApp.
 * The controller never sees this interface — only BurracoApp and ScoreViewImpl use it.
 */
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface SwingTableAccess {

    /**
     * @return the main application JFrame, needed by ScoreViewImpl for positioning.
     */
    JFrame getFrame();
}
