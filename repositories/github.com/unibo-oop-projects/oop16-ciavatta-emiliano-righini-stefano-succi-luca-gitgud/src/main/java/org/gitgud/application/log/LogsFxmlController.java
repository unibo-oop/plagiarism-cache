package org.gitgud.application.log;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.gitgud.utils.Pair;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 *
 */
public class LogsFxmlController implements LogsView {

    private static final double LINE_HEIGHT = 30.0;
    private static final double COL_OFFSET = 30.0;
    private static final double MAX_CANVAS_HEIGHT = LINE_HEIGHT * 150.0;

    private static final double STRAIGHT_LINE_X_POS = COL_OFFSET / 2;

    private static final double COMMIT_POS = 7.0;
    private static final double COMMIT_DIAMETER = 16.0;
    private static final double COMMIT_PADDING = (LINE_HEIGHT - COMMIT_DIAMETER) / 2;

    private static final double MAX_BRANCH_LABEL_WIDTH = 200.0;
    private static final double MIN_BRANCH_LABEL_WIDTH = 50.0;
    private static final double MIN_COMMIT_BOX_WIDTH = 400.0;
    private static final int STD_PADDING = 5;

    private static volatile boolean ctrl = false; // NOPMD

    private LogsDisplayController controller;
    private final List<HBox> records = new LinkedList<>();
    private final List<HBox> selectedRecords = new LinkedList<>();
    private final List<Canvas> canvasList = new LinkedList<>();
    private final List<GraphicsContext> contexts = new LinkedList<>();
    private Canvas canvas;
    private GraphicsContext gc;

    @FXML
    private VBox logVbox;
    @FXML
    private VBox canvasVbox;
    @FXML
    private VBox mainVbox; // NOPMD
    @FXML
    private HBox bottom;

    @Override
    public void addColumn() {
        canvas.setWidth(canvas.getWidth() + COL_OFFSET);
    }

    @Override
    public void addLine() {
        canvas.setHeight(canvas.getHeight() + LINE_HEIGHT);
    }

    @Override
    public void attachController(final LogsDisplayController controller) {
        this.controller = controller;
    }

    @Override
    public void drawCommit(final Pair<Integer, Integer> coordinate, final Color color) {
        gc.setFill(color);
        gc.fillOval(COMMIT_POS + COL_OFFSET * coordinate.getX(),
                COMMIT_POS + LINE_HEIGHT * coordinate.getY(), COMMIT_DIAMETER, COMMIT_DIAMETER);
    }

    private void drawCurve(final Pair<Integer, Integer> start, final Pair<Integer, Integer> end) {

        final double startX = STRAIGHT_LINE_X_POS + COL_OFFSET * start.getX();
        final double startY = COMMIT_PADDING + COMMIT_DIAMETER * 1.1 + LINE_HEIGHT * start.getY();
        final double endX = STRAIGHT_LINE_X_POS + COL_OFFSET * end.getX();
        final double endY = COMMIT_PADDING + LINE_HEIGHT * end.getY();
        final double ctrlstartX = STRAIGHT_LINE_X_POS + COL_OFFSET * start.getX();
        final double ctrlstartY = (endY - startY) / 2 + startY;
        final double ctrlendX = STRAIGHT_LINE_X_POS + COL_OFFSET * end.getX();
        final double ctrlendY = ctrlstartY;

        gc.beginPath();
        gc.moveTo(startX, startY);
        gc.bezierCurveTo(ctrlstartX, ctrlstartY, ctrlendX, ctrlendY, endX, endY);
        gc.stroke();
    }

    private void drawFullLine(final Pair<Integer, Integer> start, final Pair<Integer, Integer> end) {
        gc.strokeLine(STRAIGHT_LINE_X_POS + COL_OFFSET * start.getX(),
                COMMIT_PADDING + LINE_HEIGHT * start.getY(),
                STRAIGHT_LINE_X_POS + COL_OFFSET * end.getX(),
                COMMIT_PADDING + LINE_HEIGHT * end.getY());
    }

    private void drawLine(final Pair<Integer, Integer> start, final Pair<Integer, Integer> end) {
        gc.strokeLine(STRAIGHT_LINE_X_POS + COL_OFFSET * start.getX(),
                COMMIT_DIAMETER + COMMIT_PADDING + COMMIT_PADDING / 10 + LINE_HEIGHT * start.getY(),
                STRAIGHT_LINE_X_POS + COL_OFFSET * end.getX(),
                COMMIT_PADDING + LINE_HEIGHT * end.getY());
    }

    @Override
    public void drawLink(final Pair<Integer, Integer> start, final Pair<Integer, Integer> end, final boolean exiting,
            final Color color) {
        gc.setStroke(color);
        if (start.getX() != end.getX()) {
            if (end.getY() - start.getY() > 1) {
                if (exiting) {
                    drawFullLine(start, new Pair<>(start.getX(), end.getY() - 1));
                    drawCurve(new Pair<>(start.getX(), end.getY() - 1), end);
                } else {
                    drawCurve(start, new Pair<>(end.getX(), start.getY() + 1));
                    drawFullLine(new Pair<>(end.getX(), start.getY() + 1), end);
                }
            } else {
                drawCurve(start, end);
            }
        } else {
            drawLine(start, end);
        }

    }

