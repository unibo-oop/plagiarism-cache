package casim.ui.components.menu;

import java.util.List;

import casim.controller.menu.MenuController;
import casim.core.AppManager;
import casim.ui.components.page.PageContainer;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Models the general functionalities of a menu.
 */
public abstract class AbstractMenu extends VBox {
    private final MenuController controller;
    private final AppManager appManager;

    /**
     * Build a new {@link AbstractMenu}.
     * 
     * @param appManager the {@link AppManager} of the application.
     * @param controller the {@link MenuController} controlling the menu.
     */
    public AbstractMenu(final AppManager appManager, final MenuController controller) {
        this.controller = controller;
        this.appManager = appManager;
    }

    /**
     * Add a new node component to the menu.
     * 
     * @param <T> The type of node to add.
     * @param node the node component that has to be added to the menu.
     */
    public <T extends Node> void addNode(final T node) {
        this.getChildren().add(node);
    }

    /**
     * Add new nodes components to the menu.
     * 
     * @param <T> The type of node to add.
     * @param nodes the nodes components that have to be added to the menu.
     */
    public <T extends Node> void addNodes(final List<T> nodes) {
        nodes.forEach(this::addNode);
    }

    /**
     * Get the {@link MenuController}.
     * 
     * @return the {@link MenuController} .
     */
    public MenuController getMenuController() {
        return this.controller;
    }

    /**
     * Get the {@link PageContainer} where the menu is.
     * 
     * @return the {@link PageContainer} holding the menu.
     */
    public PageContainer getContainer() {
        return this.appManager.getContainer();
    }

    /**
     * Get the {@link AppManager} where the menu is.
     * 
     * @return the {@link AppManager} holding the menu.
     */
    public AppManager getAppManager() {
        return this.appManager;
    }
}
