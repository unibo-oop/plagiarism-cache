import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import model.bucket.Bucket;
import model.character.BigBucket;
import model.world.GameWorld;
import model.world.GameWorldImpl;

class BigBucketTest {

    @Test
    final void testUsePower() {
        final GameWorld world = new GameWorldImpl();
        final Bucket oldbucket = world.getBucket();
        final BigBucket bigbucket = new BigBucket();
        bigbucket.usePower(world);
        assertNotSame(oldbucket, world.getBucket());
    }
}
