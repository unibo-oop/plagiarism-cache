package maingame.level.tile;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import maingame.level.Level;
import util.Vector2;
import util.Vector2Impl;

/** Classe ComplexWallTile. */
public class ComplexWallTile extends WallTile {

    /**
     * Costruttore per creare una tile dato un nome, il colore associato , un
     * array di sprites ed un array di ssprites superiori.
     * 
     * @param name
     *            Nome della tile.
     * @param levelColor
     *            Colore associato alla tile.
     * @param sprites
     *            Array di sprites renderizzabili dalla tile.
     * @param topSprites
     *            Array di sprites superiori renderizzabili dalla tile.
     */
    public ComplexWallTile(final String name, final int levelColor, final Sprite[] sprites, final Sprite[] topSprites) {
        super(name, levelColor, sprites, topSprites);
    }

    @Override
    public void render(final Vector2<Integer> position, final double luminosity) {
        if (luminosity > 1.0 || luminosity < 0.0) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0.0 e 1.0");
        }
        final SpriteType s = getSprite(position);
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX() * TileImpl.TILE_SIZE, position.getY() * TileImpl.TILE_SIZE),
                getSprites()[s.ordinal()], luminosity, false, false);
        Game.getGame().getLevel().addWallCoordinate(position);
    }

    @Override
    public void renderTopSprite(final Vector2<Integer> position) {
        final SpriteType s = getTopSprite(position);
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX() * TileImpl.TILE_SIZE,
                        position.getY() * TileImpl.TILE_SIZE
                                - (int) getTopSprites()[s.ordinal()].getDimension().getHeight()),
                getTopSprites()[s.ordinal()], 1.0, false, false);
    }

    private SpriteType getSprite(final Vector2<Integer> position) {
        final Level level = Game.getGame().getLevel();
        final boolean up = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.N.ordinal())) == getLevelColor();
        final boolean down = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.S.ordinal())) == getLevelColor();
        final boolean left = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.W.ordinal())) == getLevelColor();
        final boolean right = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.E.ordinal())) == getLevelColor();
        final boolean down1 = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.S.ordinal())) == TileImpl.VOID.getLevelColor();
        final boolean left1 = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.W.ordinal())) == TileImpl.VOID.getLevelColor();
        final boolean right1 = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.E.ordinal())) == TileImpl.VOID.getLevelColor();
        if (up && down) {
            return SpriteType.VERTICAL;
        }
        if (down1 && left1) {
            return SpriteType.ANGLE_DOWN_LEFT1;
        }
        if (up && right) {
            return SpriteType.ANGLE_DOWN_LEFT;
        }
        if (down1 && right1) {
            return SpriteType.ANGLE_DOWN_RIGHT1;
        }
        if (up && left) {
            return SpriteType.ANGLE_DOWN_RIGHT;
        }
        if (down1) {
            return SpriteType.HORIZONTAL1;
        }
        if (right && left) {
            return SpriteType.HORIZONTAL;
        }
        if (down && right) {
            return SpriteType.ANGLE_UP_LEFT;
        }
        if (down && left) {
            return SpriteType.ANGLE_UP_RIGHT;
        }
        if ((!right || !left) && !up && !down) {
            return SpriteType.HORIZONTAL;
        }
        if (!up) {
            return SpriteType.VERTICAL;
        }
        if (!down) {
            return SpriteType.HORIZONTAL;
        }
        return null;
    }

}
