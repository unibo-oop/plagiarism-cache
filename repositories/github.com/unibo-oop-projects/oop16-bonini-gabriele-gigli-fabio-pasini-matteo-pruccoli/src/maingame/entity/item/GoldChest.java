package maingame.entity.item;

import java.awt.Dimension;

import maingame.game.Game;
import maingame.graphics.AnimatedSprite;
import maingame.graphics.SpriteSheetImpl;
import util.Vector2;

/**
 * Implementazione Gold chest.
 */
public class GoldChest extends Chest {
    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public GoldChest(final Vector2<Integer> position) {
        super(position);
        setSprite(new AnimatedSprite(new Dimension(16, 16), 4, 8, SpriteSheetImpl.GOLDCHESTSET));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public GoldChest(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {

        super.update();
        if (isOpened() && !Game.getGame().isGamewin()) {
            Game.getGame().setGameWin(true);
        }

    }

}
