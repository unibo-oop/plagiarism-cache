package clashclass.elements.buildings;

import clashclass.commons.BuildingTypeComponentImpl;
import clashclass.ecs.GameObject;

/**
 * Represents an implementation of BuildingFactory used for player village (no battle behaviours).
 */
public class PlayerBuildingFactoryImpl extends AbstractBuildingFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalTownHallComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.TOWN_HALL));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalWallComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.WALL));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalCannonComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.CANNON));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherTowerComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.ARCHER_TOWER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldStorageComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.GOLD_STORAGE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirStorageComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.ELIXIR_STORAGE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalGoldExtractorComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.GOLD_EXTRACTOR));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalElixirExtractorComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.ELIXIR_EXTRACTOR));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArmyCampComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.ARMY_CAMP));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarracksComponents(final GameObject.Builder builder) {
        return builder.addComponent(new BuildingTypeComponentImpl(VillageElementData.BARRACKS));
    }
}
