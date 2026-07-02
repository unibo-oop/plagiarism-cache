package justanotherchessgame.view.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import justanotherchessgame.controller.Controller;
import justanotherchessgame.model.Main;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;
import justanotherchessgame.util.ImageGenerator;
import justanotherchessgame.util.PieceImageGenerator;
import justanotherchessgame.util.PieceUtility;
import justanotherchessgame.util.Point;

/**
 * Class used to create the lists used in the game view.
 */
public class TakenListViewImpl implements TakenListView {

    private static final String FORMAT = ".png";
    private static final double LIST_PIECE_PROP = 0.4;
    private final ListView<ListMove> logList;
    private final ListView<ChessPiece> whiteList;
    private final ListView<ChessPiece> blackList;
    private final ObservableList<ChessPiece> blackPieces;
    private final ObservableList<ChessPiece> whitePieces;
    private final ObservableList<ListMove> logListComponents;

    private final Controller controller;
    /**
     * Class constructor.
     * @param controller is the controller of the list.
     */
    public TakenListViewImpl(final Controller controller) {
        System.out.println("creating lists");
        this.controller = controller;
        logList = new ListView<ListMove>(); 
        whiteList = new ListView<ChessPiece>();
        blackList = new ListView<ChessPiece>();
        blackPieces = FXCollections.observableArrayList();
        whitePieces = FXCollections.observableArrayList();
        logListComponents = FXCollections.observableArrayList();
        initializeLists();
    }

    @Override
    public final ListView<ListMove> getLogList() {
        return logList;
    }

    @Override
    public final ListView<ChessPiece> getWhiteList() {
        return whiteList;
    }

    @Override
    public final ListView<ChessPiece> getBlackList() {
        return blackList;
    }

    @Override
    public final void addPiece(final Piece p) {
        ChessPiece piece;
        if (!p.getColor().equals("white")) {
            piece = blackPieces.get(findInList(p));
        } else {
            piece = whitePieces.get(findInList(p));
        }
        piece.setCount(piece.getCount() + 1);
        whiteList.refresh();
        blackList.refresh();
    }

    @Override
    public final void addLog(final Piece p, final MoveInfoImpl m) {
        logListComponents.add(0, new ListMove(p, m));
        if (m.getPromotion() != null) {
            final Piece newP = PieceUtility.generatePiece(m.getPromotion().getSimpleName(), p.getColor().equals("white") ? true : false, m.getTo().x, m.getTo().getY());
            logListComponents.add(0, new ListMove(newP, m));
        }
        logList.refresh();
    }

