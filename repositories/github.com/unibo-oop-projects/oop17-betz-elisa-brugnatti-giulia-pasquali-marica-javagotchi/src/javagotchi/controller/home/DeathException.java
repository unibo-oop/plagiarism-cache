package javagotchi.controller.home;

import java.util.Locale;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javagotchi.controller.menu.MenuControllerImpl;
import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.view.menu.MenuView;
import javagotchi.view.menu.MenuViewImpl;

/**
 * Exception class : it occurs when the Javagotchi dies.
 * @author elisa
 *
 */
public class DeathException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -1748978802885044516L;
    private final HomeController hc;

    /**
     * Exception constructor.
     * @param hc the home controller that has caused the exception
     */
    public DeathException(final HomeController hc) {
        super();
        this.hc = hc;
    }

    /**
     * Method to manage the exception. It shows a dialog 
     * and allows to go back to the previous menu or exit the application.
     */
    public void manage() {
        Platform.runLater(() -> {
            final Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle(this.hc.getJavagotchi().getInformation().getName().toString().toUpperCase(Locale.ROOT) + " IS DEAD!");
            alert.setContentText("\nIt seems you didn't manage to take care of " 
                                + this.hc.getJavagotchi().getInformation().getName().toString().toUpperCase(Locale.ROOT)
                                + ".\n\nWhat do you want to do now?\n\n");
            alert.setGraphic(new ImageView(this.getClass().getResource("/rip.png").toString()));

            final ButtonType exit = new ButtonType("Exit");
            final ButtonType menu = new ButtonType("Back to Menu");
            alert.getButtonTypes().setAll(exit, menu);

            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == menu) {
                this.hc.getTimers().stop();
                this.hc.save();
                final MenuView previouspage = new MenuViewImpl(new MenuControllerImpl());
                previouspage.getMenuManager().showSavedAvatarMenu();
            } else if (result.get() == exit) {
                this.hc.save();
                System.exit(0);
            }
            MiniGame.getFactoryController().getControllerMiniGame().getSavedData().deleteGameSaved();
        });
    }
}
