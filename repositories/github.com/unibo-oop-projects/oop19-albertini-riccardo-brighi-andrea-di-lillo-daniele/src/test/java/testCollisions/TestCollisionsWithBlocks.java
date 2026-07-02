package testCollisions;

import element.Point2D;
import element.Point2DImpl;
import element.Vector2DImpl;
import model.block.Block;
import model.block.BlockImpl;
import model.collision.Collision;
import model.collision.CollisionImpl;
import model.ball.Ball;
import model.ball.BallBuilder;
import model.ball.BallBuilderImpl;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TestCollisionsWithBlocks {
    Collision collisionCheck;
    private BallBuilder builder = new BallBuilderImpl();

    @Test
    void zero(){
        List<Block> blocks = new ArrayList<>();
        blocks.add(new BlockImpl(1, 3));
        Point2D position = new Point2DImpl(75, 540);
        Ball ball = builder.addStartPosition(position).setRadius(50).build();
        ball.setDirection(new Vector2DImpl(1, 1));
        collisionCheck = new CollisionImpl(blocks, 600, 0, 0, 350);
        collisionCheck.checkCollision(ball);
    }
}
