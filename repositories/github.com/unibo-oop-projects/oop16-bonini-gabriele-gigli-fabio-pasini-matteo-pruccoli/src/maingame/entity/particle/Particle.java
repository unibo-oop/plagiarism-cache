package maingame.entity.particle;

import java.awt.Rectangle;
import java.util.Random;

import maingame.entity.EntityImpl;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import util.Vector2;
import util.Vector2Impl;

/** Classe Particle. */
public class Particle extends EntityImpl {

    private final Sprite sprite;
    private final int life;
    private int timeParticle;
    private final Vector2<Double> position;
    private double zPosition;
    private final Vector2<Double> movement;
    private double zMovement;
    private final Random random = new Random();
    // Costanti per random e calcoli Particle:
    private static final int COST20 = 20;
    private static final int COST10 = 10;
    private static final int PARTSIZE = 2;
    private static final double COST3 = 3.0;
    private static final double COST08 = 0.8;
    private static final double COST05 = 0.5;
    private static final double COST02 = 0.2;
    private static final double COST01 = 0.1;

    /**
     * Costruttore di particle.
     * 
     * @param position
     *            posizione.
     * @param life
     *            tempo che rimarrà nel gioco prima di svanire.
     * @param spriteparticle
     *            sprite associato.
     */
    public Particle(final Vector2<Integer> position, final int life, final Sprite spriteparticle) {
        this.position = new Vector2Impl<Double>(position.getX().doubleValue(), position.getY().doubleValue());
        zPosition = random.nextFloat() + COST3;
        this.life = life + (random.nextInt(COST20) - COST10);
        sprite = spriteparticle;
        setHitbox(new Rectangle(position.getX().intValue(), position.getY().intValue(), PARTSIZE, PARTSIZE));
        movement = new Vector2Impl<Double>(random.nextGaussian() * COST08, random.nextGaussian() * COST08);
        zMovement = 0;
    }

    @Override
    public void update() {
        timeParticle++;
        if (timeParticle == Integer.MAX_VALUE - 1) {
            timeParticle = 0;
        }
        if (timeParticle > life) {
            setRemove();
        }

        zMovement -= COST01;

        if (zPosition < 0) {
            zPosition = 0;
            movement.set(movement.getX() * -COST02, movement.getY() * -COST02);
            zMovement *= -COST01;
        }

        if (tileCollision(new Vector2Impl<Integer>((int) Math.floor(movement.getX()),
                (int) Math.floor(movement.getY() - zMovement)))) {
            movement.set(movement.getX() * -COST05, movement.getY() * -COST05);
            zMovement *= -COST05;
        }

        position.set(position.getX() + movement.getX(), position.getY() + movement.getY());
        zPosition += zMovement;
        getHitbox().setLocation(position.getX().intValue(), (int) (position.getY() - zPosition));
    }

    /**
     * Render del particellare.
     * 
     */
    public void render() {
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX().intValue(), (int) (position.getY() - zPosition)), sprite, 1.0,
                false, false);
    }
}