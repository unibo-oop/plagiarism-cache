package maingame.level.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import maingame.graphics.SpriteImpl;
import util.Vector2;
import util.Vector2Impl;

/** Classe Tile. */
public class TileImpl implements Tile {

    private Sprite sprite;
    private final int levelColor;
    private final String name;
    private boolean solid;
    private boolean swimmable;
    private boolean emitter;
    private int lightRadius;
    private static List<Tile> tiles = new ArrayList<>();
    private static final int COLORVOID = 0xFF000000;
    private static final int COLORWATER = 0xFF0000FF;
    private static final int COLORROCK = 0xFF7F7F7;
    private static final int COLORPLATEAU = 0xFF567789;

    private static final int COLORSHAFT = 0xFFD27D2C;
    private static final int COLORSHAFT_SAND = 0xFFD27D2A;
    private static final int COLORDARKWATER = 0xFF154AA8;
    private static final int COLORWATERFALL = 0xFF1B87E0;

    /** Dimensione delle tiles. */
    public static final int TILE_SIZE = 8;

    /** Tile Nulla. */
    public static final Tile VOID = new TileImpl("Void", COLORVOID, SpriteImpl.VOID).makeSolid();
    /** Tile Acqua. */
    public static final Tile WATER = new AnimatedTile("Water", COLORWATER, AnimatedSprite.WATER_ANIMATION)
            .makeSwimmable();
    /** Tile Sand. */
    public static final Tile SAND = new TerrainTile("Sand", 0xFFF4DD89, SpriteImpl.SAND_SPRITES,
            SpriteImpl.SAND_TRANSITION_SPRITES, new Tile[] { WATER });

    /** Tile Erba. */
    public static final Tile GRASS = new TerrainTile("Grass", 0xFF00FF00, SpriteImpl.GRASS_SPRITES,
            SpriteImpl.GRASS_TRANSITION_SPRITES, new Tile[] { WATER, SAND });
    /** Tile Sentiero. */
    public static final Tile PATH = new TerrainTile("Path", 0xFF875D10, SpriteImpl.PATH_SPRITES,
            SpriteImpl.PATH_TRANSITION_SPRITES, new Tile[] { GRASS, SAND, WATER });
    /** Tile Sentiero di roccia. */
    public static final Tile PATH_ROCK = new TerrainTile("Rock path", 0xFF9D8672, SpriteImpl.PATH_ROCK_SPRITES,
            SpriteImpl.PATH_ROCK_TRANSITION_SPRITES, new Tile[] { GRASS, SAND, WATER });
    /** Tile Albero. */
    public static final Tile TREE = new TreeTile("Tree", 0xFF22B14C, SpriteImpl.TREETRUNK, SpriteImpl.TREETOP);
    /** Tile Albero. */
    public static final Tile TREE2 = new TreeTile("Tree2", 0xFF45A049, SpriteImpl.TREETRUNK, SpriteImpl.TREETOP2);
    /** Tile Albero. */
    public static final Tile TREE3 = new TreeTile("Tree3", 0xFF45A045, SpriteImpl.TREETRUNK, SpriteImpl.TREETOP3);
    /** Tile Albero. */
    public static final Tile TREE4 = new TreeTile("Tree4", 0xFF00CC00, SpriteImpl.TREETRUNK, SpriteImpl.TREETOP4);
    /** Tile Roccia. */
    public static final Tile ROCK = new TileImpl("Rock", COLORROCK, SpriteImpl.ROCK).makeSolid();
    /** Tile plateau. */
    public static final Tile PLATEAU = new TerrainTile("Plateau", COLORPLATEAU, SpriteImpl.PLATEAU_SPRITES,
            SpriteImpl.PLATEAU_TRANSITION_SPRITES, new Tile[] { GRASS, SAND, WATER }).makeSolid();
    /** Tile Muro. */
    public static final Tile WALL1 = new SimpleWallTile("Wall1", 0xFF6DC2CA, SpriteImpl.WALL_SPRITES,
            SpriteImpl.WALL_TOP_SPRITES);
    /** Tile Muro rosso. */
    public static final Tile RED_WALL = new SimpleWallTile("Red Wall", 0xFF772D2D, SpriteImpl.RED_WALL_SPRITES,
            SpriteImpl.RED_WALL_TOP_SPRITES);
    /** Tile muro caverna. */
    public static final Tile CAVE_WALL = new ComplexWallTile("Cave Wall", 0xFF7F3300, SpriteImpl.CAVE_WALL_SPRITES,
            SpriteImpl.CAVE_WALL_TOP_SPRITES);
    /** Tile muro casa. */
    public static final Tile HOUSE_WALL = new ComplexWallTile("House Wall", 0xFFB88528, SpriteImpl.HOUSE_WALL_SPRITES,
            SpriteImpl.HOUSE_WALL_TOP_SPRITES);
    /** Tile Ponte. */
    public static final Tile VERTICAL_BRIDGE = new BlockTile("Vertical Bridge", 0xFF464646,
            SpriteImpl.VERTICAL_BRIDGE_SPRITES, SpriteImpl.VERTICAL_BRIDGE);
    /** Tile Ponte. */
    public static final Tile HORIZONTAL_BRIDGE = new BlockTile("Horizontal Bridge", 0xFF461586,
            SpriteImpl.HORIZONTAL_BRIDGE_SPRITES, SpriteImpl.HORIZONTAL_BRIDGE);
    /** Tile Pavimento. */
    public static final Tile FLOOR = new BlockTile("Floor", 0xFFD04648, SpriteImpl.FLOOR_SPRITES, SpriteImpl.FLOOR);
    /** Tile pavimento legno. */
    public static final Tile WOOD_FLOOR = new TerrainTile("Wood Floor", 0xFFD8A068, SpriteImpl.WOOD_FLOOR_SPRITES,
            new Sprite[][] {}, new Tile[] {});
    /** Tile pavimento mattone. */
    public static final Tile BRICK_FLOOR = new TerrainTile("Brick Floor", 0xFF854C30, SpriteImpl.BRICK_SPRITES,
            new Sprite[][] {}, new Tile[] {});
    /** Tile pavimento mattone vecchio. */
    public static final Tile OLD_BRICK_FLOOR = new TerrainTile("Old Brick Floor", 0xFF757161,
            SpriteImpl.OLD_BRICK_SPRITES, new Sprite[][] {}, new Tile[] {});

