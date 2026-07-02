package justanotherchessgame.view.game;

import java.io.File;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import justanotherchessgame.controller.Controller;
import justanotherchessgame.model.Main;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;
import justanotherchessgame.util.ImageGenerator;
import justanotherchessgame.view.MenuContainer;
import justanotherchessgame.view.MenuLine;
import justanotherchessgame.view.utils.ScreenController;

/**
 * Class used to create the entire game view.
 */
public class GameViewImpl implements GameView {

    /**
     * The proportion of the center of the view, which is the chessboard.
     */
    public static final double CHESSBOARD_PROPORTION = 0.5;

    /**
     * The proportion of the left and right panel of the game view.
     */
    public static final double MARGIN_PROPORTION = 0.25;

    /**
     * The number of elements in each row7column of the chessboard.
     */
    public static final int ROW_LENGTH = 8;
    //Vars
    private double stageHeight = Main.getStageHeight();
    private double stageWidth = Main.getStageWidth();
    //All graphichs component needed to resize the view
    private MenuContainer menu;
    private MenuLine save;
    private MenuLine sound;
    private MenuLine back;
    private TakenListView lists;
    private EventHandler<WindowEvent> saveGameEvent;
    private SplitPane leftPane;
    private SplitPane right;
    private final ChessboardViewImpl chessboard;
    private StackPane center;
    private MediaPlayer player;
    private TimerView t1;
    private TimerView t2;
    //Controller
    private final Controller controller;

    /**
     * Class constructor.
     * @param controller is thhe controller of the view.
     * @param cb is the chessboard view, created externally.
     */
    public GameViewImpl(final Controller controller, final ChessboardViewImpl cb) {
        chessboard = cb;
        this.controller = controller;
        creategameView();
    }

    /**
     * Function used to create the game view.
     * @param chessboard is the chessboard used in the view.
     * @param controller is the controller used by the chessboard for interactions.
     */
    private void creategameView() {
        //create the lists on the left side
        lists = new TakenListViewImpl(controller);
        //creating audioplayer
        final String musicFile = "res/audio/GameAudio.wav";
        final Media song = new Media(new File(musicFile).toURI().toString());
        player = new MediaPlayer(song);
        player.play();
        player.setOnEndOfMedia(new Runnable() {
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        //creating the main container
        final BorderPane chess = new BorderPane();
        chess.setStyle("-fx-background-color: blue;");
        chess.setPrefSize(stageWidth, stageHeight);
        //CENTER OF THE VIEW
        center = new StackPane(this.chessboard);
        chessboard.setAlignment(Pos.CENTER);
        chess.setCenter(center);
        //LEFT COLUMN OF THE VIEW
        leftPane = new SplitPane();
        leftPane.setMinSize(100, 100);
        //listviews creation. They will be the left panel
        final StackPane s1 = new StackPane(lists.getWhiteList());
        final StackPane s2 = new StackPane(lists.getLogList());
        final StackPane s3 = new StackPane(lists.getBlackList());
        //set the height and width of the left containers
        setSideElemLimits(s1);
        setSideElemLimits(s3);
        leftPane.getItems().addAll(s1, s2, s3);
        leftPane.setDividerPositions(0.3f, 0.6f, 0.9f);
        leftPane.setOrientation(Orientation.VERTICAL);
        chess.setLeft(leftPane);
        //RIGHT COLUMN OF THE VIEW
        right = new SplitPane();
        right.setMinSize(100, 100);
        final StackPane s4 = new StackPane();
        final StackPane s5 = new StackPane(new ListView<String>());
        final StackPane s6 = new StackPane();
        //set the height and width of the left containers
        setSideElemLimits(s4);
        setSideElemLimits(s6);
        right.getItems().addAll(s4, s5, s6);
        right.setDividerPositions(0.3f, 0.6f, 0.9f);
        right.setOrientation(Orientation.VERTICAL);
        chess.setRight(right);
        //The event needs to be saved because the stage is the same in both Main and Game view but only the second one has this event, so it must be removed
        saveGameEvent = new EventHandler<WindowEvent>() {
            public void handle(final WindowEvent event) {
                event.consume();
                final Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Program closing");
                alert.setHeaderText("You are closing the program.");
                alert.setContentText("Do you want to save the game state?");
                final ButtonType buttonSave = new ButtonType("Save");
                final ButtonType buttonNo = new ButtonType("Don't save");
                alert.getButtonTypes().setAll(buttonSave, buttonNo);
                final Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonSave) {
                    if (saveView()) {
                        player.stop();
                        Platform.exit();
                    } else {
                        player.play();
                    }
                } else {
                    Platform.exit();
                }
            }
        };
        Main.getStage().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, saveGameEvent);
        //timers
        //TODO: MAKE TIMERS WORKING WITH MODEL TIME
        t1 = new TimerViewImpl(true, 0);
        setSideElemLimits((Region) t1);
        ((Region) t1).maxWidthProperty().bind(right.widthProperty());
        ((Region) t1).minWidthProperty().bind(right.widthProperty());
        t2 = new TimerViewImpl(true, 0);
        setSideElemLimits((Region) t2);
        ((Region) t2).maxWidthProperty().bind(right.widthProperty());
        ((Region) t2).minWidthProperty().bind(right.widthProperty());
        ((Region) t2).setPrefWidth(right.getWidth());
        s4.getChildren().add((Node) t1);
        s6.getChildren().add((Node) t2);

        //Save button event
        save = new GameMenuLine("Save");
        save.setOnMouseClicked(e -> {
            //save the game only if it's not already saved
            if (!controller.isSaved()) {
                saveView();
                player.play();
            }
        });

        //Back button event
        back = new GameMenuLine("Back");
        back.setOnMouseClicked(e -> {
            player.stop();
            Main.getStage().removeEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, saveGameEvent);
            ScreenController.getIstance().activate("MainMenu");
        });

