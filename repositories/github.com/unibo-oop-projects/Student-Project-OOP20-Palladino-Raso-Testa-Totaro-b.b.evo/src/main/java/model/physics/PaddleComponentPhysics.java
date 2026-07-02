package model.physics;

import java.util.Optional;

import model.entities.GameBoardImpl;
import model.entities.GameObject;
import model.entities.Paddle;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.Position;

public class PaddleComponentPhysics implements ComponentPhysics {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final int timeElapsed, final GameObject gameObject, final GameBoardImpl board) {

        final Paddle paddle = (Paddle) gameObject;
        final Position posPaddle = paddle.getPos();
        final DirVector dirVectPaddle = paddle.getDirVector();
        paddle.setPos(posPaddle.sum(dirVectPaddle.mul(timeElapsed * paddle.getSpeed())));
        final Optional<Boundaries> collisionInfo = board.checkGameObjCollisionsWithWall(paddle);
        if (collisionInfo.isPresent()) {
            if (collisionInfo.get().equals(Boundaries.SIDE_RIGHT)) {
                paddle.setPos(new Position(board.getWall().getRightBottomCorner().getX() - paddle.getWidth(), paddle.getPos().getY()));
            } else {
                paddle.setPos(new Position(board.getWall().getUpperLeftCorner().getX(), paddle.getPos().getY()));
            }
        }
    }

}
