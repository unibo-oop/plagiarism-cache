package clashclass.elements.buildings;

import clashclass.ai.behaviourtree.BehaviourTreeDefenseBuildingFactoryImpl;
import clashclass.ai.behaviourtree.BehaviourTreeFactory;
import clashclass.ai.logic.CalculateDamageLogicFactory;
import clashclass.ai.logic.CalculateDamageLogicFactoryImpl;
import clashclass.battle.destruction.DestructionObservableImpl;
import clashclass.commons.BuildingTypeComponentImpl;
import clashclass.ecs.GameObject;
import clashclass.stats.DefenseBuildingBaseStatsComponent;

/**
 * Represents an implementation of BuildingFactory used for battle.
 */
public class BattleBuildingFactoryImpl extends AbstractBuildingFactory {
    private static final String PROGRESS_BAR_COLOR_EX = "#FF0000";
    private final BehaviourTreeFactory behaviourTreeFactory;
    private final CalculateDamageLogicFactory damageLogicFactory;

    /**
     * Constructs the battle building factory.
     */
    public BattleBuildingFactoryImpl() {
        this.behaviourTreeFactory = new BehaviourTreeDefenseBuildingFactoryImpl();
        this.damageLogicFactory = new CalculateDamageLogicFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalTownHallComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.TOWN_HALL))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.TOWN_HALL.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalWallComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.WALL))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.WALL.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalCannonComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.CANNON))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.CANNON.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl())
                .addComponent(new DefenseBuildingBaseStatsComponent(
                        BuildingHealthStat.CANNON.getHealth(),
                        DefenseBuildingBaseStats.CANNON.getDamage(),
                        DefenseBuildingBaseStats.CANNON.getAttackSpeed(),
                        DefenseBuildingBaseStats.CANNON.getAttackRange()))
                .addComponent(this.damageLogicFactory.createForCannon())
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherTowerComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ARCHER_TOWER))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.ARCHER_TOWER.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl())
                .addComponent(new DefenseBuildingBaseStatsComponent(
                        BuildingHealthStat.ARCHER_TOWER.getHealth(),
                        DefenseBuildingBaseStats.ARCHER_TOWER.getDamage(),
                        DefenseBuildingBaseStats.ARCHER_TOWER.getAttackSpeed(),
                        DefenseBuildingBaseStats.ARCHER_TOWER.getAttackRange()))
                .addComponent(this.damageLogicFactory.createForArcherTower())
                .addComponent(this.behaviourTreeFactory.create());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldStorageComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.GOLD_STORAGE))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.GOLD_STORAGE.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirStorageComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ELIXIR_STORAGE))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.ELIXIR_STORAGE.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldExtractorComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.GOLD_EXTRACTOR))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.GOLD_EXTRACTOR.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirExtractorComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ELIXIR_EXTRACTOR))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.ELIXIR_EXTRACTOR.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArmyCampComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.ARMY_CAMP))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.ARMY_CAMP.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarracksComponents(final GameObject.Builder builder) {
        return builder
                .addComponent(new BuildingTypeComponentImpl(VillageElementData.BARRACKS))
                .addComponent(this.getComponentFactory().createHealth(BuildingHealthStat.BARRACKS.getHealth()))
                .addComponent(this.getComponentFactory().createProgressBar(PROGRESS_BAR_COLOR_EX))
                .addComponent(new DestructionObservableImpl());
    }
}
