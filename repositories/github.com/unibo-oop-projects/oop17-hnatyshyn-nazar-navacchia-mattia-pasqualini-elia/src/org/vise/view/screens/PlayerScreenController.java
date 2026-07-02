package org.vise.view.screens;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.vise.controller.RemoteController;
import org.vise.model.playlist.Playlist;
import org.vise.model.playlist.song.Song;
import org.vise.model.playlist.song.SongImpl;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.FriendUser;
import org.vise.view.FXEnvironment;
import org.vise.view.UI;
import org.vise.view.UIPlayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * 
 * Rapresent the Controller of the Player Screen. It's a key component in JavaFX development.
 *
 */
public class PlayerScreenController implements UI, UIPlayer {

    private static final int DIMENSION_MOD_WIDTH_WINDOW = 300;
    private static final int DIMENSION_MOD_HEGTH_WINDOW = 200;
    private static final String ICON_PLAY_STRING = "-fx-shape: \"M14,19H18V5H14M6,19H10V5H6V19Z\";";
    private static final String ICON_PAUSE_STRING = "-fx-shape: \"M8,5.14V19.14L19,12.14L8,5.14Z\";";
    private static final String BUTTON_UNFOLLOW = "-fx-background-color: #e74c3c;\n" + "-fx-font-weight: bold;\n" + "-fx-font-size: 14;\n";
    private static final String BUTTON_FOLLOW = "-fx-background-color: #07807D;\n" + "-fx-font-weight: bold;\n" + "-fx-font-size: 14;\n";
    private static final String CLASS_HBOX = "hbox";
    private static final String PRE_ACTIVITY = "Sta ascoltando: ";
    private final FXEnvironment environment;
    private final RemoteController playerController;
    private boolean isModifyingNamePlaylist;
    private UUID tempIDPlaylistInModifying;
    private final boolean blockSliderPosition;

    @FXML
    private VBox vboxPlayer;

    @FXML
    private ScrollPane scrollPaneSearch;

    @FXML
    private StackPane stackPaneCurrentPlaylist;

    @FXML
    private Button btnPlay, btnAddSongToCurrent, btnRemoveSongToCurrent, btnChangeQueueMode, btnLoginLogout, btnSearch;

    @FXML
    private ListView<Playlist> listViewPlaylist;

    @FXML
    private ListView<VBox> listViewActivity;

    @FXML
    private Slider sliderVolume, sliderPosition;

    @FXML
    private Label labelNomeCanzoneCorrente, labelCantanteCanzoneCorrente, labelNomePlaylist, labelAutorePlaylist, labelUsername, labelFriends, labelYouTube;

    @FXML
    private TableView<Song> tableViewSongsCurrentPlaylist;

    @FXML
    private TextField textFieldSearch;

    /**
     * Constructor.
     * @param environment
     *          The environment.
     * @param playerController
     *          The player controller.
     */
    public PlayerScreenController(final FXEnvironment environment, final RemoteController playerController) {
        super();
        this.environment = environment;
        this.environment.loadScreen(FXMLScreens.PLAYER, this);
        this.playerController = playerController;
        this.isModifyingNamePlaylist = false;
        this.blockSliderPosition = false;
    }

    /**
     * 
     */
    @FXML
    public void btnLoadSongClicked() {
        final FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleziona la Canzone da eseguire.");
        final File file = chooser.showOpenDialog(null);
        if (file != null) {
            this.loadSongToPlayer(new SongImpl(file.toString()));
            this.playerController.setSingleReproductionModality(true);
        }
    }

