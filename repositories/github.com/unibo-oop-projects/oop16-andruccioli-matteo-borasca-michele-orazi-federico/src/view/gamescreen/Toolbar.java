package view.gamescreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import utils.enumerations.Status;
import view.ViewImpl;
import view.dialogs.CloseGameDiag;
import view.dialogs.SaveGameDiag;

/**
 * 
 * Gamescreen toolbar. Contains hamburger to open sidebar and a button to close the game.
 * <p>
 * @see PlayerInfoSidebar
 *
 */
public class Toolbar extends HBox {

    private static final String ICON_PATH = "/images/closeIcon.png";

    private static final JFXDrawer SIDEBAR = new JFXDrawer();
    private final JFXButton closeBtn = new JFXButton();
    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth() / 5;

    private final StackPane closeBtnContainer = new StackPane();
    private final PlayerInfoSidebar sidebarContent = new PlayerInfoSidebar();

    /**
     * 
     * Class constructor.
     * 
     */
    public Toolbar() {
        SIDEBAR.setSidePane(sidebarContent);
        SIDEBAR.setDirection(DrawerDirection.LEFT);
        SIDEBAR.setOverLayVisible(false);
        SIDEBAR.setDefaultDrawerSize(WIDTH);

        final JFXHamburger hamburgerButton = new JFXHamburger();
        final HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburgerButton);
        burgerTask2.setRate(-1);
        hamburgerButton.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();

            if (SIDEBAR.isShown()) {
                SIDEBAR.close();
            } else {
                SIDEBAR.open();
                SIDEBAR.toFront();
            }
        });
        SIDEBAR.setOnDrawerClosing(e -> {
            SIDEBAR.toBack();
        });

        final ImageView closeIcon = new ImageView(Toolbar.class.getResource(ICON_PATH).toExternalForm());
        closeIcon.setFitHeight(32);
        closeIcon.setFitWidth(32);

        closeBtn.setGraphic(closeIcon);
        closeBtn.setRipplerFill(Color.GRAY);
        closeBtnContainer.getChildren().add(closeBtn);
        closeBtnContainer.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(closeBtnContainer, Priority.ALWAYS);

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(hamburgerButton, closeBtnContainer);
        this.getStyleClass().add("toolbar");
    }

    /**
     * 
     * Add sidebar to group.
     * 
     * @param screen
     *              The group sidebar is to be added in.
     * 
     */
    public void registerComponents(final WorldMap screen) {
        screen.getChildren().add(0, SIDEBAR);
 
        closeBtn.setOnMouseClicked(e -> {
            if (ViewImpl.getIstance().getController().getGameStatus().equals(Status.DEPLOYMENT)) {
                new SaveGameDiag(screen).show(); 
            } else {
                new CloseGameDiag(screen).show(); 
            }
        });
    }

    /**
     * 
     * Sidebar content getter.
     * 
     * @return
     *          Pane with sidebar content.
     * 
     */
    public PlayerInfoSidebar getSideBar() {
        return this.sidebarContent;
    }

}
