package maingame.entity.mob.sheep;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Random;

import maingame.entity.mob.MobImpl;
import maingame.game.Game;
import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteSheetImpl;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Classe sheep, implementazione di un mob neutro.
 */
public class Sheep extends MobImpl {
    private int beUpdate;
    private double distance;
    private final Random random = new Random();
    private static final int BE = 600;
    private static final int DISTANCE_TO_BE = 100;
    private static final int FREQUENCY_BE = 15;
    private static final int MIN_TIME_TO_NEXT_BE = 520;
    private static final int NUM_OF_ANIM = 1;
    private static final Dimension DIM_ANIM = new Dimension(1, 3);
    private static final int TIME_TO_REFRESH_ANIM = 5;
    private static final int EXP = 50;

    /**
     * Costruttore per inizializzare l'entità all'interno del game.
     * 
     * @param position
     *            positione di spwan
     */
    public Sheep(final Vector2<Integer> position) {
        super(position, false);
        // 1 possibili tipi di animazione es(moving)
        // dimensione tipi di animazione
        // 5 ogni quanto modifico la sprita in movimento
        getAnimations().add(AnimatedSprite.createAnimation(NUM_OF_ANIM, new Dimension[] { DIM_ANIM },
                new int[] { TIME_TO_REFRESH_ANIM }, SpriteSheetImpl.SHEEP));
        setHitbox(new Rectangle(position.getX(), position.getY(), 8, 8));
        setAnimation(getAnimations().get(getSkin()).get(Animation.DOWN.ordinal()));
        setSprite((getAnimation().getSprite()));
        setRenderXOffset((int) getSprite().getDimension().getWidth() / 4);
        beUpdate = 0;
    }

    /**
     * Costruttore per inizializzare l'entità all'interno del livello.
     * 
     * @param levelColor
     *            colore corrispondente al punto di spwan
     * @param name
     *            nome entità
     */
    public Sheep(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (!Game.getGame().isEditor()) {
            if (getWalking()) {
                getAnimation().update();
            } else {
                getAnimation().setFrame(0);
            }
            super.randomMove();
            super.update();
            if (getHealth() <= 0) {
                SoundImpl.SHEEP.play(false);
                if (getLevel().getPlayer().getHealth() > 0) {
                    getLevel().getPlayer().setExp(EXP);
                    ScreenImpl.getScreen().setExpAnim(true, EXP);
                }
            }

            if (getMovement().getX() != 0 || getMovement().getY() != 0) {
                move(getMovement(), false);
                getMovement().set(0, 0);
                setWalking(true);
            } else if ((getTime() % 2) == 0) {
                setWalking(false);
            }
            distance = Vector2Impl.getDistance(getPosition(), getLevel().getPlayer().getPosition());
            if (this.random.nextInt(BE) < FREQUENCY_BE && beUpdate <= 0 && distance < DISTANCE_TO_BE) {
                SoundImpl.SHEEP.play(false);
                beUpdate = MIN_TIME_TO_NEXT_BE;
            }
            if (beUpdate > 0) {
                beUpdate--;
            }
        }
    }

}
