package view;

import common.CommonStrings;
import controller.menu.Controller;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Generic view class. This class contains a scene with an AnchorPane as root.
 * The implementing class is registered on the EventBus.
 */
public class GenericView {
    private final Scene scene;
    private final AnchorPane anchor;
    private final Controller controller;

    public GenericView(final Controller c) {
        super();
        this.anchor = new AnchorPane();
        this.scene = new Scene(anchor, CommonStrings.WINDOW_HEIGHT, CommonStrings.WINDOW_HEIGHT);
        this.scene.getStylesheets().add("assets/general_graphic.css");
        controller = c;
    }

    /**
     * Add node in the anchorPane. It must be call to create
     * 
     * @param node to add
     */
    public void init(final Node child) {
        this.anchor.getChildren().add(child);
    }

    /**
     * Add node in the anchorPane and set anchor offset between node and anchor
     * 
     * @param node
     * @param top    : top offset
     * @param buttom : buttom offset
     * @param left   : left offset
     * @param right  : right offset
     */
    public void init(final Node child, final Double top, final Double buttom, final Double left, final Double right) {
        init(child);
        AnchorPane.setTopAnchor(child, top);
        AnchorPane.setBottomAnchor(child, buttom);
        AnchorPane.setLeftAnchor(child, left);
        AnchorPane.setRightAnchor(child, right);

    }

    /**
     * Returns the scene of the generic view with an AnchorPane as root.
     * 
     * @return the scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Returns the dimension of the window
     * 
     * @return the dimension of the window
     */
    public Dimension2D getDimension() {
        return new Dimension2D(scene.getWidth(), scene.getHeight());
    }

    /**
     * Returns the anchorPane of the generic view.
     * 
     * @return the anchorPane
     */
    public AnchorPane getAnchorPane() {
        return this.anchor;
    }

    /**
     * Return the controller. It must be call to implements communication between
     * view and controller.
     * 
     * @return view's controller
     */
    public Controller getController() {
        return controller;
    }

}
