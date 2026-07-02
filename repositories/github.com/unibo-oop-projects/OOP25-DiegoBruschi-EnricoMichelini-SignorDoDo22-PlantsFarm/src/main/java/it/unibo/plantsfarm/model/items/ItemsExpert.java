package it.unibo.plantsfarm.model.items;

import java.util.Objects;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.plant.Rarity;

/**
 * Implementation of an expert item: it starts at maximum level and legendary rarity.
 */
public final class ItemsExpert implements ItemsFarm {

    private int experience = StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    private int level = StatsItemBase.LEVEL_MAX;
    private final Tooltype type;
    private Rarity itemRarity = Rarity.LEGENDARY;

    /**
     * Creates an expert item of the given tool type.
     *
     * @param type the tool type
     * @throws NullPointerException type is null
     */
    public ItemsExpert(final Tooltype type) {
        this.type = Objects.requireNonNull(type, "type cannot be null");
        updateRarity();
    }

    @Override
    public Tooltype getTooltype() {
        return this.type;
    }

    @Override
    public void upgrade() {
        if (this.level >= StatsItemBase.LEVEL_MAX || this.experience < StatsItemBase.EXPERIENCE_FOR_UPGRADE) {
            return;
        }
        this.level++;
        this.experience -= StatsItemBase.EXPERIENCE_FOR_UPGRADE;
        updateRarity();
    }

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
        return StatsItemBase.LEVEL_MAX;
    }

    @Override
    public Rarity getRarityItem() {
        return this.itemRarity;
    }

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
    public int getExperience() {
        return this.experience;
    }

    @Override
    public int getExperienceForLevel() {
        return StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    @Override
    public int getLevelBaseOnRarity(final Rarity plantrarity) {
        return StatsItemBase.GLOBAL_VAL_PLANT;
    }

    /**
     * Item constants for expert items.
     */
    private static final class StatsItemBase {
        private static final int EXPERIENCE_FOR_ACTION = 5;
        private static final int EXPERIENCE_FOR_UPGRADE = 30;
        private static final int GLOBAL_VAL_PLANT = 0;
        private static final int LEVEL_MAX = 10;
        private static final int VAL_RARE = 3;
        private static final int VAL_EPIC = 6;
        private static final int VAL_LEGENDARY = 10;
    }
}
