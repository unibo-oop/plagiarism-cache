package javagotchi.model.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.Pair;

/**
 * 
 * @author marica
 *
 */
public class GameGridImpl implements GameGrid {

    private static final int ROW = 5;
    private static final int COL = 4;
    private List<Pair<Integer, Integer>> coords = new ArrayList<>();

    /**
     * Constructor for GameTableImpl.
     */
    public GameGridImpl() {
        setAllCoord();
    }

    private void setAllCoord() {
        IntStream.range(0, ROW).boxed().forEach(i -> {
            Stream.iterate(Pair.of(i, 0), p -> Pair.of(p.left, p.right + 1)).limit(COL)
                    .collect(Collectors.toCollection(() -> coords));
        });
    }

    @Override
    public final Pair<Integer, Integer> getDimensionGame() {
        return Pair.of(ROW, COL);
    }

    @Override
    public final List<Pair<Integer, Integer>> getCoords() {
        return this.coords;
    }

    @Override
    public final void move() {
        this.coords = Stream.concat(coords.stream().skip(COL), coords.stream().limit(COL)).collect(Collectors.toList());
    }

    @Override
    public final int getInitialRow() {
        return ROW - 2;
    }

    @Override
    public final boolean isValStart(final int rowStart) {
        return rowStart == this.getInitialRow();
    }

}
