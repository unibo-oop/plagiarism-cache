package boardgames.model.board;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import boardgames.model.piece.Dama;
import boardgames.model.piece.Pawn;
import boardgames.model.piece.Piece;
import boardgames.model.piece.CheckersPawn;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

/**
 * inizializzano la scacchiera.
 *
 */
public class CheckersBoard implements BoardType {

    private final static int CHECKERS_PAWN = 4;
    private final static int FIRST_WHITE_ROW = 0;
    private final static int SECOND_WHITE_ROW = 1;
    private final static int THIRD_WHITE_ROW = 2;
    private final static int FIRST_BLACK_ROW = 7;
    private final static int SECOND_BLACK_ROW = 6;
    private final static int THIRD_BLACK_ROW = 5;

    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private List<List<Box>> allPiecesOnBoard;

    public CheckersBoard() {
        this.whitePieces = new LinkedList<>();
        this.blackPieces = new LinkedList<>();
        this.allPiecesOnBoard = new LinkedList<>();

        for (int x = 0; x <= PieceUtils.BOARD_SIZE; x++) {
            this.allPiecesOnBoard.add(new LinkedList<Box>());
            for (int y = 0; y <= PieceUtils.BOARD_SIZE; y++) {
                this.allPiecesOnBoard.get(x).add(new Box(x, y, Optional.empty()));
            }
        }
    }

    public List<List<Box>> reset() {
        for (int e = 0; e <= CHECKERS_PAWN; e++) {
            for (int i = 1; i <= PieceUtils.BOARD_SIZE; i += 2) {
                // initialization of first and third white row
                final Piece cp0 = new Piece(allPiecesOnBoard.get(i).get(FIRST_WHITE_ROW), Colour.White);
                cp0.setPieceType(new CheckersPawn(allPiecesOnBoard.get(i).get(FIRST_WHITE_ROW)));
                this.addWhitePiece(cp0);
                allPiecesOnBoard.get(i).get(FIRST_WHITE_ROW).setPiece(Optional.of(cp0));

                final Piece cp2 = new Piece(this.allPiecesOnBoard.get(i).get(THIRD_WHITE_ROW), Colour.White);
                cp2.setPieceType(new CheckersPawn(allPiecesOnBoard.get(i).get(THIRD_WHITE_ROW)));
                this.addWhitePiece(cp2);
                allPiecesOnBoard.get(i).get(THIRD_WHITE_ROW).setPiece(Optional.of(cp2));
            }

            for (int c = 0; c < PieceUtils.BOARD_SIZE; c += 2) {
                // initialization of second white raw
                final Piece cp1 = new Piece(allPiecesOnBoard.get(c).get(SECOND_WHITE_ROW), Colour.White);
                cp1.setPieceType(new CheckersPawn(allPiecesOnBoard.get(c).get(SECOND_WHITE_ROW)));
                this.addWhitePiece(cp1);
                allPiecesOnBoard.get(c).get(SECOND_WHITE_ROW).setPiece(Optional.of(cp1));
            }

            for (int d = 0; d < PieceUtils.BOARD_SIZE; d += 2) {
                // initialization of first and third black row
                final Piece cp7 = new Piece(allPiecesOnBoard.get(d).get(FIRST_BLACK_ROW), Colour.Black);
                cp7.setPieceType(new CheckersPawn(allPiecesOnBoard.get(d).get(FIRST_BLACK_ROW)));
                this.addBlackPiece(cp7);
                allPiecesOnBoard.get(d).get(FIRST_BLACK_ROW).setPiece(Optional.of(cp7));
                
                final Piece cp5 = new Piece(allPiecesOnBoard.get(d).get(THIRD_BLACK_ROW), Colour.Black);
                cp5.setPieceType(new CheckersPawn(allPiecesOnBoard.get(d).get(THIRD_BLACK_ROW)));
                this.addBlackPiece(cp5);
                allPiecesOnBoard.get(d).get(THIRD_BLACK_ROW).setPiece(Optional.of(cp5));
            }
            for (int b = 1; b <= PieceUtils.BOARD_SIZE; b += 2) {
                // initializatiom of second black row
                final Piece cp6 = new Piece(allPiecesOnBoard.get(b).get(SECOND_BLACK_ROW), Colour.Black);
                this.addBlackPiece(cp6);
                cp6.setPieceType(new CheckersPawn(allPiecesOnBoard.get(b).get(SECOND_BLACK_ROW)));
                allPiecesOnBoard.get(b).get(SECOND_BLACK_ROW).setPiece(Optional.of(cp6));
            }
        }
        return allPiecesOnBoard;
    }

    private void addWhitePiece(final Piece wp) {
        this.whitePieces.add(wp);
    }

    private void addBlackPiece(final Piece bp) {
        this.blackPieces.add(bp);
    }

    public List<Piece> getWhiteStartPieces() {
        return this.whitePieces;
    }

    public List<Piece> getBlackStartPieces() {
        return this.blackPieces;
    }
}