package it.unibo.modularcheckers.model.move;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic implementation of Tree.
 * 
 * @param <X> the generic value of the root.
 */
public class TreeImpl<X> implements Tree<X> {

    private final X root;
    private final List<Tree<X>> children;

    /**
     * Create a Tree and its children.
     * 
     * @param root     the value of the root.
     * @param children all the sub-tree.
     */
    public TreeImpl(final X root, final List<Tree<X>> children) {
        this.root = root;
        this.children = children;
    }

    /**
     * Create a Tree without children.
     * 
     * @param root the value of the root.
     */
    public TreeImpl(final X root) {
        this.root = root;
        this.children = new ArrayList<Tree<X>>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tree<X>> getChildren() {
        return children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tree<X>> getAllNodes() {
        List<Tree<X>> nodes = new ArrayList<>();
        nodes.add(this);
        this.getChildren().stream().forEach(n -> {
            nodes.addAll(n.getAllNodes());
        });
        return nodes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<X> getAllValues() {
        return getAllNodes().stream().map(t -> t.getRoot()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<X> getFirstChildren() {
        return this.getChildren().stream().map(t -> t.getRoot()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int height() {
        if (this.getChildren().size() > 0) {
            return this.getChildren().stream().mapToInt(t -> t.height() + 1).max().getAsInt();
        } else {
            return 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean balanceToHeight(final int maxHeight) {
        for (int i = 0; i < getChildren().size(); i++) {
            if (!getChildren().get(i).balanceToHeight(maxHeight - 1)) {
                this.getChildren().remove(i);
                i--;
            }
        }
        if (this.getChildren().size() > 0) {
            return true;
        }
        return maxHeight <= 1;
    }

}
