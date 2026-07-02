import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.ball.Ball;
import model.ball.BallImpl;
import model.bucket.Bucket;
import model.bucket.BucketImpl;
import model.world.GameWorld;
import model.world.GameWorldImpl;
import model.world.WorldSettings;

class BucketTest {

    @Test
    void testMoveBucket() {
        final Bucket bucket = new BucketImpl();

        //save the default position on the bucket and calculate the linear velocity
        final double linearVelocity = WorldSettings.WORLD_HEIGHT * 0.005;
        final double defaultPositionX = bucket.getPositions().get(2).getLeft();

        //Update the bucket to move along the axis
        bucket.update();
        assertEquals(bucket.getPositions().get(2).getLeft(), defaultPositionX + linearVelocity);

        //Check if the bucket change direction after reach the right world boarder
        while (bucket.getPositions().get(2).getLeft().compareTo(WorldSettings.WORLD_WIDTH / 2) <= 0) {
            bucket.update();
        }
        final double maxPositionX = bucket.getPositions().get(2).getLeft();
        bucket.update();
        assertEquals(bucket.getPositions().get(2).getLeft(), maxPositionX - linearVelocity);
    }

    @Test
    void testSlamDunk() {
        //Create the world and the ball
        final GameWorld world = new GameWorldImpl();
        final Ball ball = new BallImpl();

        //Initialize the world and add the ball
        ball.move(Pair.of(0., 1.));
        world.setBall(ball);

        //Move the ball and check if the ball get in the bucket
        while (!world.getBucket().slamDunk()) {
            world.update(1, Pair.of(0., 1.));
        }
        assertTrue(world.getBucket().slamDunk());
    }
}
