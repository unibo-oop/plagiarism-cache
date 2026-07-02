package tmw.controller.item;

import tmw.controller.world.WorldController;
import tmw.model.item.Item;

/**
 * Abstract controller for items.
 *
 */
public abstract class AbstractItemController {

    private final WorldController worldController;

    /**
     * Public constructor.
     * 
     * @param worldController world controller
     */
    public AbstractItemController(final WorldController worldController) {
        this.worldController = worldController;
    }

    /**
     * draw the item.
     */
    public void draw() {
        this.worldController.getView().render(this);
    }

    /**
     * Getter for the items.
     * 
     * @return an item
     * 
     */
    public abstract Item getItem();
}