    @Override
    public void endLogs() {
        bottom.setVisible(false);
    }

    /**
     *
     */
    @FXML
    public void initialize() {
        bottom.setMaxHeight(LINE_HEIGHT);
        bottom.setMinHeight(LINE_HEIGHT);
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        contexts.add(gc);
        canvasList.add(canvas);
        canvasVbox.getChildren().add(canvas);
    }

    private void loadBranchMenu(final HBox hb, final String branch, final String color) {
        final MenuItem createBranch = new MenuItem("Create a new branch here");
        final MenuItem renameBranch = new MenuItem("Rename branch '" + branch + "'");
        final MenuItem mergeBranch = new MenuItem("Merge " + branch + " on HEAD");
        final MenuItem tagHere = new MenuItem("Create a tag");
        final MenuItem deleteHere = new MenuItem("Delete " + "'" + branch + "'");
        createBranch.setOnAction(event -> controller.createBranchFrom(branch));
        renameBranch.setOnAction(event -> controller.renameBranch(branch));
        tagHere.setOnAction(event -> controller.addTag(branch));
        mergeBranch.setOnAction(event -> controller.mergeOnHead(branch));
        deleteHere.setOnAction(event -> controller.deleteBranch(branch));
        final Region reg1 = new Region();
        reg1.setPrefWidth(STD_PADDING);
        hb.getChildren().add(reg1);
        final SplitMenuButton b = new SplitMenuButton();
        b.setText(branch);
        b.setStyle("-fx-background-color: " + color);
        b.setMaxWidth(MAX_BRANCH_LABEL_WIDTH);
        b.setMinWidth(MIN_BRANCH_LABEL_WIDTH);
        b.getItems().addAll(mergeBranch, createBranch, renameBranch, tagHere, deleteHere);
        hb.getChildren().add(b);

    }

    /**
     *
     */
    @FXML
    public void onPrintMore() {
        if (canvas.getHeight() > MAX_CANVAS_HEIGHT) {
            controller.sendWarningMessage("ATTENTION",
                    "The excessive printing of commits, will crash the application soon or later!");
        }
        controller.refresh();
    }

    @Override
    public void resetView() {
        canvasVbox.getChildren().clear();
        logVbox.getChildren().clear();
        canvasList.clear();
        records.clear();
        selectedRecords.clear();
        bottom.setVisible(true);
        initialize();
    }

    private void setUpHandlers(final HBox hb, final String color) {
        final String neutral = "-fx-background-color: " + "#FFFFFF";
        final String hover = "-fx-text-fill: #fff; -fx-background-color: " + color;
        final String selected = "-fx-background-color: " + color;
        final String lineStyleClass = "commit-line-selected";

        hb.setOnKeyPressed(event -> {
            if (event.getCode().getName().equals("Ctrl")) {
                ctrl = true;
            }
        });

        hb.setOnKeyReleased(event -> {
            if (event.getCode().getName().equals("Ctrl")) {
                ctrl = false;
            }
        });

        hb.setOnMouseEntered(event -> {
            if (!selectedRecords.contains(hb)) {
                hb.setStyle(hover);
            }
            hb.requestFocus();
        });

        hb.setOnMouseExited(event -> {
            if (!selectedRecords.contains(hb)) {
                hb.setStyle(neutral);
            }
        });
        hb.setOnMouseClicked(event -> {
            if (!ctrl) {
                selectedRecords.forEach(r -> {
                    r.setStyle(neutral);
                    r.getStyleClass().remove(lineStyleClass);
                });
                selectedRecords.clear();
                selectedRecords.add(hb);
                hb.setStyle(selected);
                hb.getStyleClass().add(lineStyleClass);
            } else {
                if (selectedRecords.contains(hb)) {
                    hb.setStyle(neutral);
                    selectedRecords.remove(hb);
                    hb.getStyleClass().remove(lineStyleClass);
                } else {
                    selectedRecords.add(hb);
                    hb.setStyle(selected);
                    hb.getStyleClass().add(lineStyleClass);
                }
            }
            controller.openDiffOverview(
                    selectedRecords.stream().map(b -> b.getUserData().toString()).collect(Collectors.toList()));
        });
    }

    /**
     * no drawing here.
     */
    @Override
    public void writeNewRecord(final int index, final String code, final String shtMsg, final String author, // NOPMD
            final String color, final List<String> branches, final List<String> parents) {
        final HBox hb = new HBox();

        hb.setUserData(code);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setMinHeight(LINE_HEIGHT);
        hb.setMaxHeight(LINE_HEIGHT);
        hb.setMinWidth(MIN_COMMIT_BOX_WIDTH);

        hb.getStyleClass().add("commit-line");
        final Label msg = new Label(shtMsg);
        msg.setMaxWidth(1000);
        hb.getChildren().add(msg);

        branches.forEach(b -> loadBranchMenu(hb, b, color));

        final Region reg = new Region();
        reg.autosize();
        hb.getChildren().add(reg);
        HBox.setHgrow(reg, Priority.ALWAYS);
        final Label authorL = new Label(author);
        authorL.setMinWidth(100);
        hb.getChildren().add(authorL);

        hb.setFocusTraversable(true);
        setUpHandlers(hb, color);

        logVbox.getChildren().add(hb);
        records.add(hb);
    }

}
