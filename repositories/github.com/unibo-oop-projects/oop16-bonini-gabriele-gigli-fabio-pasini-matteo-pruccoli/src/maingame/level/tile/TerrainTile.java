package maingame.level.tile;

import java.util.Arrays;
import java.util.List;

import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import maingame.level.Level;
import util.Vector2;
import util.Vector2Impl;

/** Classe BasicTile. */
public class TerrainTile extends TileImpl {
    private final Sprite[] sprites;
    private final Sprite[][] transitionSprites;
    private final Tile[] transitionTiles;
    private static final int NUM_SPRITES_SAND = 5;
    private static final int NUM_TRANSITION_SPRITES = 12;

    /** Enum rappresentante tutte le tile di transizione. */
    protected enum Cardinal {
        /** Punti cardinali relativi alle sprites. */
        N, E, S, W, NE, NW, SE, SW, NE1, NW1, SE1, SW1;
    }

    /** Lista contenete gli offset relativi a tutti i punti cardinali. */
    public static final List<Vector2<Integer>> CARDINAL_VECTORS = Arrays.asList(new Vector2Impl<Integer>(0, -1),
            new Vector2Impl<Integer>(1, 0), new Vector2Impl<Integer>(0, 1), new Vector2Impl<Integer>(-1, 0),
            new Vector2Impl<Integer>(1, -1), new Vector2Impl<Integer>(-1, -1), new Vector2Impl<Integer>(1, 1),
            new Vector2Impl<Integer>(-1, 1));

    /** Lista contenete le sprite che la tile può renderizzare. */
    protected enum SpriteTypes {
        /** Sprite che la tile può renderizzare. */
        TYPE1, TYPE2, TYPE3, TYPE4, STEP_TYPE;
    }

    /**
     * Crea una TerrainTile a partire da un nome , una sprite ed un colore
     * associato alla tile.
     * 
     * @param name
     *            Nome della tile.
     * @param levelColor
     *            Colore associato alla tile.
     * @param sprites
     *            Sprites standard della tile.
     * @param transitionSprites
     *            Matrice contenete le sprites di transizione.
     * @param transitionTiles
     *            Array contenete le tile di transizione.
     */
    public TerrainTile(final String name, final int levelColor, final Sprite[] sprites,
            final Sprite[][] transitionSprites, final Tile[] transitionTiles) {
        super(name, levelColor, null);
        if (!(sprites.length == 4 || (sprites.length == NUM_SPRITES_SAND && name.equals("Sand")))) {
            throw new IllegalArgumentException(
                    "L'array di sprites deve avere lunghezza 4 oppure 5 se la tile è di tipo sand");
        }
        for (final Sprite[] s : transitionSprites) {
            if (s.length != NUM_TRANSITION_SPRITES) {
                throw new IllegalArgumentException("Gli array transitionSprites devono avere lunghezza 12");
            }
        }
        if (transitionTiles.length != transitionSprites.length) {
            throw new IllegalArgumentException(
                    "Gli array transitionSprites e transitionTiles devono avere la stessa dimensione");
        }
        this.sprites = sprites.clone();
        this.transitionSprites = transitionSprites.clone();
        this.transitionTiles = transitionTiles.clone();
    }

    private Sprite getSprite(final Vector2<Integer> position) {
        for (int i = 0; i < transitionSprites.length; i++) {
            setSprite(getSpriteFromPosition(position, transitionTiles[i], transitionSprites[i]));
            if (getSprite() != null) {
                return getSprite();
            }
        }
        if (sprites.length == 1) {
            setSprite(sprites[0]);
        } else {
            final Level level = Game.getGame().getLevel();
            if (this.getName().equals("Sand") && level.getSandSteps().contains(position)
                    && !Game.getGame().isEditor()) {
                setSprite(sprites[SpriteTypes.STEP_TYPE.ordinal()]);
            } else {
                if ((position.getX() & 1) == 0 && (position.getY() & 1) == 0) {
                    setSprite(sprites[SpriteTypes.TYPE1.ordinal()]);
                } else if ((position.getX() & 1) == 1 && (position.getY() & 1) == 0) {
                    setSprite(sprites[SpriteTypes.TYPE2.ordinal()]);
                } else if ((position.getX() & 1) == 0 && (position.getY() & 1) == 1) {
                    setSprite(sprites[SpriteTypes.TYPE3.ordinal()]);
                } else if ((position.getX() & 1) == 1 && (position.getY() & 1) == 1) {
                    setSprite(sprites[SpriteTypes.TYPE4.ordinal()]);
                }
            }
        }
        return getSprite();
    }

    private Sprite getSpriteFromPosition(final Vector2<Integer> position, final Tile tile, final Sprite[] sprites) {
        final Level level = Game.getGame().getLevel();
        if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.E.ordinal())) == tile.getLevelColor()
                || level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.W.ordinal())) == tile.getLevelColor()
                || level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.S.ordinal())) == tile.getLevelColor()
                || level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.N.ordinal())) == tile.getLevelColor()) {
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.E.ordinal())) == tile.getLevelColor()) {
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.N.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.NE.ordinal()]);
                    return getSprite();
                }
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.S.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.SE.ordinal()]);
                    return getSprite();
                }
                setSprite(sprites[Cardinal.E.ordinal()]);
                return getSprite();
            }
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.W.ordinal())) == tile.getLevelColor()) {
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.N.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.NW.ordinal()]);
                    return getSprite();
                }
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.S.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.SW.ordinal()]);
                    return getSprite();
                }
                setSprite(sprites[Cardinal.W.ordinal()]);
                return getSprite();
            }
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.S.ordinal())) == tile.getLevelColor()) {
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.W.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.SW.ordinal()]);
                    return getSprite();
                }
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.E.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.SE.ordinal()]);
                    return getSprite();
                }
                setSprite(sprites[Cardinal.S.ordinal()]);
                return getSprite();
            }
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.N.ordinal())) == tile.getLevelColor()) {
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.W.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.NW.ordinal()]);
                    return getSprite();
                }
                if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.E.ordinal())) == tile.getLevelColor()) {
                    setSprite(sprites[Cardinal.NE.ordinal()]);
                    return getSprite();
                }
                setSprite(sprites[Cardinal.N.ordinal()]);
                return getSprite();
            }
        } else {
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.SE.ordinal())) == tile.getLevelColor()) {
                setSprite(sprites[Cardinal.SE1.ordinal()]);
                return getSprite();
            }
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.NE.ordinal())) == tile.getLevelColor()) {
                setSprite(sprites[Cardinal.NE1.ordinal()]);
                return getSprite();
            }
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.SW.ordinal())) == tile.getLevelColor()) {
                setSprite(sprites[Cardinal.SW1.ordinal()]);
                return getSprite();
            }
            if (level.getTileColor(position, CARDINAL_VECTORS.get(Cardinal.NW.ordinal())) == tile.getLevelColor()) {
                setSprite(sprites[Cardinal.NW1.ordinal()]);
                return getSprite();
            }
        }
        return null;
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
