package tmw.test.item;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Test;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.model.entities.MilkEntity;
import tmw.model.entities.MilkEntityImpl;
import tmw.model.inventory.Inventory;
import tmw.model.inventory.InventoryImpl;
import tmw.model.item.Item;
import tmw.model.item.TemporaryItem;
import tmw.model.item.consumable.HealingItem;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.equipment.Equipment;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.PowerUpType;
import tmw.model.item.powerup.SugarCanePowerUp;
import tmw.model.item.powerup.WhiteSugarPowerUp;

/**
 * Class to test the operation of the different item and inventory.
 */
public class TestItem {

    private MilkEntity milk = new MilkEntityImpl(new P2d(0, 0), new V2d(0, 0), new Dim2D(1000, 1000));
    private static final P2d P = new P2d(0, 0);
    private static final Dim2D D = new Dim2D(1000, 1000);

    /**
     * method to test {@link HealingItemType} and {@link HealingItem}.
     */
    @Test
    public void testConsumable() {
        final Item free = new HealingItem(HealingItemType.LACTOSE_FREE_MILK, P, D);
        final Item skimmed = new HealingItem(HealingItemType.SKIMMED_MILK, P, D);
        final Item whole = new HealingItem(HealingItemType.WHOLE_MILK, P, D);

        assertEquals(HealingItemType.LACTOSE_FREE_MILK.getDescription(), free.getDescription());
        assertEquals(HealingItemType.LACTOSE_FREE_MILK.getName(), free.getName());
        assertNotEquals(HealingItemType.SKIMMED_MILK.getName(), free.getName());
        milk.takeDamage(milk.getMaxHp() / 2);
        free.useItem(milk);
        assertNotEquals(milk.getMaxHp(), milk.getCurrentHealth());
        milk = new MilkEntityImpl(new P2d(0, 0), new V2d(0, 0), new Dim2D(1000, 1000));
        milk.takeDamage(milk.getMaxHp() - 1);
        skimmed.useItem(milk);
        assertNotEquals(milk.getMaxHp(), milk.getCurrentHealth());
        milk = new MilkEntityImpl(new P2d(0, 0), new V2d(0, 0), new Dim2D(1000, 1000));
        milk.takeDamage(milk.getMaxHp() / 2);
        whole.useItem(milk);
        assertEquals(milk.getMaxHp(), milk.getCurrentHealth());
    }

    /**
     * method to test {@link EquipmentType} and {@link Equipment}.
     */
    @Test
    public void testEquipment() {
        final TemporaryItem chocolate = new Equipment(EquipmentType.CHOCOLATE, P, D);
        final TemporaryItem coffee = new Equipment(EquipmentType.COFFEE, P, D);

        assertEquals(EquipmentType.CHOCOLATE.getName(), chocolate.getName());
        assertEquals(EquipmentType.CHOCOLATE.getDuration(), chocolate.getDuration());
        chocolate.useItem(milk);
        assertNotEquals(milk.getDefaultDamage(), milk.getDamage());
        coffee.useItem(milk);
        assertNotEquals(milk.getDefaultDamage(), milk.getDamage());
    }

    /**
     * method to test {@link PowerUpType}, {@link SugarCanePowerUp} and
     * {@link WhiteSugarPowerUp}.
     */
    @Test
    public void testPowerUP() {
        final TemporaryItem cane = new SugarCanePowerUp(P, D);
        final TemporaryItem white = new WhiteSugarPowerUp(P, D);

        assertEquals(PowerUpType.SUGAR_CANE.getName(), cane.getName());
        cane.useItem(milk);
        assertNotEquals(milk.getDefaultSpeed(), milk.getSpeed());
        white.useItem(milk);
        assertNotEquals(milk.getDefaultTimeForReload(), milk.getTimeForReload());
    }

    /**
     * method to test {@link InventoryImpl} adding and removing item.
     */
    @Test
    public void testInventory() {
        final Inventory inv = new InventoryImpl();

        inv.addItem(new HealingItem(HealingItemType.SKIMMED_MILK, P, D));
        inv.removeItem(0);
        inv.addItem(new HealingItem(HealingItemType.SKIMMED_MILK, P, D));
        assertFalse(inv.isFull());
        inv.addItem(new Equipment(EquipmentType.CHOCOLATE, P, D));
        inv.addItem(new WhiteSugarPowerUp(P, D));
        inv.removeItem(0);
        inv.addItem(new SugarCanePowerUp(P, D));
        inv.addItem(new Equipment(EquipmentType.COFFEE, P, D));
        assertFalse(inv.isFull());
        inv.addItem(new HealingItem(HealingItemType.LACTOSE_FREE_MILK, P, D));
        assertTrue(inv.isFull());
        inv.removeItem(0);
        assertFalse(inv.isFull());
        inv.addItem(new Equipment(EquipmentType.CHOCOLATE, P, D));
        assertTrue(inv.isFull());
    }
}
