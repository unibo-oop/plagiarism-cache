package view;

import java.util.stream.IntStream;

import controller.TopSideMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import util.MenuVariablesUtils;

/**
 * The graphical part of the top side of the side menu.
 */
public class TopSideMenuView extends SideMenuViewAbstract {

    private static final String TURN_BUTTON = "End turn";
    private static final String SKILLTREE_BUTTON = "Skilltree";
    private static final String EXIT_BUTTON = "Exit";
    private static final double MULTIPLIER_PER_FONT = 3;

    private static final String GRID_BUTTON_TEXT = "Unlock";

    private boolean controllerSet;
    private int lines;

    private TopSideMenuController controller;

    /**
     * Draws the menu with the actual player's info.
     */
    private void draw() {
        final HBox buttonsHBox = new HBox();
        final Button skillTree = new Button(SKILLTREE_BUTTON);
        final Button turn = new Button(TURN_BUTTON);
        final Button exit = new Button(EXIT_BUTTON);
        final TextArea info = new TextArea();

        turn.setOnAction(e -> this.controller.notifyObserver());
        skillTree.setOnAction(e -> creationMenuDialog());
        exit.setOnAction(e -> this.exit());
        info.setFont(MenuVariablesUtils.LOWER_FONT);
        info.setEditable(false);
        info.setFocusTraversable(false);
        info.prefHeightProperty().bind(getMenu().heightProperty());
        info.prefWidthProperty().bind(getMenu().widthProperty());
        turn.setFocusTraversable(false);
        skillTree.setFocusTraversable(false);
        exit.setFocusTraversable(false);

        buttonsHBox.getChildren().addAll(skillTree, turn, exit);
        buttonsHBox.setSpacing(MENU_SPACING);
        buttonsHBox.setAlignment(Pos.CENTER);
        getMenu().getChildren().clear();
        getMenu().getChildren().addAll(info, buttonsHBox);
        info.setText(this.controller.getPlayerInfo());
    }

    private int calculateMaxHeight() {
        lines = 0;
        this.controller.getPlayerInfo().chars().forEach(c -> {
            if (c == '\n') {
                lines++;
            }
        });
        return lines;
    }

    /**
     * @param controller the controller for this view
     */
    public void setMenuController(final TopSideMenuController controller) {
        if (this.controllerSet) {
            throw new IllegalStateException();
        }
        this.controller = controller;
        this.controllerSet = true;
        getMenu().setMaxHeight(calculateMaxHeight() * MenuVariablesUtils.LOWER_FONT.getSize() * MULTIPLIER_PER_FONT);
    }

    /** {@inheritDoc} **/
    @Override
    public void update() {
        this.draw();
    }

    /**
     * The exit button that asks for confirm.
     */
    public void exit() {
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("");
        alert.setContentText("Are you sure?");
        if (alert.showAndWait().get().equals(ButtonType.OK)) {
            this.controller.exit();
        }
    }

    /** {@inheritDoc} **/
    @Override
    protected GridPane dialogContent(final Alert alert) {
        final GridPane gridPane = new GridPane();
        gridPane.setHgap(DIALOG_COLUMNS);
        gridPane.setVgap(controller.getSkillTreeUpgradableAttributesSize());
        IntStream.range(0, controller.getSkillTreeUpgradableAttributesSize()).forEach(i -> {
            final Label nameLabel = new Label(controller.getAttributeName(i));
            final Label costLabel = new Label(controller.getAttributeCostToString(i));
            nameLabel.setFont(MenuVariablesUtils.LOWER_FONT);
            nameLabel.setTextFill(Color.BLACK);
            final Button button = new Button(GRID_BUTTON_TEXT);
            button.setOnAction(e -> {
                controller.upgradeAttribute(i);
                alert.close();
            });
            if (!controller.canUpgradeAttribute(i)) {
                button.setDisable(true);
            }
            gridPane.add(nameLabel, 0, i);
            gridPane.add(costLabel, 1, i);
            gridPane.add(button, 2, i);
        });
        gridPane.getChildren().forEach(
                child -> GridPane.setMargin(child, new Insets(0, GRID_CHILDREN_MARGIN, 0, GRID_CHILDREN_MARGIN)));
        gridPane.setPadding(new Insets(GRID_PADDING, GRID_PADDING, GRID_PADDING, GRID_PADDING));
        return gridPane;
    }

}
