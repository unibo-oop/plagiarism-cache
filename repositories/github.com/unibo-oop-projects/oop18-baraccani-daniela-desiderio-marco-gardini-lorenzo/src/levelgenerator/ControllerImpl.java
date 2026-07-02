package levelgenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.Pair;

/**
 * The Controller of the application implementation of {@link Controller}. It
 * manages touch events and records the changed state. It performs the JSON
 * writing.
 * 
 */
public class ControllerImpl implements Controller {

    private final int blockSize;
    private static final String PATH = "levels/";
    private final int size;
    private final Map<Pair<Integer, Integer>, States> entitiesStates;
    private States selected;

    /**
     * Constructor creates the grid initialized as default.
     * 
     * @param size      The size of the grid
     * @param blockSize The size of the blocks
     */
    public ControllerImpl(final int size, final int blockSize) {
        this.blockSize = this.checkPositive(blockSize);
        this.size = this.checkPositive(size);
        this.entitiesStates = new HashMap<>();
        this.selected = States.RED;
        this.fillMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectState(final States state) {
        this.selected = Objects.requireNonNull(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public States state(final Pair<Integer, Integer> coordinate) {
        return this.entitiesStates.get(Objects.requireNonNull(coordinate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Pair<Integer, Integer>, States> values() {
        return new HashMap<>(this.entitiesStates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void touch(final Pair<Integer, Integer> coordinate) {
        final Pair<Integer, Integer> cord = Objects.requireNonNull(coordinate);
        final States state = this.entitiesStates.get(cord);

        // Base case
        if (state == States.GRAY) {
            this.entitiesStates.put(cord, this.selected);
            return;
        }

        if (this.selected == States.RED && state != States.RED && state != States.GREEN) {
            this.entitiesStates.put(cord, States.RED);
            return;
        }

        // Case base hero
        if (this.selected == States.ORANGE) {
            this.entitiesStates.put(cord, state == States.ORANGE ? States.GRAY : States.ORANGE);
            return;
        }

        // Case base hero
        if (this.selected == States.BLACK) {
            this.entitiesStates.put(cord, state == States.BLACK ? States.GRAY : States.BLACK);
            return;
        }

        // Case Green with wall selected
        if (state == States.GREEN && this.selected == States.RED) {
            this.entitiesStates.put(cord, States.GRAY);
            return;
        }

        // Case Red with wall selected
        if (state == States.RED && this.selected == States.RED) {
            if (!this.check(cord)) {
                this.entitiesStates.put(cord, States.GREEN);
                return;
            }
            this.fill(cord);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateJSON(final String name) throws IOException {
        final List<Map.Entry<Pair<Integer, Integer>, States>> list = this.entitiesStates.entrySet().stream()
                .filter(e -> e.getValue() != States.GRAY).collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + name + ".json"))) {
            writer.write("[\n");

            for (int i = 0; i < list.size(); i++) {
                writer.write(i == 0 ? "\t{\n" : "{\n");
                writer.write("\t\t\"type\": \"" + list.get(i).getValue().getEntity() + "\",\n" + "\t\t\"x\" : "
                        + this.blockSize * list.get(i).getKey().getX() + ",\n" + "\t\t\"y\" : "
                        + this.blockSize * list.get(i).getKey().getY() + ",\n" + "\t\t\"width\" : " + this.blockSize
                        + ",\n" + "\t\t\"height\" : " + this.blockSize);
                writer.write(list.get(i).getValue() == States.ORANGE || list.get(i).getValue() == States.BLACK
                        ? ",\n\t\t\"direction\" : \"RIGHT\"\n"
                        : "\n");

                writer.write(i == list.size() - 1 ? "\t}\n" : "\t},");

            }

            writer.write("]\n");
        }
    }

    /*
     * It fills the map with default state.
     */
    private void fillMap() {
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < size; c++) {
                this.entitiesStates.put(new Pair<>(c, r), States.GRAY);
            }
        }
    }

    /*
     * It check if there are two greens in the same row or col.
     */
    private boolean check(final Pair<Integer, Integer> newGreen) {
        return this.entitiesStates.entrySet().stream().filter(e -> e.getValue() == States.GREEN
                && (e.getKey().getX() == newGreen.getX() || e.getKey().getY() == newGreen.getY())).count() > 0;
    }

    /*
     * If check returns true It fills the col or the row at the time of the two
     * greens.
     */
    private void fill(final Pair<Integer, Integer> newGreen) {
        final Optional<Pair<Integer, Integer>> pairX = this.entitiesStates.entrySet().stream()
                .filter(e -> e.getValue() == States.GREEN && e.getKey().getX() == newGreen.getX()).map(e -> e.getKey())
                .findFirst();

        final Optional<Pair<Integer, Integer>> pairY = this.entitiesStates.entrySet().stream()
                .filter(e -> e.getValue() == States.GREEN && e.getKey().getY() == newGreen.getY()).map(e -> e.getKey())
                .findFirst();

        if (pairX.isPresent()) {
            final int y = pairX.get().getY();
            IntStream.range(Math.min(y, newGreen.getY()), Math.max(y, newGreen.getY()) + 1).forEach(e -> {
                this.entitiesStates.put(new Pair<>(newGreen.getX(), e), States.RED);

            });

        }
        if (pairY.isPresent()) {
            final int x = pairY.get().getX();
            IntStream.range(Math.min(x, newGreen.getX()), Math.max(x, newGreen.getX() + 1)).forEach(e -> {
                this.entitiesStates.put(new Pair<>(e, newGreen.getY()), States.RED);
            });

        }

    }

    /*
     * Check if a integer is non negative
     */
    private int checkPositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
        return value;
    }

}
