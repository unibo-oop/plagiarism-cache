package maingame.level;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import maingame.entity.Entity;
import maingame.entity.item.Bed;
import maingame.entity.item.BigPotion;
import maingame.entity.item.GoldChest;
import maingame.entity.item.Helm;
import maingame.entity.item.House;
import maingame.entity.item.HouseDoor;
import maingame.entity.item.Item;
import maingame.entity.item.ItemImpl;
import maingame.entity.item.Key;
import maingame.entity.item.KeyDoor;
import maingame.entity.item.Ladder;
import maingame.entity.item.NormalChest;
import maingame.entity.item.Potion;
import maingame.entity.item.Well;
import maingame.entity.mob.Mob;
import maingame.entity.mob.MobImpl;
import maingame.entity.mob.enemy.Enemy;
import maingame.entity.mob.player.Player;
import maingame.entity.mob.sheep.Sheep;
import maingame.entity.particle.Particle;
import maingame.entity.projectile.Projectile;
import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.level.tile.Tile;
import maingame.level.tile.TileImpl;
import maingame.level.tile.TorchTile;
import maingame.level.tile.TreeTile;
import maingame.level.tile.WallTile;
import maingame.sound.SoundImpl;
import util.Vector2;
import util.Vector2Impl;

/** Classe Level. */

public final class LevelImpl implements Level {

    private Dimension dimension;
    private int[] tiles;
    private int[] mobsItems;
    private boolean[] solidTiles;
    private static int time;
    private int brightness;
    private static final int MAX_BRIGHTNESS = 210;
    private static final int MAX_LUMINOSITY = 255;
    private static final int STEP_VANISH_TIME = 80;
    private static final double LUMINOSITY_MULTIPLICATOR = 0.9;
    private boolean day;
    private Player player;
    private final List<Mob> mobs = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Vector2<Integer>> treeCoordinates = new ArrayList<>();
    private final List<Vector2<Integer>> torchCoordinates = new ArrayList<>();
    private double[] tilesIntensity;
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Particle> particles = new ArrayList<>();
    private final List<Vector2<Integer>> sandSteps = new ArrayList<>();
    private final List<Vector2<Integer>> wallCoordinates = new ArrayList<>();
    private final List<Integer> sandStepsTimers = new ArrayList<>();
    private final Vector2<Integer> playerScreenPosition = new Vector2Impl<Integer>(0, 0);
    private Vector2<Integer> playerPos;
    private int luminosity;
    private static Difficulty difficulty = Difficulty.EASY;
    private boolean waterfall;
    /** Array contenente tutti i possibili Mobs aggiungibili al livello. */
    public static final List<Mob> MOBS = Collections.unmodifiableList(Arrays.asList(new Player(0xFF00FF7B, "Player"),
            new Enemy(0xFFFF0000, "Enemy"), new Sheep(0xFFC86BFF, "Sheep")));
    /** Array contenente tutti i possibili Oggetti aggiungibili al livello. */
    public static final List<Item> ITEMS = Collections
            .unmodifiableList(Arrays.asList(new Potion(0xFF7FEEFF, "Potion"), new BigPotion(0xFF60ECFF, "BigPotion"),
                    new Helm(0xFFC0C0C0, "Helm"), new NormalChest(0xFFDAD45E, "NormalChest"),
                    new GoldChest(0xFFFFFF23, "GoldChest"), new KeyDoor(0xFF854C30, "Key Door"),
                    new Key(0xFFFFE45E, "Key"), new Well(0xFFEEAAEE, "Well"), new HouseDoor(0xFF555555, "House Door"),
                    new Ladder(0xFF444444, "Ladder"), new House(0xFF666666, "House"), new Bed(0xFFFF00DC, "Bed")));

    private static final int DAY_DURATION = 20;
    private static final String ERROR_MESSAGE = "La luminosità fissata deve essere compresa tra -1 e 255";

