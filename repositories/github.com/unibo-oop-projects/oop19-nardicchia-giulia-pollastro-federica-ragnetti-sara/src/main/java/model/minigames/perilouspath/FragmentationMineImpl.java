package model.minigames.perilouspath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import utility.Pair;

/**
 * Implementation of a {@link FragmentationMine}.
 */
public class FragmentationMineImpl extends AbstractMine implements FragmentationMine {

    private final int size;

    public FragmentationMineImpl(final int size) {
        super(size);
        this.size = size;
    }

    private boolean checkReachLimit(final Pair<Integer, Integer> lastPosition) {
        Objects.requireNonNull(lastPosition);
        return lastPosition.getX() < 0 || lastPosition.getY() < 0 || lastPosition.getX() >= this.size || lastPosition.getY() >= this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Pair<Integer, Integer>>> getScraps() {
        final Set<Pair<Integer, Integer>> scraps = new HashSet<>();
        if (this.isExploded()) {
            final List<Pair<Integer, Integer>> tempScraps = new ArrayList<>();
            final Pair<Integer, Integer> mine = this.getMinePosition();
            tempScraps.add(new Pair<>(mine.getX() + Direction.LEFT.horizontal(), mine.getY() + Direction.UP.vertical()));
            tempScraps.add(new Pair<>(mine.getX() + Direction.LEFT.horizontal(), mine.getY() + Direction.DOWN.vertical()));
            tempScraps.add(new Pair<>(mine.getX() + Direction.RIGHT.horizontal(), mine.getY() + Direction.UP.vertical()));
            tempScraps.add(new Pair<>(mine.getX() + Direction.RIGHT.horizontal(), mine.getY() + Direction.DOWN.vertical()));
            for (final Pair<Integer, Integer> s : tempScraps) {
                if (!this.checkReachLimit(s)) {
                    scraps.add(s);
                }
            }
        }
        return Optional.of(scraps);
    }
}
