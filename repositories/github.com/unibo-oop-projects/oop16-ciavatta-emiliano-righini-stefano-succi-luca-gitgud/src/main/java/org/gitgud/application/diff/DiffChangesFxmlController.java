package org.gitgud.application.diff;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Pair;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * The implementation of diff changes view.
 */
public class DiffChangesFxmlController implements DiffChangesView {

    private static final double CHANGES_CONTAINER_RIGHT_PADDING = 50.0;
    private static final double CHANGE_TYPE_ICON_GAP = 10.0;
    private static final double CHANGES_COUNT_ICON_GAP = 5.0;
    private static final Insets CHANGES_COUNT_INSETS = new Insets(0.0, 5.0, 10.0, 0.0);

    private DiffOverviewController ctrl;
    private int id;

    @FXML
    private Pane changesCounterContainer;
    @FXML
    private Pane changesContainer;

    private final List<Label> pathLabels = new ArrayList<>();

    @Override
    public void attachController(final DiffOverviewController ctrl, final int id) {
        this.ctrl = ctrl;
        this.id = id;
    }

    @Override
    public void setChanges(final List<Pair<String, ChangeType>> changes) {
        changesContainer.getChildren().clear();
        changesCounterContainer.getChildren().clear();
        pathLabels.clear();

        changes.forEach(p -> {
            final Label changeFile = new Label(p.getX());
            changeFile.setUserData(p.getX());
            changeFile.setGraphic(ApplicationUtils.createChangeTypeIcon(p.getY()));
            changeFile.setGraphicTextGap(CHANGE_TYPE_ICON_GAP);
            pathLabels.add(changeFile);
            final HBox change = new HBox(changeFile);
            change.getStyleClass().add("diff-changes");
            change.setUserData(p.getX());
            change.setOnMouseClicked(e -> onChangeClick(e));
            HBox.setHgrow(changeFile, Priority.ALWAYS);
            changesContainer.getChildren().add(change);
        });

        changes.stream().map(p -> p.getY()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 0)
                .sorted((e1, e2) -> (int) (e2.getValue() - e1.getValue())).forEach(e -> {
                    final Label counter = new Label(
                            String.valueOf(e.getValue()).concat(" ")
                                    .concat(Utils.resolveString(ResourceType.LABELS, e.getKey().getLabelKey())
                                            .toLowerCase(Utils.getLocale())));
                    counter.setGraphic(ApplicationUtils.createChangeTypeIcon(e.getKey()));
                    counter.setGraphicTextGap(CHANGES_COUNT_ICON_GAP);
                    HBox.setMargin(counter, CHANGES_COUNT_INSETS);

                    changesCounterContainer.getChildren().add(counter);
                });

        shrinkPathLabels(changesContainer.getWidth() - CHANGES_CONTAINER_RIGHT_PADDING);

        changesContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
            shrinkPathLabels(newVal.doubleValue() - CHANGES_CONTAINER_RIGHT_PADDING);
        });
    }

    private void onChangeClick(final MouseEvent event) {
        final String filePath = (String) ((Pane) event.getSource()).getUserData();
        Utils.doHardWork(() -> ctrl.computeFileDiff(filePath, id));
    }

    private void shrinkPathLabels(final double width) {
        pathLabels.forEach(
                l -> l.setText(ApplicationUtils.pathLabelShortener((String) l.getUserData(), l.getFont(), width)));
    }

}
