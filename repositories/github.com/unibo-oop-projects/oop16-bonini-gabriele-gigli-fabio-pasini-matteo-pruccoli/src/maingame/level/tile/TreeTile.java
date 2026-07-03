package maingame.level.tile;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import util.Vector2;
import util.Vector2Impl;

/** Clase TreeeTile. */
public class TreeTile extends TileImpl {
    private final Sprite topSprite;

    /**
     * Costruttore per creare una tile dato un nome, le proprietà della tile ,
     * il colore associato ed una sprite.
     * 
     * @param name
     *            Nome della tile.
     * @param levelColor
     *            Colore associato alla tile.
     * @param sprite
     *            Immagine da visualizzare quando la tile viene renderizzata.
     * @param topSprite
     *            Immagine da visualizzare sopra la tile.
     */

    public TreeTile(final String name, final int levelColor, final Sprite sprite, final Sprite topSprite) {
        super(name, levelColor, sprite);
        this.topSprite = topSprite;
        this.makeSolid();
    }

    @Override
    public void render(final Vector2<Integer> position, final double luminosity) {
        if (luminosity > 1.0 || luminosity < 0.0) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0.0 e 1.0");
        }
        super.render(position, luminosity);
        Game.getGame().getLevel().addTreeCoordinate(position);
    }

    /**
     * Renderizza la sprite superiore.
     * 
     * @param position
     *            Coordinate della tile.
     */

    public void renderTopSprite(final Vector2<Integer> position) {
        ScreenImpl.getScreen()
                .render(new Vector2Impl<Integer>(
                        position.getX() * TileImpl.TILE_SIZE - (int) topSprite.getDimension().getWidth() / 4,
                        position.getY() * TileImpl.TILE_SIZE - (int) topSprite.getDimension().getHeight()), topSprite,
                        1.0, false, false);
    }

}
