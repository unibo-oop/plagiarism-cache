package org.gitgud.application.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;

/**
 * An implementation of a repository box view.
 */
public class RepositoryBoxFxmlController implements RepositoryBoxView {

    private static final String ERROR_CLASS = "input-error";

    private RepositoryBoxController ctrl;

    private final Map<TextInputControl, String> mapper = new HashMap<>();
    private final Map<Pane, Button> sectors = new HashMap<>();
    private final Map<String, Pane> sectorsStringMapper = new HashMap<>();

    private boolean errorBoxShown = true;
    private boolean progressBoxShown = true;

    @FXML
    private Pane paneContainer;

    @FXML
    private Pane errorSection;
    @FXML
    private Pane progressSection;
    @FXML
    private Pane openSection;
    @FXML
    private Pane cloneSection;
    @FXML
    private Pane initSection;

    @FXML
    private Button btnSectionOpen;
    @FXML
    private Button btnSectionClone;
    @FXML
    private Button btnSectionInit;

    @FXML
    private TextInputControl tfRepository;
    @FXML
    private TextInputControl tfUrl;
    @FXML
    private TextInputControl tfDestination;
    @FXML
    private TextInputControl tfPath;
    @FXML
    private TextInputControl tfUsername;
    @FXML
    private TextInputControl tfPassword;
    @FXML
    private TextInputControl tfGitignore;
    @FXML
    private TextInputControl tfLicense;

    @FXML
    private Label lCurrentOperation;
    @FXML
    private ProgressBar pbCloningProgress;

