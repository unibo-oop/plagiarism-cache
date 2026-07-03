package org.gitgud.application.stage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 */
public class StagingAreaFxmlController implements StagingAreaView {

    private static final double VBOX_RIGHT_PADDING = 30.0;
    private static final double LABEL_RIGHT_PADDING = 300.0;
    private static final double SEPARATOR = 10.0;
    private static final String UNTRACKED_TITLE = Utils.resolveString(ResourceType.LABELS,
            "label.stage.untracked.title");
    private static final String NOT_STAGED_TITLE = Utils.resolveString(ResourceType.LABELS,
            "label.stage.notstaged.title");
    private static final String STAGED_TITLE = Utils.resolveString(ResourceType.LABELS, "label.stage.staged.title");
    private StagingAreaController ctrl;
    private final Map<String, Label> untracked = new HashMap<>();
    private final Map<String, Label> notStaged = new HashMap<>();
    private final Map<String, Label> staged = new HashMap<>();

    @FXML
    private Label lUntracked;
    @FXML
    private Label lNotStaged;
    @FXML
    private Label lStaged;
    @FXML
    private Button btnAddAllUntracked;
    @FXML
    private Button btnCleanAllUntracked;

    @FXML
    private Button btnAddAllNotStaged;
    @FXML
    private Button btnRemoveAllNotStaged;
    @FXML
    private Button btnDeleteAllNotStaged;

    @FXML
    private Button btnRemoveAllStaged;
    @FXML
    private Button btnDeleteAllStaged;
    @FXML
    private Button btnResetAllStaged;

    @FXML
    private VBox vbUntracked;
    @FXML
    private VBox vbNotStaged;
    @FXML
    private VBox vbStaged;

    @FXML
    private ScrollPane spUntracked;
    @FXML
    private ScrollPane spNotStaged;
    @FXML
    private ScrollPane spStaged;

