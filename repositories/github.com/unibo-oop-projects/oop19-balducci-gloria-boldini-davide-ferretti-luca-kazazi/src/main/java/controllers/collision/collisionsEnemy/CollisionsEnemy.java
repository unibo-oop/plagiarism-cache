package controllers.collision.collisionsEnemy;

import java.util.Map;

import controllers.collision.collisionGeneral.Collisions;
import model.Direction;
import model.ID;
import model.Wall;
import model.enemy.Enemy;
import model.gameObject.GameObject;

public class CollisionsEnemy extends Collisions implements CollisionsEnemyInterface {

    private int collisionNum;
    private int index = 1;
    private final Map<Integer, Direction> indexMap = Map.ofEntries(Map.entry(0, Direction.DOWN),
            Map.entry(1, Direction.RIGHT), Map.entry(2, Direction.UP), Map.entry(3, Direction.LEFT)); // 0: DOWN - 1:
                                                                                                      // RIGHT - 2: UP -
                                                                                                      // 3: LEFT

    @Override
    public void collisionsInGame(final Enemy obj1, final GameObject obj2) {
        if (obj2.getId().equals(ID.WALL)) {
            this.collisionsWall(obj1, (Wall) obj2);
        }
    }


    private void collisionsWall(final Enemy obj1, final Wall obj2) {
        if (this.checkCollisions(obj1, obj2)) {
            obj1.getMovement().reset();
            super.collisionsWall(obj1, obj2);
            switch (collisionNum) {
            case 0:
                obj1.getMovement().setRight(true);
                obj1.setPositionAfterCollision(Direction.RIGHT);
                break;
            case 1:
                obj1.getMovement().setUp(true);
                obj1.setPositionAfterCollision(Direction.UP);
                break;
            case 2:
                obj1.getMovement().setLeft(true);
                obj1.setPositionAfterCollision(Direction.LEFT);
                break;
            case 3:
                obj1.setPositionAfterCollision(Direction.DOWN);
                obj1.getMovement().setDown(true);
                this.collisionNum = -1;
                break;
            default:
                break;
            }
            if (obj1.getRay() != null) {
                if (obj1.getRay().isRayPresent() && obj1.getSpeed() == 2) {
                    obj1.getRay().setImage(obj1.getRay().getImages().get(indexMap.get(index)).get(0));
                    index++;
                    if (index == obj1.getRay().getImages().values().size()) {
                        index = 0;
                    }
                }
            }
            this.collisionNum++;
        }
    }

}