    /** Tile Palo. */
    public static final Tile SHAFT = new TileImpl("Shaft", COLORSHAFT, SpriteImpl.SHAFT).makeSolid();

    /** Tile Palo Sabbia. */
    public static final Tile SAND_SHAFT = new TileImpl("Sand Shaft", COLORSHAFT_SAND, SpriteImpl.SHAFTSAND).makeSolid();

    /** Tile Fiore. */
    public static final Tile FLOWER = new TileImpl("Flower", 0xFFFFFF33, SpriteImpl.FLOWER);
    /** Tile Fiore. */
    public static final Tile FLOWER2 = new TileImpl("Flower2", 0xFFFFFF00, SpriteImpl.FLOWER2);
    /** Tile Fiore. */
    public static final Tile FLOWER3 = new TileImpl("Flower3", 0xFFFFFF88, SpriteImpl.FLOWER3);
    /** Tile acqua scura. */
    public static final Tile DARK_WATER = new AnimatedTile("Dark Water", COLORDARKWATER,
            AnimatedSprite.DARK_WATER_ANIMATION).makeSwimmable();
    /** Tile cascata. */
    public static final Tile WATERFALL = new AnimatedTile("Waterfall", COLORWATERFALL, SpriteImpl.WATERFALL_ANIMATION)
            .makeSolid();
    /** Tile caverna. */
    public static final Tile CAVE = new TerrainTile("Cave", 0xFF4C1E00, SpriteImpl.CAVE_SPRITES,
            SpriteImpl.CAVE_TRANSITION_SPRITES, new Tile[] { DARK_WATER });
    /** Tile Torcia . */
    public static final Tile TORCH = new TorchTile("Torch", 0xFFFFFFFF, SpriteImpl.TORCH, 4, SpriteImpl.TORCHFLAME);
    /** Tile Torcia blu . */
    public static final Tile BLUE_TORCH = new TorchTile("Blue Torch", 0xFFEEEEEE, SpriteImpl.BLUETORCH, 4,
            SpriteImpl.BLUETORCHFLAME);

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
     */
    public TileImpl(final String name, final int levelColor, final Sprite sprite) {
        tiles.forEach(new Consumer<Tile>() {
            public void accept(final Tile t) {
                if (t.getName().equals(name)) {
                    throw new IllegalArgumentException("Tile con lo stesso nome gia aggiunta");
                }
                if (t.getLevelColor() == levelColor) {
                    throw new IllegalArgumentException("Tile con lo colore associato gia aggiunta");
                }
            }
        });
        this.name = name;
        this.levelColor = levelColor;
        this.sprite = sprite;
        if (!name.equals("Void")) {
            tiles.add(this);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(final Vector2<Integer> position, final double luminosity) {
        if (luminosity > 1.0 || luminosity < 0.0) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0.0 e 1.0");
        }
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX() * TileImpl.TILE_SIZE, position.getY() * TileImpl.TILE_SIZE),
                getSprite(), luminosity, false, false);
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public boolean isSwimmable() {
        return swimmable;
    }

    @Override
    public boolean isEmitter() {
        return emitter;
    }

    @Override
    public Tile makeSwimmable() {
        this.swimmable = true;
        return this;
    }

    @Override
    public Tile makeSolid() {
        this.solid = true;
        return this;
    }

    @Override
    public Tile makeEmitter(final int lightRadius) {
        this.emitter = true;
        this.lightRadius = lightRadius;
        return this;
    }

    /**
     * Ritorna l'array contenente tutti i tipi di tile che possone essere
     * creati.
     * 
     * @return Array contenente tutte le tiles.
     */
    public static List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public int getLevelColor() {
        return levelColor;
    }

    @Override
    public int getLightRadius() {
        return lightRadius;
    }

}
