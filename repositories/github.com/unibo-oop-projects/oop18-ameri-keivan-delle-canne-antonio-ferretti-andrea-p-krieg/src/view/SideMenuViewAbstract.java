package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.MenuVariablesUtils;

/**
 * The SideMenuViewAbstract is class that extends MenuVariableUtils and
 * implements SecondaryView. This class represent a game side menu.
 *
 */
public abstract class SideMenuViewAbstract implements SecondaryView {

    /**
     * DIALOG_COLUMNS is a variable that indicate the number of columns in the
     * alert's grid panel.
     */
    protected static final int DIALOG_COLUMNS = 3;
    /**
     * GRID_CHILDREN_MARGIN is a variable that indicate the grid children margin.
     */
    protected static final double GRID_CHILDREN_MARGIN = 30;
    /**
     * GRID_PADDING is a variable that indicate the grid padding.
     */
    protected static final double GRID_PADDING = 25;
    /**
     * MENU_SPACING is a variable that indicate the menu spacing.
     */
    protected static final double MENU_SPACING = 8;
    private static final double MENU_PADDING = 8;
    private static final String DIALOG_NAME = "Menu";

    private final VBox menu;

    /**
     * SideMenuViewAbstract constructor.
     */
    public SideMenuViewAbstract() {
        this.menu = new VBox();
        this.menu.setPadding(new Insets(MENU_PADDING));
        this.menu.setSpacing(MENU_SPACING);
        this.menu.setAlignment(Pos.CENTER);
    }

    /** {@inheritDoc} **/
    @Override
    public Region get() {
        return new VBox(this.menu);
    }

    /** {@inheritDoc} **/
    @Override
    public abstract void update();

    /**
     * This method can be use to create a dialog alert menu.
     */
    protected void creationMenuDialog() {
        final Alert alert = new Alert(AlertType.NONE);
        final DialogPane dialogPane = alert.getDialogPane();
        alert.setTitle(DIALOG_NAME);
        alert.setHeaderText(null);
        dialogPane.getButtonTypes().add(ButtonType.CANCEL);
        dialogPane.setContent(dialogContent(alert));
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(MenuVariablesUtils.MENU_ICON));
        alert.showAndWait();
    }

    /**
     * This method can be use to create the content of a dialog alert menu.
     * 
     * @param alert is the Alert where put the content.
     * @return the menu with the content.
     */
    protected abstract GridPane dialogContent(Alert alert);

    /**
     * This method can be use to take the menu.
     * @return the menu.
     */
    protected VBox getMenu() {
        return this.menu;
    }

}
