package justanotherchessgame.util;

import justanotherchessgame.model.Piece;

/**
 * Class used to manage the path of the piece images.
 */
public final class PieceImageGenerator {

    /**
     * Private constructor for the static utility class.
     */
    private PieceImageGenerator() {
    };

    /**
     * Function used to generate the path of the image of a piece.
     * @param piece is the piece we want to generate the image.
     * @return the image path.
     */
    public static String getImage(final Piece piece) {
        return piece.getColor() + "_" + piece.getName() + ".png";
    }

    /**
     * Function used to generate the path of the small image of a piece.
     * @param piece the piece we want to generate the image.
     * @return the image path.
     */
    public static String getTinyImage(final Piece piece) {
        return piece.getColor() + "_t" + piece.getName() + ".png";
    }
}
