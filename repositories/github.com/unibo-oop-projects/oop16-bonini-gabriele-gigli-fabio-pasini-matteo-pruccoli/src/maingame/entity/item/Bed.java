package maingame.entity.item;

import java.awt.Rectangle;

import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteImpl;
import maingame.level.tile.TileImpl;
import util.Vector2;
import util.Vector2Impl;

/** Classe Oggetto solo estetico. */
public class Bed extends ItemImpl {

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Bed(final Vector2<Integer> position) {
        super(position, true);
        setSprite(SpriteImpl.BED);
        getDimension().setSize(2, 3);
        getOffset().set(new Vector2Impl<Integer>(0, 2));
        setHitbox(new Rectangle(position.getX(), position.getY() - TileImpl.TILE_SIZE, 16, 16));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public Bed(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
        ScreenImpl.getScreen()
                .render(new Vector2Impl<Integer>(getPosition().getX(), getPosition().getY()
                        - (int) getSprite().getDimension().getHeight() / 2 - TileImpl.TILE_SIZE / 2), this.getSprite(),
                        1.0, false, false);
    }
}