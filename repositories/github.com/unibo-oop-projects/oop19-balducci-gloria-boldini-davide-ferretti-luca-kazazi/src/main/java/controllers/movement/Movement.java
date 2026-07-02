package controllers.movement;

import java.util.Objects;

import model.ID;
import model.enemy.Enemy;
import model.gameObject.GameObject;
import model.player.Player;
import other.Pair;

public class Movement implements MovementInterface {

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private double speed;
    private final GameObject gameObj;

    /**
     * Constructor for Movement.
     * 
     * @param obj
     * @param speed
     */
    public Movement(final GameObject obj, final double speed) {
        this.up = false;
        this.down = false;
        this.right = false;
        this.left = false;
        this.gameObj = Objects.requireNonNull(obj);
        this.speed = Objects.requireNonNull(speed);
    }

    @Override
    public void updateSpeed(final double speed) {
        this.speed = Objects.requireNonNull(speed);
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public boolean isUp() {
        return up;
    }

    @Override
    public void setUp(final boolean up) {
        this.up = Objects.requireNonNull(up);
    }

    @Override
    public boolean isDown() {
        return down;
    }

    @Override
    public void setDown(final boolean down) {
        this.down = Objects.requireNonNull(down);
    }

    @Override
    public boolean isRight() {
        return right;
    }

    @Override
    public void setRight(final boolean right) {
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public boolean isLeft() {
        return left;
    }

    @Override
    public void setLeft(final boolean left) {
        this.left = Objects.requireNonNull(left);
    }

    @Override
    public void reset() {
        this.up = false;
        this.down = false;
        this.right = false;
        this.left = false;
    }

    private Pair<Double, Double> movementVelocity(final double newX, final double newY) {
        return new Pair<Double, Double>(newX, newY);
    }

    @Override
    public void moveEntity() {
        if (this.isUp()) {
            gameObj.setVelocity(this.movementVelocity(gameObj.getVelocity().getX(), -speed));
            if (gameObj.getId() == ID.PLAYER) {
                final Player p = (Player) gameObj;
                p.getAnimation().runAnimation(p.getListUP());
            } else {
                final Enemy e = (Enemy) gameObj;
                e.getAnimation().runAnimation(e.getListUp());
            }
        } else if (!this.isDown()) {
            gameObj.setVelocity(this.movementVelocity(gameObj.getVelocity().getX(), 0.0));
        }
        if (this.isDown()) {
            gameObj.setVelocity(this.movementVelocity(gameObj.getVelocity().getX(), speed));
            if (gameObj.getId() == ID.PLAYER) {
                final Player p = (Player) gameObj;
                p.getAnimation().runAnimation(p.getListDOWN());
            } else {
                final Enemy e = (Enemy) gameObj;
                e.getAnimation().runAnimation(e.getListDown());
            }
        } else if (!this.isUp()) {
            gameObj.setVelocity(this.movementVelocity(gameObj.getVelocity().getX(), 0.0));
        }
        if (this.isRight()) {
            gameObj.setVelocity(this.movementVelocity(speed, gameObj.getVelocity().getY()));
            if (gameObj.getId() == ID.PLAYER) {
                final Player p = (Player) gameObj;
                p.getAnimation().runAnimation(p.getListRIGHT());
            } else {
                final Enemy e = (Enemy) gameObj;
                e.getAnimation().runAnimation(e.getListRight());
            }
        } else if (!this.isLeft()) {
            gameObj.setVelocity(this.movementVelocity(0.0, gameObj.getVelocity().getY()));
        }
        if (this.isLeft()) {
            gameObj.setVelocity(this.movementVelocity(-speed, gameObj.getVelocity().getY()));
            if (gameObj.getId() == ID.PLAYER) {
                final Player p = (Player) gameObj;
                p.getAnimation().runAnimation(p.getListLEFT());
            } else {
                final Enemy e = (Enemy) gameObj;
                e.getAnimation().runAnimation(e.getListLeft());
            }
        } else if (!this.isRight()) {
            gameObj.setVelocity(this.movementVelocity(0.0, gameObj.getVelocity().getY()));
        }
    }

}
