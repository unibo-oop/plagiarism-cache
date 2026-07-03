package org.gitgud.application.diff;

import org.gitgud.application.utils.ApplicationUtils;
import org.gitgud.icons.IconType;
import org.gitgud.model.utils.ChangeType;
import org.gitgud.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 */
public class DiffViewerFxmlController implements DiffViewerView {

    private static final int HUNK_ICON_SIZE = 14;
    private static final String ACTIVE_CLASS = "active";
    private static final double MAX_CANVAS_HEIGHT = 500.0;
    private static final double LINE_NUMBER_CONTAINER_WIDTH = 80.0;
    private static final double LINE_OFFSET = 16.0;
    private static final double TEXT_OFFSET = 13.0;
    private static final double FIRST_COLUMN_TEXT_START = 35.0;
    private static final double SECOND_COLUMN_TEXT_START = 75.0;
    private static final double LINE_X_POSITION = 40.0;
    private static final double INITIAL_OFFSET = 3.0;
    private static final double FONT_SIZE = 12.0;
    private static final double HUNK_BOTTOM_MARGIN = 15.0;

    private static final Paint LINE_PAINT_COLOR = Paint.valueOf("#dae6ed");
    private static final Paint NUMBER_PAINT_COLOR = Paint.valueOf("#607188");

    private DiffViewerController ctrl;

    @FXML
    private Pane fileInfoContainer;
    @FXML
    private Label fileName;
    @FXML
    private Pane additionalsInfoContainer;
    @FXML
    private Label additionalsInfo;
    @FXML
    private Pane changeTypeContainer;
    @FXML
    private Label linesAdded;
    @FXML
    private Label linesRemoved;
    @FXML
    private Pane diffContainer;

    @FXML
    private Pane oldBinaryImageViewer;
    @FXML
    private ScrollPane oldBinaryImageViewerScroll;
    @FXML
    private Pane oldBinaryImageViewerContainer;

    @FXML
    private Pane newBinaryImageViewer;
    @FXML
    private ScrollPane newBinaryImageViewerScroll;
    @FXML
    private Pane newBinaryImageViewerContainer;

    private Pane currentLinesNumberContainer;
    private TextArea currentTextArea;
    private Canvas currentCanvas;
    private GraphicsContext currentGraphicsContext;
    private VBox linesBackgroundContainer;
    private double currentYPosition;
    private String currentFile;
    private Image currentOldBinaryImage;
    private Image currentNewBinaryImage;

    @Override
    public void addAddedLine(final String line, final String lineToNumber) {
        extendVerticalSpace();
        appendText(line);
        drawNumber(lineToNumber, false);
        drawNumberSeparator(LINE_OFFSET, true);

        addLineBackground("green-line");
    }

    @Override
    public void addNewBinaryImage(final Image image) {
        newBinaryImageViewerContainer.getChildren().add(new ImageView(image));
        ApplicationUtils.setVisible(newBinaryImageViewer);
        currentNewBinaryImage = image;
    }

    @Override
    public void addOldBinaryImage(final Image image) {
        oldBinaryImageViewerContainer.getChildren().add(new ImageView(image));
        ApplicationUtils.setVisible(oldBinaryImageViewer);
        currentOldBinaryImage = image;
    }

    @Override
    public void addRemovedLine(final String line, final String lineFromNumber) {
        extendVerticalSpace();
        appendText(line);
        drawNumber(lineFromNumber, true);
        drawNumberSeparator(LINE_OFFSET, true);

        addLineBackground("red-line");
    }

    @Override
    public void addUnchangedLine(final String line, final String lineFromNumber, final String toLineNumber) {
        extendVerticalSpace();
        appendText(line);
        drawNumber(lineFromNumber, true);
        drawNumber(toLineNumber, false);
        drawNumberSeparator(LINE_OFFSET, true);

        addLineBackground("transparent-line");
    }

