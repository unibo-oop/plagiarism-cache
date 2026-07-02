package shop;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.shop.MysteryBox;
import model.shop.MysteryBoxImpl;
import model.shop.ShopItem;
import model.shop.ShopItemImpl;
import model.shop.ShopModel;
import model.shop.ShopModelImpl;
import model.statistic.Statistics;
import model.statistic.StatisticsImpl;

class ShopTest {

    private ShopModel shop; 
    private Statistics stats; 
    private MysteryBox box; 
    private ShopItem item; 

    private static final int MONEY1 = 300;
    private static final int MONEY2 = 50; 
    private static final int PRICE = 100; 
    private static final String NAME = "Skin"; 

    @BeforeEach
    public final void prepare() {
        shop = new ShopModelImpl(stats); 
        stats = new StatisticsImpl(); 
        box = new MysteryBoxImpl(); 
        item = new ShopItemImpl(NAME, PRICE); 
    }

    @Test
    void testCheckMystery() { 
        assertTrue(shop.checkMystery(box, MONEY1));
        assertFalse(shop.checkMystery(box, MONEY2)); 
    }

    @Test
    void testCheckPayment() {
        assertTrue(shop.checkPayment(item, MONEY1));
        assertFalse(shop.checkPayment(item, MONEY2));
    }
}
