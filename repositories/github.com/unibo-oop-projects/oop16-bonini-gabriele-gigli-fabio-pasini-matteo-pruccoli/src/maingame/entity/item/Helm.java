package maingame.entity.item;

import java.awt.Rectangle;

import maingame.graphics.SpriteImpl;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione elmo.
 */
public class Helm extends ItemImpl {
    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Helm(final Vector2<Integer> position) {
        super(position, false);
        setSprite(SpriteImpl.HELM);
        setHitbox(new Rectangle(position.getX(), position.getY(), 16, 8));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public Helm(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null
                && mobCollision(new Vector2Impl<Integer>(0, 0), false) == getLevel().getPlayer()) {
            final int cost20 = 20;
            getLevel().getPlayer().setSkin(1);
            getLevel().getPlayer().setDamage(getLevel().getPlayer().getDamage() + cost20);
            getLevel().getPlayer().setHealth(getLevel().getPlayer().getMaxHealth());
            setRemove();
            SoundImpl.HELM.play(false);
            getLevel().getPlayer().setDrinkPotion(true);
        }

    }
}
