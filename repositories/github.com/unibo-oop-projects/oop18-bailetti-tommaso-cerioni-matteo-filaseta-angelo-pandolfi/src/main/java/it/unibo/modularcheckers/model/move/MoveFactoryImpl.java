package it.unibo.modularcheckers.model.move;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple implementation for MoveFactory.
 */
public class MoveFactoryImpl implements MoveFactory {

    private final Set<Step> stepOrderedSet;

    /**
     * Constructor to initialize the set.
     */
    public MoveFactoryImpl() {
        this.stepOrderedSet = new LinkedHashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addValue(final Step step) {
        this.stepOrderedSet.add(step);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Step removeValue() {
        final Step lastElem = this.stepOrderedSet.stream().skip(stepOrderedSet.size() - 1).findFirst().get();
        this.stepOrderedSet.remove(lastElem);
        return lastElem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.stepOrderedSet.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move returnMove() {
        if (this.stepOrderedSet.size() == 0) {
            throw new NoSuchElementException();
        }
        return new MoveImpl(this.stepOrderedSet.stream().collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move fromTree(final Tree<Step> stepTree) {
        Tree<Step> navigableTree = stepTree;
        if (!stepOrderedSet.isEmpty()) {
            this.reset();
        }
        this.addValue(navigableTree.getRoot());
        do {
            if (navigableTree.getChildren().size() <= 1) {
                navigableTree = navigableTree.getChildren().stream().findAny().get();
                this.addValue(navigableTree.getRoot());
            } else {
                throw new IllegalArgumentException("The Tree passed is not valid.");
            }
        } while (!navigableTree.getChildren().isEmpty());
        final Move moveToReturn = this.returnMove();
        this.reset();
        return moveToReturn;
    }

}
