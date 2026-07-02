package model.container;

import model.Pair;

/**
 * 
 * 
 *
 */
public class Box extends Pair<Item, Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1632283344865199115L;

    /**
     * 
     * @param newItem
     *            first element
     * 
     */
    public Box(final Item newItem) {
        super(newItem);
        this.setSecond(1);
    }

    /**
     * 
     */
    public void increaseQuantity() {
        this.setSecond(this.getSecond() + 1);
    }

    /**
     * 
     */
    public void decreaseQuantity() {
        this.setSecond(this.getSecond() - 1);
    }

    @Override
    public String toString() {
        return "Box [Item=" + this.getFirst() + ", quantity=" + this.getSecond() + "]";
    }
}
