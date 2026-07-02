package clashclass.elements.troops;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.ecs.GameObjectImpl;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.view.graphic.components.ImageRendererImpl;

import java.util.function.Function;

/**
 * Represents an Abstract Factory for the creation of the troops.
 */
public abstract class AbstractTroopFactory implements TroopFactory {
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
    public final GameObject createBarbarian(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalBarbarianComponents,
            builder -> builder
                    .addComponent(this.componentFactory.createTransform2D(position))
                    .addComponent(new ImageRendererImpl(TroopType.BARBARIAN.getName(), 1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameObject createArcher(final Vector2D position) {
        return this.createWithFactoryMethod(this::createAdditionalArcherComponents,
            builder -> builder
                    .addComponent(this.componentFactory.createTransform2D(position))
                    .addComponent(new ImageRendererImpl(TroopType.ARCHER.getName(), 1)));
    }

    /**
     * Completes the creation of a barbarian GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalBarbarianComponents(GameObject.Builder builder);

    /**
     * Completes the creation of an archer GameObject.
     *
     * @param builder the builder of the GameObject
     *
     * @return the builder after the addition of the new components
     */
    protected abstract GameObject.Builder createAdditionalArcherComponents(GameObject.Builder builder);
}
