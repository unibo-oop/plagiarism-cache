package controller;

import java.util.List;

import model.enemy.Enemy;
import model.entities.Character;
import utils.Direction;

/**
 * Control the entities movement.
 */
public final class MovementController implements MovementObserver {

    private final EntityFactory factory;
    private final CollisionObserver collision;

    /**
     * @param factory   The {@link EntityFactory}
     * @param collision The {@link CollisionObserver}
     */
    public MovementController(final EntityFactory factory, final CollisionObserver collision) {
        this.factory = factory;
        this.collision = collision;
    }

    @Override
    public void moveStuntman(final Direction direction) {
        try {
            this.factory.getStuntman()
                        .getClass()
                        .getMethod("move" + direction.toString().substring(0, 1)
                            + direction.toString().substring(1, direction.toString().length()).toLowerCase())
                        .invoke(this.factory.getStuntman());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (direction.equals(Direction.UP)) {
            this.factory.getStuntman().getCounterFloors().increment();
        }
        this.collision.collideWithClosedWindow(this.factory.getStuntman(), this.factory.getPalace().getFloors());
        this.collision(this.factory.getStuntman(), this.factory.getVases());
        this.collision(this.factory.getStuntman(), this.factory.getHawks());
        this.factory.getBonus()
                    .stream()
                    .filter(bonus -> bonus.isInGame())
                    .forEach(bonus -> this.collision.collideWithBonus(this.factory.getStuntman(), bonus));
    }

    @Override
    public void moveVase() {
        this.factory.getVases()
                    .stream()
                    .filter(vase -> vase.isInGame())
                    .forEach(vase -> vase.moveDown());
        this.collision(this.factory.getStuntman(), this.factory.getVases());
    }

    @Override
    public void moveHawk() {
        this.factory.getHawks()
                    .stream()
                    .filter(hawk -> hawk.isInGame())
                    .forEach(hawk -> hawk.moveRight());
        this.collision(this.factory.getStuntman(), this.factory.getHawks());
    }

    private void collision(final Character stuntman, final List<Enemy> enemies) {
        enemies.stream()
               .filter(enemy -> enemy.isInGame())
               .forEach(enemy -> this.collision.collideWithEnemy(this.factory.getStuntman(), enemy));
    }

}
