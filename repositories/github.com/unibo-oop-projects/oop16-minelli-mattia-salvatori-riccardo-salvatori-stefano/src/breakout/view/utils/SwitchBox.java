package breakout.view.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A node that allows you to choose objects with arrows with a cyclic behaviour.
 * Arrows are two images that pass in the constructor.
 * 
 * @param <T>
 *            the class of the node to switch
 */
public final class SwitchBox<T extends Node> extends Group {

    private final ImageView leftArrow = new ImageView();
    private final List<T> toSwitch;
    private final ImageView rightArrow = new ImageView();
    private T current;
    private final HBox box = new HBox();

    /**
     * Constructor for the node.
     * 
     * @param leftArrow
     *            the image for the left arrow
     * @param toSwitch
     *            the node that you want to switch
     * @param rightArrow
     *            the image for the right arrow
     */
    public SwitchBox(final Image leftArrow, final List<T> toSwitch, final Image rightArrow) {
        super();
        this.leftArrow.setImage(leftArrow);
        this.toSwitch = new ArrayList<>(toSwitch);
        this.rightArrow.setImage(rightArrow);
        this.current = toSwitch.get(0);
        this.leftArrow.setOnMousePressed(e -> {
            this.showPreviousItem();
        });
        this.rightArrow.setOnMousePressed(e -> {
            this.showNextItem();
        });
        this.leftArrow.setFitHeight(leftArrow.getHeight());
        this.leftArrow.setFitWidth(leftArrow.getWidth());
        this.rightArrow.setFitHeight(rightArrow.getHeight());
        this.rightArrow.setFitWidth(rightArrow.getWidth());
        this.box.getChildren().addAll(this.leftArrow, current, this.rightArrow);
        this.getChildren().add(this.box);
    }

    /**
     * Set Node spacing in the group.
     * 
     * @param boxInsets
     *            the value of the spacing
     */
    public void setSpacing(final double boxInsets) {
        this.box.setSpacing(boxInsets);
    }

    /**
     * @return The left arrow of the choose box
     */
    public ImageView getLeftArrow() {
        return this.leftArrow;
    }

    /**
     * @return The right arrow of the choose box
     */
    public ImageView getRightArrow() {
        return this.rightArrow;
    }

    /**
     * @return The items in the list of possible choices
     */
    public List<T> getToSwitch() {
        return toSwitch;
    }

    /**
     * @return the box that contains left arrow, the possible choices and the
     *         right arrow
     */
    public HBox getBox() {
        return box;
    }

    /**
     * @return The current element.
     */
    public T getCurrent() {
        return this.current;
    }

    /**
     * Switch the current item with the next one in the list. If there isn't it
     * starts from the beginning. (This method is used by default when the
     * {@link #rightArrow} is pressed so it should be called only to override
     * setOnMousePressed of {@link #rightArrow})
     */
    public void showNextItem() {
        final int nextIndex = this.toSwitch.indexOf(current) + 1 < this.toSwitch.size()
                ? this.toSwitch.indexOf(current) + 1
                : 0;
        this.box.getChildren().replaceAll(n -> n.equals(current) ? this.toSwitch.get(nextIndex) : n);
        current = this.toSwitch.get(nextIndex);

    }

    /**
     * Switch the current item with the previous one in the list. If there isn't
     * it goes to the last one.. (This method is used by default when the
     * {@link #leftArrow} is pressed so it should be called only to override
     * setOnMousePressed of {@link #lefttArrow})
     */
    public void showPreviousItem() {
        final int nextIndex = this.toSwitch.indexOf(current) - 1 >= 0
                ? this.toSwitch.indexOf(current) - 1
                : this.toSwitch.size() - 1;
        this.box.getChildren().replaceAll(n -> n.equals(current) ? this.toSwitch.get(nextIndex) : n);
        current = this.toSwitch.get(nextIndex);

    }

}
