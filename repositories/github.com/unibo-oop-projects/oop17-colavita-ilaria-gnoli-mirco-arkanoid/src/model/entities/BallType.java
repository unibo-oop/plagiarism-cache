package model.entities;

import java.util.Optional;

import javafx.geometry.HorizontalDirection;
import javafx.geometry.Side;
import javafx.geometry.VerticalDirection;
import javafx.util.Pair;
import utility.CollisionUtility;

/**
 * Semplice enumerazione che definisce le diverse tipologie di pallina e, per ciascuna, un tipo di rimbalzo.
 * @author Gnoli Mirco
 */
//fare controllo mattoneIndistruttibile quando disponibile
public enum BallType {
    /**
     * Pallina standard, rimbalzo standard.
     *
     */ 
    STANDARD_BALL {
        /**
         * {@inheritDoc}
         */
        public void bounce(final Ball ball, final IEntity ent) {
            if (ent instanceof Wall || ent instanceof Brick) {
                standardBounce(ball, ent);
            } else if (ent instanceof Bar) {
                barBounce(ball, (Bar) ent);
            }
        }
    },

    /**
     * Pallina di fuoco, non rimbalza contro i {@link Brick} che si possono rompere.
     *
     */
    FIRE_BALL {
        /**
         * {@inheritDoc}
         */
        public void bounce(final Ball ball, final IEntity ent) {
            if (ent instanceof Wall || (ent instanceof Brick && ((Brick) ent).isIndistruttibile())) { // qui controllo
                standardBounce(ball, ent);
            } else if (ent instanceof Bar) {
                barBounce(ball, (Bar) ent);
            }
        }
    },

    /**
     * Pallina magnetica, quando collide con la {@link Bar}, ci resta attaccata.
     *
     */
    MAGNET_BALL {
        /**
         * {@inheritDoc}
         */
        @Override
        public void bounce(final Ball ball, final IEntity ent) {
            if (ent instanceof Wall || ent instanceof Brick) {
                standardBounce(ball, ent);
            } /*else if (ent instanceof Bar) {
                //do nothing
            }*/
        }
    };

    /**
     * Metodo utilizzato per il rimbalzo della pallina.
     * 
     * @param ball - {@link Ball} che rimbalza
     * @param ent - {@link IEntity} contro cui rimbalza la pallina
     */
    public abstract void bounce(Ball ball, IEntity ent);

    /**
     * Rimbalzo della pallina contro la barra.
     * Se cade nel terzo più a destra, rimbalza verso destra, viceversa a sinistra.
     * Se cade nel terzo centrale, rimbalza in maniera standard come si ci aspetta.
     *
     * @param ball
     * @param bar
     */
    private static void barBounce(final Ball ball, final Bar bar) {
        ball.setPosition(ball.getPosition().getKey(), bar.getMinY() - ball.getRadius());

        if (ball.getPosition().getKey() < bar.getMinX() + bar.getLenght() / 3) {
            if (ball.getDirection().getKey().isPresent() && ball.getDirection().getKey().get() == HorizontalDirection.RIGHT) {
                ball.doOnCollision(Side.RIGHT);
            }
        } else if (ball.getPosition().getKey() > bar.getMaxX() - bar.getLenght() / 3) {
            if (ball.getDirection().getKey().isPresent() && ball.getDirection().getKey().get() == HorizontalDirection.LEFT) {
                ball.doOnCollision(Side.LEFT);
            }
        }

        ball.doOnCollision(Side.BOTTOM);
    }

    /**
     * Rimbalzo standard della {@link Ball} ball a contatto con {@link IEntity} ent.
     * 
     * @param ball
     * @param ent
     */
    private static void standardBounce(final Ball ball, final IEntity ent) {
        Pair<Optional<HorizontalDirection>, Optional<VerticalDirection>> dir = ball.getDirection();

        if (dir.getKey().isPresent()) {
            if (dir.getKey().get().equals(HorizontalDirection.RIGHT)) {
                if (CollisionUtility.firstCollidedWithLeftestVerticalBound(ball, ent)) {
                    ball.setPosition(ent.getMinX() - ball.getRadius(), ball.getPosition().getValue());
                    ball.doOnCollision(Side.RIGHT);
                }
            } else {
                if (CollisionUtility.firstCollidedWithRightestVerticalBound(ball, ent)) {
                    ball.setPosition(ent.getMaxX() + ball.getRadius(), ball.getPosition().getValue());
                    ball.doOnCollision(Side.LEFT);
                }
            }
        }

        if (dir.getValue().isPresent()) {
            if (dir.getValue().get().equals(VerticalDirection.UP)) {
                if (CollisionUtility.firstCollidedWithLowerHorizontalBound(ball, ent)) {
                    ball.setPosition(ball.getPosition().getKey(), ent.getMaxY() + ball.getRadius());
                    ball.doOnCollision(Side.TOP);
                }
            } else {
                if (CollisionUtility.firstCollidedWithTopHorizontalBound(ball, ent)) {
                    ball.setPosition(ball.getPosition().getKey(), ent.getMinY() - ball.getRadius());
                    ball.doOnCollision(Side.BOTTOM);
                }
            }
        }
    }
}
