package jwhale.view.controller;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
import jwhale.view.AppScene;
import jwhale.view.DialogUtils;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
/**
 * Add end point view controller.
 */
public class AddEndPointController extends SceneControllerImpl {
    @FXML
    private MenuItem close;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField port;

    @FXML
    private Button back;

    @FXML
    private Button add;
    /**
     * Set action handler on graphic elements.
     */
    public final void initialize() {
        Platform.runLater(() -> {
            close.setOnAction(e -> {
                Platform.exit();
            });
            add.disableProperty().bind(
                    Bindings.isEmpty(name.textProperty())
                    .or(Bindings.isEmpty(address.textProperty()))
                    .or(Bindings.isEmpty(port.textProperty())));
            back.setOnAction(e -> {
                getView().loadScene(AppScene.MAIN_SCENE);
            });
            add.setOnAction(e -> {
                addEndPoint();
            });
            port.setTextFormatter(getNumberFilter());
        });
    }

    private void addEndPoint() {
        new Thread(() -> {
            try {
                getView().getStage().getScene().setCursor(Cursor.WAIT);
                getController().loadEnv(trimAddress(address.getText()), port.getText(), name.getText());
                Platform.runLater(() -> getView().getStage().getScene().setCursor(Cursor.DEFAULT));
                Platform.runLater(() -> getView().loadScene(AppScene.MAIN_SCENE));
            } catch (ConnectionException | IllegalArgumentException | DaemonResponseException f) {
                Platform.runLater(() -> {
                    getView().getStage().getScene().setCursor(Cursor.DEFAULT);
                    DialogUtils.networkErrorDialog(f, getView().getStage());
                });
            } catch (IOException f) {
                Platform.runLater(() -> {
                    getView().getStage().getScene().setCursor(Cursor.DEFAULT);
                    DialogUtils.fileErrorDialog(f, getView().getStage());
                });
            }
        }).start();
    }

    private String trimAddress(final String address) {
        if (address.endsWith("/")) {
            return address.substring(0, address.length() - 1);
        } else {
            return address;
        }
    }

    private TextFormatter<?> getNumberFilter() {
        return new TextFormatter<>((c) -> {
            final String content = c.getControlNewText();
            if (content.matches("\\d{0,5}")) {
                return c;
            } else {
                return null;
            }
        });
    }
}
