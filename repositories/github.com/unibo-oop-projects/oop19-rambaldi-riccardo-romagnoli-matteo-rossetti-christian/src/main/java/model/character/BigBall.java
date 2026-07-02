package model.character;

import org.apache.commons.lang3.tuple.Pair;

import model.ball.Ball;
import model.ball.BallImpl;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;
import model.obstacle.ObstacleType;
import model.world.GameWorld;

/**
 * This is the implementation of {@link Character} interface.
 *
 */
public class BigBall implements Character {

    private final String name;
    private Ball oldball;
    private Ball newball;
    private Obstacle greenObstacle;

    public BigBall() {
        this.name = "Big Ball";
    }

    @Override
    public final void usePower(final GameWorld world) {

        this.greenObstacle = world.getHitObstacles().stream().filter(o -> o.getBehavior() == ObstacleBehavior.GREEN).findFirst()
                .get();
        this.oldball = world.getBall();
        this.newball = new BallImpl(oldball.getRadius() * 2);
        this.newball.getPhysicalBody().setLinearVelocity(oldball.getPhysicalBody().getLinearVelocity().getNegative().divide(2));

        if (this.greenObstacle.getType() == ObstacleType.RECTANGLE) {
            this.newball.move(getBallPosRect(this.greenObstacle, this.oldball));
        } else if (this.greenObstacle.getType() == ObstacleType.CIRCLE) {
            this.newball.move(getBallPosCirc(this.greenObstacle, this.oldball));
        }

        world.setBall(this.newball);

    }

    private Pair<Double, Double> getBallPosRect(final Obstacle greenObstacle, final Ball oldball) {
        final double newPosX = oldball.getPosition().getLeft()
                + oldball.getRadius() * Math.sin(Math.PI - greenObstacle.getMeasures().get(2));
        final double newPosY = oldball.getPosition().getRight()
                + oldball.getRadius() * Math.sin(Math.PI - greenObstacle.getMeasures().get(2));

        return Pair.of(newPosX, newPosY);

    }

    private Pair<Double, Double> getBallPosCirc(final Obstacle greenObstacle, final Ball oldball) {
        final double newPosX = oldball.getPosition().getLeft() + oldball.getRadius()
                * Math.sin(Math.atan2(oldball.getPosition().getRight() - greenObstacle.getPosition().getRight(),
                        oldball.getPosition().getLeft() - greenObstacle.getPosition().getLeft()));
        final double newPosY = oldball.getPosition().getRight() + oldball.getRadius()
                * Math.sin(Math.atan2(oldball.getPosition().getRight() - greenObstacle.getPosition().getRight(),
                        oldball.getPosition().getLeft() - greenObstacle.getPosition().getLeft()));

        return Pair.of(newPosX, newPosY);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void deletePower(final GameWorld gameWorld) {

        this.oldball = gameWorld.getBall();
        this.newball = new BallImpl();
        newball.getPhysicalBody().setLinearVelocity(oldball.getPhysicalBody().getLinearVelocity());
        newball.move(oldball.getPosition());
        gameWorld.setBall(newball);

    }

}
