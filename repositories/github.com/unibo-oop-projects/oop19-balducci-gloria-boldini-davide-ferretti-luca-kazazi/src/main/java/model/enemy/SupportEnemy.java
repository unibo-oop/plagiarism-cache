package model.enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import controllers.collision.collisionsEnemy.CollisionsEnemy;
import controllers.movement.Movement;
import model.Direction;
import model.ID;
import model.handler.Handler;
import other.Pair;

public class SupportEnemy extends Enemy {

    private static final int SPEED = 6;
    private final Movement move;
    private final CollisionsEnemy coll;
    private final Handler handler;

    /**
     * Constructor for SupportEnemy.
     * 
     * @param id
     * @param handler
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     */
    public SupportEnemy(final ID id, final Handler handler, final int posX, final int posY, final double velX,
            final double velY, final List<Pair<Direction, BufferedImage>> image) {
        super(id, posX, posY, velX, velY, image);
        this.handler = handler;
        this.move = new Movement(this, SPEED);
        this.move.setDown(true);
        this.coll = new CollisionsEnemy();
    }

    @Override
    public void tick() {
        super.tick();

        this.handler.getGameObjectList().stream().forEach(p -> coll.collisionsInGame(this, p));

        move.moveEntity();

    }

    @Override
    public boolean killLife() {
        this.setAlive(false);
        this.setVisible(false);
        return this.isAlive();
    }

    @Override
    public Rectangle getRectangle() {
        return super.getEnemyRectangle();
    }


    @Override
    public int getSpeed() {
        return SupportEnemy.SPEED;
    }

    @Override
    public Movement getMovement() {
        return this.move;
    }

    @Override
    public void setPositionAfterCollision(final Direction direction) {
    }
}
