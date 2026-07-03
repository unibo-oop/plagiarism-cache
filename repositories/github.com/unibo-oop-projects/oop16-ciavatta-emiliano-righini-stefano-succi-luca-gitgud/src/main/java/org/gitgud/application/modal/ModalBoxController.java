package org.gitgud.application.modal;

import org.gitgud.application.node.Box;
import org.gitgud.events.application.ModalListener;

import javafx.scene.Node;

/**
 * The modal controller relative to the modal box.
 */
public interface ModalBoxController extends Box {

    /**
     * @param nodes
     *            the nodes to be added to the modal box
     */
    void addNodes(Node... nodes);

    /**
     * @param buttonName
     *            the cancel button name
     */
    void enableCancelButton(String buttonName);

    /**
     * @param buttonName
     *            the primary button name
     */
    void enablePrimaryButton(String buttonName);

    /**
     * @param buttonName
     *            the secondary button name
     */
    void enableSecondaryButton(String buttonName);

    /**
     * @param title
     *            the modal title
     */
    void setTitle(String title);

    /**
     * Show the modal.
     *
     * @param listener
     *            the listener that should be informed when the user made his choice
     */
    void showModal(ModalListener listener);

}
