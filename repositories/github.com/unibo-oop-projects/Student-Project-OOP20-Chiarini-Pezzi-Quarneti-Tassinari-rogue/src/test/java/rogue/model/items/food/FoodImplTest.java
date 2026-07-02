package rogue.model.items.food;

import org.junit.Test;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class FoodImplTest {

    private static final int HUNGER_MAX = 100;
    private static final int REMOVE_AMOUNT_20 = -20;
    private static final int REMOVE_AMOUNT_10 = -10;

    private Player pl;

    @Test
    public void testUseMaxHunger() {
        pl = new PlayerFactoryImpl().create();
        final FoodImpl apple = new FoodImpl(FoodType.APPLE);
        final FoodImpl cake = new FoodImpl(FoodType.CAKE);
        final FoodImpl soup = new FoodImpl(FoodType.SOUP);
        final FoodImpl hamburger = new FoodImpl(FoodType.HAMBURGER);
        final FoodImpl cheese = new FoodImpl(FoodType.CHEESE);
        final FoodImpl steak = new FoodImpl(FoodType.STEAK);
        final FoodImpl bread = new FoodImpl(FoodType.BREAD);
        /*
         * Consume food with full hunger.
         * expecting false return.
         */
        pl.getLife().increaseFood(HUNGER_MAX - pl.getLife().getFood());
        assertEquals(HUNGER_MAX, pl.getLife().getFood());
        assertFalse(apple.use(pl));
        assertFalse(cake.use(pl));
        assertFalse(soup.use(pl));
        assertFalse(hamburger.use(pl));
        assertFalse(cheese.use(pl));
        assertFalse(steak.use(pl));
        assertFalse(bread.use(pl));
    }

    @Test
    public void testUseNormalHunger() {
        pl = new PlayerFactoryImpl().create();
        final FoodImpl testFood = new FoodImpl(FoodType.APPLE);
        /*
         * Consume food with not full hunger.
         * expecting true return and correctly updated hunger.
         */
        pl.getLife().increaseFood(REMOVE_AMOUNT_20);
        final int newAmount = pl.getLife().getFood();
        assertTrue(testFood.use(pl));
        assertEquals(newAmount + testFood.getStarvationValue(), pl.getLife().getFood());
    }

    @Test
    public void testUseMultipleFoods() {
        pl = new PlayerFactoryImpl().create();
        final FoodImpl apple = new FoodImpl(FoodType.APPLE);
        final FoodImpl cake = new FoodImpl(FoodType.CAKE);
        final FoodImpl steak = new FoodImpl(FoodType.STEAK);
        pl.getLife().increaseFood(REMOVE_AMOUNT_20 + REMOVE_AMOUNT_20);
        final int newAmount = pl.getLife().getFood();
        assertTrue(apple.use(pl));
        assertTrue(cake.use(pl));
        assertTrue(steak.use(pl));
        assertEquals(newAmount + apple.getStarvationValue() + cake.getStarvationValue() + steak.getStarvationValue(),
                pl.getLife().getFood());
    }

    @Test
    public void testOverMax() {
        pl = new PlayerFactoryImpl().create();
        final FoodImpl hamburger = new FoodImpl(FoodType.HAMBURGER);
        /*
         * Consume food that would exceed the MAX_FOOD_AMOUNT.
         * Remove 10, hamburger gives 12, expecting 50 anyway.
         */
       pl.getLife().increaseFood(HUNGER_MAX - pl.getLife().getFood() - 10);
       assertTrue(hamburger.use(pl));
       assertEquals(HUNGER_MAX, pl.getLife().getFood());
       pl.getLife().increaseFood(REMOVE_AMOUNT_10);
       assertTrue(hamburger.use(pl));
       assertEquals(HUNGER_MAX, pl.getLife().getFood());
    }
}
