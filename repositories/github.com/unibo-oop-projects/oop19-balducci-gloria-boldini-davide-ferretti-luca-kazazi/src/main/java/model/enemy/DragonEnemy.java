package model.enemy;

import java.awt.image.BufferedImage;
import java.util.List;

import controllers.collision.collisionsDragon.CollisionsDragon;
import controllers.movement.Movement;
import model.Direction;
import model.ID;
import other.Pair;

public class DragonEnemy extends Enemy {

    private static final int SPEED = 3;
    private final Movement move;
    private final CollisionsDragon coll;
    private long lastTime;
    private static final long DELAY = 5000;

    /**
     * Constructor for DragonEnemy.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     * @param rayImages
     */
    public DragonEnemy(final ID id, final int posX, final int posY, final double velX, final double velY,
            final List<Pair<Direction, BufferedImage>> image, final List<Pair<Direction, BufferedImage>> rayImages) {
        super(id, posX, posY, velX, velY, image, rayImages);
        this.move = new Movement(this, SPEED);
        this.move.setUp(true);
        this.coll = new CollisionsDragon();
        super.getRay().setRayX(0);
        super.getRay().setRayY(96);
        this.setEnemyDragon();
    }

    @Override
    public void tick() {
        super.tick();
        final long now = System.currentTimeMillis();
        if (now - lastTime > DELAY) {
            coll.randomMovementsEnemy(this);
            lastTime = System.currentTimeMillis();
        }
        coll.collisionsInGame(this);
        move.moveEntity();

    }

    @Override
    public int getSpeed() {
        return DragonEnemy.SPEED;
    }

    @Override
    public Movement getMovement() {
        return this.move;
    }

    @Override
    public void setPositionAfterCollision(final Direction direction) {
        if (Direction.RIGHT.equals(direction)) {
            super.getRay().setRayX(96);
            super.getRay().setRayY(0);
        } else if (Direction.UP.equals(direction)) {
            super.getRay().setRayX(0);
            super.getRay().setRayY(-178);
        } else if (Direction.LEFT.equals(direction)) {
            super.getRay().setRayX(-178);
            super.getRay().setRayY(0);
        } else {
            super.getRay().setRayX(0);
            super.getRay().setRayY(96);
        }

    }
}