package org.gitgud.application.branch;

import java.util.Arrays;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.events.application.ModalListener;
import org.gitgud.model.Model;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * The branch controller implementation.
 */
public class BranchControllerImpl implements BranchController {

    private static final String BRANCH_PLACEHOLDER = "branch";

    private static final Insets TEXTFIELD_INSETS = new Insets(0.0, 0.0, 20.0, 0.0);

    private final Model model;

    private final WorkingAreaController parent;

    /**
     * @param parent
     *            the parent controller
     * @param model
     *            the application model
     */
    public BranchControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;
    }

    @Override
    public void checkoutLocalBranch(final String branchName) {
        // TODO Auto-generated method stub
    }

    @Override
    public void checkoutRemoteBranch(final String branchName) {
        // TODO Auto-generated method stub
    }

    @Override
    public void createBranch(final String startPoint) {
        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Create new branch");
        modal.enableCancelButton("Cancel");
        modal.enablePrimaryButton("Create");

        final Label lbName = new Label("Name");
        final TextField tfName = new TextField();

        lbName.getStyleClass().add("text-field-title");

        VBox.setMargin(tfName, TEXTFIELD_INSETS);

        modal.addNodes(lbName, tfName);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getBranchModel().createBranch(tfName.getText(), startPoint);
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(
                            Utils.resolveString(ResourceType.MESSAGES, "branch.create.title.success"),
                            Utils.replacePattern(
                                    Utils.resolveString(ResourceType.MESSAGES, "branch.create.description.success"),
                                    BRANCH_PLACEHOLDER, tfName.getText()));
                } else {
                    parent.showErrorNotification(
                            Utils.resolveString(ResourceType.MESSAGES, "branch.create.title.error"),
                            model.getBranchModel().getError().getErrorCode());
                }
                modal.closeBox();
            }

            @Override
            public void onSecondaryButtonAction() {
            }
        };

        modal.showModal(listener);
    }

    @Override
    public void createBranchFromRef(final String branchName, final String ref) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteLocalBranch(final String branchName) {
        final CommandStatus status = model.getBranchModel().deleteBranch(Arrays.asList(branchName));
        if (status == CommandStatus.SUCCESS) {

            parent.showSuccessNotification(Utils.resolveString(ResourceType.MESSAGES, "branch.delete.title"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "branch.delete.description"),
                            BRANCH_PLACEHOLDER, branchName));
        } else {
            parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "branch.delete.title"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES,
                            model.getBranchModel().getError().getErrorCode()), BRANCH_PLACEHOLDER, branchName));
        }
    }

    @Override
    public void renameLocalBranch(final String branchName) {
        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Rename branch " + branchName);
        modal.enableCancelButton("Cancel");
        modal.enablePrimaryButton("Rename");

        final Label lbName = new Label("Name");
        final TextField tfName = new TextField();

        lbName.getStyleClass().add("text-field-title");

        VBox.setMargin(tfName, TEXTFIELD_INSETS);

        modal.addNodes(lbName, tfName);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getBranchModel().renameBranch(branchName, tfName.getText());
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(
                            Utils.resolveString(ResourceType.MESSAGES, "branch.rename.title.success"),
                            Utils.replacePattern(
                                    Utils.resolveString(ResourceType.MESSAGES, "branch.rename.description.success"),
                                    BRANCH_PLACEHOLDER, tfName.getText()));
                } else {
                    parent.showErrorNotification(
                            Utils.resolveString(ResourceType.MESSAGES, "branch.rename.title.error"),
                            model.getBranchModel().getError().getErrorCode());
                }
                modal.closeBox();
            }

            @Override
            public void onSecondaryButtonAction() {
            }
        };

        modal.showModal(listener);

    }

}