    /**
     * Function used to initialize all the lists.
     * @param controller is the controlle of the lists.
     */
    private void initializeLists() {

        //adding the css to the lists.
        logList.getStyleClass().add("transpList");
        whiteList.getStyleClass().add("transpList");
        blackList.getStyleClass().add("transpList");
        //adding the pieces to black listview
        blackPieces.add(new ChessPiece("King", "black_tKing", 0));
        blackPieces.add(new ChessPiece("Queen", "black_tQueen", 0));
        blackPieces.add(new ChessPiece("Bishop", "black_tBishop", 0));
        blackPieces.add(new ChessPiece("Knight", "black_tKnight", 0));
        blackPieces.add(new ChessPiece("Rook", "black_tRook", 0));
        blackPieces.add(new ChessPiece("Pawn", "black_tPawn", 0));
        //adding the pieces to white listview
        whitePieces.add(new ChessPiece("King", "white_tKing", 0));
        whitePieces.add(new ChessPiece("Queen", "white_tQueen", 0));
        whitePieces.add(new ChessPiece("Bishop", "white_tBishop", 0));
        whitePieces.add(new ChessPiece("Knight", "white_tKnight", 0));
        whitePieces.add(new ChessPiece("Rook", "white_tRook", 0));
        whitePieces.add(new ChessPiece("Pawn", "white_tPawn", 0));
        //setting the orientation of the pieces litviews
        whiteList.setOrientation(Orientation.HORIZONTAL);
        blackList.setOrientation(Orientation.HORIZONTAL);

        //SET UP THE LOG LIST
        logList.setCellFactory(listview -> new ListCell<ListMove>() {
            @Override
            protected void updateItem(final ListMove move, final boolean empty) {
                super.updateItem(move, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    final ImageView img = ImageGenerator.generateImage(PieceImageGenerator.getTinyImage(move.getPiece()));
                    final double dim = Math.min(Main.getStageHeight(), Main.getStageWidth()) * GameViewImpl.CHESSBOARD_PROPORTION / GameViewImpl.ROW_LENGTH;
                    img.setFitHeight(dim);
                    img.setFitWidth(dim);
                    img.setPreserveRatio(true);
                    final String result = ":[" + Character.toString((char) (move.getStartPos().getX() + 65)) + ";" + (move.getStartPos().getY() + 1) + "] ->" + "[" + Character.toString((char) (move.getFinalPos().getX() + 65)) + ";" + (move.getFinalPos().getY() + 1) + "]";
                    setGraphic(img);
                    setText(result);
                }
            }
        });
        logList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (logList.getSelectionModel().getSelectedItem() != null) {
                    controller.logClicked(logList.getSelectionModel().getSelectedIndex());
                }
            }
        });
        //making the elements of the listview easier to click with the cursor switch
        logList.setOnMouseEntered(e -> {
            logList.getScene().setCursor(Cursor.HAND);
        });

        logList.setOnMouseReleased(e -> {
            logList.getScene().setCursor(Cursor.DEFAULT);
        });
        logList.setOnMouseExited(event -> {
            if (logList.getScene() != null) {
                logList.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        logList.setOnMousePressed(event -> {
            logList.getScene().setCursor(Cursor.HAND);
        });
        //binding the list to the listview
        logList.setItems(logListComponents);

        //setting the type of each cell to display the counter and the image of each piece and binding to the observable list
        setListCell(whiteList);
        whiteList.setItems(whitePieces);

        //setting the type of each cell to display the counter and the image of each piece and binding to the observable list
        setListCell(blackList);
        blackList.setItems(blackPieces);

        //setting the background of the left pane by setting the background of each list
        setListBackground(logList);
        setListBackground(whiteList);
        setListBackground(blackList);
    }

    /**
     * Function used set the background of a given list.
     * @param list is the list we want to set the background.
     */
    private static <T> void setListBackground(final ListView<T> list) {
        final Image img = ImageGenerator.generateImage("wood.jpg").getImage(); 
        list.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    /**
     * Function used to set the cell factory of the taken pieces lists.
     * @param list is the list we are setting the cell factory.
     */
    private static void setListCell(final ListView<ChessPiece> list) {
        list.setCellFactory(listview -> new ListCell<ChessPiece>() {
            @Override
            protected void updateItem(final ChessPiece piece, final boolean empty) {
                ImageView imageView = new ImageView();
                super.updateItem(piece, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    imageView = ImageGenerator.generateImage(piece.getImage() + TakenListViewImpl.FORMAT);
                    final double dim = Math.min(Main.getStageHeight(), Main.getStageWidth()) * TakenListViewImpl.LIST_PIECE_PROP / GameViewImpl.ROW_LENGTH;
                    imageView.setFitHeight(dim);
                    imageView.setFitWidth(dim);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                    setText("x" + piece.getCount());
                }
            }
        });
    }

    /**
     * Function used to find a piece inside the list.
     * @param p is the piece we are looking for.
     * @return the position of the element in list, -1 if is not contained.
     */
    private int findInList(final Piece p) {
        int pos = 0;
        for (final ChessPiece look : blackPieces) {
            if (look.getName().equals(p.getName())) {
                return pos;
            }
            pos++;
        }
        return -1;
    }
}

/**
 * This class is used inside the log list to represent each move in a easier way.
 */
class ListMove {
    private final Piece piece;
    private final Point startPos;
    private final Point finalPos;

    /**
     * Class constructor.
     * @param p is the piece moved.
     * @param m is the move performed.
     */
    ListMove(final Piece p, final MoveInfoImpl m) {
        piece = p;
        startPos = m.getFrom();
        finalPos = m.getTo();
    }

    public Piece getPiece() {
        return piece;
    }

    public Point getStartPos() {
        return startPos;
    }

    public Point getFinalPos() {
        return finalPos;
    }

}

/**
 * This class is used inside both taken piece lists to keep track of how many pieces of each type have been eaten.
 */
class ChessPiece {
    private final String name;
    private final String image;
    private int count;

    /**
     * Class constructor.
     * @param name is the piece name.
     * @param image is the name used to generate the image of the piece.
     * @param count is the counter incremented each type a piece of this type is eaten.
     */
    ChessPiece(final String name, final String image, final int count) {
        this.name = name;
        this.image = image;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    public int hashCode() {
        switch (this.name) {
        case "King": 
            return 0;
        case "Queen":
            return 1;
        case "Bishop":
            return 2;
        case "Knight":
            return 3;
        case "Pawn":
            return 4;
        case "Rook":
            return 5;
        default:
            break;
        }
        return -1;
    }
}
