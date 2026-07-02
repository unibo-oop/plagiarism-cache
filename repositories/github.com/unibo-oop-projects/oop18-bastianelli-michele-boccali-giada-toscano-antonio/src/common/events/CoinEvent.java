package common.events;

/**
 * Event generated when a coin is got by the player.
 */
public class CoinEvent implements Event {

    private final int coinValue;

    /**
     * @param coinValue the coin value to be added to the user score.
     */
    public CoinEvent(final int coinValue) {
        this.coinValue = coinValue;
    }

    /**
     * Get the coin value.
     * @return the coin value
     */
    public final int getCoinValue() {
        return coinValue;
    }
}
