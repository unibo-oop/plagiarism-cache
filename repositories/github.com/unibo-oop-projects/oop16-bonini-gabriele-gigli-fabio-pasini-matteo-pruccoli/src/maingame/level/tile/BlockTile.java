package maingame.level.tile;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import maingame.level.Level;
import util.Vector2;
import util.Vector2Impl;

/** Classe BlockTile. */
public class BlockTile extends TileImpl {
    private final Sprite sprite;
    private final Sprite[] sprites;

    /**
     * Crea una BlockTile a partire da un nome , una sprite,un array di sprites
     * ed un colore associato alla tile.
     * 
     * @param name
     *            Nome della tile.
     * @param levelColor
     *            Colore associato alla tile.
     * @param sprites
     *            Sprites renderizzabili dalla tile.
     * @param sprite
     *            Sprite di default della tile.
     */
    public BlockTile(final String name, final int levelColor, final Sprite[] sprites, final Sprite sprite) {
        super(name, levelColor, null);
        if (sprites.length != 8) {
            throw new IllegalArgumentException("L'array di sprites deve avere lunghezza 8");
        }
        this.sprite = sprite;
        this.sprites = sprites.clone();
    }


    private Sprite getSprite(final Vector2<Integer> position) {
        final Level level = Game.getGame().getLevel();
        final boolean up = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.N.ordinal())) != getLevelColor();
        final boolean down = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.S.ordinal())) != getLevelColor();
        final boolean left = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.W.ordinal())) != getLevelColor();
        final boolean right = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.E.ordinal())) != getLevelColor();

        if (up && left) {
            setSprite(sprites[TerrainTile.Cardinal.NW.ordinal()]);
        } else if (up && right) {
            setSprite(sprites[TerrainTile.Cardinal.NE.ordinal()]);
        } else if (down && left) {
            setSprite(sprites[TerrainTile.Cardinal.SW.ordinal()]);
        } else if (down && right) {
            setSprite(sprites[TerrainTile.Cardinal.SE.ordinal()]);
        } else if (up) {
            setSprite(sprites[TerrainTile.Cardinal.N.ordinal()]);
        } else if (down) {
            setSprite(sprites[TerrainTile.Cardinal.S.ordinal()]);
        } else if (left) {
            setSprite(sprites[TerrainTile.Cardinal.W.ordinal()]);
        } else if (right) {
            setSprite(sprites[TerrainTile.Cardinal.E.ordinal()]);
        } else {
            setSprite(sprite);
        }
        return getSprite();
    }

    @Override
    public void render(final Vector2<Integer> position, final double luminosity) {
        if (luminosity > 1.0 || luminosity < 0.0) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0.0 e 1.0");
        }
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX() * TileImpl.TILE_SIZE, position.getY() * TileImpl.TILE_SIZE),
                getSprite(position), luminosity, false, false);
    }

}
