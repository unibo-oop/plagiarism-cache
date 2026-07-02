package jwhale.view.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import jwhale.controller.EnvPreView;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
import jwhale.view.AppScene;
import jwhale.view.DialogUtils;

public class MainSceneController extends SceneControllerImpl {
    @FXML
    private MenuItem addEnv;
    @FXML
    private MenuItem close;
    @FXML
    private ListView<EnvPreView> listView;
    @FXML
    private VBox vbox;
    @FXML
    private Button connect;
    @FXML
    private Button remove;

    public final void initialize() {
        Platform.runLater(() -> {
            listViewSetup();
            buttonsSetup();
            connectBtAction();
            removeBtAction();
            addEnv.setOnAction(e -> {
                Platform.runLater(() -> getView().loadScene(AppScene.ADD_ENDPOINT));
            });
            close.setOnAction(e -> {
                Platform.exit();
            });
        });
    }

    private Callback<ListView<EnvPreView>, ListCell<EnvPreView>> customCellforEnv() {
        return new Callback<ListView<EnvPreView>, ListCell<EnvPreView>>() {
            @Override
            public ListCell<EnvPreView> call(final ListView<EnvPreView> listView) {
                return new EnvCell();
            }
        };
    }

    private void buttonsSetup() {
        connect.setGraphic(new ImageView(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("connect.png"))));
        remove.setGraphic(new ImageView(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("remove.png"))));
    }

    private void connectBtAction() {
        connect.setOnAction(e -> {
            final EnvPreView selected = listView.getSelectionModel().getSelectedItem();
            new Thread(() -> {
                try {
                    cursorSwitch(Cursor.WAIT);
                    getController().setCurrentEnvName(selected.getName());
                    getController().loadEnv(selected.getUrl(), selected.getPort(), selected.getName());
                    cursorSwitch(Cursor.DEFAULT);
                    Platform.runLater(() -> getView().loadScene(AppScene.ENV_WORK));
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
        });
    }

    private void removeBtAction() {
        remove.setOnAction(e -> {
            final EnvPreView selected = listView.getSelectionModel().getSelectedItem();
            try {
                getController().removeEnv(selected.getName());
                connect.setDisable(true);
                remove.setDisable(true);
                initialize();
            } catch (IOException f) {
               DialogUtils.fileErrorDialog(f, getView().getStage());
            }
        });
    }

    private void listViewSetup() {
        final ObservableList<EnvPreView> obsItems = FXCollections.observableArrayList(getController().getEnvsList());
        listView.setPlaceholder(new Label("No Docker runtime environment."));
        listView.setItems(obsItems);
        listView.setCellFactory(customCellforEnv());
        listView.setOnMouseClicked(e -> {
            if (listView.getSelectionModel().getSelectedItem() != null) {
                connect.setDisable(false);
                remove.setDisable(false);
            }
        });
    }

    private void cursorSwitch(final Cursor cursor) {
        Platform.runLater(() -> getView().getStage().getScene().setCursor(cursor));
    }
}
