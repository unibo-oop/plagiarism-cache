package clashclass.battle.battlereport;

import clashclass.commons.BuildingTypeComponent;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;
import clashclass.gamestate.GameStateManager;
import clashclass.resources.ResourceManager;
import clashclass.resources.ResourceManagerImpl;


/**
 * Implementation of the BattleReportModel interface.
 * Stores and manages the battle report data.
 */
public class BattleReportModelImpl implements BattleReportModel {
    private static final int RESOURCES_MAX_VALUE = 1_000_000;
    private static final double HALF_VILLAGE_PERCENTAGE = 50.0;
    private double destructionPercentage;
    private ResourceManager stolenResources;
    private boolean townHallDestroyed;
    private final int totalBuildings;
    private int destroyedBuildings;
    private int troopCount;
    private GameStateManager gameStateManager;

    /**
     * Constructor initializing the battle report with default values.
     *
     * @param totalBuildings The total number of buildings in the village
     */
    public BattleReportModelImpl(final int totalBuildings) {
        this.destructionPercentage = 0.0;
        this.stolenResources = new ResourceManagerImpl(RESOURCES_MAX_VALUE);
        this.townHallDestroyed = false;
        this.totalBuildings = totalBuildings;
        this.destroyedBuildings = 0;
        this.troopCount = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDestructionPercentage() {
        return destructionPercentage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDestructionPercentage(final double percentage) {
        this.destructionPercentage = Math.min(100.0, Math.max(0.0, percentage));
    }

    /**
     * {@inheritDoc}
     * Increases the destruction percentage based on the number of buildings destroyed.
     */
    @Override
    public void increaseDestructionPercentage(final GameObject destroyedBuilding) {
        destroyedBuildings++;
        this.destructionPercentage = (double) destroyedBuildings / totalBuildings * 100.0;
        // Ensure percentage doesn't exceed 100%
        this.destructionPercentage = Math.min(100.0, this.destructionPercentage);

        destroyedBuilding.getComponentOfType(BuildingTypeComponent.class).ifPresent(type -> {
            if (type.getBuildingType().equals(VillageElementData.TOWN_HALL)) {
                this.setTownHallDestroyed(true);
            }
        });
    }

    /**
     * {@inheritDoc}
     * Calculates stars based on Clash of Clans rules: (the order of the star is not relevant for the first 2)
     * - 1 star: 50% destruction
     * - 2 stars: Town Hall destroyed
     * - 3 stars: 100% destruction
     */
    @Override
    public int getStars() {
        int stars = 0;

        // 1 star for 50% destruction
        if (destructionPercentage >= HALF_VILLAGE_PERCENTAGE) {
            stars++;
        }

        // 2 stars if Town Hall is destroyed
        if (townHallDestroyed) {
            stars++;
        }

        // 3 stars for 100% destruction
        if (destructionPercentage >= 100.0) {
            stars++;
        }

        return stars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceManager getStolenResources() {
        return stolenResources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStolenResources(final ResourceManager resources) {
        this.stolenResources = resources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStolenResources(final ResourceManager resources) {
        // this.stolenResources.addResources(resources);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTownHallDestroyed() {
        return townHallDestroyed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTownHallDestroyed(final boolean destroyed) {
        this.townHallDestroyed = destroyed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTroopCount() {
        return troopCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTroopCount(final int count) {
        this.troopCount = count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVictory() {
        return getStars() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }
}
