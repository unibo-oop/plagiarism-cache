package justanotherchessgame.view.game;

import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.GridPane;
import justanotherchessgame.controller.Controller;
import justanotherchessgame.model.Bishop;
import justanotherchessgame.model.King;
import justanotherchessgame.model.Knight;
import justanotherchessgame.model.Main;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Pawn;
import justanotherchessgame.model.Piece;
import justanotherchessgame.model.Queen;
import justanotherchessgame.model.Rook;
import justanotherchessgame.util.Point;

/**
 * Class used to create and manage the chessboard view-side.
 */
public class ChessboardViewImpl extends GridPane implements ChessboardView {

    private BoxView[][] table = new BoxViewImpl[8][8];

    /**
     * Class constructor used to create a chessboard in its initial state.
     * @param color is the chessboard color.
     * @param controller is the chessboard controller.
     */
    public ChessboardViewImpl(final boolean color, final Controller controller) {
        super();
        initialize(color, controller);
    }

    /**
     * Class constructor used to create a chessboard starting from a list of moves performed starting from a chessboard in its initial state.
     * @param color is the chessboard color.
     * @param moves is the list of moves.
     */
    public ChessboardViewImpl(final boolean color, final List<MoveInfoImpl> moves) {
        super();
        initialize(color, null);
        defineStartPositions();
        for (final MoveInfoImpl m : moves) {
            this.drawMove(m);
        }
    }

    /**
     * Function used to inizialize the chessboard.
     * @param color is a boolean indicating the color of the bottom left square. This informatin of course determines also the color of all the other cells.
     * @param controller is the controller of the chessboard.
     */
    private void initialize(final boolean color, final Controller controller) {
        //TODO: user proper length
        for (int x = 0; x < table[0].length; x++) {
            for (int y = 0; y < table[1].length; y++) {
                final boolean light = ((x + y) % 2 != 0); 
                table[x][y] = new BoxViewImpl(light, x, y);

                //if white, add Spaces so ensured bottom left is 0,0
                //if Black, add Spaces so ensured bottom left is 7,7
                if (color) { 
                    this.add((Node) table[x][y], x, 7 - y); 
                } else { 
                    this.add((Node) table[x][y], 7 - x, y); 
                }
                // Gets values into event handler
                final int xVal = x;
                final int yVal = y;
                //just to try methods
                //table[x][y].setOnMouseClicked(e->FileManagement.saveOnFile());
                //runs things that happen when a space is clicked
                if (controller != null) {
                    ((ButtonBase) table[x][y]).setOnAction(e -> controller.onSpaceClicked(xVal, yVal));
                }
                //table[x][y].setOnAction(e->BoardController.askForPromotion(table[xVal][yVal]));
            }
        }
    }

    @Override
    public final void defineStartPositions() {
        // white pieces
        this.addPiece(0, 0, new Rook(true, 0, 0));
        this.addPiece(1, 0, new Knight(true, 1, 0));
        this.addPiece(2, 0, new Bishop(true, 2, 0));
        this.addPiece(3, 0, new Queen(true, 3, 0));
        this.addPiece(4, 0, new King(true, 4, 0));
        this.addPiece(5, 0, new Bishop(true, 5, 0));
        this.addPiece(6, 0, new Knight(true, 6, 0));
        this.addPiece(7, 0, new Rook(true, 7, 0));
        for (int i = 0; i < 8; i++) {
            this.addPiece(i, 1, new Pawn(true, i, 1));
        }
        // black pieces
        this.addPiece(0, 7, new Rook(false, 0, 7));
        this.addPiece(1, 7, new Knight(false, 1, 7));
        this.addPiece(2, 7, new Bishop(false, 2, 7));
        this.addPiece(3, 7, new Queen(false, 3, 7));
        this.addPiece(4, 7, new King(false, 4, 7));
        this.addPiece(5, 7, new Bishop(false, 5, 7));
        this.addPiece(6, 7, new Knight(false, 6, 7));
        this.addPiece(7, 7, new Rook(false, 7, 7));
        for (int i = 0; i < 8; i++) {
            this.addPiece(i, 6, new Pawn(false, i, 6));
        }
    }

    @Override
    public final void disabelAllSpaces() {
        for (int x = 0; x < table[0].length; x++) {
            for (int y = 0; y < table[1].length; y++) {
                ((Node) table[x][y]).setDisable(true);
            }
        }
    }

    @Override
    public final void drawMove(final MoveInfoImpl move) {
        final Point from = move.getFrom();
        final Point to = move.getTo();
        final Piece moving = table[from.getX()][from.getY()].getPiece();
        final Piece eaten = table[to.getX()][to.getY()].getPiece();
        //check is a piece is eaten while performing this move
        table[to.getX()][to.getY()].drawPiece(moving);
        table[from.getX()][from.getY()].drawPiece(null);
        // if that's a promotion
        final Class<? extends Piece> promo = move.getPromotion();
        if (promo != null) {
            final Piece oldP = table[to.getX()][to.getY()].getPiece();
            try {
                final Piece newP = promo.getConstructor(Boolean.TYPE, Integer.TYPE, Integer.TYPE).newInstance(oldP.isWhite(), oldP.getX(), oldP.getY());
                table[to.getX()][to.getY()].drawPiece(newP);
            } catch (Exception e) {
                System.out.println("exception: " + e.getMessage());
            }
        }
        //TODO: moved into controller ?
        // En passant: when you are moving a pawn diagonally in an empty space
        if (moving instanceof Pawn && to.getX() - from.getX() != 0 && eaten == null) {
            final Point enemyPawn = new Point(to.getX(), from.getY());
            table[enemyPawn.getX()][enemyPawn.getY()].drawPiece(null);
        }
        // Castling: a king moving more than 1 square horizontally
        if (moving instanceof King && Math.abs(to.getX() - from.getX()) == 2) {
            // Rook's coordinates
            final int y = from.getY();
            // If short, take right; else take left
            final int x = to.getX() == 6 ? 7 : 0;
            final Point rookFrom = new Point(x, y);
            // If right Rook, move in 5, else in 3
            final Point rookTo = new Point(x == 7 ? 5 : 3, y);
            final Piece rook = table[rookFrom.getX()][rookFrom.getY()].getPiece();
            // Update rook
            table[rookFrom.getX()][rookFrom.getY()].drawPiece(null);
            table[rookTo.getX()][rookTo.getY()].drawPiece(rook);
            rook.setPoint(rookTo);
        }
    }

    @Override
    public final void addPiece(final int x, final int y, final Piece p) {
        this.table[x][y].drawPiece(p);
    }

    @Override
    public final BoxView[][] getTable() {
        return Arrays.copyOf(table, table.length);
    }

    @Override
    public final void resize() {
        final double size = Math.min(Main.getStageWidth(), Main.getStageHeight());
        this.setPrefSize(size * GameViewImpl.CHESSBOARD_PROPORTION, size * GameViewImpl.CHESSBOARD_PROPORTION);
        final int limit = GameViewImpl.ROW_LENGTH;
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                this.table[i][j].resize();
            }
        }
    }
}
