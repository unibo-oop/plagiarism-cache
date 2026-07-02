package controllers.collision.collisionGeneral;

import model.enemy.Enemy;
import model.gameObject.GameObject;
import other.Pair;

public class Collisions implements CollisionsInterface {

    @Override
    public boolean checkCollisions(final GameObject obj1, final GameObject obj2) {
        return obj1.getRectangle().intersects(obj2.getRectangle());
    }

    @Override
    public void collisionsWall(final GameObject obj1, final GameObject obj2) {
        if (this.checkCollisions(obj1, obj2)) {
            final Integer posX = (int) (obj1.getCoord().getX() - obj1.getVelocity().getX());
            final Integer posY = (int) (obj1.getCoord().getY() - obj1.getVelocity().getY());
            obj1.setCoord(new Pair<Integer, Integer>(posX, posY));
        }
    }

    @Override
    public boolean checkCollisionsEnemyRectangle(final GameObject obj1, final Enemy obj2) {
        return obj1.getRectangle().intersects(obj2.getEnemyRectangle());
    }

}
