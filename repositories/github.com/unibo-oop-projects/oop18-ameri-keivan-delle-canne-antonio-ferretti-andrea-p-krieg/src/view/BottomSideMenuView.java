package view;

import java.util.stream.IntStream;

import controller.BottomSideMenuController;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import util.MenuVariablesUtils;

/**
 * The BottomSideMenuView is a class that extends SideMenuViewAbstract. It
 * represent the view part of the side bottom menu. The BottomSideMenuView
 * display information about the selected object.
 */
public class BottomSideMenuView extends SideMenuViewAbstract {

    private static final String ACTION_BUTTON_TEXT = "Create";
    private static final String GRID_BUTTON_TEXT = "Create";

    private boolean controllerSet;
    private BottomSideMenuController controller;

    /**
     * This method can be use to set the controller that is link with this view.
     * 
     * @param controller is the controller link with this view.
     */
    public void setMenuController(final BottomSideMenuController controller) {
        if (this.controllerSet) {
            throw new IllegalStateException();
        }
        this.controller = controller;
        this.controllerSet = true;
    }

    /** {@inheritDoc} **/
    @Override
    public void update() {
        getMenu().getChildren().clear();
        final TextArea textArea = new TextArea(controller.getActualSelectionName());
        final Button action = new Button(ACTION_BUTTON_TEXT);
        action.setVisible(false);
        if (controller.canCreate()) {
            action.setVisible(true);
            action.setOnAction(e -> creationMenuDialog());
        }
        textArea.setFont(MenuVariablesUtils.LOWER_FONT);
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        action.setFocusTraversable(false);
        getMenu().getChildren().addAll(textArea, action);
    }

    /** {@inheritDoc} **/
    @Override
    protected GridPane dialogContent(final Alert alert) {
        final GridPane gridPane = new GridPane();
        gridPane.setHgap(DIALOG_COLUMNS);
        gridPane.setVgap(controller.getNumberOfPossibleUnit());
        IntStream.range(0, controller.getNumberOfPossibleUnit()).forEach(i -> {
            final Label nameLabel = new Label(controller.getUnitName(i));
            final Label costLabel = new Label(controller.getUnitCostToString(i));
            nameLabel.setFont(MenuVariablesUtils.LOWER_FONT);
            nameLabel.setTextFill(Color.BLACK);
            final Button button = new Button(GRID_BUTTON_TEXT);
            button.setOnAction(e -> {
                controller.createUnit(i);
                alert.close();
            });
            if (!controller.hasEnoughResourcesToCreate(i)) {
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
