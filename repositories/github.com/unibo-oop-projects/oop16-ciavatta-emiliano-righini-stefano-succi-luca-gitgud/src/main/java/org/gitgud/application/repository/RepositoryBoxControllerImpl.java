package org.gitgud.application.repository;

import java.io.File;

import org.gitgud.application.ApplicationController;
import org.gitgud.events.EscPressedListener;
import org.gitgud.events.ExitActionListener;
import org.gitgud.events.ProgressListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.repository.CloneParam;
import org.gitgud.model.repository.InitParam;
import org.gitgud.model.repository.OpenParam;
import org.gitgud.model.repository.RepositoryError;
import org.gitgud.model.repository.RepositoryFactory;
import org.gitgud.model.repository.RepositoryModel;
import org.gitgud.model.utils.CommandStatus;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;

/**
 * An implementation of a repository box controller.
 */
public class RepositoryBoxControllerImpl implements RepositoryBoxController, EscPressedListener, ExitActionListener {

    private static final String FIELD_REQUIRED_ERROR = "repository.field.required";

    private final ApplicationController parent;
    private RepositoryBoxView view;
    private Pane pane;

    private final RepositoryModel repositoryModel;

    private boolean isCloningOperationInProgress;
    private boolean isCloningOperationCancelled;

    /**
     * @param parent
     *            the parent controller
     * @param model
     *            the application model
     */
    public RepositoryBoxControllerImpl(final ApplicationController parent, final Model model) {
        this.parent = parent;
        repositoryModel = model.getRepositoryModel();

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("RepositoryBox.fxml"));
            view = loader.getController();
            view.attachController(this);
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: RepositoryBox.fxml");
        }

        parent.addEscPressedListener(this);
        parent.addExitActionListener(this);
    }

    @Override
    public void cloneRepository(final String url, final String directory, final String username,  // NOPMD
            final String password) {
        if (url.isEmpty()) {
            Utils.updateView(() -> view.markError("url"));
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, FIELD_REQUIRED_ERROR)));
            return;
        } else if (directory.isEmpty()) {
            Utils.updateView(() -> view.markError("destination"));
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, FIELD_REQUIRED_ERROR)));
            return;
        }

        isCloningOperationInProgress = true;

        final CloneParam cloneParam = RepositoryFactory.createCloneBuilder().directory(directory).remote(url)
                .username(username.isEmpty() ? null : username)
                .password(password.isEmpty() ? null : password).progressListener(new ProgressListener() {

                    @Override
                    public boolean isCancelled() {
                        return isCloningOperationCancelled;
                    }

                    @Override
                    public void onProgressUpdated(final double progress) {
                        Utils.updateView(() -> view.updateCloningProgressPercentage(progress));
                    }

                    @Override
                    public void onTaskChanged(final String taskKey) {
                        Utils.updateView(() -> view
                                .updateCloningProgressOperation(Utils.resolveString(ResourceType.MESSAGES, taskKey)));
                    }

                }).build();

        if (repositoryModel.cloneRepository(cloneParam) == CommandStatus.SUCCESS) {
            Utils.updateView(() -> view.onSuccess(""));
        } else {
            final RepositoryError error = repositoryModel.getError();
            if (error == RepositoryError.INVALID_REMOTE) {
                Utils.updateView(() -> view.markError("url"));
            } else if (error == RepositoryError.INSUFFICIENT_PERMISSIONS
                    || error == RepositoryError.PATH_ALREADY_EXISTS) {
                Utils.updateView(() -> view.markError("destination"));
            }
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, error.getErrorKey())));
        }

        isCloningOperationInProgress = false;
    }

    @Override
    public void closeBox() {
        parent.closeRepositoryBox();
    }

    @Override
    public void commandOpenBox(final String menu) {
        Utils.updateView(() -> view.changeSection(menu));
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void initRepository(final String directory, final String gitignore, final String license) {
        if (directory.isEmpty()) {
            Utils.updateView(() -> view.markError("path"));
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, FIELD_REQUIRED_ERROR)));
            return;
        }

        final InitParam initParam = RepositoryFactory.createInitBuilder().directory(directory).gitIgnore(null)
                .gitLicense(null).build();

        if (repositoryModel.initRepository(initParam) == CommandStatus.SUCCESS) {
            Utils.updateView(() -> view.onSuccess(""));
        } else {
            final RepositoryError error = repositoryModel.getError();
            if (error == RepositoryError.INSUFFICIENT_PERMISSIONS || error == RepositoryError.PATH_ALREADY_EXISTS) {
                Utils.updateView(() -> view.markError("path"));
            }
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, error.getErrorKey())));
        }
    }

    @Override
    public void onEscPressed() {
        if (isCloningOperationInProgress) {
            isCloningOperationCancelled = true;
        }
    }

    @Override
    public void onExit() {
        isCloningOperationCancelled = true;
    }

    @Override
    public void openDirectoryChooser(final String sourceKey) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            Utils.updateView(() -> view.writeField(sourceKey, selectedDirectory.getAbsolutePath()));
        }
    }

    @Override
    public void openRepository(final String directory) {
        if (directory.isEmpty()) {
            Utils.updateView(() -> view.markError("repository"));
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, FIELD_REQUIRED_ERROR)));
            return;
        }

        final OpenParam openParam = RepositoryFactory.createOpenBuilder().directory(directory).build();

        if (repositoryModel.openRepository(openParam) == CommandStatus.SUCCESS) {
            Utils.updateView(() -> view.onSuccess(""));
        } else {
            final RepositoryError error = repositoryModel.getError();
            if (error == RepositoryError.INVALID_PATH) {
                Utils.updateView(() -> view.markError("repository"));
            }
            Utils.updateView(() -> view.onError(Utils.resolveString(ResourceType.ERRORS, error.getErrorKey())));
        }
    }

    @Override
    public void pasteFromClipboard(final String sourceKey) {
        Utils.updateView(() -> view.writeField(sourceKey, Clipboard.getSystemClipboard().getString()));
    }

    @Override
    public void setWaitingState(final boolean isWaiting) {
        parent.setWaitingState(isWaiting);
    }

}
