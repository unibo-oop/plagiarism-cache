package controllers.collision.collisionsDragon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import controllers.collision.collisionsEnemy.CollisionsEnemy;
import model.Direction;
import model.enemy.Enemy;
import other.Pair;

public class CollisionsDragon extends CollisionsEnemy implements CollisionsDragonInterface {

    private final List<Direction> movements = Arrays.asList(Direction.values());

    private final Pair<Double, Double> upPairMap = new Pair<>(64.0, 64.0);
    private final Pair<Double, Double> downPairMap = new Pair<>(4032.0, 4032.0);

    @Override
    public void randomMovementsEnemy(final Enemy e) {
        this.moveEnemy(e);
    }

    @Override
    public void collisionsInGame(final Enemy obj1) {
        this.collisionsBorder(obj1);
    }

    private void collisionsBorder(final Enemy obj1) {
        if (this.hitNorthBorder(obj1)) {
            obj1.getMovement().reset();
            obj1.getMovement().setDown(true);
            obj1.getRay().setImage(obj1.getRay().getImages().get(Direction.DOWN).get(0));
            obj1.setPositionAfterCollision(this.movements.get(3));
        } else if (this.hitSouthBorder(obj1)) {
            obj1.getMovement().reset();
            obj1.getMovement().setUp(true);
            obj1.getRay().setImage(obj1.getRay().getImages().get(Direction.UP).get(0));
            obj1.setPositionAfterCollision(this.movements.get(0));
        } else if (this.hitWestBorder(obj1)) {
            obj1.getMovement().reset();
            obj1.getMovement().setRight(true);
            obj1.getRay().setImage(obj1.getRay().getImages().get(Direction.RIGHT).get(0));
            obj1.setPositionAfterCollision(this.movements.get(1));
        } else if (this.hitEastBorder(obj1)) {
            obj1.getMovement().reset();
            obj1.getMovement().setLeft(true);
            obj1.getRay().setImage(obj1.getRay().getImages().get(Direction.LEFT).get(0));
            obj1.setPositionAfterCollision(this.movements.get(2));
        }
    }

    private boolean hitNorthBorder(final Enemy e) {
        return e.getRectangle().getBounds2D().intersectsLine(upPairMap.getX(), upPairMap.getY(), downPairMap.getX(),
                upPairMap.getX());
    }

    private boolean hitEastBorder(final Enemy e) {

        return e.getRectangle().getBounds2D().intersectsLine(downPairMap.getX(), upPairMap.getY(), downPairMap.getX(),
                downPairMap.getY());

    }

    private boolean hitSouthBorder(final Enemy e) {
        return e.getRectangle().getBounds2D().intersectsLine(upPairMap.getX(), downPairMap.getY(), downPairMap.getX(),
                downPairMap.getY());

    }

    private boolean hitWestBorder(final Enemy e) {
        return e.getRectangle().getBounds2D().intersectsLine(upPairMap.getX(), upPairMap.getY(), upPairMap.getX(),
                downPairMap.getY());
    }

    private void moveEnemy(final Enemy e) {
        final Random rand = new Random();
        final Direction direction = this.movements.get(rand.nextInt(4));
        e.getMovement().reset();
        if (direction.equals(this.movements.get(2))) {
            e.getMovement().setUp(true);
            e.getRay().setImage(e.getRay().getImages().get(Direction.UP).get(0));
            e.setPositionAfterCollision(this.movements.get(0));
        } else if (direction.equals(this.movements.get(1))) {
            e.getMovement().setLeft(true);
            e.getRay().setImage(e.getRay().getImages().get(Direction.LEFT).get(0));
            e.setPositionAfterCollision(this.movements.get(2));
        } else if (direction.equals(this.movements.get(3))) {
            e.getMovement().setDown(true);
            e.getRay().setImage(e.getRay().getImages().get(Direction.DOWN).get(0));
            e.setPositionAfterCollision(this.movements.get(3));
        } else if (direction.equals(this.movements.get(0))) {
            e.getMovement().setRight(true);
            e.getRay().setImage(e.getRay().getImages().get(Direction.RIGHT).get(0));
            e.setPositionAfterCollision(this.movements.get(1));
        }
    }
}
