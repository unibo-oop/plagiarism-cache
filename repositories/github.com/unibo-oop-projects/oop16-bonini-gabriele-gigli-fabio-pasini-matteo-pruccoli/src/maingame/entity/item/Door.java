package maingame.entity.item;

import java.awt.Dimension;
import java.awt.Rectangle;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteSheetImpl;
import maingame.level.tile.TileImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione porta.
 */
public abstract class Door extends ItemImpl {

    private AnimatedSprite sprite;
    private boolean animationStarted;
    private boolean soundStarted;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Door(final Vector2<Integer> position) {
        super(position, true);
        final int cost24 = 24;
        getDimension().setSize(2, 1);
        getOffset().set(new Vector2Impl<Integer>(0, 0));
        sprite = new AnimatedSprite(new Dimension(16, cost24), 4, 8, SpriteSheetImpl.DOORSET);
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
    public Door(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        ScreenImpl.getScreen()
                .render(new Vector2Impl<Integer>(getPosition().getX(),
                        getPosition().getY() - ((int) sprite.getDimension().getHeight() - TileImpl.TILE_SIZE)),
                        this.sprite, 1.0, false, false);
    }

    @Override
    public AnimatedSprite getSprite() {
        return sprite;
    }

    /**
     * @return se l'animazione è iniziata
     */
    public boolean isAnimationStarted() {
        return animationStarted;
    }

    /**
     * @param animationStarted
     *            setter di animationstarted
     */
    public void setAnimationStarted(final boolean animationStarted) {
        this.animationStarted = animationStarted;
    }

    /**
     * Setta l'inizio del suono.
     * 
     * @param soundStarted
     *            booleano sound started.
     */
    public void setSoundStarted(final boolean soundStarted) {
        this.soundStarted = soundStarted;
    }

    /**
     * Ritorna se il suono è iniziato.
     * 
     * @return booleano suono iniziato.
     */
    public boolean isSoundStarted() {
        return soundStarted;
    }
}
