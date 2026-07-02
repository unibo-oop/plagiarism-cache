package justanotherchessgame.view.main;


import justanotherchessgame.controller.MainMenuController;
import justanotherchessgame.model.Main;
import justanotherchessgame.util.ImageGenerator;
import justanotherchessgame.view.MenuContainer;
import justanotherchessgame.view.MenuLine;
import justanotherchessgame.view.ResizableGraphicComponent;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class used to create the menu view.
 */
public class MainMenuViewImpl implements MainMenuView, ResizableGraphicComponent {

    //Graphic components needed to resize
    private Pane root;
    private final Title title = new Title("J-A-C-G");
    private MenuLine normalMode;
    private MenuLine newGame;
    private MenuLine blitzMode;
    private MenuLine challengeMode;
    private MenuLine loadGame;
    private MenuLine exit;
    private MenuLine duoLocal;
    private MenuLine online;
    private MenuLine singlePlayer;
    private MenuContainer hiddenBox;
    private MenuContainer hiddenBox2;
    private MenuContainer vbox;
    private ImageView img;
    //Audio player and controller to interact with the view
    private MediaPlayer player;
    private final MainMenuController controller;
    /**
     * Class controller.
     * @param ctrl is the controller used to interact with the class.
     */
    public MainMenuViewImpl(final MainMenuController ctrl) {
        controller = ctrl;
    }

    /**
     * Function used to hide both hidden menu after they are shown.
     * @param node is the node which containing the menu.
     * @param choice is a boolean used to determine which menu must be hidden.
     */
    private void removeMenu(final ObservableList<Node> node, final boolean choice) {
        if (choice) {
            node.remove(hiddenBox);
        } else {
            node.remove(hiddenBox2);
        }
    }

    /**
     * Function used to generate the file picker when LoadGame button is clicked.
     * @param stage is the stage where the new window will be shown.
     */
    private void generateFileChooser(final Stage stage) {
        final FileChooser fileChooser = new FileChooser();

        //Set extension filter
        final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        final File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            player.stop();
            controller.loadGame(file);
        } else {
            player.play();
        }
    }

    @Override
    public final Pane createContent() {
        //creating audioPlayer
        final String musicFile = "res/audio/MenuAudio.wav";
        final Media sound = new Media(new File(musicFile).toURI().toString());
        player = new MediaPlayer(sound);
        player.play();
        player.setOnEndOfMedia(new Runnable() {
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        //create the root of the view
        root = new Pane();
        root.setId("rootPane");
        img = ImageGenerator.generateImage("bg.jpg");
        root.getChildren().add(img);
        //////MENU LINES OF HIDDEN MENUS

        normalMode = new MainMenuLine("NORMAL", false);
        normalMode.setOnMousePressed(e -> {
            if (!root.getChildren().contains(hiddenBox2) && root.getScene() != null) {
                root.getChildren().add(hiddenBox2);
            }
        });
        blitzMode = new MainMenuLine("BLITZ", false);
        challengeMode = new MainMenuLine("CHALL", false);
        duoLocal = new MainMenuLine("DUO LOCAL", false);
        duoLocal.setOnMousePressed(e -> {
            player.stop();
            removeMenu(root.getChildren(), true);
            removeMenu(root.getChildren(), false);
            controller.createNewGame();
        });
        online = new MainMenuLine("ONLINE", false);
        online.setOnMousePressed(e -> {
            //TODO:CREATE ONLINE GAME
        });
        singlePlayer = new MainMenuLine("SOLO", false);
        singlePlayer.setOnMousePressed(e -> {
            player.stop();
            removeMenu(root.getChildren(), true);
            removeMenu(root.getChildren(), false);
            controller.createNewAIGame();
        });
        ////END MENU LINES OF HIDDEN MENUS

        //Create both hidden menus
        hiddenBox = new MainMenuContainer(2, normalMode, blitzMode, challengeMode);
        hiddenBox2 = new MainMenuContainer(3, singlePlayer, duoLocal, online);
        // creating the main menu of the view
        newGame = new MainMenuLine("NEW GAME", true);
        newGame.setOnMouseClicked(e -> {
            if (!root.getChildren().contains(hiddenBox) && root.getScene() != null) {
                root.getChildren().add(hiddenBox);
                root.getChildren().remove(hiddenBox2);
            }
        });
        loadGame = new MainMenuLine("LOAD GAME", true);
        loadGame.setOnMouseClicked(e -> {
            if (root.getChildren().contains(hiddenBox) && root.getScene() != null) {
                root.getChildren().remove(hiddenBox);
                root.getChildren().remove(hiddenBox2);
            }
            player.pause();
            generateFileChooser(Main.getStage());
        });
        exit = new MainMenuLine("EXIT", true);
        exit.setOnMouseClicked(e -> System.exit(0));
        vbox = new MainMenuContainer(1, newGame, loadGame, exit);
        root.getChildren().addAll(title, vbox);
        return root;
    }

    @Override
    public final void resize() {
        //resizing and moving title
        title.resize();
        //resizing background
        img.setFitHeight(Main.getStageHeight());
        img.setFitWidth(Main.getStageWidth());
        //resizing menu container
        vbox.resize();
        if (hiddenBox != null) {
            hiddenBox.resize();
        }
        if (hiddenBox2 != null) {
            hiddenBox2.resize();
        }
        //resizing items
        normalMode.resize();
        newGame.resize();
        blitzMode.resize();
        challengeMode.resize();
        loadGame.resize();
        exit.resize();
        duoLocal.resize();
        singlePlayer.resize();
        online.resize();
    }
    /**
     * Class used to create the title.
     */
    private static class Title extends StackPane implements ResizableGraphicComponent {
        private final Rectangle bg;
        private final Text text;
        /**
         * Function used to create the title.
         * @param name is text of the title.
         */
        Title(final String name) {
            bg = new Rectangle();
            bg.setStroke(Color.rgb(201, 222, 255));
            bg.setStrokeWidth(2);
            bg.setFill(null);
            text = new Text(name);
            text.setFill(Color.rgb(201, 222, 255));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }

        @Override
        public void resize() {
            final double width = Main.getStageWidth();
            this.setTranslateX(Main.getStageWidth() * 0.10);
            this.setTranslateY(Main.getStageHeight() * 0.20);
            this.bg.setWidth(width * 0.3);
            this.bg.setHeight(width * 0.3 / 3.5);
            this.text.setFont(Font.font("Terminator Two", FontWeight.SEMI_BOLD, width * 0.2 / 3.5 - 10));
        }
    }
}
