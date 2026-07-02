package it.unibo.plantsfarm.model;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.AXE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import it.unibo.plantsfarm.model.items.ItemsExpert;
import it.unibo.plantsfarm.model.items.ItemsFarmBase;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.plant.Rarity;

class ItemsTest {

    @Test
    void testItemCreation() {
        final ItemsFarm hoe = new ItemsFarmBase(HOE);
        final ItemsFarm axe = new ItemsFarmBase(AXE);
        final ItemsFarm watercan = new ItemsFarmBase(WATERCAN);

        assertEquals(HOE, hoe.getTooltype());
        assertEquals(AXE, axe.getTooltype());
        assertEquals(WATERCAN, watercan.getTooltype());

        assertEquals(Rarity.COMMON, hoe.getRarityItem());
        assertEquals(Rarity.COMMON, axe.getRarityItem());
        assertEquals(Rarity.COMMON, watercan.getRarityItem());
    }

    @Test
    void testUseItemIncreaseExperience() {
        final ItemsFarm item = new ItemsFarmBase(WATERCAN);
        final int experienceBefore = item.getExperience();
        item.useItem();
        final int experienceAfter = item.getExperience();
        assertTrue(experienceAfter > experienceBefore);
    }

    @Test
    void testExpertItemIsLegendaryAndMaxLevel() {
        final ItemsFarm hoe = new ItemsExpert(HOE);
        final ItemsFarm axe = new ItemsExpert(AXE);
        final ItemsFarm water = new ItemsExpert(WATERCAN);

        assertEquals(HOE, hoe.getTooltype());
        assertEquals(AXE, axe.getTooltype());
        assertEquals(WATERCAN, water.getTooltype());

        assertEquals(Rarity.LEGENDARY, hoe.getRarityItem());
        assertEquals(Rarity.LEGENDARY, axe.getRarityItem());
        assertEquals(Rarity.LEGENDARY, water.getRarityItem());

        final int levelBefore = hoe.getLevel();
        hoe.upgrade();
        assertEquals(levelBefore, hoe.getLevel());
    }

    @Test
    void testUpgradeRare() {
        final ItemsFarm axe = new ItemsFarmBase(AXE);
        final int forRare = 30;
        for (int i = 0; i < forRare; i++) {
            axe.useItem();
            axe.upgrade();
        }

        assertEquals(axe.getRarityItem(), Rarity.RARE);
    }

    @Test
    void testUpgradeEpic() {
        final ItemsFarm axe = new ItemsFarmBase(AXE);
        final int forEpic = 90;
        for (int i = 0; i < forEpic; i++) {
            axe.useItem();
            axe.upgrade();
        }
        assertEquals(axe.getRarityItem(), Rarity.EPIC);
    }

    @Test
    void testUpgradeLegendary() {
        final int forLegendary = 200;
        final ItemsFarm axe = new ItemsFarmBase(AXE);
        for (int i = 0; i < forLegendary; i++) {
            axe.useItem();
            axe.upgrade();
        }
        assertEquals(axe.getRarityItem(), Rarity.LEGENDARY, "level:" + axe.getLevel());
    }

}
