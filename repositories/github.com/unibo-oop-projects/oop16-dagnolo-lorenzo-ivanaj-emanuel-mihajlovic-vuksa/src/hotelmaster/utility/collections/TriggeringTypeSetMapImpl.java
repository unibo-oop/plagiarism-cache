package hotelmaster.utility.collections;

/**
 * Basic implementation of {@link TriggeringTypeSetMap} which relies on
 * {@link TypeSetMapImpl}.
 * 
 * @param <T>
 *            the supertype of the elements
 */
public class TriggeringTypeSetMapImpl<T> extends TypeSetMapImpl<T> implements TriggeringTypeSetMap<T> {

    private final TriggerManager<T> triggers;

    /**
     * Instances an empty TypeSetMap with no triggers.
     */
    protected TriggeringTypeSetMapImpl() {
        super();
        this.triggers = TriggerManager.create();
    }

    @Override
    public boolean add(final T element) {
        if (super.add(element)) {
            this.triggers.execute(TriggeringOperation.ADD, element);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked") // cast is safe, since if the element has
                                   // been removed it was also in the
                                   // collection, and therefore of type T (or a
                                   // subtype of T)
    @Override
    public boolean remove(final Object element) {
        if (super.remove(element)) {
            this.triggers.execute(TriggeringOperation.REMOVE, (T) element);
            return true;
        }
        return false;
    }

    @Override
    public void addTrigger(final Trigger<T> trigger) throws IllegalArgumentException {
        switch (trigger.getOperation()) {
        case ADD:
        case REMOVE:
            this.triggers.add(trigger);
            break;
        default:
            throw new IllegalArgumentException("Unsupported TriggeringOperation");
        }

    }

}
