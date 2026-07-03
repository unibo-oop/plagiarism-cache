package hotelmaster.pricing;

/**
 * A {@link PriceDescriber} for rooms.
 */
public abstract class RoomPriceDescriber extends PriceDescriber {

    RoomPriceDescriber(final String description, final double price) {
        super(description, price);
    }

}
