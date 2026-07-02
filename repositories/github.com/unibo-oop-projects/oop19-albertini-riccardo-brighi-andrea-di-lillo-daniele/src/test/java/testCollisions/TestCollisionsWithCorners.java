package testCollisions;

import model.ball.Ball;
import model.ball.BallBuilder;
import model.ball.BallBuilderImpl;
import model.block.Block;
import model.collision.Collision;
import model.collision.CollisionDetectedImpl;
import model.collision.CollisionImpl;
import element.Point2D;
import element.Point2DImpl;
import element.Vector2D;
import element.Vector2DImpl;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCollisionsWithCorners {
    private BallBuilder builder = new BallBuilderImpl();
    private static Optional NO_COLLISION = Optional.empty();
    Collision collisionCheck = new CollisionImpl((Map<Pair<Integer, Integer>, Block>) null, 500, 0, 0, 350);

    private Optional<CollisionDetectedImpl> generalBorderCollision(final Point2D newPos, final Vector2D newVec){
        return Optional.of(new CollisionDetectedImpl(newVec, newPos));
    }

    @Test
    void testCornerTopLeft() {
        int x = 4, y= 496;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, 1));
        //3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //2.3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //1.6
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.9
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //-0.5
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(1,499),
                                            new Vector2DImpl(1, -1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testCornerTopRight() {
        int x = 346, y= 496;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, 1));
        //3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //2.3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //1.6
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.9
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //-0.5
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(349,499),
                                            new Vector2DImpl(-1, -1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    private Optional<CollisionDetectedImpl> bottomCollision(final Point2D newPos){
        return Optional.of(new CollisionDetectedImpl(newPos));
    }

    @Test
    void testCornerBottomLeft() {
        int x = 4, y= 4;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, -1));
        //3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //2.3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //1.6
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.9
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //-0.5
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(1,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        new Vector2DImpl(0,0));
    }

    @Test
    void testCornerBottomRight() {
        int x = 346, y= 4;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, -1));
        //3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //2.3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //1.6
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.9
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0.2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //-0.5
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(349,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        new Vector2DImpl(0,0));
    }
}
