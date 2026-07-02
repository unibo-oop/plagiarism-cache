package tmw.controller.item;

import tmw.controller.world.WorldController;
import tmw.model.item.Item;

/**
 * Class that handle the items.
 *
 * @param <K> type of item
 */
public class ItemControllerImpl<K extends Item> extends AbstractItemController implements ItemController<K> {

    private final K item;

    /**
     * Public constructor.
     * @param item item
     * @param worldController worldController
     */
    public ItemControllerImpl(final K item, final WorldController worldController) {
        super(worldController);
        this.item = item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K getItem() {
        return this.item;
    }

}
