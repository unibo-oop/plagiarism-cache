package maingame.entity.item;

import java.awt.Rectangle;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteImpl;
import maingame.level.LevelEnum;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione casa.
 */
public class House extends ItemImpl {
    private Rectangle doorHitbox;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public House(final Vector2<Integer> position) {
        super(position, true);
        final int cost9 = 9;
        final int cost62 = 62;
        final int cost58 = 58;
        final int cost19 = 19;
        getDimension().setSize(8, 8);
        setSprite(SpriteImpl.HOUSE);
        getOffset().set(new Vector2Impl<Integer>(4, 4));
        setHitbox(new Rectangle(position.getX() - (int) getSprite().getDimension().getWidth() / 2 + 1,
                position.getY() - (int) getSprite().getDimension().getHeight() / 2 + cost9, cost62, cost58));
        doorHitbox = new Rectangle(position.getX() - 1, position.getY() + cost19, 2, 16);
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public House(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null && getLevel().getPlayer().getHitbox().intersects(doorHitbox)
                && getLevel().getPlayer().getLastMovement().getY() < 0) {
            SoundImpl.DOOR_OPEN.play(false);
            Game.getGame().setLevel(LevelEnum.HOUSE, false, false);
        }

    }

    @Override
    public void render() {
        ScreenImpl.getScreen()
                .render(new Vector2Impl<Integer>(getPosition().getX() - (int) getSprite().getDimension().getWidth() / 2,
                        getPosition().getY() - (int) getSprite().getDimension().getHeight() / 2), this.getSprite(), 1.0,
                        false, false);
    }

}
