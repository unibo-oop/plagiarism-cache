package talisman.model.board;

import talisman.util.Pair;

/**
 * A pawn on a talisman board.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanBoardPawn implements BoardPawn {
    private final String imagePath;
    private final int player;
    private Pair<Integer, Integer> position;

    /**
     * Creates a new pawn.
     * 
     * @param imagePath the path to the pawn's image
     * @param player the index of the player controlling the pawn
     */
    public TalismanBoardPawn(final String imagePath, final int player) {
        this.imagePath = imagePath;
        this.position = new Pair<>(0, 0);
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerIndex() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPositionSection() {
        return this.position.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPositionCell() {
        return this.position.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final int section, final int cell) {
        this.position = new Pair<>(section, cell);
    }

    /**
     * Constructs a new pawn.
     * 
     * @param imagePath the path to the pawn's image
     * @param player the index of the player controlling the pawn
     * @return the created pawn
     */
    public static TalismanBoardPawn createPawn(final String imagePath, final int player) {
        return new TalismanBoardPawn(imagePath, player);
    }
}