    /**
     * Costruttore utilizzto per creare un livello a partire da due file png,
     * uno per le tiles e l'altro per gli items/mobs.
     * 
     * @param levelPath
     *            Percorso del file png in cui i colori sono assiciato alle
     *            tiles.
     * @param mobsItemsPath
     *            Percorso del file png in cui i colori sono assiciato ad
     *            oggetti/mobs.
     * @param luminosity
     *            Luminosità fissata del livello oppure -1 se la luminosità è
     *            variabile.
     * 
     */
    public LevelImpl(final String levelPath, final String mobsItemsPath, final int luminosity) {
        if (luminosity > MAX_LUMINOSITY || luminosity < -1) {
            throw new IllegalArgumentException();
        }
        this.luminosity = luminosity;
        if (luminosity != -1) {
            brightness = luminosity;
        }
        BufferedImage image;
        BufferedImage image1;
        try {
            image = ImageIO.read(LevelImpl.class.getResource(levelPath));
            image1 = ImageIO.read(LevelImpl.class.getResource(mobsItemsPath));
            dimension = new Dimension(image.getWidth(), image.getHeight());
            tiles = new int[(int) (dimension.getWidth() * dimension.getHeight())];
            tilesIntensity = new double[(int) (dimension.getWidth() * dimension.getHeight())];
            Arrays.fill(tilesIntensity, 1);
            mobsItems = new int[(int) (dimension.getWidth() * dimension.getHeight())];
            image.getRGB(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), tiles, 0,
                    (int) dimension.getWidth());
            image1.getRGB(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), mobsItems, 0,
                    (int) dimension.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
        addMobs();
        findLightIntensity();
        findSolidTiles();
    }

    /**
     * Costruttore utilizzato per creare un file a partire da un array di
     * integer contenete i colori che verranno poi associati a tiles,un array di
     * integer contenete i colori che verranno poi associati ad oggetti(mobs e
     * le dimensioni del livello da creare in tiles.
     * 
     * @param tiles
     *            Array contenete i colori delle tiles.
     * @param mobsItems
     *            Array contenete i colori degli oggetti/mobs.
     * @param dimension
     *            Dimensioni de livello da creare.
     * @param luminosity
     *            Luminosità fissata del livello oppure -1 se la luminosità è
     *            variabile.
     * 
     */