        //Sound button event
        sound = new GameMenuLine("Sound");
        sound.setOnMouseClicked(e -> {
            if (player.getStatus() == Status.PLAYING) {
                player.pause();
            } else {
                player.play();
            }
        });
        menu = new GameMenuContainer(save, sound, back);
        s5.getChildren().add(menu);
        //adding the scene to the controller and switching to it
        ScreenController.getIstance().addScreen("ChessBoard", chess);
        ScreenController.getIstance().activate("ChessBoard");
        resize();
        changeTimerState();
    }

    /**
     * Function used to set height limits for left and right column of the view.
     * @param reg is the region we want to set the limits.
     */
    private void setSideElemLimits(final Region reg) {
        reg.maxHeightProperty().bind(leftPane.widthProperty().multiply(0.53));
        reg.minHeightProperty().bind(leftPane.widthProperty().multiply(0.53));
    }

    /**
     * Function used to show the saving view.
     * @return an integer indicating if the user actually saved the game or he closed the window before saving.
     */
    private boolean saveView() {
        player.pause();
        final FileChooser fileChooser = new FileChooser();
        //Set extension filter
        final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "xml");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showSaveDialog(Main.getStage());
        if (file != null) {
            if (!file.getName().contains(".")) {
                file = new File(file.getAbsolutePath() + ".xml");
            }
            controller.setSaved();
            controller.saveGame(file);
            return true;
        }
        return false;
    }

    /**
     * Function used to create the log view, shown where a user click any element of the log.
     * @param cb is the chessboard of the log.
     * @return the entire content used in the scene, in this case a BorderPane.
     */
    private BorderPane createLogChess(final ChessboardViewImpl cb) {
        final BorderPane chess = new BorderPane();
        final StackPane center = new StackPane(cb);
        chess.setCenter(center);
        stageWidth = Main.getStageWidth();
        stageHeight = Main.getStageHeight();
        if (stageHeight > stageWidth) {
            center.setPrefSize(stageWidth * CHESSBOARD_PROPORTION, stageWidth * CHESSBOARD_PROPORTION);
            cb.resize();
            chess.setPrefSize(stageWidth * CHESSBOARD_PROPORTION, stageWidth * CHESSBOARD_PROPORTION);
        } else {
            center.setPrefSize(stageHeight * CHESSBOARD_PROPORTION, stageHeight * CHESSBOARD_PROPORTION);
            cb.resize();
            chess.setPrefSize(stageWidth * CHESSBOARD_PROPORTION, stageWidth * CHESSBOARD_PROPORTION);
        }
        return chess;
    }

    @Override
    public final void addTakenpiece(final Piece p) {
        lists.addPiece(p);
    }

    @Override
    public final void addLog(final Piece p, final MoveInfoImpl m) {
        lists.addLog(p, m);
    }

    @Override
    public final void createLogView(final ChessboardViewImpl cb) {
        final Scene scene = new Scene(createLogChess(cb));
        final Stage stage = new Stage();
        ImageGenerator.iconGenerator(stage, "Icon.png");
        stage.setTitle("Review");
        scene.getStylesheets().add("justAnotherChessGame/view/style.css");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @Override
    public final void resize() {
        stageWidth = Main.getStageWidth();
        stageHeight = Main.getStageHeight();
        if (right != null && leftPane != null && center != null && chessboard != null) {
            right.setPrefSize(stageWidth * MARGIN_PROPORTION, stageHeight);
            leftPane.setPrefSize(stageWidth * MARGIN_PROPORTION, stageHeight);
            if (stageHeight > stageWidth) {
                center.setPrefSize(stageWidth * CHESSBOARD_PROPORTION, stageWidth * CHESSBOARD_PROPORTION);
                chessboard.resize();
            } else {
                center.setPrefSize(stageHeight * CHESSBOARD_PROPORTION, stageHeight * CHESSBOARD_PROPORTION);
                chessboard.resize();
            }
            save.resize();
            back.resize();
            sound.resize();
            menu.resize();
        }
    }

    @Override
    public final void changeTimerState() {
        //t1 is runnig, stop it and start t2
        if (t1.isActive()) {
            t1.stopTimer();
            t2.startTimer();
            //t2 is running: stop it and run t1
        } else if (t2.isActive()) {
            t2.stopTimer();
            t1.startTimer();
        } else {
            //both timer are stopped: is the first time and so the timer t1 must start
            t2.startTimer();
        }
    }

    @Override
    public final void stopTimers() {
        t1.stopTimer();
        t2.stopTimer();
    }
}
