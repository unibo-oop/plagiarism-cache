package hotelmaster.pricing;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.structure.ModifiableHotel;
import hotelmaster.utility.collections.Trigger;
import hotelmaster.utility.collections.TriggerManager;
import hotelmaster.utility.collections.TriggeringOperation;
import hotelmaster.utility.collections.TriggeringTypeSetMap;
import hotelmaster.utility.collections.TypeSetMap;

/**
 * A {@link PriceCollection} implementation which relies on a
 * {@link TypeSetMap}.
 */
public class PriceCollectionImpl implements PriceCollection {

    private final TriggeringTypeSetMap<PriceDescriber> prices;
    private final TriggerManager<PriceDescriber> ownTriggers;

    PriceCollectionImpl() {
        this.ownTriggers = TriggerManager.create();
        this.prices = TriggeringTypeSetMap.create();
        this.prices.addAll(ModifiableHotel.instance().getData().getRoomExtraUtilities().getAll());
        this.prices.addAll(ModifiableHotel.instance().getData().getRoomTypeUtilities().getAll());
        this.prices.addAll(ModifiableHotel.instance().getData().getStayExtraUtilities().getAll());
        this.prices.addAll(ModifiableHotel.instance().getData().getStayTypeUtilities().getAll());
        this.prices.addAll(ModifiableHotel.instance().getData().getPersonPriceUtilities().getAll());
        this.prices.addAll(ModifiableHotel.instance().getData().getSeasonUtilities().getAll());
        this.prices.addTrigger(Trigger.create(TriggeringOperation.ADD, (price) -> {
            ModifiableHotel.instance().getData().getPrices().create(price);
        }));
        this.prices.addTrigger(Trigger.create(TriggeringOperation.REMOVE, (price) -> {
            try {
                ModifiableHotel.instance().getData().getPrices().delete(price);
            } catch (PriceDescriberRemovalException e) {
                e.printStackTrace();
            }
        }));
        this.ownTriggers.add(Trigger.create(TriggeringOperation.UPDATE_ELEMENT, (price) -> {
            try {
                ModifiableHotel.instance().getData().getPrices().update(price);
            } catch (UnmodifiablePriceDescriberException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public boolean setPrice(final PriceDescriber price, final double newValue) {
        if (!prices.contains(price)) {
            return false;
        }
        price.setPrice(newValue);
        this.ownTriggers.execute(TriggeringOperation.UPDATE_ELEMENT, price);
        return true;
    }

    @Override
    public boolean add(final PriceDescriber element) {
        return this.prices.add(element);
    }

    @Override
    public boolean addAll(final Collection<? extends PriceDescriber> elements) {
        return this.prices.addAll(elements);
    }

    @Override
    public boolean remove(final Object element) {
        return this.prices.remove(element);
    }

    @Override
    public boolean removeAll(final Collection<?> elements) {
        boolean modified = false;
        for (final Object element : elements) {
            if (this.prices.remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeIf(final Predicate<? super PriceDescriber> predicate) {
        return this.prices.removeIf(predicate);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return this.prices.retainAll(c);
    }

    @Override
    public void clear() {
        this.prices.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return this.prices.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return this.prices.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return this.prices.isEmpty();
    }

    @Override
    public Iterator<PriceDescriber> iterator() {
        return this.prices.iterator();
    }

    @Override
    public int size() {
        return this.prices.size();
    }

    @Override
    public Object[] toArray() {
        return this.prices.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return this.prices.toArray(a);
    }

    @Override
    public <E extends PriceDescriber> Set<E> get(final Class<E> elementType) {
        return this.prices.get(elementType);
    }

    @Override
    public <E extends PriceDescriber> Set<E> getOfInstance(final E element) {
        return this.prices.getOfInstance(element);
    }

    @Override
    public Collection<PriceDescriber> getAll() {
        return this.prices.getAll();
    }

}
