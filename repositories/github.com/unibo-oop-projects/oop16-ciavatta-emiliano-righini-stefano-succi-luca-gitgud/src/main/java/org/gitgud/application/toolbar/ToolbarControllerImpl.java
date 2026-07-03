package org.gitgud.application.toolbar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.gitgud.application.WorkingAreaController;
import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.events.application.ModalListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.icons.IconType;
import org.gitgud.model.Model;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.Pair;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * An implementation of a toolbar controller.
 */
public class ToolbarControllerImpl implements ToolbarController {

    private static final double MERGE_MODE_IV_INSET_TOP = 5.0;
    private static final double MERGE_MODE_IV_INSET_RIGHT = 15.0;
    private static final Insets TEXTFIELD_INSETS = new Insets(0.0, 0.0, 20.0, 0.0);

    private final WorkingAreaController parent;
    private final Model model;
    private Pane pane;

    private Pane currentMergeMode;

    /**
     * @param parent
     *            the parent controller
     * @param model
     *            the application model
     */
    public ToolbarControllerImpl(final WorkingAreaController parent, final Model model) {
        this.parent = parent;
        this.model = model;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("Toolbar.fxml"));
            final ToolbarView view = loader.getController();
            view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: Toolbar.fxml");
        }
    }

    @Override
    public void doBranchAction() {
        if (parent.checkRepositoryOpened()) {
            parent.getBranchController().createBranch("HEAD");
        }
    }

    @Override
    public void doDiffAction() {
        parent.displayDiffOverviewFromToolbar();
    }

    @Override
    public void doLogAction() {
        parent.displayLogsView();
    }

    @Override
    public void doMergeMode() {
        if (!parent.checkRepositoryOpened()) {
            return;
        }

        final ModalBoxController modal = parent.getModalBox();
        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                ApplicationUtils.getApplicationProperties().setMergeMode((String) currentMergeMode.getUserData());
                modal.closeBox();
            }

            @Override
            public void onSecondaryButtonAction() {
            }
        };
        modal.setTitle("Select merge mode to use in pull operations");
        modal.enableCancelButton("Cancel");
        modal.enablePrimaryButton("Save");

        final List<String> options = Arrays.asList("fastforward-ifpossible", "fastforward-only", "merge-always",
                "rebase");
        final Map<String, ImageView> icons = new HashMap<>();
        icons.put(options.get(0), ApplicationUtils.createIcon("fastforward", IconType.ICON_DARK, 32));
        icons.put(options.get(1), ApplicationUtils.createIcon("commit", IconType.ICON_DARK, 32));
        icons.put(options.get(2), ApplicationUtils.createIcon("merge", IconType.ICON_DARK, 32));
        icons.put(options.get(3), ApplicationUtils.createIcon("rebase", IconType.ICON_DARK, 32));

        for (final String s : options) {
            final Label modeTitle = new Label(Utils.resolveString(ResourceType.MESSAGES, "merge.mode." + s + ".title"));
            final Label modeDescription = new Label(
                    Utils.resolveString(ResourceType.MESSAGES, "merge.mode." + s + ".description"));
            final Pane wrapperPane = new VBox(modeTitle, modeDescription);
            final Pane modeContainer = new HBox(icons.get(s), wrapperPane);
            HBox.setMargin(icons.get(s), new Insets(MERGE_MODE_IV_INSET_TOP, MERGE_MODE_IV_INSET_RIGHT, 0.0, 0.0));

            modeTitle.getStyleClass().add("modal-list-title");
            modeDescription.getStyleClass().add("modal-list-description");
            modeContainer.getStyleClass().add("modal-list-container");
            if (ApplicationUtils.getApplicationProperties().getMergeMode().equals(s)) {
                modeContainer.getStyleClass().add("selected");
                currentMergeMode = modeContainer;
            }

            modeContainer.setUserData(s);
            if (!s.equals(options.get(3))) {
                modeContainer.setOnMouseClicked(event -> {
                    final Pane newMode = (Pane) event.getSource();
                    currentMergeMode.getStyleClass().remove("selected");
                    newMode.getStyleClass().add("selected");
                    currentMergeMode = newMode;
                });
            }
            modal.addNodes(modeContainer);
        }
        modal.showModal(listener);
    }

    @Override
    public void doPullAction() {
        if (parent.checkRepositoryOpened()) {
            parent.getRemoteController().pull();
        }
    }

    @Override
    public void doPushAction() {
        if (parent.checkRepositoryOpened()) {
            parent.getRemoteController().push();
        }
    }

    @Override
    public void doRebaseAction() {
        unsupportedOperation();
    }

    @Override
    public void doRemoteAction() {
        if (parent.checkRepositoryOpened()) {
            parent.getRemoteController().addRemote();
        }
    }

    @Override
    public void doRepositoryAction() {
        parent.openRepositoryBox();
    }

    @Override
    public void doSettingsAction() {
        if (!parent.checkRepositoryOpened()) {
            return;
        }

        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Preferences");
        modal.enableCancelButton("Cancel");
        modal.enablePrimaryButton("Save");

        final Label lbAutoFetchInterval = new Label("Auto fetch interval");
        final TextField tfAutoFetchInterval = new TextField();
        final Label lbAutoFetchIntervalDescription = new Label("Fetches all remotes. 0 to disable");

        lbAutoFetchInterval.getStyleClass().add("text-field-title");
        lbAutoFetchIntervalDescription.getStyleClass().add("text-field-info");
        tfAutoFetchInterval.setText(String.valueOf(model.getRepositoryModel().getAutoFetchInterval()));

        modal.addNodes(lbAutoFetchInterval, tfAutoFetchInterval, lbAutoFetchIntervalDescription);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                int interval = 0;
                try {
                    interval = Integer.parseInt(tfAutoFetchInterval.getText());
                } catch (final Exception e) {
                    parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "user.edit.title.error"),
                            model.getRepositoryModel().getError().getErrorKey());
                    modal.closeBox();
                }

                final CommandStatus status = model.getRepositoryModel().setAutoFetchInterval(interval);
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(
                            Utils.resolveString(ResourceType.MESSAGES, "user.edit.title.success"),
                            Utils.resolveString(ResourceType.MESSAGES, "remote.edit.description"));
                } else {
                    parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "user.edit.title.error"),
                            model.getRepositoryModel().getError().getErrorKey());
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
    public void doStageAction() {
        parent.displayStagingView();
    }

    @Override
    public void doStashAction() {
        unsupportedOperation();
    }

    @Override
    public void doTagAction() {
        unsupportedOperation();
    }

    @Override
    public void doUserAction() {
        if (!parent.checkRepositoryOpened()) {
            return;
        }

        final ModalBoxController modal = parent.getModalBox();

        modal.setTitle("Edit git account");
        modal.enableCancelButton("Cancel");
        modal.enablePrimaryButton("Save");

        final Label lbName = new Label("Name");
        final TextField tfName = new TextField();
        final Label lbEmail = new Label("Email");
        final TextField tfEmail = new TextField();

        lbName.getStyleClass().add("text-field-title");
        lbEmail.getStyleClass().add("text-field-title");

        VBox.setMargin(tfName, TEXTFIELD_INSETS);

        final Optional<Pair<String, String>> identity = model.getRepositoryModel().getGlobalIdentity();
        if (identity.isPresent()) {
            tfName.setText(identity.get().getX());
            tfEmail.setText(identity.get().getY());
        }

        modal.addNodes(lbName, tfName, lbEmail, tfEmail);

        final ModalListener listener = new ModalListener() {

            @Override
            public void onCancelButtonAction() {
                modal.closeBox();
            }

            @Override
            public void onPrimaryButtonAction() {
                final CommandStatus status = model.getRepositoryModel().setGlobalIdentiry(tfName.getText(),
                        tfEmail.getText());
                if (status == CommandStatus.SUCCESS) {
                    parent.showSuccessNotification(
                            Utils.resolveString(ResourceType.MESSAGES, "user.edit.title.success"),
                            Utils.resolveString(ResourceType.MESSAGES, "remote.edit.description"));
                } else {
                    parent.showErrorNotification(Utils.resolveString(ResourceType.MESSAGES, "user.edit.title.error"),
                            model.getRepositoryModel().getError().getErrorKey());
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
    public Pane getPane() {
        return pane;
    }

    private void unsupportedOperation() {
        parent.showWarningNotification("Unsupported operation", "This operation has not yet been implemented.");
    }

}
