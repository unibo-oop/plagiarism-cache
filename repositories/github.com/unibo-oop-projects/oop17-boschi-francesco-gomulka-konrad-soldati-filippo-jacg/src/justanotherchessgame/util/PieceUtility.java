package justanotherchessgame.util;

import javafx.application.Platform;
import justanotherchessgame.model.Bishop;
import justanotherchessgame.model.King;
import justanotherchessgame.model.Knight;
import justanotherchessgame.model.Pawn;
import justanotherchessgame.model.Piece;
import justanotherchessgame.model.Queen;
import justanotherchessgame.model.Rook;

/**
 * utility class used to generate pieces and classes from strings.
 */
public final class PieceUtility {

    /**
     * Private constructor for the utility static class.
     */
    private PieceUtility() {
    };

    /**
     * Function used to generate a piece from its information.
     * @param name is the piece name.
     * @param color is the piece color:
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece.
     * @return the piece created.
     */
    public static Piece generatePiece(final String name, final boolean color, final int x, final int y) {
        Piece result = null;
        switch (name) {
        case "King": 
        result = new King(color, x, y);
            break;
        case "Queen": 
        result = new Queen(color, x, y);
            break;
        case "Bishop": 
        result = new Bishop(color, x, y);
            break;
        case "Knight": 
        result = new Knight(color, x, y);
            break;
        case "Rook": 
        result = new Rook(color, x, y);
            break;
        case "Pawn":
        result = new Pawn(color, x, y);
            break;
        default:Platform.exit();
        }
        return result;
    }

    /**
     * Function used to generate a class used to keep track of the pieces promoted.
     * @param name is the piece name.
     * @return the class created.
     */
    public static Class<? extends Piece> generateClass(final String name) {
        Class<? extends Piece> result = null;
        switch (name) {
        case "King": 
        result = King.class;
            break;
        case "Queen": 
        result = Queen.class;
            break;
        case "Bishop": 
        result = Bishop.class;
            break;
        case "Knight": 
        result = Knight.class;
            break;
        case "Rook": 
        result = Rook.class;
            break;
        case "Pawn":
        result = Pawn.class;
            break;
        default:Platform.exit();
        }
        return result;
    }

}
