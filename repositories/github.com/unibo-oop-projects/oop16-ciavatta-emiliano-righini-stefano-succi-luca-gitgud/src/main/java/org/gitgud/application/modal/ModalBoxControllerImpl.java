package org.gitgud.application.modal;

import java.util.Map;

import org.gitgud.application.ApplicationController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.events.application.ModalListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * The implementation of the modal controller.
 */
public class ModalBoxControllerImpl implements ModalBoxController {

    private static final String EMPTY_STRING = "";

    private final ApplicationController parent;
    private Pane pane;
    private final Label title;
    private final Pane nodesContainer;
    private final Button cancelButton;
    private final Button secondaryButton;
    private final Button primaryButton;

    private ModalListener listener;

    /**
     * @param parent
     *            the main ApplicationController
     */
    public ModalBoxControllerImpl(final ApplicationController parent) {
        this.parent = parent;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());

        try {
            pane = loader.load(getClass().getResourceAsStream("ModalBox.fxml"));
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: ModalBox.fxml");
        }

        final Map<String, Object> namespace = loader.getNamespace();
        title = (Label) namespace.get("title");
        nodesContainer = (Pane) namespace.get("nodesContainer");

        cancelButton = (Button) namespace.get("cancelButton");
        secondaryButton = (Button) namespace.get("secondaryButton");
        primaryButton = (Button) namespace.get("primaryButton");

        ApplicationUtils.setInvisible(cancelButton);
        ApplicationUtils.setInvisible(secondaryButton);
        ApplicationUtils.setInvisible(primaryButton);

        final Button closeButton = (Button) namespace.get("closeButton");

        cancelButton.setOnAction(e -> Utils.doHardWork(() -> listener.onCancelButtonAction()));
        secondaryButton.setOnAction(e -> Utils.doHardWork(() -> listener.onSecondaryButtonAction()));
        primaryButton.setOnAction(e -> Utils.doHardWork(() -> listener.onPrimaryButtonAction()));

        closeButton.setOnAction(event -> closeBox()); // NOPMD
    }

    @Override
    public void addNodes(final Node... nodes) {
        Utils.updateView(() -> {
            for (final Node n : nodes) {
                nodesContainer.getChildren().add(n);
            }
        });
    }

    @Override
    public void closeBox() {
        parent.closeModalBox();
        Utils.updateView(() -> {
            title.setText(EMPTY_STRING);
            nodesContainer.getChildren().clear();
            ApplicationUtils.setInvisible(cancelButton);
            ApplicationUtils.setInvisible(secondaryButton);
            ApplicationUtils.setInvisible(primaryButton);
        });
    }

    @Override
    public void enableCancelButton(final String buttonName) {
        Utils.updateView(() -> {
            ApplicationUtils.setVisible(cancelButton);
            cancelButton.setText(buttonName);
        });
    }

    @Override
    public void enablePrimaryButton(final String buttonName) {
        Utils.updateView(() -> {
            ApplicationUtils.setVisible(primaryButton);
            primaryButton.setText(buttonName);
        });
    }

    @Override
    public void enableSecondaryButton(final String buttonName) {
        Utils.updateView(() -> {
            ApplicationUtils.setVisible(secondaryButton);
            secondaryButton.setText(buttonName);
        });
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void setTitle(final String title) {
        Utils.updateView(() -> this.title.setText(title));
    }

    @Override
    public void showModal(final ModalListener listener) {
        Utils.updateView(() -> this.listener = listener);
        parent.openModalBox();
    }

}
