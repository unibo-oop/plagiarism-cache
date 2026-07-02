package it.unibo.modularcheckers.model.move;

import java.util.List;

/**
 * Represents a generic tree.
 * 
 * @param <X> the generic class of the nodes.
 */
public interface Tree<X> {

    /**
     * Get the root of the tree.
     * 
     * @return the root of the tree.
     */
    X getRoot();

    /**
     * Get all the children of the tree.
     * 
     * @return a list containing the children of the tree.
     */
    List<Tree<X>> getChildren();

    /**
     * @return a List containing all the nodes.
     */
    List<X> getAllValues();

    /**
     * @return a List containing all the nodes as Tree.
     */
    List<Tree<X>> getAllNodes();

    /**
     * Return all the children of the first level of the tree.
     * 
     * @return a list containing only the first level of the tree.
     */
    List<X> getFirstChildren();

    /**
     * 
     * @return the height of the Tree.
     */
    int height();

    /**
     * Trim a tree, by leaving only branches of at least height maxHeight.
     * 
     * @param maxHeight height of the final tree.
     * @return true if remains at least one branch of height maxHeight or greater.
     */
    boolean balanceToHeight(int maxHeight);

}
