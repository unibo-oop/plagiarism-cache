package maingame.entity.item;

import java.awt.Rectangle;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteImpl;
import maingame.level.LevelEnum;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione Pozzo.
 */
public class Well extends ItemImpl {
    private static final int COST14 = 14;
    private static final int COST10 = 10;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Well(final Vector2<Integer> position) {

        super(position, true);
        setSprite(SpriteImpl.WELL);
        getDimension().setSize(2, 2);
        getOffset().set(new Vector2Impl<Integer>(0, 1));
        setHitbox(new Rectangle(position.getX(), position.getY() - 2, COST14, COST10));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public Well(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null && getLevel().getPlayer().checkItemCollision(this)) {
            Game.getGame().setLevel(LevelEnum.PIT, false, false);
        }

    }

    @Override
    public void render() {
        ScreenImpl.getScreen()
                .render(new Vector2Impl<Integer>(getPosition().getX() - getOffset().getX(),
                        getPosition().getY() - (int) getSprite().getDimension().getHeight() / 2), this.getSprite(), 1.0,
                        false, false);
    }
}
