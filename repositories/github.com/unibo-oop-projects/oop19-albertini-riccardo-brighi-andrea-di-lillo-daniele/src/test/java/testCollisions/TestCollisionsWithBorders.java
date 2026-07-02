package testCollisions;

import model.ball.Ball;
import model.ball.BallBuilder;
import model.ball.BallBuilderImpl;
import model.block.Block;
import model.collision.*;
import element.Point2D;
import element.Point2DImpl;
import element.Vector2D;
import element.Vector2DImpl;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollisionsWithBorders {
    private BallBuilder builder = new BallBuilderImpl();
    private static Optional NO_COLLISION = Optional.empty();
    Collision collisionCheck = new CollisionImpl((Map<Pair<Integer, Integer>, Block>) null, 500, 0, 0, 350);

    @Test
    void testWithNoSpeedBall() {
        Point2D position = new Point2DImpl(1, 1);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(0, 0));

        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);
    }

    private Optional<CollisionDetectedImpl> bottomCollision(final Point2D newPos){
        return Optional.of(new CollisionDetectedImpl(newPos));
    }

    //----------------------------------BOTTOM------------------------------------
    @Test
    void testBottomVerticalCollision() {
        int x = 5;
        Point2D position = new Point2DImpl(x, 5);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(0, -1));
        //4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //1
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(x,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(), ball.getDirection());

        ball.moveByDistance(2);
        //-1
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(x,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(), ball.getDirection());

        ball.moveByDistance(3);
        //-2
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(x,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        new Vector2DImpl(0,0));
    }

    @Test
    void testBottomCollisionNotVerticalDirectionFromLeft() {
        int x = 5;
        Point2D position = new Point2DImpl(x, 5);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, -1));
        //4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //3.7
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //2.6
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //1.9
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //1.2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //0.5
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //-0.2
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(9,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        new Vector2DImpl(0,0));
    }

    @Test
    void testBottomCollisionNotVerticalDirectionFromRight() {
        int x = 10;
        Point2D position = new Point2DImpl(x, 5);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, -1));
        //4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //3.7
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //2.6
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //1.9
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //1.2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //0.5
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //-0.2
        assertEquals(collisionCheck.checkCollision(ball), bottomCollision(new Point2DImpl(6,1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        new Vector2DImpl(0,0));
    }

    //------------------------------------TOP------------------------------------
    private Optional<CollisionDetectedImpl> generalBorderCollision(final Point2D newPos, final Vector2D newVec){
        return Optional.of(new CollisionDetectedImpl(newVec, newPos));
    }

    @Test
    void testTopVerticalCollision() {
        int x = 5, y= 496;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(0, 1));
        //497
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //498
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //499
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //500
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(x,499),
                                                                new Vector2DImpl(0, -1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());

        ball.setDirection(new Vector2DImpl(0, 1));
        ball.moveByDistance(2);
        //501
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(x,499),
                                                            new Vector2DImpl(0, -1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());

        ball.setDirection(new Vector2DImpl(0, 1));
        ball.moveByDistance(3);
        //502
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(x,499),
                                                            new Vector2DImpl(0, -1)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testTopCollisionNotVerticalDirectionFromLeft() {
        int x = 5, y= 496;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, 1));
        //497
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //497.7
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //498.4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //499.1
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //499.8
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //500.5
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(8,499),
                                            new Vector2DImpl(1, -1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testTopCollisionNotVerticalDirectionFromRight() {
        int x = 10, y= 496;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, 1));
        //497
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //497.7
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //498.4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //499.1
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //499.8
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //500.5
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(7,499),
                                            new Vector2DImpl(-1, -1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    //------------------------------------RIGHT------------------------------------

    @Test
    void testRightHorizontalCollision() {
        int x = 347, y= 10;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, 0));
        //348
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //349
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //350
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(349,y),
                                                                new Vector2DImpl(-1, 0)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());

        ball.moveByDistance(2);
        //351
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(349,y),
                                                                new Vector2DImpl(-1, 0)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testRightCollisionNotHorizontalDirectionFromTop() {
        int x = 347, y= 15;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, -1));
        //348
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //348.7
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //349.4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //350.1
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(349,12.757358),
                                            new Vector2DImpl(-1, -1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testRightCollisionNotHorizontalDirectionFromBottom() {
        int x = 347, y= 15;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(1, 1));
        //348
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //348.7
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //349.4
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //350.1
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(349,17),
                                            new Vector2DImpl(-1, 1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    //-------------------------------------LEFT------------------------------------

    @Test
    void testLeftHorizontalCollision() {
        int x = 3, y= 10;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, 0));
        //2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //1
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByDistance(1);
        //0
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(1,y),
                                                                new Vector2DImpl(1, 0)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());

        ball.moveByDistance(2);
        //-1
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(1,y),
                                                                new Vector2DImpl(1, 0)));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testLeftCollisionNotHorizontalDirectionFromTop() {
        int x = 3, y= 15;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, -1));
        //2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //1.3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //0.5
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //350.1
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(1,13),
                                            new Vector2DImpl(1, -1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }

    @Test
    void testLeftCollisionNotHorizontalDirectionFromBottom() {
        int x = 3, y= 15;
        Point2D position = new Point2DImpl(x, y);
        Ball ball = builder.addStartPosition(position).setRadius(1).build();
        ball.setDirection(new Vector2DImpl(-1, 1));
        //2
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //1.3
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //0.5
        assertEquals(collisionCheck.checkCollision(ball), NO_COLLISION);

        ball.moveByTime(1);
        //350.1
        assertEquals(collisionCheck.checkCollision(ball), generalBorderCollision(new Point2DImpl(1,17.242642),
                                            new Vector2DImpl(1, 1).getNormalizedVector()));
        ball.collision(collisionCheck.checkCollision(ball).get().getNewCenterPosition().get(),
                        collisionCheck.checkCollision(ball).get().getNewDirection().get());
    }
}
