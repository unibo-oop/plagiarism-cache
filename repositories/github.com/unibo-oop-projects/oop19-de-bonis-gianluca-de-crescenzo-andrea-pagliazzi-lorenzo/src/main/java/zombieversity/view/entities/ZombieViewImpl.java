package zombieversity.view.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import zombieversity.model.entities.EntityType;
import zombieversity.view.imagefactory.SpriteFactoryUtils;

/**
 * Implementation of {@link ZombieView}.
 *
 */
public class ZombieViewImpl implements ZombieView {

    /**
     * Width of zombies sprites.
     */
    private static final int IMAGE_WIDTH = 15;

    /**
     * Height of zombies sprites.
     */
    private static final int IMAGE_HEIGHT = 15;

    private Set<Pair<Point2D, Double>> positions;
    private final EntityType entityType;
    private final Set<ImageView> zombieImages;
    private Set<Pair<Point2D, Image>> imagesToRender;

    /**
     * Instantiates a {@link ZombieViewImpl}.
     */
    public ZombieViewImpl() {
        this.positions = new HashSet<>();
        this.entityType = EntityType.ZOMBIE;
        this.zombieImages = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPositionsAndDirections(final Set<Pair<Point2D, Double>> positions) {
        this.positions = positions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        final Iterator<Pair<Point2D, Double>> posIt = this.positions.iterator();
        final Iterator<ImageView> imgIt = this.zombieImages.iterator();

        this.updatePositions(posIt, imgIt);
        this.removeDeadZombies(imgIt);
        this.addSpawnedZombies(posIt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Pair<Point2D, Image>> getSprites() {
        return this.imagesToRender;
    }

    /**
     * Used to update positions and zombies images directions.
     * @param posIt positions iterator.
     * @param imgIt images iterator.
     */
    private void updatePositions(final Iterator<Pair<Point2D, Double>> posIt, final Iterator<ImageView> imgIt) {
        final SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);
        imagesToRender = new HashSet<>();
        while (imgIt.hasNext() && posIt.hasNext()) {
            final ImageView zombieImg = imgIt.next();
            final Pair<Point2D, Double> pos = posIt.next();
            zombieImg.relocate(pos.getKey().getX(), pos.getKey().getY());
            zombieImg.setRotate(pos.getValue());
            imagesToRender.add(new Pair<>(pos.getKey(), zombieImg.snapshot(sp, null)));
        }
    }

    /**
     * Used to remove dead zombies images from the view.
     * @param imgIt images iterator.
     */
    private void removeDeadZombies(final Iterator<ImageView> imgIt) {
        imgIt.forEachRemaining(img -> {
            this.zombieImages.remove(img);
        });
    }

    /**
     * Used to add new zombies images to the view.
     * @param posIt positions iterator.
     */
    private void addSpawnedZombies(final Iterator<Pair<Point2D, Double>> posIt) {
        posIt.forEachRemaining(pos -> {
            ImageView zombie;
            zombie = SpriteFactoryUtils.createSprite(entityType).getImageView();
            zombie.setFitHeight(IMAGE_HEIGHT);
            zombie.setFitWidth(IMAGE_WIDTH);
            zombie.setPreserveRatio(true);
            this.zombieImages.add(zombie);
        });
    }

}
