package model.enemy;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

import controllers.collision.collisionsEnemy.CollisionsEnemy;
import controllers.movement.Movement;
import model.Direction;
import model.ID;
import model.handler.Handler;
import other.Pair;

public class AvarageEnemy extends Enemy {

    private static final int SPEED = 3;
    private final Movement move;
    private final CollisionsEnemy coll;
    private final Handler handler;

    /**
     * Constructor for AvarageEnemy.
     * 
     * @param id
     * @param handler
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     * @param rayImages
     */
    public AvarageEnemy(final ID id, final Handler handler, final int posX, final int posY, final double velX,
            final double velY, final List<Pair<Direction, BufferedImage>> image, final BufferedImage rayImages) {
        super(id, posX, posY, velX, velY, image, rayImages);
        this.handler = Objects.requireNonNull(handler);
        this.move = new Movement(this, SPEED);
        this.move.setDown(true);
        this.coll = new CollisionsEnemy();
        super.getRay().setRayX(-50);
        super.getRay().setRayY(-40);
    }

    @Override
    public void tick() {
        super.tick();
        this.handler.getGameObjectList().stream().forEach(p -> coll.collisionsInGame(this, p));
        move.moveEntity();

    }

    @Override
    public int getSpeed() {
        return AvarageEnemy.SPEED;
    }

    @Override
    public Movement getMovement() {
        return this.move;
    }

    @Override
    public void setPositionAfterCollision(final Direction direction) {
        super.getRay().setRayX(-50);
        super.getRay().setRayY(-40);
    }
}