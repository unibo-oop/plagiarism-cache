package maingame.entity.item;

import java.awt.Rectangle;

import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteImpl;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione chiave.
 */
public class Key extends ItemImpl {
    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Key(final Vector2<Integer> position) {
        super(position, false);
        setSprite(SpriteImpl.KEY);
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
    public Key(final int levelColor, final String name) {
        super(levelColor, name);
    }

    // Metodo render per la key, che nonostante sia nella grotta, deve brillare.
    // Quindi ha intensity 0.0
    @Override
    public void render() {
        ScreenImpl.getScreen().render(new Vector2Impl<Integer>(getPosition().getX(), getPosition().getY()),
                this.getSprite(), 0.0, false, false);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null
                && mobCollision(new Vector2Impl<Integer>(0, 0), false) == getLevel().getPlayer()) {

            getLevel().getPlayer().setGotKey(true);
            setRemove();
            SoundImpl.KEY.play(false);
        }

    }
}