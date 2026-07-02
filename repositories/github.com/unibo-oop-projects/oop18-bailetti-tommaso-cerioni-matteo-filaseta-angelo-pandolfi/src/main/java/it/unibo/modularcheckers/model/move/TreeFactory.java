package it.unibo.modularcheckers.model.move;

/**
 * A basic factory to create Trees of Steps.
 * 
 * @param <X> the generic type for the Tree factory construction
 */
public interface TreeFactory<X> extends BaseConstructionFactory<X> {

    /**
     * After calling this method, the steps added using the method addValue will be
     * children of the first tree inserted in which there were no insertions of
     * childrens yet.
     * 
     * @return the first child inserted in the last Tree inserted.
     */
    Tree<X> goToNextChild();

    /**
     * Start a new Tree.
     * 
     * @param root The root Value
     */
    void startNewTree(X root);

    /**
     * Get the move generated.
     * 
     * @return The move generated.
     */
    Tree<X> returnTree();

}
