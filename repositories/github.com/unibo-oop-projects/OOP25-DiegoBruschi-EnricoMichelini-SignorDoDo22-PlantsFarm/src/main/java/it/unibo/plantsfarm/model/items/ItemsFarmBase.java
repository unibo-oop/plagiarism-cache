package it.unibo.plantsfarm.model.items;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.plant.Rarity;

/**
 * Base implementation of an ItemsFarm for the playertype Farmer.
 * The item gains experience when used and can be upgraded if it has enough experience.
 *
 */
public final class ItemsFarmBase implements ItemsFarm {

    /**
     * Current accumulated experience.
     */
    private int experience = StatsItemBase.EXPERIENCE_BEGIN;

    /**
     * Current level of the item.
     */
    private int level = StatsItemBase.LEVEL_BEGIN;

    /**
     * Tool type (hoe, watercan, fertilizer...).
     */
    private final Tooltype type;

    /**
     * Current rarity of the item.
     */
    private Rarity itemRarity = Rarity.COMMON;

    /**
     * Creates a base item of the given tool type.
     *
     * @param type the tool type
     * @throws NullPointerException if {@code type} is null
     */
    public ItemsFarmBase(final Tooltype type) {
        this.type = Objects.requireNonNull(type, "type cannot be null");
        updateRarity();
    }

    @Override
    public Tooltype getTooltype() {
        return this.type;
    }

    /**
     * Upgrades the item if possible.
     * For Upgrade need with experience to reach or sorpass the quantity experienceForLevel
     */
    @Override
    public void upgrade() {
        if (this.level >= StatsItemBase.LEVEL_MAX || this.experience < StatsItemBase.EXPERIENCE_FOR_UPGRADE) {
            return;
        }
        this.level++;
        this.experience -= StatsItemBase.EXPERIENCE_FOR_UPGRADE;
        updateRarity();
    }

    /**
     * Increases experience by a fixed amount when the item is used.
     */
    @Override
    public void useItem() {
        this.experience += StatsItemBase.EXPERIENCE_FOR_ACTION;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getMaxLevel() {
        return StatsItemBase.LEVEL_MAX;
    }

    @Override
    public int getMinLevel() {
        return StatsItemBase.LEVEL_BEGIN;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public int getExperienceForLevel() {
        return StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    }

    /**
     * Updates the rarity based on the provided level.
     */
    @Override
    public void updateRarity() {
        if (this.level < StatsItemBase.VAL_RARE) {
            this.itemRarity = Rarity.COMMON;
        } else if (this.level < StatsItemBase.VAL_EPIC) {
            this.itemRarity = Rarity.RARE;
        } else if (this.level < StatsItemBase.VAL_LEGENDARY) {
            this.itemRarity = Rarity.EPIC;
        } else {
            this.itemRarity = Rarity.LEGENDARY;
        }
    }

    @Override
    public Rarity getRarityItem() {
        return this.itemRarity;
    }

    @Override
    public int getLevelBaseOnRarity(final Rarity plantrarity) {
        return StatsItemBase.MAP_RARITY.get(plantrarity);

    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Constants for base items.
     */
    private static final class StatsItemBase {
        private static final int EXPERIENCE_FOR_ACTION = 5;
        private static final int EXPERIENCE_FOR_UPGRADE = 50;

        private static final int EXPERIENCE_BEGIN = 0;
        private static final int LEVEL_BEGIN = 0;
        private static final int LEVEL_MAX = 10;

        private static final int VAL_RARE = 3;
        private static final int VAL_EPIC = 6;
        private static final int VAL_LEGENDARY = 10;

        private static final Map<Rarity, Integer> MAP_RARITY = new LinkedHashMap<>(Map.of(Rarity.COMMON, 0, Rarity.RARE, 3,
            Rarity.EPIC, 6, Rarity.LEGENDARY, 10));
    }

}