    @Override
    public void attachController(final RepositoryBoxController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * The documentation for this method is not necessary.
     *
     * @param event
     *            unknown
     */
    @FXML
    public void btnBrowseOnCommand(final ActionEvent event) {
        final String id = ((Button) event.getSource()).getId();

        if (id.equals("btnBrowseRepository")) {
            ctrl.openDirectoryChooser("repository");
        } else if (id.equals("btnBrowseDestination")) {
            ctrl.openDirectoryChooser("destination");
        } else if (id.equals("btnBrowsePath")) {
            ctrl.openDirectoryChooser("path");
        }
    }

    /**
     * The documentation for this method is not necessary.
     *
     * @param event
     *            unknown
     */
    @FXML
    public void btnChangeSectionOnCommand(final ActionEvent event) {
        final Pane sector = sectors.entrySet().stream()
                .filter(e -> e.getValue().equals(event.getSource()))
                .map(e -> e.getKey())
                .findAny()
                .get();
        changeActiveSection(sector);
    }

    /**
     * The documentation for this method is not necessary.
     *
     * @param event
     *            unknown
     */
    @FXML
    public void btnCloneOnCommand(final ActionEvent event) {
        toggleErrorSection(false);
        Utils.doHardWork(() -> ctrl.cloneRepository(tfUrl.getText(), tfDestination.getText(), tfUsername.getText(),
                tfPassword.getText()));
        ctrl.setWaitingState(true);
        toggleProgressSection(true);
    }

    /**
     * @param event
     *            the ActionEvent
     */
    public void btnCloseOnAction(final ActionEvent event) {
        ctrl.closeBox();
    }

    /**
     * The documentation for this method is not necessary.
     *
     * @param event
     *            unknown
     */
    @FXML
    public void btnInitOnCommand(final ActionEvent event) {
        toggleErrorSection(false);
        Utils.doHardWork(() -> ctrl.initRepository(tfPath.getText(), tfGitignore.getText(), tfLicense.getText()));
        ctrl.setWaitingState(true);
    }

    /**
     * The documentation for this method is not necessary.
     *
     * @param event
     *            unknown
     */
    @FXML
    public void btnOpenOnCommand(final ActionEvent event) {
        toggleErrorSection(false);
        Utils.doHardWork(() -> ctrl.openRepository(tfRepository.getText()));
        ctrl.setWaitingState(true);
    }

    /**
     * The documentation for this method is not necessary.
     *
     * @param event
     *            unknown
     */
    @FXML
    public void btnPasteOnCommand(final ActionEvent event) {
        final String id = ((Node) event.getSource()).getId();

        if (id.equals("btnPasteUrl")) {
            ctrl.pasteFromClipboard("url");
        }
    }

    @Override
    public void changeSection(final String sectorKey) {
        changeActiveSection(sectorsStringMapper.get(sectorKey));
    }

    /**
     * The documentation for this method is not necessary.
     */
    @FXML
    public void initialize() {
        toggleErrorSection(false);
        toggleProgressSection(false);

        sectors.put(openSection, btnSectionOpen);
        sectors.put(cloneSection, btnSectionClone);
        sectors.put(initSection, btnSectionInit);

        mapper.put(tfRepository, "repository");
        mapper.put(tfUrl, "url");
        mapper.put(tfDestination, "destination");
        mapper.put(tfPath, "path");
        mapper.put(tfUsername, "username");
        mapper.put(tfPassword, "password");
        mapper.put(tfGitignore, "gitignore");
        mapper.put(tfLicense, "license");

        sectorsStringMapper.put("open", openSection);
        sectorsStringMapper.put("clone", cloneSection);
        sectorsStringMapper.put("init", initSection);

        mapper.keySet().forEach(tf -> {
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                if (tf.getStyleClass().contains(ERROR_CLASS)) {
                    tf.getStyleClass().remove(ERROR_CLASS);
                    toggleErrorSection(false);
                }
            });
        });
    }

    @Override
    public void markError(final String keyDestination) {
        final Optional<TextInputControl> textInput = getControlByKey(keyDestination);

        if (textInput.isPresent() && !textInput.get().getStyleClass().contains(ERROR_CLASS)) {
            textInput.get().getStyleClass().add(ERROR_CLASS);
        }
    }

    @Override
    public void onError(final String message) {
        ctrl.setWaitingState(false);
        displayError(message);
        toggleErrorSection(true);
        toggleProgressSection(false);
    }

    @Override
    public void onSuccess(final String message) {
        ctrl.setWaitingState(false);
        ctrl.closeBox();
    }

    @Override
    public void updateCloningProgressOperation(final String operationName) {
        lCurrentOperation.setText(operationName);
    }

    @Override
    public void updateCloningProgressPercentage(final double progress) {
        pbCloningProgress.setProgress(progress);
    }

    @Override
    public void writeField(final String keyDestination, final String value) {
        final Optional<TextInputControl> textInput = getControlByKey(keyDestination);

        if (textInput.isPresent()) {
            textInput.get().setText(value);
        }
    }

    private void changeActiveSection(final Pane newSection) {
        mapper.keySet().forEach(tic -> {
            tic.clear();
            tic.getStyleClass().remove(ERROR_CLASS);
        });

        toggleErrorSection(false);
        toggleProgressSection(false);

        sectors.keySet().forEach(p -> {
            paneContainer.getChildren().remove(p);
            sectors.get(p).getStyleClass().remove("active");
        });

        paneContainer.getChildren().add(newSection);
        sectors.get(newSection).getStyleClass().add("active");
    }

    private void displayError(final String errorMessage) {
        final Label errorLabel = (Label) errorSection.getChildren().get(0);
        errorLabel.setText(errorMessage);
    }

    private Optional<TextInputControl> getControlByKey(final String key) {
        return mapper.entrySet().stream()
                .filter(e -> e.getValue().equals(key))
                .map(e -> e.getKey())
                .findAny();
    }

    private void toggleErrorSection(final boolean display) {
        if (display && !errorBoxShown) {
            paneContainer.getChildren().add(0, errorSection);
            errorBoxShown = true;
        } else if (!display && errorBoxShown) {
            paneContainer.getChildren().remove(errorSection);
            errorBoxShown = false;
        }
    }

    private void toggleProgressSection(final boolean display) {
        if (display && !progressBoxShown) {
            paneContainer.getChildren().add(0, progressSection);
            progressBoxShown = true;
        } else if (!display && progressBoxShown) {
            paneContainer.getChildren().remove(progressSection);
            lCurrentOperation.setText(Utils.resolveString(ResourceType.LABELS, "label.cloning.current.operation"));
            pbCloningProgress.setProgress(0);
            progressBoxShown = false;
        }
    }

}
