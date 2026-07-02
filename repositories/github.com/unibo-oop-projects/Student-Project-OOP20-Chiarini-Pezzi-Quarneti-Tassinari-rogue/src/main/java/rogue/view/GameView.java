package rogue.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rogue.controller.StatusBarController;
import rogue.controller.WorldController;
import rogue.controller.inventory.InventoryController;
import rogue.view.inventory.InventoryViewImpl;

/**
 * Class that creates the Game View.
 *
 */
public final class GameView {

    private final Stage stage = new Stage();
    private final Scene scene;

    private void loadStatusBar(final StatusBarController status) {
        final HBox box = (HBox) this.scene.lookup("#top");
        box.getChildren().add(status.getView().getNode());
    }

    private void loadInventory(final InventoryController inventory) throws IOException {
        final VBox box = (VBox) this.scene.lookup("#inventory");
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layout/InventoryView.fxml"));
        box.getChildren().add(loader.load());
        final InventoryViewImpl controller = loader.getController();
        controller.init(inventory);
    }

    private void loadWorld(final WorldController world) {
        final VBox box = (VBox) this.scene.lookup("#world");
        box.getChildren().add(world.getWorldBox());
    }

    public GameView(final StatusBarController status, final InventoryController inventory, final WorldController world) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layout/MainView.fxml"));
        this.scene = new Scene(root);

        this.loadStatusBar(status);
        this.loadWorld(world);
        this.loadInventory(inventory);

        stage.setScene(this.scene);
        stage.getIcons().add(new Image(ClassLoader.getSystemResource("images/rogueIcon.png").toExternalForm()));
        stage.setTitle("Rogue");
        stage.setResizable(true);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, world::movePlayer);
        stage.show();
    }
}
