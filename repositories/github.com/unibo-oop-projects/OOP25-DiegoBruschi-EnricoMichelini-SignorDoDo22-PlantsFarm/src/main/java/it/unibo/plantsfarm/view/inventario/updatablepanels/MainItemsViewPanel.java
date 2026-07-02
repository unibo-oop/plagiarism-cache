package it.unibo.plantsfarm.view.inventario.updatablepanels;

import javax.swing.JPanel;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import it.unibo.plantsfarm.view.inventario.UpdatablePanel;

/**
 * The MainItemsViewPanel is a compositve view that contains few updatabalePanel.
 * This panel is responsible for updating all its child panels.
 * Thanks to the the interface UpdatablePanel each panel can be easily replaced with onother implementation.
 */
public final class MainItemsViewPanel extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 8L;

    private final List<UpdatablePanel> panelsComposition;
    private final UpdatablePanel panelProgressBar;
    private final UpdatablePanel panelViewImageItems;
    private final UpdatablePanel panelItemsUpgradeButtons;

    /**
     * Create the main updatablePanel.
     *
     * @param controllerInventario is passed to all the panel children.
     */
    public MainItemsViewPanel(final ControllerInventario controllerInventario) {
        this.setLayout(new BorderLayout());
        this.panelItemsUpgradeButtons = new ItemsViewButtonUpgrade(controllerInventario);
        this.panelViewImageItems = new ItemsViewImageItem(controllerInventario);
        this.panelProgressBar = new ItemsViewBarProgress(controllerInventario);
        this.panelsComposition = new LinkedList<>(List.of(panelItemsUpgradeButtons, panelProgressBar, panelViewImageItems));
        this.add((JPanel) panelItemsUpgradeButtons, BorderLayout.EAST);
        this.add((JPanel) panelViewImageItems, BorderLayout.WEST);
        this.add((JPanel) panelProgressBar, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        for (final UpdatablePanel updatablePanel : panelsComposition) {
            updatablePanel.update();
        }
    }

}
