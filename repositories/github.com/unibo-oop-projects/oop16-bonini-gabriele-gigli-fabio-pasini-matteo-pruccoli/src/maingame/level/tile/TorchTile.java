package maingame.level.tile;

import maingame.game.Game;
import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import util.Vector2;
import util.Vector2Impl;

/** Classe TorchTile. */
public class TorchTile extends TileImpl {

    private final AnimatedSprite topSprite;

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
     * @param lightRadius
     *            Raggio di luminosità della tile.
     * @param topSprite
     *            Immagine da visualizzare sopra la tile.
     */
    public TorchTile(final String name, final int levelColor, final Sprite sprite, final int lightRadius,
            final AnimatedSprite topSprite) {
        super(name, levelColor, sprite);
        this.topSprite = topSprite;
        this.makeSolid();
        this.makeEmitter(lightRadius);
    }

    @Override
    public void update() {
        topSprite.update();
    }

    @Override
    public void render(final Vector2<Integer> position, final double luminosity) {
        if (luminosity > 1.0 || luminosity < 0.0) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0.0 e 1.0");
        }
        super.render(position, luminosity);
        Game.getGame().getLevel().addTorchCoordinate(position);
    }

    /**
     * Renderizza la sprite superiore.
     * 
     * @param position
     *            Coordinate della tile.
     */

    public void renderTopSprite(final Vector2<Integer> position) {
        ScreenImpl
                .getScreen().render(
                        new Vector2Impl<Integer>(position.getX() * TileImpl.TILE_SIZE,
                                position.getY() * TileImpl.TILE_SIZE
                                        - (int) topSprite.getSprite().getDimension().getHeight()),
                        topSprite.getSprite(), 0.0, false, false);
    }

}
