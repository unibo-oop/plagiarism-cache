package maingame.entity.item;

import java.awt.Dimension;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.SpriteSheetImpl;
import util.Vector2;

/**
 * implementazione scrigno item munizioni.
 */
public class NormalChest extends Chest {
    private static final int NORMALAMMO = 15;
    private static final int SUPERAMMO = 5;

    private boolean gathered;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public NormalChest(final Vector2<Integer> position) {
        super(position);
        setSprite(new AnimatedSprite(new Dimension(16, 16), 4, 8, SpriteSheetImpl.CHESTSET));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public NormalChest(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        super.update();
        if (!gathered && isOpened()) {
            gathered = true;
            getLevel().getPlayer().setAmmo(getLevel().getPlayer().getAmmo()[0] + NORMALAMMO,
                    getLevel().getPlayer().getAmmo()[1] + SUPERAMMO);
            getLevel().getPlayer().setGatherAmmo(true);

        }

    }

    /**
     * Ritorna il num. di NormalAmmo in questo chest.
     * 
     * @return intero normalAmmo.
     */
    public static int getNormalammo() {
        return NORMALAMMO;
    }

    /**
     * Ritorna il num. di SuperAmmo in questo chest.
     * 
     * @return intero superAmmo.
     */
    public static int getSuperammo() {
        return SUPERAMMO;
    }
}
