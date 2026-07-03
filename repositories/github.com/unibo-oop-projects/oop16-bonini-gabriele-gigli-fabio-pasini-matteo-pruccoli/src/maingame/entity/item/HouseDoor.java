package maingame.entity.item;

import maingame.game.Game;
import maingame.level.LevelEnum;
import maingame.sound.SoundImpl;
import util.Vector2;

/**
 * Implementazione porta.
 */
public class HouseDoor extends Door {
    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public HouseDoor(final Vector2<Integer> position) {
        super(position);
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public HouseDoor(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null) {
            if (getLevel().getPlayer().checkItemCollision(this)) {
                setAnimationStarted(true);
            }
            if (isAnimationStarted()) {
                if (!isSoundStarted()) {
                    setSoundStarted(true);
                    SoundImpl.DOOR_OPEN.play(false);
                }
                getSprite().update();
                if (getSprite().getCount() == 1) {
                    getSprite().setFrame(0);
                    getSprite().resetCount();
                    setAnimationStarted(false);
                    setSoundStarted(false);
                    Game.getGame().setLevel(LevelEnum.MAIN, false, false);
                }
            }
        }

    }
}