    public LevelImpl(final int[] tiles, final int[] mobsItems, final Dimension dimension, final int luminosity) {
        if (luminosity > MAX_LUMINOSITY || luminosity < -1) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        if (tiles.length != (int) (dimension.getHeight() * dimension.getWidth())) {
            throw new IllegalArgumentException("Dimensione livello non corretta");
        }
        this.luminosity = luminosity;
        if (luminosity != -1) {
            brightness = luminosity;
        }
        this.tiles = tiles.clone();
        this.mobsItems = mobsItems.clone();
        this.dimension = new Dimension(dimension);
        tilesIntensity = new double[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(tilesIntensity, 1);
        findLightIntensity();
        addMobs();
        if (playerPos != null) {
            this.add(new Player(playerPos, null));
        } else if (!Game.getGame().isEditor()) {
            throw new IllegalArgumentException("Il livello deve contenere un player");
        }
        solidTiles = new boolean[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(solidTiles, false);
        findSolidTiles();
    }

    private void addMobs() {
        for (int i = 0; i < mobsItems.length; i++) {
            for (int i2 = 0; i2 < MOBS.size(); i2++) {
                if (mobsItems[i] == MOBS.get(i2).getLevelColor()) {
                    if (MOBS.get(i2) instanceof Player) {
                        playerPos = new Vector2Impl<Integer>((i % (int) dimension.getWidth()) * TileImpl.TILE_SIZE,
                                (i / (int) dimension.getWidth()) * TileImpl.TILE_SIZE);
                    } else {
                        try {
                            this.add(MOBS.get(i2).getClass().getConstructor(Vector2.class)
                                    .newInstance(new Vector2Impl<Integer>(
                                            (i % (int) dimension.getWidth()) * TileImpl.TILE_SIZE,
                                            (i / (int) dimension.getWidth()) * TileImpl.TILE_SIZE)));
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            for (int i2 = 0; i2 < ITEMS.size(); i2++) {
                if (mobsItems[i] == ITEMS.get(i2).getLevelColor()) {
                    try {
                        this.add(ITEMS.get(i2).getClass().getConstructor(Vector2.class).newInstance(
                                new Vector2Impl<Integer>((i % (int) dimension.getWidth()) * TileImpl.TILE_SIZE,
                                        (i / (int) dimension.getWidth()) * TileImpl.TILE_SIZE)));

                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void findLightIntensity() {
        double maxDistance, distance;
        int tileIndex;
        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                final Tile t = getTile(new Vector2Impl<Integer>(x, y));
                if (t.isEmitter()) {
                    maxDistance = t.getLightRadius() + (t.getLightRadius() / 2);
                    for (int i = -t.getLightRadius(); i <= t.getLightRadius(); i++) {
                        for (int i2 = -t.getLightRadius(); i2 <= t.getLightRadius(); i2++) {
                            distance = Math.abs(i) + Math.abs(i2);
                            tileIndex = (x + i2) + (y + i) * (int) dimension.getWidth();
                            if (tileIndex >= 0 && tileIndex < tilesIntensity.length) {
                                if (distance == 0) {
                                    tilesIntensity[tileIndex] = 0;
                                } else {
                                    if (distance <= maxDistance) {
                                        if (tilesIntensity[tileIndex] == 1) {
                                            tilesIntensity[tileIndex] = (distance / maxDistance)
                                                    * LUMINOSITY_MULTIPLICATOR;
                                        } else {
                                            final double intensity = (1 - tilesIntensity[tileIndex])
                                                    + (1 - ((distance / maxDistance) * LUMINOSITY_MULTIPLICATOR));
                                            tilesIntensity[tileIndex] = 1 - (intensity > 1 ? 1 : intensity);
                                        }
                                    } else {
                                        if (tilesIntensity[tileIndex] != 1) {
                                            final double intensity = (1 - tilesIntensity[tileIndex]);
                                            tilesIntensity[tileIndex] = 1 - (intensity > 1 ? 1 : intensity);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void findSolidTiles() {
        solidTiles = new boolean[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(solidTiles, false);
        final List<Item> items = getSolidItems();
        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                for (final Item it : items) {
                    final Vector2<Integer> position = new Vector2Impl<Integer>(
                            it.getPosition().getX() / TileImpl.TILE_SIZE, it.getPosition().getY() / TileImpl.TILE_SIZE);
                    if (position.equals(new Vector2Impl<Integer>(x, y))) {
                        for (int y1 = y - it.getOffset().getY(); y1 < y - it.getOffset().getY()
                                + it.getDimension().getHeight(); y1++) {
                            for (int x1 = x - it.getOffset().getX(); x1 < x - it.getOffset().getX()
                                    + it.getDimension().getWidth(); x1++) {
                                final int index = x1 + y1 * (int) dimension.getWidth();
                                if (index >= 0 && index < solidTiles.length) {
                                    solidTiles[index] = true;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Tile getTile(final Vector2<Integer> position) {
        if (position.getX() < 0 || position.getY() < 0 || position.getX() >= dimension.getWidth()
                || position.getY() >= dimension.getHeight()) {
            return TileImpl.VOID;
        }
        for (int i = 0; i < TileImpl.getTiles().size(); i++) {
            final Tile tile = TileImpl.getTiles().get(i);
            if (tiles[position.getX() + position.getY() * (int) dimension.getWidth()] == tile.getLevelColor()) {
                return tile;
            }
        }

        return TileImpl.VOID;

    }

    @Override
    public int getTileColor(final Vector2<Integer> position, final Vector2<Integer> offset) {
        final int x = position.getX() + offset.getX();
        final int y = position.getY() + offset.getY();
        if (x < 0 || y < 0 || x >= dimension.getWidth() || y >= dimension.getHeight()) {
            return -1;
        }
        return tiles[x + y * (int) dimension.getWidth()];
    }

    @Override
    public double getLightIntensity(final Mob m) {
        final List<Double> lights = new ArrayList<>();

        torchCoordinates.forEach(new Consumer<Vector2<Integer>>() {
            @Override
            public void accept(final Vector2<Integer> l) {
                double radius, distance;
                distance = Vector2Impl.getDistance(
                        new Vector2Impl<Integer>(l.getX() * TileImpl.TILE_SIZE + TileImpl.TILE_SIZE / 2,
                                l.getY() * TileImpl.TILE_SIZE),
                        new Vector2Impl<Integer>(
                                m.getPosition().getX()
                                        + ((int) m.getSprite().getDimension().getWidth() / 2 - m.getRenderXOffset()),
                                m.getPosition().getY()));
                radius = getTile(l).getLightRadius();
                if (distance <= (radius * TileImpl.TILE_SIZE) + (TileImpl.TILE_SIZE / 2)) {
                    lights.add(distance / ((radius * TileImpl.TILE_SIZE) + (TileImpl.TILE_SIZE / 2)));
                }
            }
        });

        if (lights.isEmpty()) {
            return -1;
        }
        return Collections.min(lights);
    }

    @Override
    public void update(final int fixedLuminosity) {
        if (fixedLuminosity < -1 || fixedLuminosity > MAX_LUMINOSITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        time++;
        if (time == Integer.MAX_VALUE) {
            time = 0;
        }

        if (time % DAY_DURATION == 0) {
            updateTime(fixedLuminosity);
        }

        mobs.forEach(new Consumer<Mob>() {
            @Override
            public void accept(final Mob mob) {
                mob.update();
            }
        });
        items.forEach(new Consumer<Item>() {
            @Override
            public void accept(final Item item) {
                item.update();
            }
        });
        projectiles.forEach(new Consumer<Projectile>() {
            @Override
            public void accept(final Projectile projectile) {
                projectile.update();
            }
        });
        particles.forEach(new Consumer<Particle>() {
            @Override
            public void accept(final Particle particle) {
                particle.update();
            }
        });

        TileImpl.getTiles().forEach(new Consumer<Tile>() {
            @Override
            public void accept(final Tile tile) {
                tile.update();
            }
        });

        remove();

        for (int i = 0; i < sandStepsTimers.size(); i++) {
            if (sandStepsTimers.get(i) == 0) {
                sandStepsTimers.remove(i);
                sandSteps.remove(i);
                i--;
            } else {
                sandStepsTimers.set(i, sandStepsTimers.get(i) - 1);
            }
        }
        if (waterfall && !Game.getGame().isEditor() && !Game.getGame().getPlayer().isInjured()) {
            if (SoundImpl.WATERFALL.isStopped() && !Game.getGame().isEnd()) {
                SoundImpl.WATERFALL.play(true);
            }
        } else {
            SoundImpl.WATERFALL.stop();
        }

    }

    private void updateTime(final int fixedLuminosity) {

        if (fixedLuminosity != -1) {
            brightness = fixedLuminosity;
        } else {

            if (brightness == 0) {
                day = true;

            }
            if (brightness == MAX_BRIGHTNESS) {
                day = false;

            }
            if (day) {
                brightness++;
            } else {
                brightness--;
            }
        }
    }

    @Override
    public void add(final Entity e) {
        e.init(this);
        if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Mob) {
            if (e instanceof Player) {
                player = (Player) e;
            }
            mobs.add((MobImpl) e);
        } else {
            items.add((ItemImpl) e);
        }
    }

    private void remove() {
        for (int i = 0; i < mobs.size(); i++) {
            if (mobs.get(i).isRemoved()) {
                mobs.remove(i);
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isRemoved()) {
                items.remove(i);
            }
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) {
                particles.remove(i);
            }
        }
    }

    @Override
    public List<Mob> getMobs(final Entity e, final int radius) {
        final List<Mob> result = new ArrayList<>();
        mobs.forEach(new Consumer<Mob>() {

            @Override
            public void accept(final Mob mob) {
                final double distance = Vector2Impl.getDistance(mob.getPosition(), e.getPosition());
                if (!mob.equals(e) && distance <= radius) {
                    result.add(mob);
                }
            }
        });
        return result;
    }

    @Override
    public List<Item> getItems(final Entity e, final int radius) {
        final List<Item> result = new ArrayList<>();
        items.forEach(new Consumer<Item>() {

            @Override
            public void accept(final Item item) {
                final double distance = Vector2Impl.getDistance(item.getPosition(), e.getPosition());
                if (!item.equals(e) && distance <= radius && item.isSolid()) {
                    result.add(item);
                }
            }
        });
        return result;
    }

    @Override
    public void render(final Vector2<Integer> scroll) {
        if (!Game.getGame().isEditor()) {
            if (scroll.getX() < 0
                    || scroll.getX() > ((dimension.getWidth() * TileImpl.TILE_SIZE)
                            - ScreenImpl.getScreen().getDimension().getWidth())
                    || scroll.getY() < 0 || scroll.getY() > ((dimension.getHeight() * TileImpl.TILE_SIZE)
                            - ScreenImpl.getScreen().getDimension().getHeight())) {
                if (scroll.getX() < 0) {
                    playerScreenPosition.setX((Game.getGame().getWindowWidth() / 2 + scroll.getX() * Game.SCALE)
                            + player.getRenderXOffset() * Game.SCALE);
                    scroll.setX(0);
                }
                if (scroll.getX() > ((dimension.getWidth() * TileImpl.TILE_SIZE)
                        - ScreenImpl.getScreen().getDimension().getWidth())) {
                    playerScreenPosition.setX((Game.getGame().getWindowWidth() / 2
                            + (scroll.getX() - (((int) dimension.getWidth() * TileImpl.TILE_SIZE)
                                    - (int) ScreenImpl.getScreen().getDimension().getWidth())) * Game.SCALE)
                            + player.getRenderXOffset() * Game.SCALE);
                    scroll.setX((((int) dimension.getWidth() * TileImpl.TILE_SIZE)
                            - (int) ScreenImpl.getScreen().getDimension().getWidth()));
                }
                if (scroll.getY() < 0) {
                    playerScreenPosition.setY(Game.getGame().getWindowHeight() / 2 + scroll.getY() * Game.SCALE);
                    scroll.setY(0);
                }
                if (scroll.getY() > ((dimension.getHeight() * TileImpl.TILE_SIZE)
                        - ScreenImpl.getScreen().getDimension().getHeight())) {
                    playerScreenPosition.setY(Game.getGame().getWindowHeight() / 2
                            + (scroll.getY() - (((int) dimension.getHeight() * TileImpl.TILE_SIZE)
                                    - (int) ScreenImpl.getScreen().getDimension().getHeight())) * Game.SCALE);
                    scroll.setY((((int) dimension.getHeight() * TileImpl.TILE_SIZE)
                            - (int) ScreenImpl.getScreen().getDimension().getHeight()));
                }
            } else {
                playerScreenPosition.set((Game.getGame().getWindowWidth() / 2) + player.getRenderXOffset() * Game.SCALE,
                        Game.getGame().getWindowHeight() / 2);
            }
        }
        ScreenImpl.getScreen().setOffset(scroll);

        final int x0 = scroll.getX() / TileImpl.TILE_SIZE;
        int x1 = (scroll.getX() + (int) ScreenImpl.getScreen().getDimension().getWidth() + TileImpl.TILE_SIZE)
                / TileImpl.TILE_SIZE;
        if (x1 > dimension.getWidth()) {
            x1 = (int) dimension.getWidth();
        }
        final int y0 = scroll.getY() / TileImpl.TILE_SIZE;
        int y1 = (scroll.getY() + (int) ScreenImpl.getScreen().getDimension().getHeight() + (TileImpl.TILE_SIZE * 4))
                / TileImpl.TILE_SIZE;
        if (y1 > dimension.getHeight()) {
            y1 = (int) dimension.getHeight();
        }

        Tile t;
        boolean b = false;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                t = getTile(new Vector2Impl<Integer>(x, y));
                if (t.getName().equals("Waterfall")) {
                    b = true;
                }
                t.render(new Vector2Impl<Integer>(x, y), tilesIntensity[x + y * (int) dimension.getWidth()]);
            }
        }
        if (b) {
            waterfall = true;
        } else {
            waterfall = false;
        }
        particles.forEach(new Consumer<Particle>() {

            @Override
            public void accept(final Particle particle) {
                particle.render();
            }
        });
        mobs.forEach(new Consumer<Mob>() {

            @Override
            public void accept(final Mob mob) {
                mob.renderBodies();
            }
        });
        items.forEach(new Consumer<Item>() {

            @Override
            public void accept(final Item item) {
                item.render();
            }
        });
        mobs.forEach(new Consumer<Mob>() {

            @Override
            public void accept(final Mob mob) {
                mob.renderHeads();
            }
        });
        projectiles.forEach(new Consumer<Projectile>() {

            @Override
            public void accept(final Projectile projectile) {
                projectile.render();
            }
        });
        wallCoordinates.forEach(new Consumer<Vector2<Integer>>() {

            @Override
            public void accept(final Vector2<Integer> position) {
                if (getTile(position) instanceof WallTile) {
                    ((WallTile) getTile(position)).renderTopSprite(position);
                }
            }
        });
        treeCoordinates.forEach(new Consumer<Vector2<Integer>>() {

            @Override
            public void accept(final Vector2<Integer> position) {
                if (getTile(position) instanceof TreeTile) {
                    ((TreeTile) getTile(position)).renderTopSprite(position);
                }
            }
        });
        torchCoordinates.forEach(new Consumer<Vector2<Integer>>() {

            @Override
            public void accept(final Vector2<Integer> position) {
                if (getTile(position) instanceof TorchTile) {
                    ((TorchTile) getTile(position)).renderTopSprite(position);
                }
            }
        });
        if (!Game.getGame().isEditor()) {
            ScreenImpl.getScreen().renderHud(player.getHealth(), player.getMaxHealth());
        }
        treeCoordinates.clear();
        torchCoordinates.clear();
        wallCoordinates.clear();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    @Override
    public Vector2<Integer> getPlayerScreenPosition() {
        return playerScreenPosition;
    }

    @Override
    public void addSandStep(final Vector2<Integer> position) {
        sandSteps.add(position);
        sandStepsTimers.add(STEP_VANISH_TIME);
    }

    @Override
    public List<Vector2<Integer>> getSandSteps() {
        return new ArrayList<Vector2<Integer>>(sandSteps);
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public int getBrightness() {
        return brightness;
    }

    @Override
    public Vector2<Integer> getPlayerPos() {
        return playerPos;
    }

    @Override
    public void addTorchCoordinate(final Vector2<Integer> position) {
        torchCoordinates.add(position);
    }

    @Override
    public void addTreeCoordinate(final Vector2<Integer> position) {
        treeCoordinates.add(position);
    }

    @Override
    public void addWallCoordinate(final Vector2<Integer> position) {
        wallCoordinates.add(position);
    }

    @Override
    public int getLuminosity() {
        return luminosity;
    }

    @Override
    public void setLuminosity(final int luminosity) {
        if (luminosity < -1 || luminosity > MAX_LUMINOSITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.luminosity = luminosity;
    }

    @Override
    public void setBrightness(final int brightness) {
        if (brightness < 0 || brightness > MAX_BRIGHTNESS) {
            throw new IllegalArgumentException("La luminosità deve essere compresa tra 0 e MAX_BRIGHTNESS");
        }
        this.brightness = brightness;
    }

    @Override
    public boolean isDay() {
        return day;
    }

    @Override
    public void setDay(final boolean day) {
        this.day = day;
    }

    /**
     * Ritorna il valore della variabile che tiene conto del tempo.
     * 
     * @return Varibile timer.
     */
    public static int getTime() {
        return time;
    }

    /**
     * Setta il contatore temporale.
     * 
     * @param time
     *            Valore del contatore.
     */
    public static void setTime(final int time) {
        LevelImpl.time = time;
    }

    private List<Item> getSolidItems() {
        final List<Item> list = new ArrayList<>();
        this.items.forEach(new Consumer<Item>() {
            @Override
            public void accept(final Item t) {
                if (t.isSolid()) {
                    list.add(t);
                }
            }
        });
        return list;
    }

    @Override
    public List<Mob> getMobs() {
        final List<Mob> result = new ArrayList<>();
        mobs.forEach(new Consumer<Mob>() {

            @Override
            public void accept(final Mob mob) {
                if (!(mob instanceof Player)) {
                    result.add(mob);
                }
            }
        });
        return result;
    }

    @Override
    public void setMobs(final List<Mob> mobs) {
        this.mobs.clear();
        mobs.forEach(new Consumer<Mob>() {
            @Override
            public void accept(final Mob mob) {
                add(mob);
            }
        });
    }

    @Override
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public void setItems(final List<Item> items) {
        this.items.clear();
        items.forEach(new Consumer<Item>() {
            @Override
            public void accept(final Item item) {
                add(item);
            }
        });
    }

    @Override
    public boolean[] getSolidTiles() {
        return solidTiles.clone();
    }

    @Override
    public boolean isSolidTileItem(final Vector2<Integer> position, final Vector2<Integer> movement) {
        return solidTiles[(Math.floorDiv(position.getX() + movement.getX(), TileImpl.TILE_SIZE))
                + (Math.floorDiv(position.getY() + movement.getY(), TileImpl.TILE_SIZE)) * (int) dimension.getWidth()];
    }

    @Override
    public List<Vector2<Integer>> getCoordinateMobs(final Mob mob) {
        final List<Vector2<Integer>> result = new ArrayList<>();
        mobs.forEach(new Consumer<Mob>() {

            @Override
            public void accept(final Mob m) {
                if (!(m instanceof Player) && !m.equals(mob)) {
                    result.add(new Vector2Impl<>(m.getPosition().getX() / TileImpl.TILE_SIZE,
                            m.getPosition().getY() / TileImpl.TILE_SIZE));
                }
            }
        });

        return result;
    }

    /**
     * Ritorna la difficoltà impostata.
     * 
     * @return difficoltà.
     */
    public static Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Setta la difficoltà.
     * 
     * @param difficulty
     *            Difficoltà.
     */
    public static void setDifficulty(final Difficulty difficulty) {
        LevelImpl.difficulty = difficulty;
        Game.getGame().setDamage(difficulty.getDamage());
    }
}
