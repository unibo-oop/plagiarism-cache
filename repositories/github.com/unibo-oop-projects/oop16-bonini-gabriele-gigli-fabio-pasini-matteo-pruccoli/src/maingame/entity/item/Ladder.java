package maingame.entity.item;

import java.awt.Rectangle;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteImpl;
import maingame.level.LevelEnum;
import maingame.level.tile.TileImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione scala a pioli.
 */
public class Ladder extends ItemImpl {
    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Ladder(final Vector2<Integer> position) {
        super(position, false);
        setSprite(SpriteImpl.LADDER);
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
    public Ladder(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null
                && mobCollision(new Vector2Impl<Integer>(0, 0), false) == getLevel().getPlayer()) {
            Game.getGame().setLevel(LevelEnum.MAIN, false, false);
        }

    }

    @Override
    public void render() {
        ScreenImpl
                .getScreen().render(
                        new Vector2Impl<Integer>(getPosition().getX(),
                                getPosition().getY()
                                        - ((int) getSprite().getDimension().getHeight() - TileImpl.TILE_SIZE) - 2),
                        this.getSprite(), 1.0, false, false);
    }
}