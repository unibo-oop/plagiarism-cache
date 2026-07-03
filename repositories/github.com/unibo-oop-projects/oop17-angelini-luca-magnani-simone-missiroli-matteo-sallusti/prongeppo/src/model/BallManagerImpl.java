package model;

import java.awt.Point;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.List;

import javafx.util.Pair;
import model.Ball.Combo;
import utility.GameValues;


/**
 * @author Missi
 *
 */
public final class BallManagerImpl implements BallManager {


    private Bar lastBar;
    private final Ball observedBall;
    private int i;
    private int comboteam1;
    private int comboteam2;

    /**
     * @param ball **the to be observed ball**
     */
    public BallManagerImpl(final Ball ball) { 
        this.observedBall = ball;
        this.reset();
    }

    /**
     * @param list **the list of bar hittable**
     * @param combo **the combo used by the team**
     */
    @Override
    public void collisionCheck(final List<Bar> list, final Combo combo) {
        list.forEach(bar -> {
            final Area a = new Area(this.observedBall.getHitbox());
            final Area b = new Area(bar.getHitbox());
            a.intersect(b);
            if (!a.isEmpty() && !this.lastBar.equals(bar)) {
                if (Math.abs(this.observedBall.getPosition().x + (this.observedBall.getSpeed().x / Math.abs(this.observedBall.getSpeed().x)) - bar.getPosition().x) > Math.abs(this.observedBall.getPosition().x - bar.getPosition().x)) {
                    if (this.observedBall.getCombo().equals(Combo.NULL)) {
                        this.observedBall.setCombo(combo);
                        if (bar.getPosition().getX() < GameValues.WORLDWIDTH / 2 && !combo.equals(Combo.NULL)) {
                            this.comboteam1++;
                        } else if (bar.getPosition().getX() > GameValues.WORLDWIDTH / 2 && !combo.equals(Combo.NULL)) {
                            this.comboteam2++;
                        }
                    }
                } else if (this.observedBall.getCombo().equals(Combo.STRONG)) {
                    this.lastBar = bar;
                    this.observedBall.setCombo(Combo.NULL);
                } else {
                    this.bounce(bar);
                }
            }
        });
    }

    private void bounce(final Bar bar) {
        this.observedBall.setSpeed(new Point(this.observedBall.getSpeed().x * -1,
                                             this.observedBall.getSpeed().y +  this.randomValue()));
        this.observedBall.setCombo(Combo.NULL);
        this.observedBall.setVisible(true);
        this.lastBar = bar;
    }

    private int randomValue() {
        final double i = Math.random();
        if ((this.observedBall.getSpeed().y <= 0 || this.observedBall.getSpeed().y > GameValues.RANDOMBOUNCE_Y) && i <= 1 / 3d) {
            return -GameValues.RANDOMBOUNCE_Y;
        } else if ((this.observedBall.getSpeed().y >= 0 || this.observedBall.getSpeed().y < -GameValues.RANDOMBOUNCE_Y) && i >= 2 / 3d) {
            return GameValues.RANDOMBOUNCE_Y;
        }
        return 0;
    }
    @Override
    public void comboHandler() {
        if (this.observedBall.getCombo().equals(Combo.ZIGZAG)) {
            this.i++;
            if (this.i == GameValues.ZIGZAG_TIME) {
                this.observedBall.setSpeed(new Point(this.observedBall.getSpeed().x, -this.observedBall.getSpeed().y));
                this.i = 0;
            }
        }
    }
    /**
     * @param list **the list of pickup collectible**
     * @return the list of pickUp taken
     */
    @Override
    public List<PickUp> collectedPickups(final List<PickUp> list) {
        final List<PickUp> pickUpList = new LinkedList<>();
        list.forEach(pickup -> {
            final Area a = new Area(this.observedBall.getHitbox());
            final Area b = new Area(pickup.getHitbox());
            a.intersect(b);
            if (!a.isEmpty()) {
                pickup.trigger(this.observedBall);
                pickUpList.add(pickup);
            }
        });
        return pickUpList;
    }
    /**
     * 
     * @return pair of combos of the teams for this ball (Pair(team1,team2))
     */
    @Override
    public Pair<Integer, Integer> getComboCount() {
        return new Pair<>(this.comboteam1, this.comboteam2);
    }

    /**
     * reset the memory of BallManager.
     */
    @Override
    public void reset() {
        this.lastBar = new BarImpl(null);
        this.observedBall.setVisible(true);
        this.observedBall.setCombo(Combo.NULL);
        this.i = 0;
    }
}
