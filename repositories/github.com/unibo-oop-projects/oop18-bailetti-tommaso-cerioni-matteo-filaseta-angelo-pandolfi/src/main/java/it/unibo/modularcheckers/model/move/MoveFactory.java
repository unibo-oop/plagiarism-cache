package it.unibo.modularcheckers.model.move;

/**
 * Factory to create moves.
 */
public interface MoveFactory extends BaseConstructionFactory<Step> {

    /**
     * Return the move built.
     * 
     * @return the move created.
     */
    Move returnMove();

    /**
     * Create a Move from a Tree. Note that every node of the tree can't have more
     * than one son.
     * 
     * @param stepTree the not-balanced-at-all tree.
     * @return The move generated.
     */
    Move fromTree(Tree<Step> stepTree);

    /**
     * Delete all the values.
     */
    void reset();
}
