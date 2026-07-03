package maingame.entity.item;

import java.awt.Rectangle;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/** Classe astratta Chest. */
public abstract class Chest extends ItemImpl {
    private AnimatedSprite sprite;
    private boolean animationStarted;
    private boolean soundStarted;
    private static final int LASTFRAME = 3;
    private boolean opened;

    /**
     * costruttore per gioco.
     * 
     * @param position
     *            posizione di spawn
     */
    public Chest(final Vector2<Integer> position) {
        super(position, true);
        getDimension().setSize(2, 2);
        getOffset().set(new Vector2Impl<Integer>(0, 1));
        setHitbox(new Rectangle(position.getX(), position.getY(), 16, 10));
    }

    /**
     * costruttore per editor e level.
     * 
     * @param levelColor
     *            colore di spawn
     * @param name
     *            nome dell'oggetto
     */
    public Chest(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {
        if (getLevel().getPlayer() != null && !opened) {

            if (getLevel().getPlayer().checkItemCollision(this)) {
                animationStarted = true;
            }
            if (animationStarted) {
                if (!soundStarted) {
                    soundStarted = true;
                    SoundImpl.DOOR_OPEN.play(false);
                }
                sprite.update();

                if (sprite.getCount() == 1) {
                    opened = true;
                    sprite.setFrame(LASTFRAME);
                }
            }

        }
    }

    @Override
    public void render() {
        ScreenImpl.getScreen()
                .render(new Vector2Impl<Integer>(getPosition().getX(),
                        getPosition().getY() - (int) sprite.getDimension().getHeight() / 2), this.sprite.getSprite(),
                        1.0, false, false);
    }

    /**
     * Setta la sprite del chest.
     * 
     * @param sprite
     *            sprite
     */
    public void setSprite(final AnimatedSprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public AnimatedSprite getSprite() {
        return sprite;
    }

    /**
     * Ottiene booleano aperto o chiuso.
     * 
     * @return se è aperto oppure no
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     * Setta se aperto.
     * 
     * @param opened
     *            booleano aperto si o no.
     */
    public void setOpened(final boolean opened) {
        this.opened = opened;
    }

}
