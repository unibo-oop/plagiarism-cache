package it.unibo.superpeach.graphics;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Sprites manager class, loads from resource files sprites to draw.
 * 
 * @author Maurizio Capuano
 */
public final class Texturer {

    private static final int BRICKSCASTLEBLOCKS_COUNT = 16;
    private static final int LUCKYBLOCKS_COUNT = 2;
    private static final int SCOREBOARD_COUNT = 4;
    private static final int PIPEBLOCKS_COUNT = 4;
    private static final int CLOUDBLOCKS_COUNT = 6;
    private static final int BUSHBLOCKS_COUNT = 3;
    private static final int HILLBLOCKS_COUNT = 6;
    private static final int FLAGBLOCKS_COUNT = 4;
    private static final int GOOMBA_COUNT = 1;
    private static final int KOOPATROOPA_COUNT = 2;
    private static final int FLYINGKOOPA_COUNT = 2;
    private static final int PEACH_COUNT = 9;
    private static final int POWERUPS_COUNT = 4;

    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;
    private static final int KOOPA_HEIGHT = 23;

    private final Optional<BufferedImage> blocksTileSet;
    private final Optional<BufferedImage> enemiesTileSet;
    private final Optional<BufferedImage> playerTileSet;
    private final Optional<BufferedImage> powerupsTileSet;

    private final Optional<BufferedImage[]> terrain, lucky, scoreboard, pipe, cloud, bush, hill, flag;
    private final Optional<BufferedImage[]> goomba, koopaTroopa, flyingKoopa;
    private final Optional<BufferedImage[]> peach;
    private final Optional<BufferedImage[]> powerups;

    /**
     * Texturer construtctor which initializes images arrays and loads sprites
     * inside of them.
     */
    public Texturer() {

        final BufferedImageLoader loader = new BufferedImageLoader();

        terrain = Optional.of(new BufferedImage[BRICKSCASTLEBLOCKS_COUNT]);
        lucky = Optional.of(new BufferedImage[LUCKYBLOCKS_COUNT]);
        scoreboard = Optional.of(new BufferedImage[SCOREBOARD_COUNT]);
        pipe = Optional.of(new BufferedImage[PIPEBLOCKS_COUNT]);
        cloud = Optional.of(new BufferedImage[CLOUDBLOCKS_COUNT]);
        bush = Optional.of(new BufferedImage[BUSHBLOCKS_COUNT]);
        hill = Optional.of(new BufferedImage[HILLBLOCKS_COUNT]);
        flag = Optional.of(new BufferedImage[FLAGBLOCKS_COUNT]);

        goomba = Optional.of(new BufferedImage[GOOMBA_COUNT]);
        koopaTroopa = Optional.of(new BufferedImage[KOOPATROOPA_COUNT]);
        flyingKoopa = Optional.of(new BufferedImage[FLYINGKOOPA_COUNT]);

        peach = Optional.of(new BufferedImage[PEACH_COUNT]);

        powerups = Optional.of(new BufferedImage[POWERUPS_COUNT]);

        blocksTileSet = Optional.of(loader.loadImage("it/unibo/superpeach/tiles/blocks_tile.png"));
        enemiesTileSet = Optional.of(loader.loadImage("it/unibo/superpeach/tiles/enemies.png"));
        playerTileSet = Optional.of(loader.loadImage("it/unibo/superpeach/tiles/player_tile.png"));
        powerupsTileSet = Optional.of(loader.loadImage("it/unibo/superpeach/tiles/powerups_tiles.png"));
        loadTerrain();
        loadLucky();
        loadScoreboard();
        loadPipe();
        loadCloud();
        loadBush();
        loadHill();
        loadFlag();
        loadGoomba();
        loadKoopaTroopa();
        loadFlyingKoopa();
        loadPeach();
        loadPowerups();

    }

    private void loadPeach() {
        for (int i = 0; i < PEACH_COUNT; i++) {
            peach.get()[i] = playerTileSet.get().getSubimage((TILE_WIDTH + 1) * i, 0, TILE_WIDTH, TILE_HEIGHT * 2);
        }

    }

