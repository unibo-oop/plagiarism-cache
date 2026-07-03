package view.scenes;

import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import application.Controller;
import application.ViewObserver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.board.Position;
import model.cards.Card;
import model.player.PlayerInfo;
import utilities.enumerations.CharacterCard;

/**
 * Graphical User Interface in javaFx for the principal game scene.
 */
public final class BoardScene {
    private static final String BOARD = "images/board.png";
    private static final double SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int BOARD_HEIGHT = 95;
    private static final int LEFTPANE_SIZE = 23;
    private static final int CENTERPANE_SIZE = 60;
    private static final int RIGHTPANE_SIZE = 17;
    private static final double HYSTORY_H = SCREEN_H * 0.30;
    private static final double CLUES_H = SCREEN_H * 0.37;
    private static final double HYSTORY_PADDING = 30;
    private static final int PADDING = 15;
    private static final int BOARD_ROWS = 19;

    private static double boardDim;
    private static double boardStartX;
    private static double boardStartY;
    private static double cellSize;
    private static ListView<String> listView = new ListView<String>();
    private static GridPane rootPane;
    private static Label name;
    private static ImageView avatar;
    private static Clues clues;
    private static TextArea txtNotes;

    /**
     * Constructor.
     */
    private BoardScene() {
    }

    /**
     * Method which create the board scene.
     * 
     * @param l
     *            list of player selected to play.
     * @param mapImage
     *            map which associate pawn to players.
     * @return pane to show.
     */
    public static Parent createPane(final List<PlayerInfo> l, final Map<String, ImageView> mapImage) {
        final ViewObserver observer = Controller.getController();

        // Root pane
        rootPane = new GridPane();
        final ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(LEFTPANE_SIZE);
        final ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(CENTERPANE_SIZE);
        final ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(RIGHTPANE_SIZE);
        rootPane.getColumnConstraints().addAll(c1, c2, c3);

        // Left pane: contains history, clues and notes
        final VBox leftPane = new VBox();
        leftPane.setMinHeight(SCREEN_H);
        leftPane.setSpacing(PADDING);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(PADDING));
        final BorderPane historyPane = new BorderPane();
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(final ListView<String> list) {
                return new ListCell<String>() {
                    public void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        final Text text = new Text(item);
                        text.wrappingWidthProperty().bind(list.widthProperty().subtract(HYSTORY_PADDING));
                        text.textProperty().bind(itemProperty());
                        setPrefWidth(0);
                        setGraphic(text);
                    }
                };
            }
        });
        historyPane.setCenter(listView);
        historyPane.setMinHeight(HYSTORY_H);
        historyPane.setMaxHeight(HYSTORY_H);
        leftPane.getChildren().add(new Label("History"));
        leftPane.getChildren().add(historyPane);
        clues = new Clues(CLUES_H, LEFTPANE_SIZE * SCREEN_W / 100);
        leftPane.getChildren().add(new Label("Clues notebook"));
        leftPane.getChildren().add(clues);
        txtNotes = new TextArea();
        txtNotes.setWrapText(true);
        leftPane.getChildren().add(new Label("Notes"));
        leftPane.getChildren().add(txtNotes);

        // Center pane: contains the board
        final BorderPane centerPane = new BorderPane();
        centerPane.setMinHeight(SCREEN_H);
        // Sets board as background
        boardDim = Math.min(SCREEN_H * BOARD_HEIGHT, CENTERPANE_SIZE * SCREEN_W) / 100;
        boardStartX = (CENTERPANE_SIZE * SCREEN_W / 100 - boardDim) / 2;
        boardStartY = (SCREEN_H - boardDim) / 2;
        cellSize = boardDim / BOARD_ROWS;
        final BackgroundPosition pos = new BackgroundPosition(Side.LEFT, boardStartX, false, Side.TOP, boardStartY,
                false);
        final BackgroundSize size = new BackgroundSize(boardDim, boardDim, false, false, false, false);
        final Background bg = new Background(new BackgroundImage(new Image(BOARD), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, pos, size));
        centerPane.setBackground(bg);
        // Sets pawns
        final double pawnHeight = cellSize * 0.8;
        for (final PlayerInfo p : l) {
            final ImageView pawnIm = new ImageView(p.getName().getPawnPath());
            pawnIm.setFitHeight(pawnHeight);
            pawnIm.setPreserveRatio(true);
            centerPane.getChildren().add(pawnIm);
            updatePlayerPos(pawnIm, p.getPosition());
            mapImage.put(p.getName().toString(), pawnIm);
        }

        // Right pane: contains the buttons
        final VBox rightPane = new VBox();
        rightPane.setMinHeight(SCREEN_H);
        rightPane.setSpacing(PADDING);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(PADDING));
        avatar = new ImageView();
        avatar.setFitHeight(SCREEN_H / 4);
        avatar.setPreserveRatio(true);
        final Button btnShowCards = new Button("YOUR CARDS");
        final Button btnGuess = new Button("GUESS");
        final Button btnAccuse = new Button("ACCUSE");
        final Button btnEndTurn = new Button("END TURN");
        final Button btnGiveUp = new Button("GIVE UP");
        final Button btnQuit = new Button("QUIT");
        rightPane.getChildren().add(new Label("Now it's the turn of"));
        rightPane.getChildren().add(avatar);
        name = new Label("Name");
        name.setWrapText(true);
        name.getStyleClass().add("title");
        rightPane.getChildren().add(name);
        rightPane.getChildren().add(btnGuess);
        rightPane.getChildren().add(btnAccuse);
        rightPane.getChildren().add(btnEndTurn);
        rightPane.getChildren().add(btnShowCards);
        rightPane.getChildren().add(btnGiveUp);
        rightPane.getChildren().add(btnQuit);

        // Assembly
        leftPane.setVisible(false);
        rootPane.add(leftPane, 0, 0);
        rootPane.add(centerPane, 1, 0);
        rootPane.add(rightPane, 2, 0);

        // Action listeners
        btnGuess.setOnAction(e -> {
            observer.guessClick();
        });
        btnAccuse.setOnAction(e -> {
            observer.accuseClick();
        });
        btnEndTurn.setOnAction(e -> {
            observer.endTurn();
        });
        btnGiveUp.setOnAction(e -> {
            observer.giveUp();
        });
        btnQuit.setOnAction(e -> {
            observer.quit();
        });

        btnShowCards.setOnAction(e -> {
            observer.showCards();
        });

        return rootPane;
    }

    /**
     * To update the character card showed on screen of current player.
     * 
     * @param path
     *            the path of the image.
     */
    public static void updateCurrentAvatar(final String path) {
        avatar.setImage(new Image(path));
    }

    /**
     * Update the name of the current player.
     * 
     * @param character
     *            the character of current player
     */
    public static void updateCurrentName(final CharacterCard character) {
        name.setText(character.toString());
    }

    /**
     * Update the notes of the current player.
     * 
     * @param pNotes
     *            Notes of the actual player.
     */
    public static void updateNote(final Map<Card, Boolean> pNotes) {
        clues.setNotes(pNotes);
    }

    /**
     * Update position of current player.
     * 
     * @param pawnImage
     *            the right pawn associate to the character.
     * @param position
     *            new position to update.
     */
    public static void updatePlayerPos(final ImageView pawnImage, final Position position) {
        final double pawnStartPosX = boardStartX + position.getX() * cellSize
                + pawnImage.getBoundsInParent().getWidth() / 2 + 1;
        final double pawnStartPosY = boardStartY + position.getY() * cellSize
                + (cellSize - pawnImage.getFitHeight()) / 2;

        pawnImage.setX(pawnStartPosX);
        pawnImage.setY(pawnStartPosY);
    }

    /**
     * Set invisible the pawn of players which give up or lose.
     * 
     * @param pawnImage
     *            the image associate at the player removed.
     */
    public static void removePlayerPos(final ImageView pawnImage) {
        pawnImage.setVisible(false);

    }

    /**
     * Update player history during his turn.
     * 
     * @param s
     *            string to add.
     */
    public static void updatePlayerHistory(final String s) {
        listView.getItems().add(s);
    }

    /**
     * Set player's history when his turn starts.
     * 
     * @param list
     *            list of events to show.
     */
    public static void refreshPlayerHistory(final List<String> list) {
        listView.getItems().clear();
        list.forEach(listView.getItems()::add);
    }

    /**
     * Set player's text area updated when a turn starts.
     * 
     * @param notes2
     *            string to set in the text area.
     */
    public static void refreshTextArea(final String notes2) {
        txtNotes.setText(notes2);
    }

    /**
     * Get the text in the area at the end of the turn.
     * 
     * @return text in string format.
     */
    public static String getTextArea() {
        return txtNotes.getText();
    }

    /**
     * Shows or hides the left pane with the clues.
     * 
     * @param show
     *            true if the clues are to be shown, false otherwise
     */
    public static void showLeftToolbar(final boolean show) {
        for (final Node node : rootPane.getChildren()) {
            if (GridPane.getColumnIndex(node).equals(0) && GridPane.getRowIndex(node).equals(0)) {
                node.setVisible(show);
            }
        }
    }
}