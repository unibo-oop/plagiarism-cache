package model.minigames.perilouspath;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import model.DifficultyLevel;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;
import utility.Pair;

/**
 * Implementation of {@link PerilousPathModel}.
 */
public class PerilousPathModelImpl implements PerilousPathModel {

    /**
     * The Perilous Path's base point.
     */
    private static final int BASE_POINT = 10;

    private final int size;
    private final int numMines;
    private final ScoreModel score;
    private final MineFactory mineFactory;
    private final LinkedList<Pair<Integer, Integer>> selected; //NOPMD
    private Pair<Integer, Integer> start;
    private Pair<Integer, Integer> finish;
    private Set<Mine> mines;
    private LinkedList<Pair<Integer, Integer>> path; //NOPMD

    /**
     * Constructor of {@link PerilousPathModel}.
     * 
     * @param difficultyLevel 
     *                  the current difficulty level
     */
    public PerilousPathModelImpl(final DifficultyLevel difficultyLevel) {
        Objects.requireNonNull(difficultyLevel);
        final PerilousPathDifficulty difficulty = new PerilousPathDifficultyBuilderImpl().setDifficultyLevel(difficultyLevel).build();
        this.score = new ScoreModelImpl(difficultyLevel, BASE_POINT);
        this.selected = new LinkedList<>();
        this.path = new LinkedList<>();
        this.size = difficulty.getSize();
        this.check(this.size);
        this.numMines = difficulty.getNumMines();
        this.check(this.numMines);
        this.mineFactory = new MineFactoryImpl();
    }

    private void check(final int value) {
        final Predicate<Integer> predicate = (i) -> i == 0;
        if (predicate.test(value)) {
            throw new IllegalStateException();
        }
    }

    private Mine getRandomMines() {
        final Mine mine = this.mineFactory.createSimpleMine(this.size);
        final Pair<Integer, Integer> minePosition = mine.getMinePosition();
        return this.path.contains(minePosition) || this.start.equals(minePosition) || this.finish.equals(minePosition)
                || this.mines.stream().anyMatch(m -> m.getMinePosition().equals(mine.getMinePosition())) ? this.getRandomMines() : mine;
    }

    private boolean couldMove(final Pair<Integer, Integer> position, final Pair<Integer, Integer> last) {
        Objects.requireNonNull(position);
        Objects.requireNonNull(last);
        return Math.abs(position.getX() - last.getX()) + Math.abs(position.getY() - last.getY()) == 1;
    }

    private boolean couldHit(final int row, final int col) {
        final Pair<Integer, Integer> position = new Pair<>(row, col);
        return this.selected.isEmpty() && position.equals(this.start) || !this.selected.contains(position)
                && !this.selected.isEmpty() && this.couldMove(position, this.selected.getLast());
    }

    private void initPath() {
        this.path = PathGameImpl.createRandomDirectionPath(this.size, this.numMines).getPathList();
        this.start = this.path.getFirst();
        this.finish = this.path.getLast();
    }

    private void initMines() {
        this.mines = new HashSet<>();
        for (int i = 0; i < this.numMines; i++) {
            this.mines.add(this.getRandomMines());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final int row, final int col) {
        if (this.couldHit(row, col)) {
            this.mines.stream().filter(m -> m.getMinePosition().equals(new Pair<>(row, col))).forEach(m -> m.setExploded());
            this.selected.add(new Pair<>(row, col));
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getStart() {
        return this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getFinish() {
        return this.finish;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Mine> getMines() {
        return this.mines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoint() {
        this.score.addPoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFinalScore() {
        return this.score.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDone() {
        return !this.selected.isEmpty() && this.selected.getLast().equals(this.finish);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFailed() {
        return this.mines.stream().anyMatch(m -> m.isExploded());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        this.initPath();
        this.initMines();
        this.selected.clear();
    }
}
