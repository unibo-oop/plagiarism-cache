package hotelmaster.database.admin;

import java.util.HashMap;
import java.util.Map;

import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.pricing.SeasonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
/**
 * Price describer operations (Administrator level).
 */
public class PriceUtilitiesImpl implements PriceUtilities {

    private static final Map<Class<? extends PriceDescriber>, BasicPriceDescriberOperation> LOGIC = new HashMap<>();

    /**
     * 
     */
    public PriceUtilitiesImpl() {
        if (LOGIC.isEmpty()) {
            LOGIC.put(RoomTypePriceDescriber.class, new RoomType());
            LOGIC.put(RoomExtraPriceDescriber.class, new RoomExtra());
            LOGIC.put(SeasonPriceDescriber.class, new Season());
            LOGIC.put(StayTypePriceDescriber.class, new StayType());
            LOGIC.put(StayExtraPriceDescriber.class, new StayExtra());
        }
    }

    @Override
    public void create(final PriceDescriber price) {
        if (LOGIC.containsKey(price.getClass())) {
            LOGIC.get(price.getClass()).create(price);
        }
    }

    @Override
    public void delete(final PriceDescriber price) throws PriceDescriberRemovalException {
        if (LOGIC.containsKey(price.getClass())) {
            LOGIC.get(price.getClass()).removeType(price);
        }

    }

    @Override
    public void update(final PriceDescriber price) throws UnmodifiablePriceDescriberException {
        if (LOGIC.containsKey(price.getClass())) {
            LOGIC.get(price.getClass()).modifyPrice(price);
        }

    }

}
