package it.unibo.modularcheckers.model.move;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Tree Factory Implementation (specific for Step Class).
 */
public class StepTreeFactoryImpl implements TreeFactory<Step> {

    private Tree<Step> treeToReturn;
    private final Queue<Tree<Step>> treesInserted;

    /**
     * Sole constructor.
     */
    public StepTreeFactoryImpl() {
        super();
        this.treesInserted = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addValue(final Step value) {
        if (this.treesInserted.isEmpty()) {
            throw new IllegalStateException("I don't know where to add the new child.");
        }
        final Tree<Step> treeToAdd = new TreeImpl<>(value);
        this.treesInserted.element().getChildren().add(treeToAdd);
        this.treesInserted.add(treeToAdd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Step removeValue() {
        final List<Tree<Step>> childrenOfLastTree = this.treesInserted.element().getChildren();
        if (childrenOfLastTree.isEmpty()) {
            throw new IllegalStateException("This tree have no children.");
        }
        return childrenOfLastTree.remove(childrenOfLastTree.size() - 1).getRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tree<Step> returnTree() {
        return treeToReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startNewTree(final Step root) {
        this.treeToReturn = new TreeImpl<Step>(root);
        this.treesInserted.clear();
        this.treesInserted.add(treeToReturn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tree<Step> goToNextChild() {
        return this.treesInserted.remove();
    }

}
