package model.physics;

import java.util.Optional;

import controller.event.HitEvent;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameBoardImpl;
import model.entities.GameObject;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.Pair;
import model.utilities.Position;

public class BallComponentPhysics implements ComponentPhysics {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final int timeElapsed, final GameObject gameObject, final GameBoardImpl board) {
        final Ball ball = (Ball) gameObject;
        final Position posBall = ball.getPos();
        final DirVector dirVectBall = ball.getDirVector();
        ball.setPos(ball.getPos().sum(dirVectBall.mul(timeElapsed * ball.getSpeed())));

        final Optional<Boundaries> wallCollisionInfo = board.checkGameObjCollisionsWithWall(ball);
        if (wallCollisionInfo.isPresent()) {
            ball.setPos(posBall);
            board.eventListener(new HitEvent(Optional.of(ball), Optional.of(wallCollisionInfo.get())));
            if (wallCollisionInfo.get().equals(Boundaries.SIDE_LEFT) 
                    || wallCollisionInfo.get().equals(Boundaries.SIDE_RIGHT)) {
                ball.setDirOnX();
            } else {
                ball.setDirOnY();
            }
        }

        final Optional<Pair<Brick, Boundaries>> brickCollisionInfo = board.checkBallCollisionsWithBrick(ball);
        if (brickCollisionInfo.isPresent()) {
            ball.setPos(posBall);
            board.eventListener(new HitEvent(Optional.of(brickCollisionInfo.get().getX()), Optional.of(brickCollisionInfo.get().getY())));
            if (brickCollisionInfo.get().getY().equals(Boundaries.SIDE_LEFT) 
                    || brickCollisionInfo.get().getY().equals(Boundaries.SIDE_RIGHT)) {
                ball.setDirOnX();
            } else {
                ball.setDirOnY();
            }
        }

        final Pair<Optional<Boundaries>, Optional<Angle>> paddleCollisionInfo = board.checkBallCollisionsWithPaddle(ball);
        if (paddleCollisionInfo.getX().isPresent()) {
            final Boundaries collisionSide = paddleCollisionInfo.getX().get();
            if (collisionSide.equals(Boundaries.SIDE_LEFT) || collisionSide.equals(Boundaries.SIDE_RIGHT)) {
                ball.setDirOnX();
            } else if (collisionSide.equals(Boundaries.UPPER)) {
                ball.setPos(posBall);
                ball.setDirVector(paddleCollisionInfo.getY().get().getAngleVector());
                ball.setDirOnY();
                board.eventListener(new HitEvent(Optional.of(board.getpaddle()), Optional.empty()));
            }
        }
    }

}
