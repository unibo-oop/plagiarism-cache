package controller;

import java.io.IOException;

import model.shop.ShopModel;
import model.shop.ShopModelImpl;
import model.statistic.Statistics;
import view.ShopView;

public class ShopControllerImpl implements ShopController {

    private final ShopView shopView;
    private final ShopModel shopModel;
    private int imageIndex;

    /**
     * @param shopView the shop menu. 
     * @param statistics the statistics of the game. 
     */
    public ShopControllerImpl(final ShopView shopView, final Statistics statistics) {
        this.shopView = shopView;
        this.shopModel = new ShopModelImpl(statistics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.shopView.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopModel getShopModel() {
        return this.shopModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int increaseSkinCounter() {
        imageIndex++; 
        if (imageIndex >= this.shopModel.getItems().size()) {
            imageIndex = 0; 
        }
        return imageIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int decreaseSkinCounter() {
        imageIndex--; 
        if (imageIndex < 0) {
            imageIndex = this.shopModel.getItems().size() - 1; 
        }
        return imageIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalCoins() {
        return this.shopModel.getTotalCoins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        this.shopModel.saveShopItem();
        this.shopModel.writeSkinOnFile();
    }

}
