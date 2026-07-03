package org.gitgud.application.toolbar;

import org.gitgud.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * An implementation of a toolbar view.
 */
public class ToolbarFxmlController implements ToolbarView {

    private ToolbarController ctrl;

    @Override
    public void attachController(final ToolbarController ctrl) {
        this.ctrl = ctrl;
    }

    @FXML // NOPMD
    void onBranchAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doBranchAction());
    }

    @FXML // NOPMD
    void onDiffAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doDiffAction());
    }

    @FXML // NOPMD
    void onLogAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doLogAction());
    }

    @FXML // NOPMD
    void onMergeModeAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doMergeMode());
    }

    @FXML // NOPMD
    void onPullAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doPullAction());
    }

    @FXML // NOPMD
    void onPushAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doPushAction());
    }

    @FXML // NOPMD
    void onRebaseAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doRebaseAction());
    }

    @FXML // NOPMD
    void onRemoteAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doRemoteAction());
    }

    @FXML // NOPMD
    void onRepositoryAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doRepositoryAction());
    }

    @FXML // NOPMD
    void onSettingsAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doSettingsAction());
    }

    @FXML // NOPMD
    void onStageAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doStageAction());
    }

    @FXML // NOPMD
    void onStashAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doStashAction());
    }

    @FXML // NOPMD
    void onTagAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doTagAction());
    }

    @FXML // NOPMD
    void onUserAction(final ActionEvent event) {
        Utils.doHardWork(() -> ctrl.doUserAction());
    }

}
