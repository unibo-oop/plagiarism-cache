package org.gitgud.application.remote;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.events.application.ModalListener;
import org.gitgud.model.Model;
import org.gitgud.model.remote.RemoteError;
import org.gitgud.model.remote.fetch.FetchParamBuilder;
import org.gitgud.model.remote.pull.PullParamBuilder;
import org.gitgud.model.remote.push.PushParamBuilder;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.model.utils.RepositoryUtils;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * An implementation of the remote controller.
 */
public class RemoteControllerImpl implements RemoteController {

    private static final String TEXT_FIELD_TITLE_CLASS = "text-field-title";
    private static final String CANCEL_BUTTON_NAME = "Cancel";
    private static final String REMOTE_PLACEHOLDER = "remote";
    private static final String BRANCH_PLACEHOLDER = "branch";
    private static final String OPERATION_PLACEHOLDER = "operation";
    private static final String AUTH_OP_CANC_TITLE = "authenticate.operation.cancelled.title";

    private static final Insets TEXTFIELD_INSETS = new Insets(0.0, 0.0, 20.0, 0.0);

    private final Model model;

    private final WorkingAreaController parent;

    /**
     * @param parent
     *            the parent controller
     * @param model
     *            the application model
     */
    public RemoteControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;
    }

    @Override
    public void addRemote() {
        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Add new remote");
        modal.enableCancelButton(CANCEL_BUTTON_NAME);
        modal.enablePrimaryButton("Add");

        final Label lbName = new Label("Name");
        final TextField tfName = new TextField();
        final Label lbUrl = new Label("URL");
        final TextField tfUrl = new TextField();

        lbName.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);
        lbUrl.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);

        VBox.setMargin(tfName, TEXTFIELD_INSETS);

        modal.addNodes(lbName, tfName, lbUrl, tfUrl);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getRemoteModel().getOperations().addRemote(tfName.getText(),
                        tfUrl.getText());
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.add.title"),
                            Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.add.description"),
                                    REMOTE_PLACEHOLDER, tfName.getText()));
                } else {
                    parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.add.title"),
                            model.getRemoteModel().getOperations().getError().getErrorKey());
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
    public void authenticate(final boolean wrongCredentials) {
        authenticate(null, null, wrongCredentials);
    }

    @Override
    public void authenticate(final String operation, final Runnable afterAuthentication,
            final boolean wrongCredentials) {
        final ModalBoxController modal = parent.getModalBox();

        if (wrongCredentials) {
            modal.setTitle("Wrong credentials");
        } else {
            modal.setTitle("Authentication required");
        }

        modal.enableCancelButton(CANCEL_BUTTON_NAME);
        modal.enablePrimaryButton("Login");

        final Label lbUsername = new Label("Username");
        final TextField tfUsername = new TextField();
        final Label lbPassword = new Label("Password");
        final PasswordField tfPassword = new PasswordField();

        lbUsername.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);
        lbPassword.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);

        VBox.setMargin(tfUsername, TEXTFIELD_INSETS);

        modal.addNodes(lbUsername, tfUsername, lbPassword, tfPassword);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                if (operation != null) {
                    parent.showWarningNotification(Utils.resolveString(ResourceType.MESSAGES, AUTH_OP_CANC_TITLE),
                            Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, AUTH_OP_CANC_TITLE),
                                    OPERATION_PLACEHOLDER, operation));
                }
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getRepositoryModel().setCredentials(tfUsername.getText(),
                        tfPassword.getText());
                if (status == CommandStatus.SUCCESS) {
                    if (afterAuthentication != null) {
                        Utils.doHardWork(afterAuthentication);
                    }
                } else {
                    parent.showWarningNotification(Utils.resolveString(ResourceType.MESSAGES, AUTH_OP_CANC_TITLE),
                            Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, AUTH_OP_CANC_TITLE),
                                    OPERATION_PLACEHOLDER, operation));
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
    public void editRemote(final String remoteName) {
        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Edit existing remote");
        modal.enableCancelButton(CANCEL_BUTTON_NAME);
        modal.enablePrimaryButton("Save");

        final Label lbName = new Label("Name");
        final TextField tfName = new TextField();
        final Label lbUrl = new Label("URL");
        final TextField tfUrl = new TextField();

        lbName.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);
        lbUrl.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);
        tfName.getStyleClass().add("readonly");
        tfName.setText(remoteName);
        tfName.setEditable(false);

        VBox.setMargin(tfName, TEXTFIELD_INSETS);

        modal.addNodes(lbName, tfName, lbUrl, tfUrl);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getRemoteModel().getOperations().editRemote(remoteName,
                        tfUrl.getText());
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.edit.title"),
                            Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.edit.description"),
                                    REMOTE_PLACEHOLDER, remoteName));
                } else {
                    parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.edit.title"),
                            model.getRemoteModel().getOperations().getError().getErrorKey());
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
    public void fetchRemote(final String remoteName) {
        final FetchParamBuilder param = FetchParamBuilder.createFetchParamBuilder();
        param.remoteName(remoteName).checkValidity(true).isDryRun(false)
                .progressListener(parent.startTaskProgressMode(Utils.replacePattern(
                        Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.task.title"), REMOTE_PLACEHOLDER,
                        remoteName)));

        final CommandStatus status = model.getRemoteModel().getActions().fetch(param.build());
        parent.stopTaskProgressMode();
        if (status == CommandStatus.SUCCESS_NO_UPDATES || status == CommandStatus.SUCCESS_WITH_UPDATES) {
            parent.showInfoNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.title"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.description"),
                            REMOTE_PLACEHOLDER, remoteName));
        } else if (status == CommandStatus.ERROR) {
            final RemoteError error = model.getRemoteModel().getActions().getError();
            if (error == RemoteError.AUTHENTICATION_REQUIRED) {
                authenticate("fetch", () -> fetchRemote(remoteName), false);
            } else if (error == RemoteError.NOT_AUTHORIZED) {
                authenticate("fetch", () -> fetchRemote(remoteName), true);
            } else {
                parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.fetch.title"),
                        error.getErrorKey());
            }
        }
    }

    @Override
    public void pull() {
        final PullParamBuilder param = PullParamBuilder.createPullParamBuilder();
        final String mergeMode = ApplicationUtils.getApplicationProperties().getMergeMode();
        if (mergeMode.equals("fastforward-only")) {
            param.onlyFastForward(true);
        } else if (mergeMode.equals("merge-always")) {
            param.alwaysMerge(true);
        }

        final String branchName = RepositoryUtils.formatLocalBranchRef(model.getBranchModel().getCheckedOutBranch(),
                true);
        param.commitMessage(Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "merge.commit.message"),
                BRANCH_PLACEHOLDER, branchName)).progressListener(parent.startTaskProgressMode(Utils.replacePattern(
                        Utils.resolveString(ResourceType.MESSAGES, "remote.pull.task.title"), BRANCH_PLACEHOLDER,
                        branchName)));

        final CommandStatus status = model.getRemoteModel().getActions().pull(param.build());
        parent.stopTaskProgressMode();
        if (status != CommandStatus.ERROR) {
            parent.showInfoNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.pull.title"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.pull.description"),
                            BRANCH_PLACEHOLDER, branchName));
        } else {
            final RemoteError error = model.getRemoteModel().getActions().getError();
            if (error == RemoteError.AUTHENTICATION_REQUIRED) {
                authenticate("pull", () -> pull(), false);
            } else if (error == RemoteError.NOT_AUTHORIZED) {
                authenticate("pull", () -> pull(), true);
            } else {
                parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.pull.title"),
                        model.getRemoteModel().getActions().getError().getErrorKey());
            }
        }
    }

    @Override
    public void push() {
        final PushParamBuilder pushParamBuilder = PushParamBuilder.createPushParamBuilder();
        final String branchName = RepositoryUtils.formatLocalBranchRef(model.getBranchModel().getCheckedOutBranch(),
                true);
        pushParamBuilder.dryRun(false).force(false).pushAll(false).pushTags(true)
                .progressListener(parent.startTaskProgressMode(Utils.replacePattern(
                        Utils.resolveString(ResourceType.MESSAGES, "remote.push.task.title"), BRANCH_PLACEHOLDER,
                        branchName)));

        final CommandStatus status = model.getRemoteModel().getActions().push(pushParamBuilder.build());
        parent.stopTaskProgressMode();
        if (status != CommandStatus.ERROR) {
            parent.showInfoNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.push.title.success"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.push.description.success"),
                            BRANCH_PLACEHOLDER, branchName));
        } else {
            final RemoteError error = model.getRemoteModel().getActions().getError();
            if (error == RemoteError.AUTHENTICATION_REQUIRED) {
                authenticate("push", () -> push(), false);
            } else if (error == RemoteError.NOT_AUTHORIZED) {
                authenticate("push", () -> push(), true);
            } else {
                parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.push.title.error"),
                        model.getRemoteModel().getActions().getError().getErrorKey());
            }
        }
    }

    @Override
    public void removeRemote(final String remoteName) {
        final CommandStatus status = model.getRemoteModel().getOperations().removeRemote(remoteName);
        if (status == CommandStatus.SUCCESS) {
            parent.showSuccessNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.remove.title"),
                    Utils.replacePattern(Utils.resolveString(ResourceType.MESSAGES, "remote.remove.description"),
                            REMOTE_PLACEHOLDER, remoteName));
        } else {
            parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.remove.title"),
                    model.getRemoteModel().getActions().getError().getErrorKey());
        }
    }

    @Override
    public void setUpstream(final String branchName) {
        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Set upstream");
        modal.enableCancelButton(CANCEL_BUTTON_NAME);
        modal.enablePrimaryButton("Save");

        final Label lbName = new Label("Remote name");
        final TextField tfName = new TextField();
        final Label lbBranch = new Label("Remote branch");
        final TextField tfBranch = new TextField();

        lbName.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);
        lbBranch.getStyleClass().add(TEXT_FIELD_TITLE_CLASS);

        VBox.setMargin(tfName, TEXTFIELD_INSETS);

        modal.addNodes(lbName, tfName, lbBranch, tfBranch);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getRemoteModel().getOperations().setUpstream(branchName,
                        tfName.getText(), tfBranch.getText());
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.upstream.title"),
                            Utils.replacePattern(
                                    Utils.resolveString(ResourceType.MESSAGES, "remote.upstream.description"),
                                    BRANCH_PLACEHOLDER,
                                    branchName));
                } else {
                    parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "remote.upstream.title"),
                            model.getRemoteModel().getOperations().getError().getErrorKey());
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