    /**
     * 
     *        .
     */
    @SuppressWarnings("unchecked")
    @FXML
    public void initialize() {
        // Disable visibility of table view that contains the content of the current playlist.
        this.stackPaneCurrentPlaylist.setVisible(false);

        // Creation of the menu that permits to manage the list of playlists.
        final ContextMenu contextPlaylist = new ContextMenu();
        contextPlaylist.setStyle("-fx-background-color: #2A2A2A;");
        contextPlaylist.setAutoHide(true);
        final MenuItem removePlaylist = new MenuItem("Rimuovi Playlist");
        removePlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                playerController.removePlaylist(listViewPlaylist.getSelectionModel().getSelectedItem());
                stackPaneCurrentPlaylist.setVisible(false);
            }
        });
        final MenuItem renamePlaylist = new MenuItem("Rinomina Playlist");
        renamePlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final Playlist playlistToEdit = listViewPlaylist.getSelectionModel().getSelectedItem();

                // Creation of the pop-up that permits to modify the name of the playlist.
                // Creation fields for playlist's name.
                final HBox contentPlaylist = new HBox();
                setStyleSheetNode(contentPlaylist, PlayerScreenController.CLASS_HBOX);
                final Label labelPlaylist = new Label("Nome: ");
                final TextField playlist = new TextField();
                playlist.setText(playlistToEdit.getName());
                contentPlaylist.getChildren().addAll(labelPlaylist, playlist);
                // Creation of button modify.
                final Button buttonModify = new Button("Modifica");
                buttonModify.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(final ActionEvent e) {
                        isModifyingNamePlaylist = true;
                        tempIDPlaylistInModifying = playlistToEdit.getPlaylistID();
                        playerController.setNamePlaylist(playlist.getText());
                    }
                });

                createAndShowPannelModify(Arrays.asList(contentPlaylist, buttonModify));
            }
        });
        contextPlaylist.getItems().addAll(removePlaylist, renamePlaylist);

        // Add event to right click on list of playlists.
        this.listViewPlaylist.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    contextPlaylist.show(listViewPlaylist, event.getScreenX(), event.getScreenY());
                }
            }
        });

        // Set ui personalized visualization for items into listViewPlaylist.
        this.listViewPlaylist.setCellFactory(param -> new ListCell<Playlist>() {
            @Override
            protected void updateItem(final Playlist item, final boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        // Creation of the menu that permits to manage the list of songs of the current playlist.
        final ContextMenu contextTableView = new ContextMenu();
        contextTableView.setStyle("-fx-background-color: #2A2A2A;");
        contextTableView.setAutoHide(true);
        final MenuItem editSong = new MenuItem("Modifica Canzone");
        editSong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final Song songToEdit = tableViewSongsCurrentPlaylist.getSelectionModel().getSelectedItem();

                // Creation of the pop-up that permits to modify the song.
                // Creation fields for song's title.
                final HBox contentTitle = new HBox();
                setStyleSheetNode(contentTitle, PlayerScreenController.CLASS_HBOX);
                final Label labelTitle = new Label("Titolo: ");
                final TextField title = new TextField();
                title.setText(songToEdit.getTitle());
                contentTitle.getChildren().addAll(labelTitle, title);
                // Creation fields for song's arthist.
                final HBox contentArtist = new HBox();
                setStyleSheetNode(contentArtist, PlayerScreenController.CLASS_HBOX);
                final Label labelArtist = new Label("Artista: ");
                final TextField artist = new TextField();
                artist.setText(songToEdit.getArtist());
                contentArtist.getChildren().addAll(labelArtist, artist);
                // Creation fields for song's album.
                final HBox contentAlbum = new HBox();
                setStyleSheetNode(contentAlbum, PlayerScreenController.CLASS_HBOX);
                final Label labelAlbum = new Label("Album: ");
                final TextField album = new TextField();
                album.setText(songToEdit.getAlbum());
                contentAlbum.getChildren().addAll(labelAlbum, album);
                // Creation of button modify.
                final Button buttonModify = new Button("Modifica");
                buttonModify.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(final ActionEvent e) {
                        playerController.modifyMetaSong(tableViewSongsCurrentPlaylist.getSelectionModel().getSelectedIndex(), title.getText(), artist.getText(), album.getText());
                    }
                });
                createAndShowPannelModify(Arrays.asList(contentTitle, contentArtist, contentAlbum, buttonModify));
            }
        });
        contextTableView.getItems().addAll(editSong);
        this.tableViewSongsCurrentPlaylist.setRowFactory(tv -> {
            final TableRow<Song> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    // Necessary to distinguish remote and local songs. They have two different events associated.
                    if (row.getItem() instanceof SongImpl) {
                        this.loadSongToPlayer(row.getItem());
                        this.playerController.setQueueIndex(row.getIndex());
                        this.playerController.setSingleReproductionModality(false);
                        this.btnPlayClicked();
                    } else {
                        this.playerController.searchOnYouTube(this.tableViewSongsCurrentPlaylist.getSelectionModel().getSelectedItem());
                    }
                }
                if (event.getButton().equals(MouseButton.SECONDARY) && row.getItem() instanceof SongImpl) { 
                    contextTableView.show(tableViewSongsCurrentPlaylist, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

        // Creation columns of the tableView that contains songs.
        final TableColumn<Song, String> titleCol = new TableColumn<>("Titolo");
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("title")
        );
        final TableColumn<Song, String> albumCol = new TableColumn<>("Album");
        albumCol.setCellValueFactory(
            new PropertyValueFactory<Song, String>("album")
        );
        final TableColumn<Song, String>  artistCol = new TableColumn<>("Artista");
        artistCol.setCellValueFactory(
            new PropertyValueFactory<Song, String>("artist")
        );
        final TableColumn<Song, String> durationCol = new TableColumn<>("Durata");
        durationCol.setCellValueFactory(
            new PropertyValueFactory<Song, String>("duration")
        );
        this.tableViewSongsCurrentPlaylist.getColumns().addAll(titleCol, artistCol, albumCol, durationCol);
        // Set placeholder to tableView of songs.
        this.tableViewSongsCurrentPlaylist.setPlaceholder(new Label("Nessuna canzone aggiunta alla Playlist."));

        // Set on closing event
        this.environment.getMainStage().setOnCloseRequest(e -> {
            if (this.playerController.isPlayerOnlineMode()) {
                this.playerController.close();
            }
            System.exit(0);
        });

        this.sliderPosition.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * 
             * @param ov
             *          .
             * @param oldVal
             *          .
             * @param new_val
             *          .
             */
            public void changed(final ObservableValue<? extends Number> ov, final Number oldVal, final Number newVal) {
                try {
                    if (playerController.isLoaded() && playerController.isPlaying()) {
                        playerController.setPosition((int) sliderPosition.getValue());
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Unable to set a new position");
                }
            }
        });
    }

    /**
     * 
     */
    @FXML
    public void btnAddPlaylistClicked() {
        final TextInputDialog dialog = new TextInputDialog();
        dialog.setResizable(false);
        dialog.setGraphic(null);
        dialog.setTitle("Nuova playlist");
        dialog.setHeaderText("Inserire il nome della nuova playlist");
        final DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("Dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("Dialog");
        final Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            this.playerController.addPlaylist(result.get());
        }
    }

    /**
     * 
     */
    @FXML
    public void selectPlaylist() {
        if (this.listViewPlaylist.getSelectionModel().getSelectedItem() != null) {
            this.labelYouTube.setVisible(false);
            this.btnAddSongToCurrent.setDisable(false);
            this.btnRemoveSongToCurrent.setDisable(false);
            this.playerController.setCurrentPlaylist(this.listViewPlaylist.getSelectionModel().getSelectedItem());
            this.labelNomePlaylist.setText(this.playerController.getCurrentPlaylist().getName());
            this.labelAutorePlaylist.setText("di " + this.playerController.getCurrentPlaylist().getAuthor());
            this.showPlaylistOnTop();
        }
    }

    /**
     * 
     */
    public void btnSearchClicked() {
        final String searchString = this.textFieldSearch.getText();
        if (!searchString.isEmpty()) {
            this.playerController.search(searchString);
        } else {
            this.showPlaylistOnTop();
        }
    }

    /**
     * 
     */
    @FXML
    public void btnGoToLoginClicked() {
        try {
            this.playerController.logout();
            this.environment.displayScreen(FXMLScreens.RLFORM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @FXML
    public void btnSaveOfflineClicked() {
        this.playerController.savePlaylist();
    }

    /**
     * 
     */
    @FXML
    public void btnPlayClicked() {
        String style = "";
        if (this.playerController.isLoaded()) {
            if (this.playerController.isPlaying()) {
                this.playerController.pause();
                style = PlayerScreenController.ICON_PAUSE_STRING;
            } else {
                this.playerController.play();
                style = PlayerScreenController.ICON_PLAY_STRING;
            }
            this.btnPlay.setStyle(style);
        } else {
            this.showNotification("Non puoi ascoltare una canzone se prima non l'hai caricata.");
        }
    }

    /**
     * 
     */
    @FXML
    public void btnStopClicked() {
        this.playerController.stop();
        this.btnPlay.setStyle(PlayerScreenController.ICON_PAUSE_STRING);
    }

    /**
     * 
     */
    @FXML
    public void btnPrevClicked() {
        this.playerController.prev();
        this.sliderPosition.setMax(this.playerController.getLength());
        this.btnPlay.setStyle(PlayerScreenController.ICON_PLAY_STRING);
    }

    /**
     * 
     */
    @FXML
    public void btnNextClicked() {
        this.playerController.next();
        this.sliderPosition.setMax(this.playerController.getLength());
        this.btnPlay.setStyle(PlayerScreenController.ICON_PLAY_STRING);
    }

    /**
     * 
     */
    @FXML
    public void btnChangeQueueModeClicked() {
        if (this.playerController.isShuffle()) {
            if (this.playerController.getCurrentPlaylist() != null) {
                this.btnChangeQueueMode.setStyle("-icon-paint: #ffffff;");
                this.playerController.setShuffle(false, this.tableViewSongsCurrentPlaylist.getSelectionModel().getSelectedIndex());
            } else {
                this.showNotification("Prima di cambiare modalità di ascolto crea una playlist da ascoltare.");
            }
        } else {
            if (this.playerController.getCurrentPlaylist() != null) {
                this.btnChangeQueueMode.setStyle("-icon-paint: #07807D;");
                this.playerController.setShuffle(true, this.tableViewSongsCurrentPlaylist.getSelectionModel().getSelectedIndex());
            } else {
                this.showNotification("Prima di cambiare modalità di ascolto crea una playlist da ascoltare.");
            }
        }
    }

    /**
     * 
     */
    @FXML
    public void slideVolumeReleased() {
        final float amount = (float) this.sliderVolume.getValue();
        this.playerController.setVolume(amount);
    }

    /**
     * 
     */
    @FXML
    public void btnAddSongToCurrentClicked() {
        final FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleziona la Canzone da inserire.");
        final File file = chooser.showOpenDialog(null);
        if (file != null) {
            this.playerController.addSongToPlaylist(file.toString());
            this.tableViewSongsCurrentPlaylist.getSelectionModel().select(this.playerController.getCurrentPlaylist().getDimension() - 1);
        }
    }

    /**
     * 
     */
    @FXML
    public void btnRemoveSongToCurrentClicked() {
        this.playerController.removeSongFromPlaylist(this.tableViewSongsCurrentPlaylist.getSelectionModel().getSelectedItem());
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UI#show()
     */
    @Override
    public void show() {
        try {
            this.environment.displayScreen(FXMLScreens.PLAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#updateReproductionSongInfo(org.vise.model.playlist.song.Song)
     */
    @Override
    public void updateReproductionSongInfo(final Song song) {
        this.labelNomeCanzoneCorrente.setText(song.getTitle());
        this.labelCantanteCanzoneCorrente.setText(song.getArtist());
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#updateReproductionSongPosition(java.lang.Integer)
     */
    @Override
    public void updateReproductionSongPosition(final Integer position) {
        if (!sliderPosition.isValueChanging() && !blockSliderPosition) {
            this.sliderPosition.setValue(position);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UI#showNotification(java.lang.String)
     */
    @Override
    public void showNotification(final String textNotification) {
        final Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Vise Music");
        alert.setContentText(textNotification);
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        // Adding custom css to alert.
        final DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("Dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("Dialog");
        // Show alert and wait.
        alert.showAndWait();
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#refreshListPlaylists(java.util.List)
     */
    @Override
    public void refreshListPlaylists(final List<Playlist> playlists) {
        this.listViewPlaylist.getItems().clear();
        this.listViewPlaylist.setItems(FXCollections.observableArrayList(playlists));
        if (this.isModifyingNamePlaylist && this.playerController.getCurrentPlaylist().getPlaylistID().equals(this.tempIDPlaylistInModifying)) {
            this.labelNomePlaylist.setText(this.playerController.getCurrentPlaylist().getName());
            this.isModifyingNamePlaylist = false;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#refreshTableSongs(java.util.List)
     */
    @Override
    public void refreshTableSongs(final List<Song> songs) {
        this.tableViewSongsCurrentPlaylist.getItems().clear();
        this.tableViewSongsCurrentPlaylist.setItems(FXCollections.observableArrayList(songs));
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#updateUserInfo(org.vise.model.user.CurrentUser)
     */
    @Override
    public void updateUserInfo(final CurrentUser user) {
        this.labelUsername.setText(user.getUsername());
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#updateActivityFriends(java.util.List)
     */
    @Override
    public void updateActivityFriends(final List<Pair<String, String>> activities) {
        final List<VBox> friendsList = new ArrayList<>();
        activities.forEach(a -> {
            final VBox friendContainer = new VBox();
            final Label friendHead = new Label(a.getKey());
            friendHead.setTextAlignment(TextAlignment.CENTER);
            final Button friendIcon = new Button();
            friendIcon.setStyle("-fx-background-color: #ffffff;\n" + "-size: 24;\n" + "-fx-min-height: -size;\n" + "-fx-min-width: -size;\n" + "-fx-max-height: -size;\n" 
                    + "-fx-max-width: -size;" + "-fx-shape: \"M28.82,27.979l-0.846-4.75c-0.227-1.271-1.392-2.474-2.652-2.738l-4.362-0.92l-0.898-1.842 c-0.045-0.092-0.123-0.158-0.212-0.207c0.79-1.013,1.421-2.213,1.876-3.42c0.778-0.281,1.562-0.977,1.789-1.881 c0.302-1.201-0.017-2.271-0.762-2.738V8.634c0.681-2.047,1.034-5.396-2.052-7.199c-1.384-0.807-2.529-0.859-3.396-0.576 c-0.135-0.352,0.268-0.858,0.268-0.858c-0.711-0.025-1.085,0.354-1.24,0.567c-0.949-0.438-2.192-0.623-3.784-0.289 c-5.104,1.07-5.756,7.756-4.822,9.222c-0.729,0.471-1.039,1.53-0.74,2.719c0.227,0.908,1.016,1.606,1.798,1.888 c0.448,1.207,1.074,2.409,1.861,3.42c-0.085,0.049-0.161,0.112-0.205,0.203l-0.899,1.842l-4.364,0.92 c-1.261,0.269-2.426,1.469-2.652,2.738l-0.846,4.75c-0.118,0.661,0.039,1.293,0.442,1.771c0.403,0.481,0.996,0.746,1.668,0.746 h22.918c0.673,0,1.266-0.265,1.668-0.746C28.782,29.271,28.938,28.642,28.82,27.979z M27.611,29.109 c-0.21,0.252-0.529,0.39-0.901,0.39h-4.442v-2.578c0-0.273-0.225-0.5-0.5-0.5s-0.5,0.227-0.5,0.5v2.578H9.234v-2.578 c0-0.273-0.224-0.5-0.5-0.5c-0.276,0-0.5,0.227-0.5,0.5v2.578H3.792c-0.372,0-0.692-0.138-0.902-0.39 c-0.21-0.25-0.29-0.59-0.225-0.955l0.846-4.75c0.154-0.866,1.013-1.754,1.874-1.937l4.064-0.856 c0.023,0.021,0.043,0.045,0.071,0.062l4.391,2.541c0.077,0.045,0.164,0.068,0.25,0.068c0.049,0,0.098-0.008,0.146-0.021 c0.132-0.041,0.242-0.136,0.303-0.261l0.64-1.308l0.641,1.309c0.061,0.125,0.171,0.22,0.303,0.261 c0.048,0.014,0.097,0.021,0.146,0.021c0.087,0,0.175-0.023,0.25-0.068l4.392-2.541c0.027-0.018,0.047-0.041,0.07-0.062l4.063,0.856 c0.861,0.183,1.72,1.068,1.875,1.937l0.846,4.75C27.9,28.521,27.821,28.859,27.611,29.109z M10.783,19.31l0.321-0.658l0.748,0.433 l0.647,0.375l0.647,0.375l1.481,0.854l-0.681,1.396l-3.062-1.771l-0.461-0.27l0.08-0.164L10.783,19.31z M19.718,19.31l0.278,0.567 l0.08,0.164l-0.461,0.269l-3.062,1.772l-0.681-1.395l1.48-0.855L18,19.457l0.646-0.375l0.748-0.432L19.718,19.31z M9.598,13.148 c-0.414,0.088-1.372-0.432-1.561-1.188c-0.194-0.772-0.01-1.494,0.411-1.613c0.125-0.034,0.254-0.006,0.383,0.062V9.253 c0.002-0.002,0.004-0.004,0.007-0.008C8.834,9.231,8.833,9.208,8.831,9.192V8.794c0.104-0.979,1.03-2.836,6.126-1.166 c1.755,0.576,2.839-0.336,3.385-1.545c1.73,0.193,2.887,2.158,3.33,3.369v0.959c0.063-0.035,0.128-0.06,0.193-0.07 c-0.003,0.008-0.002,0.025-0.005,0.031c0.014,0.016,0.039,0.004,0.073-0.027c0.041-0.002,0.082-0.006,0.122,0.004 c0.42,0.119,0.604,0.84,0.41,1.613c-0.188,0.75-1.139,1.27-1.551,1.191c-0.655,1.989-2.079,4.639-4.142,5.561 c-0.475,0.211-0.981,0.332-1.521,0.332c-0.548,0-1.061-0.119-1.538-0.33C11.65,17.804,10.253,15.189,9.598,13.148z\";");
            friendHead.setGraphic(friendIcon);
            friendContainer.getChildren().add(friendHead);
            if (!a.getValue().isEmpty()) {
                final Label friendDesc = new Label(PRE_ACTIVITY + "\n" + a.getValue());
                friendDesc.setTextAlignment(TextAlignment.CENTER);
                friendContainer.getChildren().add(friendDesc);
            }
            friendContainer.setOnMouseClicked(e -> {
                this.playerController.search(a.getKey());
            });
            friendsList.add(friendContainer);
        });
        this.listViewActivity.setItems(FXCollections.observableArrayList(friendsList));
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIPlayer#updateSearchView(java.util.List)
     */
    @Override
    public void updateSearchView(final List<FriendUser> friendUsers) {
        this.showSearchOnTop();
        final FlowPane container = new FlowPane();
        final VBox vboxContainer = new VBox();
        friendUsers.forEach(f -> {
            // Creation fields for the header.
            final HBox header = new HBox();
            header.setMinHeight(Control.USE_COMPUTED_SIZE);
            header.setMaxHeight(Control.USE_COMPUTED_SIZE);
            header.setAlignment(Pos.CENTER_LEFT);

            // Creation icon user.
            final Button icon = new Button();
            icon.setStyle("-fx-background-color: #ffffff;\n" + "-size: 80;\n" + "-fx-min-height: -size;\n" + "-fx-min-width: -size;\n" + "-fx-max-height: -size;\n" 
                        + "-fx-max-width: -size;" + "-fx-shape: \"M12,19.2C9.5,19.2 7.29,17.92 6,16C6.03,14 10,12.9 12,12.9C14,12.9 17.97,14 18,16C16.71,17.92 14.5,19.2 12,19.2M12,5A3,3 0 0,1 15,8A3,3 0 0,1 12,11A3,3 0 0,1 9,8A3,3 0 0,1 12,5M12,2A10,10 0 0,0 2,12A10,10 0 0,0 12,22A10,10 0 0,0 22,12C22,6.47 17.5,2 12,2Z\";");
            // Creationf fileds that contain informations about user.
            final VBox utente = new VBox();
            utente.setMinHeight(Control.USE_COMPUTED_SIZE);
            utente.setMaxHeight(Control.USE_COMPUTED_SIZE);
            utente.setAlignment(Pos.CENTER_LEFT);
            final Label nameSurname = new Label(f.getUsername());
            nameSurname.setStyle("-fx-font-size: 25;");
            final Label numeberPlaylist = new Label("Playlist pubbliche: ");
            numeberPlaylist.setStyle("-fx-font-size: 25");
            final Button btnFriendship = new Button("Segui");
            if (f.getUserID() == playerController.getCurrentUser().getUserID()) {
                btnFriendship.setDisable(true);
            }
            btnFriendship.setStyle(PlayerScreenController.BUTTON_FOLLOW);
            if (f.isFriend()) {
                btnFriendship.setText("Smetti di seguire");
                btnFriendship.setStyle(PlayerScreenController.BUTTON_UNFOLLOW);
            }
            btnFriendship.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    playerController.modifyFriendship(f.isFriend(), f.getUserID());
                    if (f.isFriend()) {
                        btnFriendship.setText("Segui");
                        btnFriendship.setStyle(PlayerScreenController.BUTTON_FOLLOW);
                        f.deleteFriend();
                    } else {
                        btnFriendship.setText("Smetti di seguire");
                        btnFriendship.setStyle(PlayerScreenController.BUTTON_UNFOLLOW);
                        f.addAsFriend();
                    }
                }
            });
            utente.getChildren().addAll(nameSurname, numeberPlaylist, btnFriendship);
            VBox.setMargin(btnFriendship, new Insets(10, 0, 0, 0));
            header.getChildren().addAll(icon, utente);
            HBox.setMargin(utente, new Insets(0, 0, 10 + 10, 0));
            HBox.setMargin(icon, new Insets(0, 10, 0, 0));

            // Creation fields for the playlists of the users.
            final HBox playlists = new HBox();
            f.getPlaylists().forEach(p -> {
                final VBox playlist = new VBox();
                playlist.setCursor(Cursor.HAND);
                playlist.setMinHeight(Control.USE_COMPUTED_SIZE);
                playlist.setMaxHeight(Control.USE_COMPUTED_SIZE);
                playlist.setAlignment(Pos.CENTER_LEFT);
                final ImageView imagePlaylist = new ImageView(new Image(PlayerScreenController.class.getResource("/icons/songplaying.png").toExternalForm()));
                final Label namePlaylist = new Label(p.getName());
                namePlaylist.setPadding(new Insets(10, 10, 10, 10));
                playlist.getChildren().addAll(imagePlaylist, namePlaylist);

                HBox.setMargin(playlist, new Insets(10, 10, 10, 10));
                playlist.setOnMouseClicked(e -> {
                    this.labelYouTube.setVisible(true);
                    this.btnAddSongToCurrent.setDisable(true);
                    this.btnRemoveSongToCurrent.setDisable(true);
                    this.labelNomePlaylist.setText(p.getName());
                    this.labelAutorePlaylist.setText(f.getUsername());
                    this.refreshTableSongs(p.getPlaylistContent());
                    this.showPlaylistOnTop();
                });
                playlists.getChildren().add(playlist);
            });
            final FlowPane playlistsContainer = new FlowPane();
            playlistsContainer.getChildren().add(playlists);
            final ScrollPane playlistsScroll = new ScrollPane();
            playlistsScroll.setContent(playlistsContainer);
            final VBox v = new VBox();
            v.getChildren().addAll(header, playlistsScroll);
            vboxContainer.getChildren().add(v);
        });

        container.getChildren().add(vboxContainer);
        this.scrollPaneSearch.setContent(container);
        this.scrollPaneSearch.setPannable(true);
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UI#preShowOperation()
     */
    @Override
    public void preShowOperation() {
        if (this.environment.getPlayerMode() == FXEnvironment.PlayerMode.OFFLINE) {
            offlineMode();
        } else {
            onlineMode();
        }
        this.playerController.loadPlaylists();
    }

    private void onlineMode() {
        this.btnLoginLogout.setText("Logout");
        this.playerController.setPlayerMode(true);
    }

    private void offlineMode() {
        this.textFieldSearch.setDisable(true);
        this.btnSearch.setDisable(true);
        this.labelUsername.setText(System.getProperty("user.name"));
        this.labelFriends.setText("Modalità offline");
        this.btnLoginLogout.setText("Login");
        this.playerController.setPlayerMode(false);
    }

    private void createAndShowPannelModify(final List<Node> nodes) {
        final Stage editSongStage = new Stage();
        final VBox content = new VBox();
        content.getChildren().addAll(nodes);
        content.getStyleClass().add("vbox");
        content.getStylesheets().add(getClass().getResource("Popup.css").toExternalForm());
        final Scene stageScene = new Scene(content, PlayerScreenController.DIMENSION_MOD_WIDTH_WINDOW, PlayerScreenController.DIMENSION_MOD_HEGTH_WINDOW);
        editSongStage.setScene(stageScene);
        editSongStage.show();
    }

    private void showSearchOnTop() {
        if (!this.stackPaneCurrentPlaylist.isVisible()) {
            this.stackPaneCurrentPlaylist.setVisible(true);
        }
        this.vboxPlayer.setVisible(false);
        this.scrollPaneSearch.setVisible(true);
        this.scrollPaneSearch.toFront();
        this.labelYouTube.setVisible(false);
    }

    private void showPlaylistOnTop() {
        if (!this.stackPaneCurrentPlaylist.isVisible()) {
            this.stackPaneCurrentPlaylist.setVisible(true);
        }
        this.scrollPaneSearch.setVisible(false);
        this.vboxPlayer.setVisible(true);
        this.vboxPlayer.toFront();
    }

    private void loadSongToPlayer(final Song song) {
        this.playerController.loadSong(song);
        this.sliderPosition.setMax(this.playerController.getLength());
    }

    private void setStyleSheetNode(final Node node, final String classCss) {
        node.getStyleClass().add(classCss);
    }
}
