package model.character;

import model.bucket.Bucket;
import model.bucket.BucketImpl;
import model.world.GameWorld;
import model.world.WorldSettings;

/**
 * This is the implementation of {@link Character} interface.
 *
 */

public class BigBucket implements Character {

    private final String name;

    public BigBucket() {
        this.name = "Big Bucket";
    }

    @Override
    public final void usePower(final GameWorld world) {

        final Bucket bucket = new BucketImpl(WorldSettings.WORLD_WIDTH * 0.6);

        world.setBucket(bucket);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void deletePower(final GameWorld gameWorld) {
        gameWorld.setBucket(new BucketImpl());

    }

}
