package maingame.graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import util.Vector2;
import util.Vector2Impl;

/** Classe SpriteSheet. */
public class SpriteSheetImpl implements SpriteSheet {

    private final Dimension dimension;
    private final int[] pixels;
    private Sprite[] sprites;
    /** SpriteSheet principali, contenente tiles e oggetti di gioco. */
    public static final SpriteSheetImpl TILESET = new SpriteSheetImpl("/spritesheets/tileset.png",
            new Dimension(128, 272));

    private static final SpriteSheetImpl BLOODSET = new SpriteSheetImpl("/spritesheets/blood.png",
            new Dimension(600, 168));
    private static final SpriteSheetImpl RAINSET = new SpriteSheetImpl("/spritesheets/rain.png",
            new Dimension(1200, 672));

    /** SpriteSheet contenente la pioggia. */
    public static final SpriteSheet RAIN = new SpriteSheetImpl(RAINSET, new Vector2Impl<Integer>(0, 0),
            new Dimension(4, 4), new Dimension(300, 168));
    /** SpriteSheet contenente la cascata. */
    public static final SpriteSheet WATERFALL = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(1, 24),
            new Dimension(6, 1), new Dimension(8, 8));
    /** SpriteSheet contenente la cornice di salute al limite del giocatore. */
    public static final SpriteSheet BLOOD = new SpriteSheetImpl(BLOODSET, new Vector2Impl<Integer>(0, 0),
            new Dimension(2, 1), new Dimension(300, 168));
    /** SpriteSheet contenente tutti i proiettili. */
    public static final SpriteSheetImpl PROJECTILES = new SpriteSheetImpl("/spritesheets/projectiles.png",
            new Dimension(48, 32));
    /** SpriteSheet per sprite animate contenente il super-proiettile. */
    public static final SpriteSheet SUPERPROJECTILE = new SpriteSheetImpl(PROJECTILES, new Vector2Impl<Integer>(1, 0),
            new Dimension(2, 1), new Dimension(16, 16));
    /** SpriteSheet per sprite animate contenente il super-proiettile rosso. */
    public static final SpriteSheet REDSUPERPROJECTILE = new SpriteSheetImpl(PROJECTILES,
            new Vector2Impl<Integer>(1, 1), new Dimension(2, 1), new Dimension(16, 16));

    /** SpriteSheet delle animazioni del player. */
    public static final SpriteSheetImpl PLAYER = new SpriteSheetImpl("/spritesheets/player.png",
            new Dimension(64, 224));
    /** SpriteSheet delle animazioni della seconda skin del player. */
    public static final SpriteSheetImpl PLAYER2 = new SpriteSheetImpl("/spritesheets/player2.png",
            new Dimension(64, 224));
    /** SpriteSheet delle animazioni della terza skin del player. */
    public static final SpriteSheetImpl PLAYER3 = new SpriteSheetImpl("/spritesheets/player3.png",
            new Dimension(64, 224));

    /** SpriteSheet delle animazioni del enemy. */
    public static final SpriteSheetImpl ENEMY = new SpriteSheetImpl("/spritesheets/enemy.png", new Dimension(64, 112));
    /** SpriteSheet delle animazioni della seconda sprite di enemy. */
    public static final SpriteSheetImpl ENEMY2 = new SpriteSheetImpl("/spritesheets/enemy2.png",
            new Dimension(64, 112));

    /** SpriteSheet delle animazioni della pecora. */
    public static final SpriteSheetImpl SHEEP = new SpriteSheetImpl("/spritesheets/sheep.png", new Dimension(64, 48));
    /** SpriteSheet delle animazioni dei cuori di salute dell'Hud del gioco. */
    public static final SpriteSheet HEARTBIT = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(6, 2),
            new Dimension(2, 1), new Dimension(16, 10));
    /** SpriteSheet delle animazioni di Door. */
    public static final SpriteSheet DOORSET = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 5),
            new Dimension(4, 1), new Dimension(16, 24));
    /** SpriteSheet delle animazioni di Chest. */
    public static final SpriteSheet CHESTSET = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 9),
            new Dimension(4, 1), new Dimension(16, 16));
    /** SpriteSheet delle animazioni di GoldChest. */
    public static final SpriteSheet GOLDCHESTSET = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 10),
            new Dimension(4, 1), new Dimension(16, 16));
    /** SpriteSheet delle animazioni dell'acqua. */
    public static final SpriteSheet WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 1),
            new Dimension(4, 1), new Dimension(8, 8));

    /** SpriteSheet delle animazioni dell'acqua scura. */
    public static final SpriteSheet DARK_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 11),
            new Dimension(4, 1), new Dimension(8, 8));
    /** SpriteSheet delle animazioni della torcia rossa. */
    public static final SpriteSheet TORCHFLAME = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(12, 0),
            new Dimension(1, 2), new Dimension(8, 8));
    /** SpriteSheet delle animazioni della torcia blu. */
    public static final SpriteSheet BLUETORCHFLAME = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(13, 0),
            new Dimension(1, 2), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra grass e
     * water.
     */
    public static final SpriteSheet GRASS_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 3),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra sand e
     * water.
     */
    public static final SpriteSheet SAND_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(4, 3),
            new Dimension(4, 3), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra cave e
     * water.
     */
    public static final SpriteSheet CAVE_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(8, 15),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra grass e
     * sand.
     */
    public static final SpriteSheet GRASS_SAND = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(8, 3),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra path e
     * grass.
     */
    public static final SpriteSheet PATH_GRASS = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(2, 6),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra path e
     * sand.
     */
    public static final SpriteSheet PATH_SAND = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(6, 6),
            new Dimension(4, 3), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra path e
     * water.
     */
    public static final SpriteSheet PATH_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(10, 6),
            new Dimension(4, 3), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra rock e
     * grass.
     */
    public static final SpriteSheet PATH_ROCK_GRASS = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 12),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra rock e
     * sand.
     */
    public static final SpriteSheet PATH_ROCK_SAND = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(4, 12),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra rock e
     * water.
     */
    public static final SpriteSheet PATH_ROCK_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(8, 12),
            new Dimension(4, 3), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra plateau e
     * grass.
     */
    public static final SpriteSheet PLATEAU_GRASS = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(8, 28),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra plateau e
     * sand.
     */
    public static final SpriteSheet PLATEAU_SAND = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(12, 28),
            new Dimension(4, 3), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile di contatto tra plateau e
     * water.
     */
    public static final SpriteSheet PLATEAU_WATER = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(8, 31),
            new Dimension(4, 3), new Dimension(8, 8));
    /**
     * SpriteSheet , estratto da tileset, delle tile del ponte sospeso
     * sull'acqua.
     */
    public static final SpriteSheet VERTICAL_BRIDGE = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(10, 9),
            new Dimension(4, 2), new Dimension(8, 8));

    /**
     * SpriteSheet , estratto da tileset, delle tile del ponte sospeso
     * sull'acqua.
     */
    public static final SpriteSheet HORIZONTAL_BRIDGE = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(0, 22),
            new Dimension(4, 2), new Dimension(8, 8));

    /** SpriteSheet , estratto da tileset, delle tile di pavimento. */
    public static final SpriteSheet FLOOR = new SpriteSheetImpl(TILESET, new Vector2Impl<Integer>(12, 12),
            new Dimension(4, 2), new Dimension(8, 8));

    /**
     * Costruttore di SpriteSheetImpl tramite un percorso di un immagine.
     * 
     * @param path
     *            Percorso del file png.
     * @param dimension
     *            dimensione dello SpriteSheet da creare.
     * 
     */
    public SpriteSheetImpl(final String path, final Dimension dimension) {
        this.dimension = new Dimension(dimension);
        pixels = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        load(path);
    }

    /**
     * Costruttore di SpriteSheetImpl estratto da un altro SpriteSheet. Tramite
     * la posizione e la dimensione delle sprite, crea uno sheet che non è altro
     * che un "vettore" delle sprite, che appunto così, avranno un'animazione.
     * 
     * @param sheet
     *            SpriteSheet da cui creare il nuovo.
     * @param position
     *            posizione x,y di partenza nel selezionare il nuovo SpriteSheet
     * @param dimension
     *            dimensione ,cioè numero di elementi in lunghezze e larghezza,
     *            del vettore di sprite animate.
     * @param spriteDimension
     *            dimensione in pixel dello sprite animato
     */
    public SpriteSheetImpl(final SpriteSheet sheet, final Vector2<Integer> position, final Dimension dimension,
            final Dimension spriteDimension) {
        this.dimension = new Dimension((int) (dimension.getWidth() * spriteDimension.getWidth()),
                (int) (dimension.getHeight() * spriteDimension.getHeight()));
        pixels = new int[(int) this.dimension.getWidth() * (int) this.dimension.getHeight()];
        final Vector2<Integer> v = new Vector2Impl<Integer>(0, 0);
        for (int y = 0; y < this.dimension.getHeight(); y++) {
            v.setY(position.getY() * (int) spriteDimension.getHeight() + y);
            for (int x = 0; x < this.dimension.getWidth(); x++) {
                v.setX(position.getX() * (int) spriteDimension.getWidth() + x);
                pixels[x + y * (int) this.dimension.getWidth()] = sheet.getPixels()[v.getX()
                        + v.getY() * (int) sheet.getDimension().getWidth()];
            }
        }

        int frame = 0;
        sprites = new SpriteImpl[(int) dimension.getWidth() * (int) dimension.getHeight()];
        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                int[] spritePixels = new int[(int) spriteDimension.getWidth() * (int) spriteDimension.getHeight()];
                for (int y1 = 0; y1 < spriteDimension.getHeight(); y1++) {
                    for (int x1 = 0; x1 < spriteDimension.getWidth(); x1++) {
                        spritePixels[x1 + y1
                                * (int) spriteDimension.getWidth()] = pixels[(x1 + x * (int) spriteDimension.getWidth())
                                        + (y1 + y * (int) spriteDimension.getHeight())
                                                * (int) this.dimension.getWidth()];
                    }
                }
                final SpriteImpl sprite = SpriteImpl.spriteFromPixels(spriteDimension, spritePixels);
                sprites[frame++] = sprite;
            }
        }
    }

    @Override
    public int[] getPixels() { // Non ritorno un nuovo array perchè l'eccessiva
                               // dimensione causa errore.
        return pixels;
    }

    private void load(final String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(SpriteSheetImpl.class.getResource(path));
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sprite[] getSprites() {
        return sprites.clone();
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(dimension);
    }

}
