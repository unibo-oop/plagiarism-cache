package maingame.entity.item;

import java.awt.Rectangle;

import maingame.graphics.SpriteImpl;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione pozione grande.
 */
public class BigPotion extends ItemImpl {
    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public BigPotion(final Vector2<Integer> position) {
        super(position, false);
        setSprite(SpriteImpl.BIGPOTION);
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
    public BigPotion(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null
                && mobCollision(new Vector2Impl<Integer>(0, 0), false) == getLevel().getPlayer()) {
            getLevel().getPlayer().setHealth(getLevel().getPlayer().getMaxHealth());
            setRemove();
            SoundImpl.POTION.play(false);
            getLevel().getPlayer().setDrinkPotion(true);
        }
    }

}