    private void loadTerrain() {
        final int x = 0;
        final int y = 16;
        int index = 0;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT / 8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT / 2; j++) {
                terrain.get()[index] = blocksTileSet.get().getSubimage(x + j * (TILE_WIDTH + 1), y + i * (TILE_HEIGHT + 1),
                        TILE_WIDTH, TILE_HEIGHT);
                index++;
            }
        }
    }

    private void loadLucky() {
        final int x1 = 298, x2 = 349, y = 78;
        lucky.get()[0] = blocksTileSet.get().getSubimage(x1, y, TILE_WIDTH, TILE_HEIGHT);
        lucky.get()[1] = blocksTileSet.get().getSubimage(x2, y, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadScoreboard() {
        final int x1 = 315, x2 = 298, x3 = 332, y1 = 78, y2 = 95;
        scoreboard.get()[0] = blocksTileSet.get().getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        scoreboard.get()[1] = blocksTileSet.get().getSubimage(x1, y2, TILE_WIDTH, TILE_HEIGHT);
        scoreboard.get()[2] = blocksTileSet.get().getSubimage(x2, y2, TILE_WIDTH, TILE_HEIGHT);
        scoreboard.get()[3] = blocksTileSet.get().getSubimage(x3, y2, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadPipe() {
        final int x1 = 119, x2 = 136, y1 = 196, y2 = 213;
        pipe.get()[0] = blocksTileSet.get().getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        pipe.get()[1] = blocksTileSet.get().getSubimage(x2, y1, TILE_WIDTH, TILE_HEIGHT);
        pipe.get()[2] = blocksTileSet.get().getSubimage(x1, y2, TILE_WIDTH, TILE_HEIGHT);
        pipe.get()[3] = blocksTileSet.get().getSubimage(x2, y2, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadCloud() {
        final int x = 298;
        final int y = 16;
        int index = 0;

        for (int i = 0; i < CLOUDBLOCKS_COUNT / 3; i++) {
            for (int j = 0; j < CLOUDBLOCKS_COUNT / 2; j++) {
                cloud.get()[index] = blocksTileSet.get().getSubimage(x + j * (TILE_WIDTH + 1), y + i * (TILE_HEIGHT + 1),
                        TILE_WIDTH, TILE_HEIGHT);
                index++;
            }
        }
    }

    private void loadBush() {
        final int x = 0;
        final int y = 213;

        for (int i = 0; i < BUSHBLOCKS_COUNT; i++) {
            bush.get()[i] = blocksTileSet.get().getSubimage(x + i * (TILE_WIDTH + 1), y, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void loadHill() {
        final int x1 = 34, x2 = 0;
        final int y1 = 230, y2 = 247;

        hill.get()[0] = blocksTileSet.get().getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        for (int i = 0; i < HILLBLOCKS_COUNT - 1; i++) {
            hill.get()[i + 1] = blocksTileSet.get().getSubimage(x2 + i * (TILE_WIDTH + 1), y2, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void loadFlag() {
        final int x1 = 136, x2 = 119, y1 = 230, y2 = 247;
        flag.get()[0] = blocksTileSet.get().getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        flag.get()[1] = blocksTileSet.get().getSubimage(x1, y2, TILE_WIDTH, TILE_HEIGHT);
        flag.get()[2] = blocksTileSet.get().getSubimage(x2, y1, TILE_WIDTH, TILE_HEIGHT);
        flag.get()[3] = blocksTileSet.get().getSubimage(x2, y2, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadGoomba() {
        goomba.get()[0] = enemiesTileSet.get().getSubimage(0, 4, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadKoopaTroopa() {
        final int x1 = 180, x2 = 211;
        koopaTroopa.get()[0] = enemiesTileSet.get().getSubimage(x1, 0, TILE_WIDTH, KOOPA_HEIGHT);
        koopaTroopa.get()[1] = enemiesTileSet.get().getSubimage(x2, 0, TILE_WIDTH, KOOPA_HEIGHT);
    }

    private void loadFlyingKoopa() {
        final int x1 = 120, x2 = 271;
        flyingKoopa.get()[0] = enemiesTileSet.get().getSubimage(x1, 0, TILE_WIDTH, KOOPA_HEIGHT);
        flyingKoopa.get()[1] = enemiesTileSet.get().getSubimage(x2, 0, TILE_WIDTH, KOOPA_HEIGHT);
    }

    private void loadPowerups() {
        final int xy2 = 96, x3 = 256;
        powerups.get()[0] = powerupsTileSet.get().getSubimage(16, 32, TILE_WIDTH, TILE_HEIGHT);
        powerups.get()[1] = powerupsTileSet.get().getSubimage(xy2, xy2, TILE_WIDTH, TILE_HEIGHT);
        powerups.get()[2] = powerupsTileSet.get().getSubimage(x3, 32, TILE_WIDTH, TILE_HEIGHT);
        powerups.get()[3] = powerupsTileSet.get().getSubimage(64, 16, TILE_WIDTH, TILE_HEIGHT);
    }

    /**
     * @return peach sprites
     */
    public BufferedImage[] getPeach() {
        return this.peach.get();
    }

    /**
     * @return bricks and ground sprites
     */
    public BufferedImage[] getTerrain() {
        return this.terrain.get();
    }

    /**
     * @return lucky blocks sprites
     */
    public BufferedImage[] getLucky() {
        return this.lucky.get();
    }

    /**
     * @return coins and hearts sprites
     */
    public BufferedImage[] getScoreboard() {
        return this.scoreboard.get();
    }

    /**
     * @return pipes sprites
     */
    public BufferedImage[] getPipe() {
        return this.pipe.get();
    }

    /**
     * @return cloud blocks sprites
     */
    public BufferedImage[] getCloud() {
        return this.cloud.get();
    }

    /**
     * @return bush blocks sprites
     */
    public BufferedImage[] getBush() {
        return this.bush.get();
    }

    /**
     * @return hills blocks sprites
     */
    public BufferedImage[] getHill() {
        return this.hill.get();
    }

    /**
     * @return flag, flag pole and flag tip sprites
     */
    public BufferedImage[] getFlag() {
        return this.flag.get();
    }

    /**
     * @return goomba (enemy mushroom) sprites
     */
    public BufferedImage[] getGoombaImg() {
        return this.goomba.get();
    }

    /**
     * @return koopa (turtle) sprites
     */
    public BufferedImage[] getKoopaImg() {
        return this.koopaTroopa.get();
    }

    /**
     * @return flying koopa (turtle) sprites
     */
    public BufferedImage[] getFlyingKoopa() {
        return this.flyingKoopa.get();
    }
    /**
     * @return powerups sprites
     */
    public BufferedImage[] getPowerups() {
        return this.powerups.get();
    }
}
