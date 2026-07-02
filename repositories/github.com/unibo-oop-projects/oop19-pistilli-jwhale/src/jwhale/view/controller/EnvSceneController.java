package jwhale.view.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.util.Callback;
import jwhale.commons.ItemType;
import jwhale.controller.ContainerPreView;
import jwhale.controller.EnvController;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
import jwhale.model.engine.operations.object.Playback;
import jwhale.view.AppScene;
import jwhale.view.DialogUtils;

public class EnvSceneController extends SceneControllerImpl {
    @FXML
    private MenuItem back;
    @FXML
    private ListView<ContainerPreView> listView;
    @FXML
    private Button start;
    @FXML
    private Button stop;
    @FXML
    private Button restart;
    @FXML
    private Button remove;
    @FXML
    private MenuItem inspectItem;
    @FXML
    private Button rename;
    @FXML
    private MenuItem pullImage;
    @FXML
    private MenuItem removeImage;
    @FXML
    private MenuItem buildImage;
    @FXML
    private MenuItem createNetwork;
    @FXML
    private MenuItem removeNetwork;
    @FXML
    private MenuItem createVolume;
    @FXML
    private MenuItem removeVolume;
    @FXML
    private MenuItem createContainerItem;
    private EnvController envController;
    private ContainerPreView selected;
    private String envName;
    private ObservableList<ContainerPreView> viewItems;

    public final void initialize() {
        Platform.runLater(() -> {
            envName = getController().getCurrentEnvName();
            envController = getController().getEnvController(envName);
            viewItems = FXCollections.observableArrayList(envController.getPreViewList());
            listSetup();
            backAction();
            setBtnsGraphic();
            setBtnsActions();
            imageActions();
            networkActions();
            volumesActions();
            createContainerItem.setOnAction(e -> containerCreateProc());
            inspectItem.setOnAction(e -> inspect());
        });
    }

    private void containerCreateProc() {
        DialogRunnable param = (res) -> envController.setContainerName(res);
        inputDialogAction("Set Container Name", "Enter container name: ", param);
        param = (res) -> envController.setContainerImage(res);
        choiceDialogAction(envController.getItemsAsList(ItemType.IMAGE), "Enter container image: ", param);
        param = (res) -> envController.setContainerNetwork(res);
        choiceDialogAction(envController.getItemsAsList(ItemType.NETWORK), "Enter container network: ", param);
        param = (res) -> envController.setContainerVolume(res);
        choiceDialogAction(envController.getItemsAsList(ItemType.VOLUME), "Enter container volume: ", param);
        param = (res) -> exposePort(res);
        inputDialogAction("Expose port", "<PublicPort:ContainerPort>. Leave emtpy to not set.", param);
        try {
            envController.pushContainer();
            getController().loadEnv(envController.getUrl(), 
                    envController.getPort(), 
                    envController.getControllerName());
            initialize();
        } catch (ConnectionException | DaemonResponseException e) {
            Platform.runLater(() -> DialogUtils.networkErrorDialog(e, getView().getStage()));
        } catch (IOException e) {
            Platform.runLater(() -> DialogUtils.fileErrorDialog(e, getView().getStage()));
        }
    }

    private void imageActions() {
        pullImage.setOnAction(e -> {
            final DialogRunnable pull = (name) -> createItemAction(name, ItemType.IMAGE);
            inputDialogAction("Pull Image", "Enter image name [Docker Hub]:", pull);
            afterClick();
        });
        removeImage.setOnAction(e -> {
            final DialogRunnable remove = (name) -> removeItemAction(name, ItemType.IMAGE);
            choiceDialogAction(envController.getItemsAsList(ItemType.IMAGE), "Select image to remove :", remove);
            afterClick();
        });
        buildImage.setOnAction(e -> {
            final FileChooser picker = new FileChooser();
            picker.setTitle("Select tar archive");
            picker.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tar archive", "*.tar"));
            final File archive = picker.showOpenDialog(getView().getStage().getScene().getWindow());
            if (archive != null) {
                try {
                    final String archiveName = archive.getName();
                    envController.imageBuild(archiveName.substring(0, archiveName.lastIndexOf(".tar")), 
                            archive.getAbsolutePath());
                    DialogUtils.successfulOperation(getView().getStage());
                } catch (ConnectionException | DaemonResponseException e1) {
                    DialogUtils.networkErrorDialog(e1, getView().getStage());
                } catch (IOException e1) {
                    DialogUtils.fileErrorDialog(e1, getView().getStage());
                } 
            }
        });
    }

