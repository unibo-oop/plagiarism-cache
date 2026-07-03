package maingame.entity.item;

import java.awt.Dimension;

import maingame.entity.EntityImpl;
import maingame.graphics.ScreenImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * implementazione di un item generico.
 */
public abstract class ItemImpl extends EntityImpl implements Item {
    private final String name;
    private final int levelColor;
    private boolean solid;
    private final Vector2<Integer> offset = new Vector2Impl<Integer>(0, 0);
    private final Dimension dimension = new Dimension();

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     * @param solid
     *            se � solido
     */
    public ItemImpl(final Vector2<Integer> position, final boolean solid) {
        super(position);
        name = null;
        levelColor = 0;
        this.solid = solid;
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public ItemImpl(final int levelColor, final String name) {
        this.name = name;
        this.levelColor = levelColor;
    }

    @Override
    public abstract void update();

    @Override
    public void render() {
        ScreenImpl.getScreen().render(new Vector2Impl<Integer>(getPosition().getX(), getPosition().getY()),
                this.getSprite(), 1.0, false, false);
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public int getLevelColor() {
        return levelColor;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Imposta l'oggetto a solito.
     * 
     * @param sol
     *            setta se è solido
     */
    public void setSolid(final boolean sol) {
        solid = sol;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Vector2<Integer> getOffset() {
        return offset;
    }

}