    @Override
    public void attachController(final DiffViewerController ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void clearDifferences() {
        diffContainer.getChildren().clear();
        ApplicationUtils.setInvisible(oldBinaryImageViewer);
        ApplicationUtils.setInvisible(newBinaryImageViewer);
        oldBinaryImageViewerContainer.getChildren().clear();
        newBinaryImageViewerContainer.getChildren().clear();
    }

    @Override
    public void closeHunk() {
        currentCanvas.setHeight(currentYPosition + LINE_OFFSET * 3);
        drawNumberSeparator(LINE_OFFSET * 3, false);
    }

    @Override
    public void openHunk(final String header) {
        final Label hunkHeaderLabel = new Label(header);
        final Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        final Button copyButton = new Button();
        copyButton.setGraphic(ApplicationUtils.createIcon("clipboard", IconType.ICON_DARK, HUNK_ICON_SIZE));
        copyButton.getStyleClass().add("hunk-header-button");

        final HBox hunkHeader = new HBox(hunkHeaderLabel, region, copyButton);
        hunkHeader.getStyleClass().add("hunk-header");

        currentLinesNumberContainer = new VBox();
        currentLinesNumberContainer.setMinWidth(LINE_NUMBER_CONTAINER_WIDTH);
        currentLinesNumberContainer.setMaxWidth(LINE_NUMBER_CONTAINER_WIDTH);
        currentLinesNumberContainer.getStyleClass().add("hunk-line-number-container");

        createCanvas(true);
        drawNumberSeparator(INITIAL_OFFSET, false);
        currentYPosition = INITIAL_OFFSET;

        final TextArea textArea = new TextArea();
        currentTextArea = textArea;
        currentTextArea.setEditable(false);
        currentTextArea.getStyleClass().add("hunk-text-area");
        currentTextArea.setFont(Font.font("Consolas", FONT_SIZE));

        copyButton.setOnAction(event -> ctrl.copyToClipboard(textArea.getText()));

        linesBackgroundContainer = new VBox();
        linesBackgroundContainer.setTranslateY(currentYPosition);
        final StackPane editorPane = new StackPane(linesBackgroundContainer, currentTextArea);
        HBox.setHgrow(editorPane, Priority.ALWAYS);
        editorPane.getStyleClass().add("hunk-editor");
        final HBox hunkContent = new HBox(currentLinesNumberContainer, editorPane);
        VBox.setMargin(hunkContent, new Insets(0.0, 0.0, HUNK_BOTTOM_MARGIN, 0.0));
        hunkContent.getStyleClass().add("hunk-content");

        diffContainer.getChildren().addAll(hunkHeader, hunkContent);
    }

    @Override
    public void setAdditionalsInfo(final String additionalsInfo) {
        this.additionalsInfo.setText(additionalsInfo);
    }

    @Override
    public void setChangeType(final ChangeType changeType) {
        new ImageView();
        fileName.setGraphic(ApplicationUtils.createChangeTypeIcon(changeType));
        changeTypeContainer.getChildren().clear();
        changeTypeContainer.getChildren().add(ApplicationUtils.createChangeTypeLabel(changeType, false));
    }

    @Override
    public void setFileName(final String fileName) {
        final String shortString = ApplicationUtils.pathLabelShortener(fileName, this.fileName.getFont(),
                additionalsInfoContainer.getWidth());
        this.fileName.setText(shortString);
        currentFile = fileName;

        shrinkFileName(fileInfoContainer.getWidth() - 180.0);
    }

    @Override
    public void setLines(final String linesAdded, final String linesRemoved) {
        this.linesAdded.setText(linesAdded);
        this.linesRemoved.setText(linesRemoved);
    }

    private void addLineBackground(final String classStyle) {
        final Pane lineBackground = new Pane();
        lineBackground.setPrefHeight(LINE_OFFSET);
        lineBackground.getStyleClass().add(classStyle);
        linesBackgroundContainer.getChildren().add(lineBackground);
    }

    private void appendText(final String text) {
        currentTextArea.appendText(text.concat("\n"));
    }

    private void createCanvas(final boolean firstCanvas) {
        currentYPosition = 0.0;

        if (firstCanvas) {
            currentCanvas = new Canvas(LINE_NUMBER_CONTAINER_WIDTH, INITIAL_OFFSET);
        } else {
            currentCanvas = new Canvas(LINE_NUMBER_CONTAINER_WIDTH, 0);
        }

        currentGraphicsContext = currentCanvas.getGraphicsContext2D();
        currentGraphicsContext.setTextAlign(TextAlignment.RIGHT);
        currentGraphicsContext.setFont(Font.font("Consolas", FONT_SIZE));

        currentLinesNumberContainer.getChildren().add(currentCanvas);
    }

    private void drawNumber(final String lineNumber, final boolean firstColumn) {
        currentGraphicsContext.setFill(NUMBER_PAINT_COLOR);
        if (firstColumn) {
            currentGraphicsContext.fillText(lineNumber, FIRST_COLUMN_TEXT_START, currentYPosition + TEXT_OFFSET);
        } else {
            currentGraphicsContext.fillText(lineNumber, SECOND_COLUMN_TEXT_START, currentYPosition + TEXT_OFFSET);
        }
    }

    private void drawNumberSeparator(final double height, final boolean incrementYPosition) {
        currentGraphicsContext.setFill(LINE_PAINT_COLOR);
        currentGraphicsContext.fillRect(LINE_X_POSITION, currentYPosition, 1.0, height);

        if (incrementYPosition) {
            currentYPosition += LINE_OFFSET;
        }
    }

    private void extendVerticalSpace() {
        if (currentYPosition > MAX_CANVAS_HEIGHT) {
            createCanvas(false);
        }

        currentCanvas.setHeight(currentYPosition + LINE_OFFSET);
        currentTextArea.setMinHeight(currentYPosition + LINE_OFFSET * 3);
    }

    @FXML
    private void initialize() { // NOPMD
        oldBinaryImageViewerContainer.prefWidthProperty().bind(oldBinaryImageViewerScroll.widthProperty());
        newBinaryImageViewerContainer.prefWidthProperty().bind(newBinaryImageViewerScroll.widthProperty());

        fileInfoContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
            shrinkFileName(newVal.doubleValue() - 180.0);
        });
    }

    @FXML
    private void onIgnoreFirstLineChar(final ActionEvent event) { // NOPMD
        final Button btn = (Button) event.getSource();

        if (btn.getStyleClass().remove(ACTIVE_CLASS)) {
            ctrl.setIgnoreFirstLineChar(false);
        } else {
            btn.getStyleClass().add(ACTIVE_CLASS);
            ctrl.setIgnoreFirstLineChar(true);
        }

        Utils.doHardWork(() -> ctrl.updateDifferences());
    }

    @FXML
    private void onIgnoreWhiteSpace(final ActionEvent event) { // NOPMD
        final Button btn = (Button) event.getSource();

        if (btn.getStyleClass().remove(ACTIVE_CLASS)) {
            ctrl.setIgnoreWhiteSpace(false);
        } else {
            btn.getStyleClass().add(ACTIVE_CLASS);
            ctrl.setIgnoreWhiteSpace(true);
        }

        Utils.doHardWork(() -> ctrl.updateDifferences());
    }

    @FXML
    private void onSaveNewBinaryImage(final ActionEvent event) { // NOPMD
        ctrl.saveImageToDisk(currentNewBinaryImage, currentFile);
    }

    @FXML
    private void onSaveOldBinaryImage(final ActionEvent event) { // NOPMD
        ctrl.saveImageToDisk(currentOldBinaryImage, currentFile);
    }

    private void shrinkFileName(final double width) {
        fileName.setText(ApplicationUtils.pathLabelShortener(currentFile, fileName.getFont(), width));
    }

}
