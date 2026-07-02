package piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import gamelogic.Board;
import pair.Pair;
import java.awt.Color;
import java.io.Serializable;

/**
 * This class implements the interface {@link Piece}.
 *
 */
public final class PieceImpl implements Piece, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6126742313682778126L;
    private final Set<Pair<Integer, Integer>> startingPosition = new HashSet<>();
    private final Color color;
    private Pair<Integer, Integer> center;
    private int left;
    private int top;
    private final Type type;
    private final Set<Pair<Integer, Integer>> coordinates = new HashSet<>();

    /**
     * @param type : the type of the piece
     * @param form : if the type is custom, it's the form of the piece in the drawing matrix
     * @param color : if the type is custom, is the color of the piece
     * @param center : if the piece is custom, it's the coordinate in the drawing matrix that the piece consider as center for rotate
     */
    public PieceImpl(final Type type, final Optional<Set<Pair<Integer, Integer>>> form, final Optional<Color> color,
            final Optional<Pair<Integer, Integer>> center) {
        this.type = type;
        switch (type) {
        case I:
            this.color = Color.cyan;
            this.center = new Pair<>(0, 1);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(0, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(0, 2));
            this.startingPosition.add(new Pair<Integer, Integer>(0, 3));
            break;
        case J:
            this.color = Color.blue;
            this.center = new Pair<>(1, 1);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 2));
            break;
        case L:
            this.color = Color.orange;
            this.center = new Pair<>(1, 1);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 2));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 2));
            break;
        case O:
            this.color = Color.yellow;
            this.center = new Pair<>(1, 0);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(0, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 1));
            break;
        case S:
            this.color = Color.green;
            this.center = new Pair<>(1, 1);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(0, 2));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 1));
            break;
        case T:
            this.color = Color.pink;
            this.center = new Pair<>(1, 1);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 2));
            break;
        case Z:
            this.color = Color.red;
            this.center = new Pair<>(1, 1);
            this.startingPosition.add(new Pair<Integer, Integer>(0, 0));
            this.startingPosition.add(new Pair<Integer, Integer>(0, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 1));
            this.startingPosition.add(new Pair<Integer, Integer>(1, 2));
            break;
        default:
            this.color = color.get();
            this.center = center.get();
            this.startingPosition.addAll(form.get());
            break;
        }
        this.top = 0;
        this.left = Board.ROWLENGTH / 2 - 2;
        this.coordinates.addAll(this.startingPosition);
    }

    @Override
    public Set<Pair<Integer, Integer>> getPosition() {
        final Set<Pair<Integer, Integer>> copy = new HashSet<>();
        for (final Pair<Integer, Integer> i : this.coordinates) {
            copy.add(new Pair<Integer, Integer>(i.getX() + this.top, i.getY() + this.left));
        }
        return copy;
    }

    @Override
    public Set<Pair<Integer, Integer>> getCoordinates() {
        final Set<Pair<Integer, Integer>> copy = new HashSet<>();
        copy.addAll(this.coordinates);
        return copy;
    }

    @Override
    public void setLeft(final int left) {
        this.left = left;
    }

    @Override
    public void setTop(final int top) {
        this.top = top;
    }

    @Override
    public int getLeft() {
        return this.left;
    }

    @Override
    public int getTop() {
        return this.top;
    }

    @Override
    public void setCenter(final Pair<Integer, Integer> center) {
        this.center = center;
    }

    @Override
    public Pair<Integer, Integer> getCenter() {
        return this.center;
    }

    @Override
    public void setCoordinates(final Set<Pair<Integer, Integer>> update) {
        this.coordinates.clear();
        this.coordinates.addAll(update);
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void resetPiece() {
        this.top = 0;
        this.left = Board.ROWLENGTH / 2 - 2;
        this.coordinates.clear();
        this.coordinates.addAll(this.startingPosition);
    }

    @Override
    public Piece duplicate() {
        Piece clone;
        if (!this.type.equals(Type.CUSTOM)) {
            clone = new PieceImpl(this.type, Optional.empty(), Optional.empty(), Optional.empty());
        } else {
            clone = new PieceImpl(this.type, Optional.of(this.startingPosition), Optional.of(this.color),
                    Optional.of(this.center));
        }
        return clone;
    }

}
