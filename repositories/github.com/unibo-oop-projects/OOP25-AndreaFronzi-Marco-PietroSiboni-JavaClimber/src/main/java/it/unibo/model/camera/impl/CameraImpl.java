package it.unibo.model.camera.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.api.Camera;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.GameObject;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldconstructor.worldgenerator.api.WorldConstructor;

/**
 * Implementation of {@link Camera} and {@link AltitudeObserver} interface.
 */
public class CameraImpl implements Camera, AltitudeObserver {

    private final double width;
    private final double height;
    private final World world;
    private final WorldConstructor worldConstructor;
    private final double y;

    /**
     * @param width  width of the viewport
     * @param height height of the viewport
     * @param world  the world containing Real and Upper worlds
     * @param wc     the world constructor used to generate new upper world
     */
    public CameraImpl(final double width, final double height, final World world, final WorldConstructor wc) {
        this.worldConstructor = wc;
        this.width = width;
        this.height = height;
        this.world = world;
        this.y = 0;
    }

    /**
     * {@inheritDoc}
     * This method also makes:
     * shift world objects vertically;
     * transferring objects from upper to real world;
     * removes objects that have fallen under the screen;
     * trigger new world generation if the camera moved enough distance.
     */
    @Override
    public void update(final double delta) {
        final Alien alien = world.getRealWorld().getAlien();
        alien.setPosition(new Vector2dImpl(alien.getPosX(), alien.getPosY() + delta));

        List.of(
                world.getRealWorld().getStaticPlatforms(),
                world.getRealWorld().getOnTouchPlatforms(),
                world.getRealWorld().getMovingPlatforms(),
                world.getRealWorld().getMoneys(),
                world.getRealWorld().getGadgets(),
                world.getRealWorld().getMonsters(),
                world.getUpperWorld().getStaticPlatforms(),
                world.getUpperWorld().getOnTouchPlatforms(),
                world.getUpperWorld().getMovingPlatforms(),
                world.getUpperWorld().getMoneys(),
                world.getUpperWorld().getGadgets(),
                world.getUpperWorld().getMonsters()).forEach(list -> moveWorldList(list, delta));

        transferGameObj();
        cleanRealWorld();

        worldConstructor.fillWorld();
    }

    /**
     * Shift a list of game objects vertically.
     * 
     * @param objects the list of objects to move
     * @param delta   the delta to move them on the y
     */
    private void moveWorldList(final List<? extends GameObject> objects, final double delta) {
        for (final GameObject obj : objects) {
            final double newY = obj.getPosY() + delta;
            obj.setPosition(new Vector2dImpl(obj.getPosX(), newY));
        }
    }

    /**
     * Transfers objects from the UpperWorld to the RealWorld when objects y
     * positions crosses a specified limit.
     */
    private void transferGameObj() {
        final double dynamicMargin = this.height / 6.0;
        final double viewLimit = this.y - dynamicMargin;

        final var upper = world.getUpperWorld();
        final var real = world.getRealWorld();

        transferCategory(upper::getStaticPlatforms, upper::removeFirstStaticPlatform, real::addStaticPlatform,
                viewLimit);
        transferCategory(upper::getMovingPlatforms, upper::removeFirstMovingPlatform, real::addMovingPlatform,
                viewLimit);
        transferCategory(upper::getOnTouchPlatforms, upper::removeFirstOnTouchPlatform, real::addOnTouchPlatform,
                viewLimit);
        transferCategory(upper::getGadgets, upper::removeFirstGadget, real::addGadget, viewLimit);
        transferCategory(upper::getMoneys, upper::removeFirstMoney, real::addMoney, viewLimit);
        transferCategory(upper::getMonsters, upper::removeFirstMonster, real::addMonster, viewLimit);
    }

    /**
     * Support method for transferGameObj that abstracts the logic of transferring
     * objects from upper to real world for a specific category of game objects.
     * 
     * @param <T>         the type of the game object category to transfer
     * @param getList     a supplier for the list of the category in the upper world
     * @param removeFirst a supplier for removing the first element of the category
     *                    in the upper world
     * @param addToReal   a consumer for adding an element to the real world
     * @param viewLimit   the y position limit that determines when to transfer
     *                    objects from upper to real world
     */
    private <T extends GameObject> void transferCategory(
            final Supplier<List<T>> getList,
            final Supplier<Optional<T>> removeFirst,
            final Consumer<T> addToReal,
            final double viewLimit) {

        while (!getList.get().isEmpty() && getList.get().get(0).getPosY() > viewLimit) {
            removeFirst.get().ifPresent(addToReal);
        }
    }

    /**
     * Removes objects from the RealWorld that are no longer visible, so when
     * objects y position is greater than the viewport height and that therefore
     * have fallen under the screen.
     */
    private void cleanRealWorld() {
        final double limit = this.height;

        List.of(
                world.getRealWorld().getStaticPlatforms(),
                world.getRealWorld().getOnTouchPlatforms(),
                world.getRealWorld().getMovingPlatforms(),
                world.getRealWorld().getGadgets(),
                world.getRealWorld().getMonsters(),
                world.getRealWorld().getMoneys()).forEach(list -> list.removeIf(obj -> obj.getPosY() > limit));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getViewportWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getViewportHeight() {
        return this.height;
    }

}
