package maingame.entity.item;

import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione Porta con chiave necessaria.
 */
public class KeyDoor extends Door {

    private static final int LASTFRAME = 3;
    private boolean opened;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public KeyDoor(final Vector2<Integer> position) {
        super(position);
        setSolid(true);
        getDimension().setSize(2, 1);
        getOffset().set(new Vector2Impl<Integer>(0, 0));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public KeyDoor(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {

        if (getLevel().getPlayer() != null && !opened && getLevel().getPlayer().checkItemCollision(this)) {
            if (!getLevel().getPlayer().isGotKey()) {
                getLevel().getPlayer().setHasnotKey(true);
            } else {
                setAnimationStarted(true);
            }

            if (isAnimationStarted()) {
                if (!isSoundStarted()) {
                    setSoundStarted(true);
                    SoundImpl.DOOR_OPEN.play(false);
                }
                getSprite().update();
                if (getSprite().getCount() == 1) {
                    opened = true;
                    setSolid(false);
                    getLevel().findSolidTiles();
                    getSprite().setFrame(LASTFRAME);
                    getLevel().getPlayer().setGotKey(false);
                }
            }
        }
    }

}
