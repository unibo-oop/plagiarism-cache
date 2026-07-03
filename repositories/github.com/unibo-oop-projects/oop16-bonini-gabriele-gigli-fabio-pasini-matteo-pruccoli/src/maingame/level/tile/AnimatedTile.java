package maingame.level.tile;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import util.Vector2;
import util.Vector2Impl;

/**Classe AnimatedTile.*/
public class AnimatedTile extends TileImpl {

    private final AnimatedSprite animSprite;

    /**
     * Crea una tile animata a partire da un nome , una sprite ed un colore associato
     * alla tile.
     * 
     * @param name
     *            Nome della tile.
     * @param levelColor
     *            Colore associato alla tile.
     * @param sprite
     *            Sprite associata alla tile.aa
     */
    public AnimatedTile(final String name, final int levelColor, final AnimatedSprite sprite) {
        super(name, levelColor, sprite);
        animSprite = sprite;
    }

    @Override
    public void update() {
        animSprite.update();
    }

    @Override
    public void render(final Vector2<Integer> position, final double luminosity) {
        if (luminosity > 1.0 || luminosity < 0.0) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0.0 e 1.0");
        }
        setSprite(animSprite.getSprite());
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX() * TileImpl.TILE_SIZE, position.getY() * TileImpl.TILE_SIZE),
                getSprite(), luminosity, false, false);
    }

}
