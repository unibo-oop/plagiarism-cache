package model.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.chessboard.Chessboard;
import utilities.CoordinateOperations;
import utilities.Directions;
import utilities.Pair;

/**
 * @author : Giulio Bacchilega
 */
public final class Movement implements MovementLogic {

    private static final Chessboard C = Chessboard.getLog();
    private static final CoordinateOperations CO = CoordinateOperations.getLog();
    private static Movement movement;
    private boolean last = false;
    private Movement() { }

    @Override
    public Set<Pair<Integer, Integer>> getMoves(final List<Directions> dir, final Optional<Integer> distance, final Pair<Integer, Integer> actual) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        if (dir.contains(Directions.knight_moves)) {
            return this.getKnightPossibleMoves(actual);
        } else {
            List<ThreadDirection> threads = new ArrayList<>();
            dir.stream().forEach(x-> Directions.get(x).stream().forEach(y-> threads.add(new ThreadDirection(y, distance, actual))));
            threads.forEach(x-> {
                x.start();
                try {
                    x.join();
                } catch (Exception e) { }
                set.addAll(x.getSet());
            });
        }
        return set;
    }

    @Override
    public Set<Pair<Integer, Integer>> getPath(final Directions singleD, final Optional<Integer> distance, final Pair<Integer, Integer> actual) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        int range;
        Optional<Pair<Integer, Integer>> temp;
        this.last = false;
        temp = Optional.of(actual);
        if (distance.isPresent()) {
            range = distance.get();
            while (range > 0) {
                temp = this.getNextCoordinate(singleD, temp.get());
                if (temp.isPresent()) {
                    set.add(temp.get());
                }
                range--;
            }
        } else {
            while (temp.isPresent()) {
                temp = this.getNextCoordinate(singleD, temp.get());
                if (temp.isPresent()) {
                    set.add(temp.get());
                }
            }
        }
        return set;
    }

    private Set<Pair<Integer, Integer>> getKnightPossibleMoves(final Pair<Integer, Integer> actual) {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        int x = actual.getX();
        int y = actual.getY();
        moves.addAll(Arrays.asList(new Pair<>(x - 2, y + 1), new Pair<>(x - 1, y + 2), new Pair<>(x + 1, y + 2), new Pair<>(x + 2, y + 1),
                                   new Pair<>(x - 2, y - 1), new Pair<>(x - 1, y - 2), new Pair<>(x + 1, y - 2), new Pair<>(x + 2, y - 1)));
        moves = moves.stream().filter(e-> Chessboard.getLog().isIncluded(e)).collect(Collectors.toSet());
        return moves;
    }

    private Optional<Pair<Integer, Integer>> getNextCoordinate(final Directions dir, final Pair<Integer, Integer> actual) {
        Pair<Integer, Integer> next = CO.getNext(dir, actual);
        if (this.isAccessible(next)) {
            if (next != actual) {
                return Optional.of(next);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isAccessible(final Pair<Integer, Integer> pair) {
        if (C.isIncluded(pair)) {
            if (!C.isEmpty(pair) && !this.last) {
                this.last = true;
                return (this.last);
            }
            return !this.last;
        }
        return false;
    }

    private class ThreadDirection extends Thread {
        private Directions dir;
        private Optional<Integer> distanceToCover;
        private Pair<Integer, Integer> origin;
        private Set<Pair<Integer, Integer>> set = new HashSet<>();

        ThreadDirection(final Directions d, final Optional<Integer> distance, final Pair<Integer, Integer> origin) {
            this.dir = d;
            this.distanceToCover = distance;
            this.origin = origin;
        }

        public void run() {
            set = movement.getPath(this.dir, this.distanceToCover, this.origin);
        }

        public Set<Pair<Integer, Integer>> getSet() {
            return this.set;
        }
    }

    /**
     * @return the singleton of this class
     */
    public static Movement getLog() {
        if (movement == null) {
             movement = new Movement();
        }
        return movement;
    }
}
