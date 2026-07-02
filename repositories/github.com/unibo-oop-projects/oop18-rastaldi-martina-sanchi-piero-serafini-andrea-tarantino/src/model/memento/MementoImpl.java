package model.memento;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * 
 * Rastaldi Martina.
 *
 */
public final class MementoImpl implements Memento {

    private List<Pair<Integer, Integer>> lastMovement = new ArrayList<>();

    @Override
    public Pair<Integer, Integer> getLastStep() {
        final Pair<Integer, Integer> temp = this.lastMovement.get(this.lastMovement.size() - 1);
        this.lastMovement.remove(this.lastMovement.size() - 1);
        return temp;
    }

    @Override
    public boolean isStepPresent() {
        return this.lastMovement.size() != 0;
    }

    @Override
    public void lastStep(final Pair<Integer, Integer> step) {
        this.lastMovement.add(step);
    }

    @Override
    public void resetMemento() {
        this.lastMovement = new ArrayList<>();
    }
}
