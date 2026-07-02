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

public class BaseEnemy extends Enemy {

    private static final int SPEED = 2;
    private final Movement move;
    private final CollisionsEnemy coll;
    private final Handler handler;

    /**
     * Constructor for BaseEnemy.
     * 
     * @param id
     * @param handler
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param textureImages
     * @param rayImages
     */
    public BaseEnemy(final ID id, final Handler handler, final int posX, final int posY, final double velX,
            final double velY, final List<Pair<Direction, BufferedImage>> textureImages,
            final List<Pair<Direction, BufferedImage>> rayImages) {
        super(id, posX, posY, velX, velY, textureImages, rayImages);
        this.handler = Objects.requireNonNull(handler);
        this.move = new Movement(this, SPEED);
        this.move.setDown(true);
        this.getRay().setImage(this.getRay().getImages().get(Direction.DOWN).get(0));
        this.coll = new CollisionsEnemy();

        super.getRay().setRayX(-16);
        super.getRay().setRayY(48);
    }

    @Override
    public void tick() {
        move.moveEntity();
        this.handler.getGameObjectList().stream().forEach(p -> coll.collisionsInGame(this, p));
        super.tick();
    }

    @Override
    public int getSpeed() {
        return BaseEnemy.SPEED;
    }

    @Override
    public Movement getMovement() {
        return this.move;
    }

    @Override
    public void setPositionAfterCollision(final Direction direction) {
        if (Direction.RIGHT.equals(direction)) {
            super.getRay().setRayX(32);
            super.getRay().setRayY(-8);
        } else if (Direction.UP.equals(direction)) {
            super.getRay().setRayX(-16);
            super.getRay().setRayY(-128);
        } else if (Direction.LEFT.equals(direction)) {
            super.getRay().setRayX(-128);
            super.getRay().setRayY(-8);
        } else {
            super.getRay().setRayX(-16);
            super.getRay().setRayY(48);
        }

    }
}
