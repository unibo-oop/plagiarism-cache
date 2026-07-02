package gamelogic;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pair.Pair;

/**
 * This class implements the interface {@link Board}.
 *
 */
public final class BoardImpl implements Board {

    private final Map<Pair<Integer, Integer>, Color> board = new HashMap<>();
    private final GameLogic game;

    /**
     * @param game : the current instance of GameLogic
     */
    public BoardImpl(final GameLogic game) {
        this.game = game;
    }

    @Override
    public void placePiece() {
        for (final Pair<Integer, Integer> i : this.game.getCurrent().getPosition()) {
            this.board.put(i, this.game.getCurrent().getColor());
        }
    }

    @Override
    public Map<Pair<Integer, Integer>, Color> getBoard() {
        return this.board;
    }

    @Override
    public void findRowsCompleted() {
        final Set<Integer> completeLines = new HashSet<>();
        for (final Pair<Integer, Integer> i : this.game.getCurrent().getPosition()) {
            int count = 0;
            if (!completeLines.contains(i.getX())) {
                for (final var blocks : this.board.entrySet()) {
                    if (blocks.getKey().getX().equals(i.getX())) {
                        count++;
                    }
                }
                if (count == Board.ROWLENGTH) {
                    completeLines.add(i.getX());
                }
            }
        }
        this.game.getScore().addPoints(completeLines.size());
        this.cancelLines(completeLines);
    }

    @Override
    public void cancelLines(final Set<Integer> lines) {
        for (final Integer line : lines) {
            for (int i = 0; i < Board.ROWLENGTH; i++) {
                this.board.remove(new Pair<Integer, Integer>(line, i));
            }
        }
        this.updateField(lines);
    }

    private void updateField(final Set<Integer> lines) {
        final Map<Pair<Integer, Integer>, Color> update = new HashMap<>();
        for (final var block : this.board.entrySet()) {
            int shift = 0;
            for (final int i : lines) {
                if (block.getKey().getX() < i) {
                    shift++;
                }
            }
            update.put(new Pair<Integer, Integer>(block.getKey().getX() + shift, block.getKey().getY()),
                    block.getValue());
        }
        this.board.clear();
        this.board.putAll(update);
    }

}
