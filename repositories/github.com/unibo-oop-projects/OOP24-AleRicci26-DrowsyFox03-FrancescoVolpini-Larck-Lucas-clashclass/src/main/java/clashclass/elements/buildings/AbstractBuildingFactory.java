package clashclass.elements.buildings;

import clashclass.commons.BuildingFlagsComponent;
import clashclass.commons.ConversionUtility;
import clashclass.commons.GridTileData2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.view.graphic.components.ImageRendererImpl;

import java.util.EnumSet;
import java.util.function.Function;

/**
 * Represents an Abstract Factory for the creation of the building.
 */
public abstract class AbstractBuildingFactory implements BuildingFactory {
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();

    /**
     * Gets the component factory.
     *
     * @return the component factory
     */
    protected final ComponentFactory getComponentFactory() {
        return this.componentFactory;
    }

    /**
     * Helper method to create a base GameObject and let the child class take care of GameObject specialization.
     *
     * @param additionalBuilder the additional builder defined in the child class
     * @param mainBuilder the main builder defined in this class
     *
     * @return the final GameObject
     */
    private GameObject createWithFactoryMethod(
            final Function<GameObject.Builder, GameObject.Builder> additionalBuilder,
            final Function<GameObject.Builder, GameObject.Builder> mainBuilder) {
        return additionalBuilder.apply(mainBuilder.apply(new GameObjectImpl.BuilderImpl())).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createTownHall(final VectorInt2D position) {
        final var buildingData = VillageElementData.TOWN_HALL;
        return this.createWithFactoryMethod(this::createAdditionalTownHallComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.of(BuildingFlag.TOWN_HALL)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createWall(final VectorInt2D position) {
        final var buildingData = VillageElementData.WALL;
        return this.createWithFactoryMethod(this::createAdditionalWallComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createCannon(final VectorInt2D position) {
        final var buildingData = VillageElementData.CANNON;
        return this.createWithFactoryMethod(this::createAdditionalCannonComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.of(BuildingFlag.DEFENSE)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createArcherTower(final VectorInt2D position) {
        final var buildingData = VillageElementData.ARCHER_TOWER;
        return this.createWithFactoryMethod(this::createAdditionalArcherTowerComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.of(BuildingFlag.DEFENSE)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createGoldStorage(final VectorInt2D position) {
        final var buildingData = VillageElementData.GOLD_STORAGE;
        return this.createWithFactoryMethod(this::createAdditionalGoldStorageComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createElixirStorage(final VectorInt2D position) {
        final var buildingData = VillageElementData.ELIXIR_STORAGE;
        return this.createWithFactoryMethod(this::createAdditionalElixirStorageComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createGoldExtractor(final VectorInt2D position) {
        final var buildingData = VillageElementData.GOLD_EXTRACTOR;
        return this.createWithFactoryMethod(this::createAdditionalGoldExtractorComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createElixirExtractor(final VectorInt2D position) {
        final var buildingData = VillageElementData.ELIXIR_EXTRACTOR;
        return this.createWithFactoryMethod(this::createAdditionalElixirExtractorComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createArmyCamp(final VectorInt2D position) {
        final var buildingData = VillageElementData.ARMY_CAMP;
        return this.createWithFactoryMethod(this::createAdditionalArmyCampComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createBarracks(final VectorInt2D position) {
        final var buildingData = VillageElementData.BARRACKS;
        return this.createWithFactoryMethod(this::createAdditionalBarracksComponents,
                builder -> builder
                        .addComponent(this.componentFactory.createTransform2D(ConversionUtility
                                .convertGridToWorldPosition(position, buildingData.getRowSpan(), buildingData.getColSpan())))
                        .addComponent(new GridTileData2D(position, buildingData.getRowSpan(), buildingData.getColSpan()))
                        .addComponent(new BuildingFlagsComponent(EnumSet.noneOf(BuildingFlag.class)))
                        .addComponent(new ImageRendererImpl(buildingData.getName(), 1)));
    }

    /**
     * Completes the creation of a town hall GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalTownHallComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a wall GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalWallComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a cannon GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalCannonComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an archer tower GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalArcherTowerComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a gold storage GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalGoldStorageComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an elixir storage GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalElixirStorageComponents(GameObject.Builder builder);

    /**
     * Completes the creation of a gold extractor GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalGoldExtractorComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an elixir extractor GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalElixirExtractorComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an army camp GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalArmyCampComponents(GameObject.Builder builder);

    /**
     * Completes the creation of the barracks GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalBarracksComponents(GameObject.Builder builder);
}
