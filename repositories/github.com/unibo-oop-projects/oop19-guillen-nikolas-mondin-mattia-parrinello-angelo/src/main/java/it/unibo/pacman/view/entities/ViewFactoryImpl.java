package it.unibo.pacman.view.entities;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Pair;
import it.unibo.pacman.model.utilities.Status;
import it.unibo.pacman.view.utilities.BufferedImageLoader;
/**
 * An implementation of {@link ViewFactory}.
 */
public class ViewFactoryImpl implements ViewFactory {
    private static final int WIDTHIMAGE = 30;
    private static final int HEIGHTIMAGE = 30;
    /**
     * {@inheritDoc}
     */
    @Override
    public MovableView getBlinkyView() {
        return new MovableGameObjView(EntityType.BLINKY, WIDTHIMAGE, HEIGHTIMAGE,
                this.loadGhostsImages(EntityType.BLINKY.toString()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public MovableView getInkyView() {
        return new MovableGameObjView(EntityType.INKY, WIDTHIMAGE, HEIGHTIMAGE,
                this.loadGhostsImages(EntityType.INKY.toString()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public MovableView getPinkyView() {
        return new MovableGameObjView(EntityType.PINKY, WIDTHIMAGE, HEIGHTIMAGE,
                this.loadGhostsImages(EntityType.PINKY.toString()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public MovableView getClydeView() {
        return new MovableGameObjView(EntityType.CLYDE, WIDTHIMAGE, HEIGHTIMAGE,
                this.loadGhostsImages(EntityType.CLYDE.toString()));
    }
    private Map<Pair<Direction, Status>, BufferedImage> loadGhostsImages(final String type) {
        final Map<Pair<Direction, Status>, BufferedImage> images = new HashMap<>();
        for (final Direction dir : Direction.getDirectionList()) {
                images.put(new Pair<>(dir, Status.CHASING),
                        BufferedImageLoader.loadImage("Images/Ghost/" + type + "_" + dir.toString() + ".png"));
                images.put(new Pair<>(dir, Status.CHASED),
                        BufferedImageLoader.loadImage("Images/Ghost/Frightened_Phantom.png"));
                images.put(new Pair<>(dir, Status.CHASED_END),
                        BufferedImageLoader.loadImage("Images/Ghost/Frightened_End_Phantom.png"));
        }
        return images;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public MovableView getPacManView() {
        final Map<Pair<Direction, Status>, BufferedImage> images = new HashMap<>();
        for (final Direction dir : Direction.getDirectionList()) {
            for (final Status stat : Status.getStatusList()) {
                images.put(new Pair<>(dir, stat),
                        BufferedImageLoader.loadImage("Images/Pacman/Pacman_" + dir.toString() + ".png"));
            }
        }
        return new MovableGameObjView(EntityType.PACMAN, WIDTHIMAGE, HEIGHTIMAGE, images);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityView getWallView() {
        return new SimpleGameObjView(EntityType.WALL, BufferedImageLoader.loadImage("Images/Map/Wall.png"),
                WIDTHIMAGE, HEIGHTIMAGE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityView getPillView() {
        return new SimpleGameObjView(EntityType.PILL, BufferedImageLoader.loadImage("Images/Map/pill.png"),
                WIDTHIMAGE, HEIGHTIMAGE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityView getPowerPillView() {
        return new SimpleGameObjView(EntityType.POWERPILL,
                BufferedImageLoader.loadImage("Images/Map/PowerPill.png"), WIDTHIMAGE, HEIGHTIMAGE);
    }
}
