package model.memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.utilities.pawns.Pawn;
import utilities.Pair;

/**
 * 
 * @author : Giulio Bacchilega
 *
 */
public final class MementoImpl implements Memento {

    private static Memento memento;
    private Map<Integer, List<Pair<Pawn, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>>> mementoMap = new HashMap<>();
    private Map<Integer, Pair<Pawn, Pair<Integer, Integer>>> removedPawn = new HashMap<>();
    private Map<Integer, Pair<Pawn, Pair<Integer, Integer>>> addedPawn = new HashMap<>();
    private Integer counter;

    private MementoImpl() {
        this.counter = 0;
    }

    @Override
    public void setNewMovements(final List<Pair<Pawn, Pair<Integer, Integer>>> list) {
        List<Pair<Pawn, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> temp = new ArrayList<>();
        for (Pair<Pawn, Pair<Integer, Integer>> p : list) {
            temp.add(new Pair<>(p.getX(), new Pair<>(p.getY(), p.getX().getActualPosition())));
        }
        this.mementoMap.put(this.counter, temp);
        this.counter++;
    }

    @Override
    public void setNewRemoved(final Pawn removed, final Pair<Integer, Integer> position) {
        this.removedPawn.put(this.counter, new Pair<>(removed, position));
    }

    @Override
    public void setNewPawnAdded(final Pawn added, final Pair<Integer, Integer> position) {
        this.addedPawn.put(this.counter, new Pair<>(added, position));
    }

    @Override
    public List<Pair<Pawn, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> getLast() {
        List<Pair<Pawn, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> toReturn;
        this.counter--;
        toReturn = this.mementoMap.get(this.counter);
        this.mementoMap.remove(this.counter);
        return toReturn;
    }

    @Override
    public Pair<Pawn, Pair<Integer, Integer>> getRemovedPawn() {
        Pair<Pawn, Pair<Integer, Integer>> removed  = null; 
        if (this.removedPawn.containsKey(this.counter)) {
            removed = this.removedPawn.get(this.counter);
            this.removedPawn.remove(this.counter);
        }
        return removed;
    }

    @Override
    public Pair<Pawn, Pair<Integer, Integer>> getAddedPawn() {
        Pair<Pawn, Pair<Integer, Integer>> added  = null; 
        if (this.addedPawn.containsKey(this.counter)) {
            added = this.addedPawn.get(this.counter);
            this.addedPawn.remove(this.counter);
        }
        return added;
    }

    @Override
    public void resetMemento() {
        this.mementoMap = new HashMap<>();
        this.removedPawn = new HashMap<>();
        this.addedPawn = new HashMap<>();
        this.counter = 0;
    }

    /**
     * Return the Singleton of this class.
     * @return MementoImpl
     */
    public static synchronized Memento getLog() {
        if (memento == null) {
            memento = new MementoImpl();
        }
        return memento;
    }
}
