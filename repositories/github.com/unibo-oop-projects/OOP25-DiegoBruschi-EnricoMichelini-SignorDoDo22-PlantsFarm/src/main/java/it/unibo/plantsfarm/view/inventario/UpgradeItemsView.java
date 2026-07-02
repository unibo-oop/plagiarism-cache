package it.unibo.plantsfarm.view.inventario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.inventario.updatablepanels.MainItemsViewPanel;

/**
 * Dialog that shows the experience of each item/tool and allows upgrading them.
 */
public final class UpgradeItemsView extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;
    private static final String TITLE = " ITEMS EXPERIENCE AND STATS PLAYER ";
    private final UpdatablePanel mainPanel;
    private ImplViewGamePanel gamePanel;

    /**
     * Creates a new dialog for items experience.
     *
     * @param gamePanel the game panel that will receive focus when this dialog closes
     *
     * @param controllerInventario for review the item of the player.
     *
     */
    public UpgradeItemsView(final ImplViewGamePanel gamePanel, final ControllerInventario controllerInventario) {
        super();
        this.setTitle(TITLE);
        setImplViewGamePanel(gamePanel);
        this.mainPanel = new MainItemsViewPanel(controllerInventario);
        this.add((JPanel) mainPanel);
        this.setResizable(false);
        this.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        this.setLocationRelativeTo(gamePanel);
        this.setModal(true);
        updateAllItemsPanel();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent e) {
                restoreFocus();
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                restoreFocus();
            }
        });
    }

    private void restoreFocus() {
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }

    /**
     * Calls the mainPanel and after the main panel update all its child panels.
     *
     */
    public void updateAllItemsPanel() {
        mainPanel.update();
    }

    private void setImplViewGamePanel(final ImplViewGamePanel givenGamePanel) {
        this.gamePanel = java.util.Objects.requireNonNull(givenGamePanel);
    }
}
