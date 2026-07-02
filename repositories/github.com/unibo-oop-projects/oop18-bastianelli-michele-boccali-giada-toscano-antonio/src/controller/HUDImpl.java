package controller;

import common.CommonStrings;

/**
 * HUD implementation.
 */
public class HUDImpl implements HUD {

    private int coins;
    private int maxHeight;

    public HUDImpl() {
    }

    @Override
    public void init(final int maxHeight, final int coins) {
        this.maxHeight = maxHeight;
        this.coins = coins;
    }
    
    @Override
    public void addCoins(final int coins) {
        this.coins += coins;
    }

    @Override
    public void removeCoins(final int coins) {
        this.coins -= coins;
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

	@Override
	public void setMaxHeight(final int maxHeight) {
		if (maxHeight + CommonStrings.WINDOW_HEIGHT > this.maxHeight) {
			this.maxHeight = maxHeight + CommonStrings.WINDOW_HEIGHT;
		}
	}

}