    private void networkActions() {
        createNetwork.setOnAction(e -> {
            final DialogRunnable nCreate = (nName) -> createItemAction(nName, ItemType.NETWORK);
            inputDialogAction("Create Network", "Enter Network name: ", nCreate);
            afterClick();
        });
        removeNetwork.setOnAction(e -> {
            final DialogRunnable nRemove = (nName) -> removeItemAction(nName, ItemType.NETWORK);
            choiceDialogAction(envController.getItemsAsList(ItemType.NETWORK), "Select network to remove :", nRemove);
            afterClick();
        });
    }

    private void volumesActions() {
        createVolume.setOnAction(e -> {
            final DialogRunnable vCreate = (vName) -> createItemAction(vName, ItemType.VOLUME);
            inputDialogAction("Create Volume", "Enter Volume name: ", vCreate);
            afterClick();
        });
        removeVolume.setOnAction(e -> {
            final DialogRunnable vRemove = (name) -> removeItemAction(name, ItemType.VOLUME);
            choiceDialogAction(envController.getItemsAsList(ItemType.VOLUME), "Select volume to remove: ", vRemove);
            afterClick();
        });
    }

    private void createItemAction(final String itemName, final ItemType type) {
        new Thread(() -> {
            try {
                getView().getStage().getScene().setCursor(Cursor.WAIT);
                if (type.equals(ItemType.VOLUME)) {
                    envController.createItem(itemName, envController.getMountPath(itemName), type);
                } else {
                    envController.createItem(itemName, type);
                }
                getView().getStage().getScene().setCursor(Cursor.DEFAULT);
                Platform.runLater(() -> DialogUtils.successfulOperation(getView().getStage()));
            } catch (ConnectionException | DaemonResponseException f) {
                Platform.runLater(() -> {
                    getView().getStage().getScene().setCursor(Cursor.DEFAULT);
                    DialogUtils.networkErrorDialog(f, getView().getStage());
                });
            }
        }).start();
    }

    private void removeItemAction(final String itemName, final ItemType type) {
        try {
            envController.removeItem(itemName, type);
            getController().loadEnv(envController.getUrl(), envController.getPort(), envController.getControllerName());
            initialize();
        } catch (ConnectionException | DaemonResponseException e) {
            DialogUtils.networkErrorDialog(e, getView().getStage());
        }  catch (IOException e) {
            DialogUtils.fileErrorDialog(e, getView().getStage());
        }
    }

    private void exposePort(final String cmd) {
        if (cmd.matches("(\\d{1,5})(:)(\\d{1,5})")) {
            final String[] ports = cmd.split(":");
            envController.exposeContainerPort(Integer.valueOf(ports[0]), Integer.valueOf(ports[1])); 
        }
    }

    private Callback<ListView<ContainerPreView>, ListCell<ContainerPreView>> customCell() {
        return new Callback<ListView<ContainerPreView>, ListCell<ContainerPreView>>() {
            @Override
            public ListCell<ContainerPreView> call(final ListView<ContainerPreView> listView) {
                return new ContainerCell();
            }
        };
    }

    private void listSetup() {
        listView.setPlaceholder(new Label("No Docker containers."));
        listView.setItems(viewItems);
        listView.setCellFactory(customCell());
        listView.setOnMouseClicked(e -> {
            if (listView.getSelectionModel().getSelectedItem() != null) {
                selected = listView.getSelectionModel().getSelectedItem();
                buttonsHandler(selected.getStatus());
            }
        });
    }

    private ImageView getBtnImg(final String name) {
        return new ImageView(
                new Image(ClassLoader.getSystemClassLoader().getResourceAsStream(name)));
    }

