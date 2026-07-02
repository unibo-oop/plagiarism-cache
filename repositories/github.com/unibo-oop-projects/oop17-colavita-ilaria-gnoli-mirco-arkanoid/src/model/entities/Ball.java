package model.entities;

import java.util.Optional;

import javafx.geometry.HorizontalDirection;
import javafx.geometry.Side;
import javafx.geometry.VerticalDirection;
import javafx.util.Pair;

/**
 * Classe concreta che modella una pallina. Implementa {@link IEntity}.
 * @author Gnoli Mirco
 */

public class Ball implements IEntityThatMoves {

    private static final int ANGLE_ZERO = 0;
    private static final int ANGLE_RECT = 90;
    private static final int ANGLE_FLAT = 180;
    private static final int ANGLE_270 = 270;
    private static final int ANGLE_MAX = 360;
    /*
    private static final int CHANGE_ANGLE = 10;
    private int countCollision;
    */

    private Pair<Integer, Integer> position;
    private int radius;
    private int speed;
    private int angle;
    private BallType type;

    /**
     * Costruttore per creare una {@link Ball}.
     * 
     * @param x -Coordinata X del centro della pallina.
     * @param y -Coordinata y del centro della pallina.
     * @param radius - Raggio della pallina.
     * @param speed - Velocità della pallina.
     * @param angle -Angolo di direzione. L'angolo è calcolato in sessagesimali, dall'asse delle ascisse in senso orario.
     * @param type - Tipologia di pallina, a scelta fra {@link BallType}
     * 
     * @see {@link BallBuilder}
     */
    //controllare la velocità della pallina <13 >0
    public Ball(final int x, final int y, final int radius, final int speed, final int angle, final BallType type) {
        this.position = new Pair<>(x, y);
        this.radius = radius;
        this.speed = speed;
        this.angle = Math.abs(angle) % ANGLE_MAX;
        this.type = type;
    }

    @Override
    public final void refreshPosition() {
        final int newX = (int) (this.getPosition().getKey() + this.speed * Math.cos(Math.toRadians(angle)));
        final int newY = (int) (this.getPosition().getValue() + this.speed * Math.sin(Math.toRadians(angle)));
        this.setPosition(newX, newY);
    }

    /**
     * Metodo che, preso in input la posizione della collisione, devia la pallina modificando l'angolo della stessa.
     * 
     * @param pos - {@link Side} di collisione della pallina.
     */
    public void doOnCollision(final Side pos) {
        switch (pos) {
        case TOP: case BOTTOM:
            this.angle = ANGLE_MAX - this.angle;
            break;

        case RIGHT:
            if (this.angle < ANGLE_RECT) {
                this.angle = ANGLE_FLAT - this.angle;
            } else {
                this.angle = (ANGLE_MAX - this.angle) + ANGLE_FLAT;
            }
            break;

        case LEFT:
            if (this.angle < ANGLE_FLAT) {
                this.angle = ANGLE_FLAT - this.angle;
            } else {
                this.angle = ANGLE_MAX - (this.angle - ANGLE_FLAT);
            }
            break;

        default:
            //da fare l'eccezione
            System.out.println("throw new Errore");
        }
    }

    /**
     * Setta la nuova tipologia di pallina.
     * @param type - {@link BallType}
     */
    public final void setType(final BallType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     * I parametri in input settano il centro della pallina.
     */
    @Override
    public final void setPosition(final int newX, final int newY) {
        this.position = new Pair<>(newX, newY);
    }

    @Override
    public final int getMinX() {
        return this.position.getKey() - getRadius();
    }

    @Override
    public final int getMaxX() {
        return this.position.getKey() + getRadius();
    }

    @Override
    public final int getMinY() {
        return this.position.getValue() - getRadius();
    }

    @Override
    public final int getMaxY() {
        return this.position.getValue() + getRadius();
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Ritorna il raggio della pallina.
     *
     * @return int
     */
    public final int getRadius() {
        return this.radius;
    }

    /**
     * Ritorna la velocità della pallina. 
     * 
     * @return int
     */
    public final int getSpeed() {
        return speed;
    }

    /**
     * Ritorna l'angolo di direzione della pallina. 
     * 
     * @return int
     */
    public final int getAngle() {
        return angle;
    }

    /**
     * Ritorna la tipologia della pallina. 
     * 
     * @return {@link BallType}
     */
    public final BallType getType() {
        return this.type;
    }

    /**
     * Metodo per ritornare la direzione.
     * 
     * @return Pair(Optional(HorizontalDirection), Optional(VerticalDirection))
     */
    public Pair<Optional<HorizontalDirection>, Optional<VerticalDirection>> getDirection() {
        Optional<HorizontalDirection> oriz = Optional.empty();
        Optional<VerticalDirection> vert = Optional.empty();

        if (this.angle > 0 && this.angle < ANGLE_FLAT) {
            vert = Optional.of(VerticalDirection.DOWN);
        } else if (this.angle > ANGLE_FLAT && this.angle < ANGLE_MAX) {
            vert = Optional.of(VerticalDirection.UP);
        }

        if (this.angle > ANGLE_270 && this.angle <= ANGLE_MAX || this.angle >= ANGLE_ZERO && this.angle < ANGLE_RECT) {
            oriz = Optional.of(HorizontalDirection.RIGHT);
        } else if (this.angle > 90 && this.angle < ANGLE_270) {
            oriz = Optional.of(HorizontalDirection.LEFT);
        }

        return new Pair<>(oriz, vert);
    }

    /**
     * @param entity - {@link IEntity} contro cui rimbalza.
     */
    public void bounce(final IEntity entity) {
        this.type.bounce(this, entity);
    }
}
