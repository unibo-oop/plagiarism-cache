package gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import piece.Piece;
import piece.PieceImpl;
import piece.Type;
import level.Levels;
import pair.Pair;

/**
 * This class implements the interface {@link GameLogic}.
 *
 */
public final class GameLogicImpl implements GameLogic {

    private final List<Piece> pieceList = new ArrayList<>();
    private int counter;
    private Piece current;
    private Piece next;
    private boolean gameOver;
    private final Board board = new BoardImpl(this);
    private final HoldBox holdBox = new HoldBoxImpl(this);
    private final Score score;

    /**
     * @param level : the starting level of this game
     * @param customList : the custom pieces to use this game
     */
    public GameLogicImpl(final Levels level, final Optional<List<Piece>> customList) {
        this.score = new ScoreImpl(level);
        for (final var i : Type.values()) {
            if (i != Type.CUSTOM) {
                this.pieceList.add(new PieceImpl(i, Optional.empty(), Optional.empty(), Optional.empty()));
            }
        }
        if (!customList.isEmpty()) {
            for (final var e : customList.get()) {
                this.pieceList.add(new PieceImpl(e.getType(), Optional.of(e.getCoordinates()),
                        Optional.of(e.getColor()), Optional.of(e.getCenter())));
            }
        }
        this.shuffleBlocks();
        this.current = this.pieceList.get(this.counter).duplicate();
        this.counter = this.counter + 1;
        this.next = this.pieceList.get(this.counter).duplicate();
        this.counter = this.counter + 1;
    }

    @Override
    public void hold() {
        if (this.holdBox.canHold()) {
            if (!this.holdBox.isHolding()) {
                this.holdBox.firstHold();
                this.nextPiece();
            } else {
                this.current = this.holdBox.hold();
            }
            this.holdBox.setCanHold(false);
        }
    }

    private void nextPiece() {
        this.current = this.next;
        this.next = this.pieceList.get(this.counter).duplicate();
        this.counter = this.counter + 1;
        if (this.counter == this.pieceList.size()) {
            this.shuffleBlocks();
            this.counter = 0;
        }
    }

    @Override
    public Piece getCurrent() {
        return this.current;
    }

    @Override
    public Piece getNext() {
        return this.next;
    }

    private void shuffleBlocks() {
        Collections.shuffle(pieceList);
        this.counter = 0;
    }

    @Override
    public boolean isCurrentLegal() {
        return this.isLegalPosition(this.getCurrent().getPosition());
    }

    @Override
    public boolean isLegalPosition(final Set<Pair<Integer, Integer>> piece) {
        for (final Pair<Integer, Integer> i : piece) {
            if (this.board.getBoard().containsKey(i) || i.getX() >= Board.COLLENGTH || i.getY() < 0
                    || i.getY() >= Board.ROWLENGTH || this.gameOver) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void placePiece() {
        this.board.placePiece();
        this.board.findRowsCompleted();
        if (!this.isLegalPosition(this.next.getPosition())) {
            this.next.setTop(-1);
        }
        this.gameOver = !this.isLegalPosition(this.next.getPosition());
        if (!this.gameOver) {
            this.holdBox.setCanHold(true);
            this.nextPiece();
        } else {
            this.holdBox.setCanHold(false);
        }
    }

    @Override
    public boolean isOver() {
        return this.gameOver;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public HoldBox getHoldBox() {
        return this.holdBox;
    }

    @Override
    public void setCurrent(final Piece block) {
        this.current = block;
    }

}