    /**
     *
     */
    @FXML
    public void initialize() {
        this.btnAddAllUntracked.setOnAction(e -> this.ctrl.doAddOperation(this.untracked.keySet()));
        this.btnCleanAllUntracked.setOnAction(e -> this.ctrl.doCleanOperation(this.untracked.keySet()));

        this.btnAddAllNotStaged.setOnAction(e -> this.ctrl.doAddOperation(this.notStaged.keySet()));
        this.btnRemoveAllNotStaged.setOnAction(e -> this.ctrl.doRemoveOperation(this.notStaged.keySet(), true));
        this.btnDeleteAllNotStaged.setOnAction(e -> this.ctrl.doRemoveOperation(this.notStaged.keySet(), false));

        this.btnRemoveAllStaged.setOnAction(e -> this.ctrl.doRemoveOperation(this.staged.keySet(), true));
        this.btnDeleteAllStaged.setOnAction(e -> this.ctrl.doRemoveOperation(this.staged.keySet(), false));
        this.btnResetAllStaged.setOnAction(e -> this.ctrl.doResetOperation(this.staged.keySet()));

        this.vbUntracked.prefWidthProperty().bind(this.spUntracked.widthProperty());
        this.spUntracked.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.vbUntracked.maxWidthProperty()
                    .set(newVal.doubleValue() - StagingAreaFxmlController.VBOX_RIGHT_PADDING);
            this.vbUntracked.minWidthProperty()
                    .set(newVal.doubleValue() - StagingAreaFxmlController.VBOX_RIGHT_PADDING);
        });
        this.vbNotStaged.prefWidthProperty().bind(this.spNotStaged.widthProperty());
        this.spNotStaged.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.vbNotStaged.maxWidthProperty()
                    .set(newVal.doubleValue() - StagingAreaFxmlController.VBOX_RIGHT_PADDING);
            this.vbNotStaged.minWidthProperty()
                    .set(newVal.doubleValue() - StagingAreaFxmlController.VBOX_RIGHT_PADDING);
        });
        this.vbStaged.prefWidthProperty().bind(this.spStaged.widthProperty());
        this.spStaged.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.vbStaged.maxWidthProperty().set(newVal.doubleValue() - StagingAreaFxmlController.VBOX_RIGHT_PADDING);
            this.vbStaged.minWidthProperty().set(newVal.doubleValue() - StagingAreaFxmlController.VBOX_RIGHT_PADDING);
        });
    }

    @Override
    public void attachController(final StagingAreaController ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void setLabelsLength() {
        this.vbUntracked.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.shrinkPathLabels(this.untracked, newVal.doubleValue() - StagingAreaFxmlController.LABEL_RIGHT_PADDING);
        });
        this.vbNotStaged.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.shrinkPathLabels(this.notStaged, newVal.doubleValue() - StagingAreaFxmlController.LABEL_RIGHT_PADDING);
        });
        this.vbStaged.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.shrinkPathLabels(this.staged, newVal.doubleValue() - StagingAreaFxmlController.LABEL_RIGHT_PADDING);
        });
    }

    @Override
    public void setNotStaged(final Set<Pair<String, ChangeType>> notStaged) {
        this.vbNotStaged.getChildren().clear();
        this.notStaged.clear();
        this.lNotStaged
                .setText(StagingAreaFxmlController.NOT_STAGED_TITLE + " (" + notStaged.size() + ")");
        notStaged.forEach((p) -> {
            final List<Button> buttons = new LinkedList<>();
            if (!p.getY().equals(ChangeType.DELETED)) {
                buttons.add(this.createAddBtn(p.getX()));
            }
            buttons.add(this.createRemoveBtn(p.getX()));
            buttons.add(this.createDeleteBtn(p.getX()));
            final HBox hb = this.createFileLine(p, buttons, this.vbNotStaged.getWidth());
            this.vbNotStaged.getChildren().add(hb);
            this.notStaged.put(p.getX(), (Label) hb.getChildren().get(1));
        });
    }

    @Override
    public void setStaged(final Set<Pair<String, ChangeType>> staged) {
        this.vbStaged.getChildren().clear();
        this.staged.clear();
        this.lStaged.setText(StagingAreaFxmlController.STAGED_TITLE + " (" + staged.size() + ")");
        staged.forEach((p) -> {
            final List<Button> buttons = new LinkedList<>();
            buttons.add(this.createRemoveBtn(p.getX()));
            buttons.add(this.createDeleteBtn(p.getX()));
            buttons.add(this.createResetBtn(p.getX()));
            final HBox hb = this.createFileLine(p, buttons, this.vbStaged.getWidth());
            this.vbStaged.getChildren().add(hb);
            this.staged.put(p.getX(), (Label) hb.getChildren().get(1));
        });
    }

    @Override
    public void setUntracked(final Set<Pair<String, ChangeType>> untracked) {
        this.vbUntracked.getChildren().clear();
        this.untracked.clear();
        this.lUntracked
                .setText(StagingAreaFxmlController.UNTRACKED_TITLE + " (" + untracked.size() + ")");
        untracked.forEach((p) -> {
            final List<Button> buttons = new LinkedList<>();
            buttons.add(this.createAddBtn(p.getX()));
            buttons.add(this.createCleanBtn(p.getX()));
            final HBox hb = this.createFileLine(p, buttons, this.vbUntracked.getWidth());
            this.vbUntracked.getChildren().add(hb);
            this.untracked.put(p.getX(), (Label) hb.getChildren().get(1));
        });
    }

    private HBox createFileLine(final Pair<String, ChangeType> p, final List<Button> buttons,
            final double containerWidth) {
        final HBox hb = new HBox();
        final List<Node> c = hb.getChildren();
        c.add(ApplicationUtils.createChangeTypeIcon(p.getY()));
        final Label l = new Label();
        l.setText(ApplicationUtils.pathLabelShortener(p.getX(), l.getFont(),
                containerWidth - StagingAreaFxmlController.LABEL_RIGHT_PADDING));
        c.add(l);
        final Region region = new Region();
        c.add(region);
        HBox.setHgrow(region, Priority.ALWAYS);
        buttons.forEach(b -> {
            final Region divider = new Region();
            divider.setPrefWidth(StagingAreaFxmlController.SEPARATOR);
            c.add(divider);
            c.add(b);
        });
        hb.setOnMouseEntered(e -> this.setButtonsVisible((HBox) e.getSource(), true));
        hb.setOnMouseExited(e -> this.setButtonsVisible((HBox) e.getSource(), false));
        hb.getStyleClass().add("stage-file-line");
        return hb;
    }

    private Button createAddBtn(final String path) {
        final Button btn = new Button(Utils.resolveString(ResourceType.LABELS, "label.stage.button.add"));
        btn.setOnAction(e -> {
            final Set<String> paths = new HashSet<>();
            paths.add(path);
            this.ctrl.doAddOperation(paths);
        });
        btn.getStyleClass().add("stage-button-green");
        btn.setVisible(false);
        return btn;
    }

    private Button createCleanBtn(final String path) {
        final Button btn = new Button(Utils.resolveString(ResourceType.LABELS, "label.stage.button.clean"));
        btn.setOnAction(e -> {
            final Set<String> paths = new HashSet<>();
            paths.add(path);
            this.ctrl.doCleanOperation(paths);
        });
        btn.getStyleClass().add("stage-button-red");
        btn.setVisible(false);
        return btn;
    }

    private Button createDeleteBtn(final String path) {
        final Button btn = new Button(Utils.resolveString(ResourceType.LABELS, "label.stage.button.delete"));
        btn.setOnAction(e -> {
            final Set<String> paths = new HashSet<>();
            paths.add(path);
            this.ctrl.doRemoveOperation(paths, false);
        });
        btn.getStyleClass().add("stage-button-red");
        btn.setVisible(false);
        return btn;
    }

    private Button createRemoveBtn(final String path) {
        final Button btn = new Button(Utils.resolveString(ResourceType.LABELS, "label.stage.button.remove"));
        btn.setOnAction(e -> {
            final Set<String> paths = new HashSet<>();
            paths.add(path);
            this.ctrl.doRemoveOperation(paths, true);
        });
        btn.getStyleClass().add("stage-button-yellow");
        btn.setVisible(false);
        return btn;
    }

    private Button createResetBtn(final String path) {
        final Button btn = new Button(Utils.resolveString(ResourceType.LABELS, "label.stage.button.reset"));
        btn.setOnAction(e -> {
            final Set<String> paths = new HashSet<>();
            paths.add(path);
            this.ctrl.doResetOperation(paths);
        });
        btn.getStyleClass().add("stage-button-blue");
        btn.setVisible(false);
        return btn;
    }

    private void setButtonsVisible(final HBox hb, final boolean visible) {
        final List<Node> c = hb.getChildren();
        for (int i = 3; i < c.size(); i++) {
            hb.getChildren().get(i).setVisible(visible);
        }
    }

    private void shrinkPathLabels(final Map<String, Label> map, final double width) {
        map.entrySet().forEach(e -> {
            final Label l = e.getValue();
            l.setText(ApplicationUtils.pathLabelShortener(e.getKey(), l.getFont(), width));
        });
    }
}
