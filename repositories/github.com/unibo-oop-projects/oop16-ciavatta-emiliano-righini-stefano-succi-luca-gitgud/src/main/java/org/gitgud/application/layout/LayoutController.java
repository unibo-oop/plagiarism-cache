package org.gitgud.application.layout;

import org.gitgud.application.node.Panel;

import javafx.scene.layout.Pane;

/**
 * The controller of a generic layout.
 */
public interface LayoutController extends Panel {

    /**
     * @param pane
     *            the pane to set
     */
    void setCenterPane(Pane pane);

    /**
     * @param pane
     *            the pane to set
     */
    void setLeftPane(Pane pane);

    /**
     * @param pane
     *            the pane to set
     */
    void setRightPane(Pane pane);

}
