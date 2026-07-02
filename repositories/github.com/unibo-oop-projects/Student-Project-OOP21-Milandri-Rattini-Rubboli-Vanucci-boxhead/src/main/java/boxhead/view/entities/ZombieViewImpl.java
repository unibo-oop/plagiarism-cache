package boxhead.view.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import boxhead.model.entities.EntityType;
import boxhead.model.entities.utils.Direction;
import boxhead.view.spriteutils.Sprite;
import boxhead.view.spriteutils.SpriteFactory;

/**
 * {@link ZombieView} implementation
 *	Renders zombies images
 */
public class ZombieViewImpl implements ZombieView {

    private Set<Pair<Point2D, Direction>> posIteratorions;
    private final EntityType entityType;
    private final Set<ImageView> zombieImages;
    private Set<Pair<Point2D, Image>> imagesToRender;

    /**
     * Instantiates a {@link ZombieViewImpl}
     */
    public ZombieViewImpl() {
        this.posIteratorions = new HashSet<>();
        this.entityType = EntityType.ZOMBIE;
        this.zombieImages = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPositionsAndDirections(final Set<Pair<Point2D, Direction>> posIteratorions) {
        this.posIteratorions = posIteratorions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        final Iterator<Pair<Point2D, Direction>> posIterator = this.posIteratorions.iterator();
        final Iterator<ImageView> imageIterator = this.zombieImages.iterator();

        this.updateposIteratorions(posIterator, imageIterator);
        this.removeDeadZombies(imageIterator);
        this.addSpawnedZombies(posIterator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Pair<Point2D, Image>> getSprites() {
        return this.imagesToRender;
    }

    /**
     * Update posIteratorions and images directions
     * @param posIterator posIteratorions iterator
     * @param imageIterator images iterator
     */
    private void updateposIteratorions(final Iterator<Pair<Point2D, Direction>> posIterator, final Iterator<ImageView> imageIterator) {
        final SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);
        imagesToRender = new HashSet<>();
        while (imageIterator.hasNext() && posIterator.hasNext()) {
            final ImageView zombieImg = imageIterator.next();
            final Pair<Point2D, Direction> pos = posIterator.next();
            zombieImg.relocate(pos.getKey().getX(), pos.getKey().getY());
            Sprite.updateZombieImage(zombieImg, pos.getValue());
            imagesToRender.add(new Pair<>(pos.getKey(), zombieImg.snapshot(sp, null)));
        }
    }


    /**
     * Add zombie images to the view
     * @param posIterator posIteratorions iterator
     */
    private void addSpawnedZombies(final Iterator<Pair<Point2D, Direction>> posIterator) {
        posIterator.forEachRemaining(pos -> {
            ImageView zombie;
            zombie = SpriteFactory.createSprite(entityType).getImageView();
            zombie.setPreserveRatio(true);
            this.zombieImages.add(zombie);
        });
    }
    
    /**
     * Remove zombies from view
     * @param imageIterator images iterator
     */
    private void removeDeadZombies(final Iterator<ImageView> imageIterator) {
    	try {
    		imageIterator.forEachRemaining(img -> {
            	this.zombieImages.remove(img);
            	});
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}