    private void buttonsHandler(final String status) {
        if (status.equals("Up")) {
            stop.setDisable(false);
            start.setDisable(true);
            remove.setDisable(true);
            restart.setDisable(false);
            rename.setDisable(false);
        } else {
            stop.setDisable(true);
            start.setDisable(false);
            remove.setDisable(false);
            restart.setDisable(true);
            rename.setDisable(false);
        }

    }

    private void playBackAction(final Playback action) {
        new Thread(() -> {
            try {
                envController.playBackContainer(selected.getName(), action);
            } catch (ConnectionException | DaemonResponseException e) {
                Platform.runLater(() -> DialogUtils.networkErrorDialog(e, getView().getStage()));
            }
            Platform.runLater(() -> listSetup());
        }).start();
    }

    private void removeAction() {
        new Thread(() -> {
            try {
                envController.removeContainer(selected.getName());
                initialize();
            } catch (ConnectionException | DaemonResponseException e) {
                Platform.runLater(() -> DialogUtils.networkErrorDialog(e, getView().getStage()));
            }
        }).start();
    }

    private void backAction() {
        back.setOnAction(e -> {
            this.getView().loadScene(AppScene.MAIN_SCENE);
        });
    }

    private void inspect() {
        new Thread(() -> {
            try {
                final String result = envController.inspectContainer(selected.getName());
                exportInspect(selected.getName() + ".txt", result);
                DialogUtils.successfulOperation(getView().getStage());
            } catch (ConnectionException | DaemonResponseException e) {
                Platform.runLater(() -> DialogUtils.networkErrorDialog(e, getView().getStage()));
            } catch (IOException e) {
                Platform.runLater(() -> DialogUtils.fileErrorDialog(e, getView().getStage()));
            }
        }).start();
    }

    private void renameAction(final String newName) {
        new Thread(() -> {
            try {
                envController.renameContainer(selected.getName(), newName);
                initialize();
            } catch (ConnectionException | DaemonResponseException e) {
                Platform.runLater(() -> DialogUtils.networkErrorDialog(e, getView().getStage()));
            } 
        }).start();
    }

    private void afterClick() {
        listView.getSelectionModel().clearSelection();
        start.setDisable(true);
        stop.setDisable(true);
        remove.setDisable(true);
        restart.setDisable(true);
        rename.setDisable(true);
    }

    private void exportInspect(final String fileName, final String result) 
            throws IOException {
        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            writer.print(result);
            writer.close();
        }
    }

    private void inputDialogAction(final String title, final String msg, final DialogRunnable exe) {
        final TextInputDialog input = new TextInputDialog();
        input.initModality(Modality.WINDOW_MODAL);
        input.initOwner(this.getView().getStage());
        input.setTitle(title);
        input.setHeaderText(msg);
        final String res = input.showAndWait().get();
        exe.execute(res);
    }

    private void choiceDialogAction(final List<String> choices, final String msg, final DialogRunnable setter) {
        final ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(this.getView().getStage());
        dialog.setTitle("Choice");
        dialog.setHeaderText(msg);
        final String res = dialog.showAndWait().get();
        setter.execute(res);
    }

    private void setBtnsGraphic() {
        start.setGraphic(getBtnImg("start.png"));
        remove.setGraphic(getBtnImg("remove.png"));
        stop.setGraphic(getBtnImg("stop.png"));
        restart.setGraphic(getBtnImg("restart.png"));
        rename.setGraphic(getBtnImg("rename.png"));
    }

    private void setBtnsActions() {
        start.setOnAction(e -> {
            playBackAction(Playback.START);
            afterClick();
        });
        stop.setOnAction(e -> {
            playBackAction(Playback.STOP);
            afterClick();
        });
        restart.setOnAction(e -> {
            playBackAction(Playback.RESTART);
            afterClick();
        });
        remove.setOnAction(e -> {
            removeAction();
            afterClick();
        });
        rename.setOnAction(e -> {
            final DialogRunnable exe = f -> renameAction(f);
            inputDialogAction("Rename Container", "Enter new container name:", exe);
            afterClick();
        });
    }

}
