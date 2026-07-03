package maingame.level.tile;

import maingame.game.Game;
import maingame.graphics.Sprite;
import maingame.level.Level;
import util.Vector2;

/** Classe WallTile. */
public abstract class WallTile extends TileImpl {

    private final Sprite[] sprites;
    private final Sprite[] topSprites;
    private static final int TOP_SPRITES_SIZE = 6;
    private static final int SPRITES_SIZE = 6;
    private static final int SPRITES_SIZE_COMPLEX = 9;

    /** Enum rappresentante tutti i possibili tipi di sprites renderizzabili. */
    public enum SpriteType {
        /** Tipi di sprites renderizzabili. */
        HORIZONTAL, VERTICAL, ANGLE_UP_RIGHT, ANGLE_UP_LEFT, ANGLE_DOWN_RIGHT, ANGLE_DOWN_LEFT, ANGLE_DOWN_RIGHT1, ANGLE_DOWN_LEFT1, HORIZONTAL1;
    }

    /**
     * Crea una WallTile a partire da un nome , una sprite ed un colore
     * associato alla tile.
     * 
     * @param name
     *            Nome della tile.
     * @param levelColor
     *            Colore associato alla tile.
     * @param sprites
     *            Sprites che la tile può renderizzare.
     * @param topSprites
     *            Sprites superiori che la tile può renderizzare.
     */
    public WallTile(final String name, final int levelColor, final Sprite[] sprites, final Sprite[] topSprites) {
        super(name, levelColor, null);
        if (topSprites.length != TOP_SPRITES_SIZE) {
            throw new IllegalArgumentException("L'array topSprites deve avere lunghezza 6");
        }
        if (sprites.length != SPRITES_SIZE && this instanceof SimpleWallTile) {
            throw new IllegalArgumentException(
                    "L'array sprites deve avere lunghezza 6 per una tile di tipo SimpleWall");
        }

        if (sprites.length != SPRITES_SIZE_COMPLEX && this instanceof ComplexWallTile) {
            throw new IllegalArgumentException(
                    "L'array sprites deve avere lunghezza 9 per una tile di tipo ComplexWall");
        }
        this.sprites = sprites.clone();
        this.topSprites = topSprites.clone();
        this.makeSolid();
    }

    /**
     * Renderizza la sprite superiore alla tile.
     * 
     * @param position
     *            Coordinate della tile.
     */
    public abstract void renderTopSprite(Vector2<Integer> position);

    /**
     * Ritorna una sprite superiore dinamicamente in base alla posizione della
     * tile.
     * 
     * @param position
     *            Posizione della tile.
     * @return Sprite in base a posizione della tile nel livello.
     */
    protected SpriteType getTopSprite(final Vector2<Integer> position) {
        final Level level = Game.getGame().getLevel();
        final boolean up = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.N.ordinal())) == getLevelColor();
        final boolean down = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.S.ordinal())) == getLevelColor();
        final boolean left = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.W.ordinal())) == getLevelColor();
        final boolean right = level.getTileColor(position,
                TerrainTile.CARDINAL_VECTORS.get(TerrainTile.Cardinal.E.ordinal())) == getLevelColor();
        if (up && down) {
            return SpriteType.VERTICAL;
        }
        if (right && left) {
            return SpriteType.HORIZONTAL;
        }
        if (up && right) {
            return SpriteType.ANGLE_DOWN_LEFT;
        }
        if (up && left) {
            return SpriteType.ANGLE_DOWN_RIGHT;
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

    /**
     * Ritorna le sprites che la tile può renderizzare.
     * 
     * @return Array di sprites renderizzabli dalla tile.
     */
    public Sprite[] getSprites() {
        return sprites.clone();
    }

    /**
     * Ritorna le sprites superiori che la tile può renderizzare.
     * 
     * @return Array di sprites superiori renderizzabli dalla tile.
     */

    public Sprite[] getTopSprites() {
        return topSprites.clone();
    }

}
