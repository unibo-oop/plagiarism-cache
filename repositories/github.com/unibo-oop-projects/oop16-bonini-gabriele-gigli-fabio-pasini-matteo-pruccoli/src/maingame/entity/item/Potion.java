package maingame.entity.item;

import java.awt.Rectangle;

import maingame.graphics.SpriteImpl;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione pozione normale.
 */
public class Potion extends ItemImpl {
    private static final int COST10 = 10;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Potion(final Vector2<Integer> position) {
        super(position, false);
        setSprite(SpriteImpl.POTION);
        setHitbox(new Rectangle(position.getX(), position.getY(), 8, 8));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public Potion(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null
                && mobCollision(new Vector2Impl<Integer>(0, 0), false) == getLevel().getPlayer()) {

            if (getLevel().getPlayer().getHealth() + COST10 >= getLevel().getPlayer().getMaxHealth()) {
                getLevel().getPlayer().setHealth(getLevel().getPlayer().getMaxHealth());
            } else {
                getLevel().getPlayer().setHealth(getLevel().getPlayer().getHealth() + COST10);
            }
            setRemove();
            SoundImpl.POTION.play(false);
            getLevel().getPlayer().setDrinkPotion(true);
        }

    }
}