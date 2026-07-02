package it.unibo.superpeach.level;

import java.awt.image.BufferedImage;
import java.util.Optional;

import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.blocks.LuckyBlock;
import it.unibo.superpeach.gameentities.blocks.MapBackgroundBlock;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;
import it.unibo.superpeach.gameentities.enemies.FlyingKoopa;
import it.unibo.superpeach.gameentities.enemies.Goomba;
import it.unibo.superpeach.gameentities.enemies.KoopaTroopa;
import it.unibo.superpeach.gameentities.powerups.PowerUp.PowerUpType;
import it.unibo.superpeach.graphics.BufferedImageLoader;

/**
 * Level generation class that matches each color of a pixel matrix with a
 * particular map block or enemy.
 * 
 * @author Maurizio Capuano
 */
public final class LevelHandler {

    private final BufferedImageLoader loader;
    private final Optional<BlocksHandler> blocksHandler;
    private final int gameScale;

    private final Optional<EnemiesHandler> enemiesHandler;

    /**
     * Level handler constructor.
     * 
     * @param blocksHandler  handler of game blocks which contains every block and
     *                       can create them
     * @param scale          game scale
     * @param enemiesHandler handler of enemies entities which contains every enemy
     *                       and can create them
     */
    public LevelHandler(final BlocksHandler blocksHandler, final int scale, final EnemiesHandler enemiesHandler) {
        this.blocksHandler = Optional.of(blocksHandler);
        this.enemiesHandler = Optional.of(enemiesHandler);
        gameScale = scale;
        loader = new BufferedImageLoader();
    }

    /**
     * Methods that adds all blocks and enemies to their handlers which will then
     * render them.
     */
    public void parseDrawLevel() {
        final BufferedImage levelImage = loader.loadImage("it/unibo/superpeach/level/level_blocks.png");
        final int width = levelImage.getWidth();
        final int height = levelImage.getHeight();
        final int trueAnd = 0xff, flyingKoopaHeight = 23;
        final int[] emptyColor = { 255, 255, 255 };
        final int[] brickColor = { 185, 122, 87 };
        final int[] terrainColor = { 100, 100, 100 };
        final int[] stoneColor = { 0, 0, 0 };
        final int[] luckyCoinColor = { 255, 201, 14 };
        final int[] luckyRedColor = { 255, 202, 14 };
        final int[] luckyLifeColor = { 255, 203, 14 };
        final int[] luckyStarColor = { 255, 204, 14 };
        final int[] deathColor = { 255, 127, 39 };
        final int[] altColor = { 127, 127, 127 };
        final int[] bgTerrainColor = { 100, 100, 50 };
        final int[] bgStoneColor = { 0, 0, 50 };
        final int[] cloudTopLeftColor = { 153, 217, 234 };
        final int[] cloudTopMiddleColor = { 153, 217, 235 };
        final int[] cloudTopRightColor = { 153, 217, 236 };
        final int[] cloudBotLeftColor = { 153, 217, 237 };
        final int[] cloudBotMiddleColor = { 153, 217, 238 };
        final int[] cloudBotRightColor = { 153, 217, 239 };
        final int[] bushLeftColor = { 181, 230, 29 };
        final int[] bushMiddleColor = { 181, 231, 29 };
        final int[] bushRightColor = { 181, 232, 29 };
        final int[] hillUpColor = { 34, 177, 76 };
        final int[] hillBlankColor = { 34, 178, 76 };
        final int[] hillSpots1Color = { 34, 179, 76 };
        final int[] hillSpots2Color = { 34, 180, 76 };
        final int[] hillTopColor = { 34, 181, 76 };
        final int[] hillDownColor = { 34, 182, 76 };
        final int[] pipeTopLeftColor = { 237, 28, 36 };
        final int[] pipeTopRightColor = { 238, 28, 36 };
        final int[] pipeLeftColor = { 239, 28, 36 };
        final int[] pipeRightColor = { 240, 28, 36 };
        final int[] flagTipColor = { 200, 191, 231 };
        final int[] flagPoleColor = { 163, 73, 164 };
        final int[] flagLeftColor = { 163, 73, 165 };
        final int[] flagRightColor = { 163, 73, 166 };
        final int[] castleDoorColor = { 255, 174, 201 };
        final int[] castleBrickColor = { 255, 174, 202 };
        final int[] castleBalcony1Color = { 255, 174, 203 };
        final int[] castleBalcony2Color = { 255, 174, 204 };
        final int[] castleWindLeftColor = { 255, 174, 205 };
        final int[] castleWindRightColor = { 255, 174, 206 };
        final int[] goombaColor = { 63, 72, 204 };
        final int[] koopaColor = { 64, 72, 204 };
        final int[] flyingKoopaColor = { 65, 72, 204 };
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                final int pixel = levelImage.getRGB(i, j);
                final int r = (pixel >> 16) & trueAnd;
                final int g = (pixel >> 8) & trueAnd;
                final int b = (pixel) & trueAnd;

                if (!(r == emptyColor[0] && g == emptyColor[1] && b == emptyColor[2])) {
                    if (r == brickColor[0] && g == brickColor[1] && b == brickColor[2]) {
                        blocksHandler.get()
                                .addFixedBlock(new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BRICK));
                    } else if (r == terrainColor[0] && g == terrainColor[1] && b == terrainColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.TERRAIN));
                    } else if (r == stoneColor[0] && g == stoneColor[1] && b == stoneColor[2]) {
                        blocksHandler.get()
                                .addFixedBlock(new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.STONE));
                    } else if (r == luckyCoinColor[0] && g == luckyCoinColor[1] && b == luckyCoinColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY, PowerUpType.COIN));
                    } else if (r == luckyRedColor[0] && g == luckyRedColor[1] && b == luckyRedColor[2]) {
                        blocksHandler.get()
                                .addFixedBlock(new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY,
                                        PowerUpType.RED_MUSHROOM));
                    } else if (r == luckyLifeColor[0] && g == luckyLifeColor[1] && b == luckyLifeColor[2]) {
                        blocksHandler.get()
                                .addFixedBlock(new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY,
                                        PowerUpType.LIFE_MUSHROOM));
                    } else if (r == luckyStarColor[0] && g == luckyStarColor[1] && b == luckyStarColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY, PowerUpType.STAR));
                    } else if (r == deathColor[0] && g == deathColor[1] && b == deathColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.DEATH_BLOCK));
                    } else if (r == altColor[0] && g == altColor[1] && b == altColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.ALT_BLOCK));
                    } else if (r == bgTerrainColor[0] && g == bgTerrainColor[1] && b == bgTerrainColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.TERRAIN));
                    } else if (r == bgStoneColor[0] && g == bgStoneColor[1] && b == bgStoneColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.STONE));
                    } else if (r == cloudTopLeftColor[0] && g == cloudTopLeftColor[1] && b == cloudTopLeftColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_TOP_LEFT));
                    } else if (r == cloudTopMiddleColor[0] && g == cloudTopMiddleColor[1]
                            && b == cloudTopMiddleColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_TOP_MIDDLE));
                    } else if (r == cloudTopRightColor[0] && g == cloudTopRightColor[1] && b == cloudTopRightColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_TOP_RIGHT));
                    } else if (r == cloudBotLeftColor[0] && g == cloudBotLeftColor[1] && b == cloudBotLeftColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_BOT_LEFT));
                    } else if (r == cloudBotMiddleColor[0] && g == cloudBotMiddleColor[1]
                            && b == cloudBotMiddleColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_BOT_MIDDLE));
                    } else if (r == cloudBotRightColor[0] && g == cloudBotRightColor[1] && b == cloudBotRightColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_BOT_RIGHT));
                    } else if (r == bushLeftColor[0] && g == bushLeftColor[1] && b == bushLeftColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BUSH_LEFT));
                    } else if (r == bushMiddleColor[0] && g == bushMiddleColor[1] && b == bushMiddleColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BUSH_MIDDLE));
                    } else if (r == bushRightColor[0] && g == bushRightColor[1] && b == bushRightColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BUSH_RIGHT));
                    } else if (r == hillUpColor[0] && g == hillUpColor[1] && b == hillUpColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_UP));
                    } else if (r == hillBlankColor[0] && g == hillBlankColor[1] && b == hillBlankColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_BLANK));
                    } else if (r == hillSpots1Color[0] && g == hillSpots1Color[1] && b == hillSpots1Color[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_SPOTS1));
                    } else if (r == hillSpots2Color[0] && g == hillSpots2Color[1] && b == hillSpots2Color[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_SPOTS2));
                    } else if (r == hillTopColor[0] && g == hillTopColor[1] && b == hillTopColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_TOP));
                    } else if (r == hillDownColor[0] && g == hillDownColor[1] && b == hillDownColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_DOWN));
                    } else if (r == pipeTopLeftColor[0] && g == pipeTopLeftColor[1] && b == pipeTopLeftColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_TOP_LEFT));
                    } else if (r == pipeTopRightColor[0] && g == pipeTopRightColor[1] && b == pipeTopRightColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_TOP_RIGHT));
                    } else if (r == pipeLeftColor[0] && g == pipeLeftColor[1] && b == pipeLeftColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_LEFT));
                    } else if (r == pipeRightColor[0] && g == pipeRightColor[1] && b == pipeRightColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_RIGHT));
                    } else if (r == flagTipColor[0] && g == flagTipColor[1] && b == flagTipColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_TIP));
                    } else if (r == flagPoleColor[0] && g == flagPoleColor[1] && b == flagPoleColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_POLE));
                    } else if (r == flagLeftColor[0] && g == flagLeftColor[1] && b == flagLeftColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_LEFT));
                    } else if (r == flagRightColor[0] && g == flagRightColor[1] && b == flagRightColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_POLE));
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_RIGHT));
                    } else if (r == castleDoorColor[0] && g == castleDoorColor[1] && b == castleDoorColor[2]) {
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, (j - 1) * 16, 16, 16, gameScale, BlockType.CASTLE_DOOR_TOP));
                        blocksHandler.get().addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_DOOR_BOT));
                    } else if (r == castleBrickColor[0] && g == castleBrickColor[1] && b == castleBrickColor[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_BRICK));
                    } else if (r == castleBalcony1Color[0] && g == castleBalcony1Color[1]
                            && b == castleBalcony1Color[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_BALCONY1));
                    } else if (r == castleBalcony2Color[0] && g == castleBalcony2Color[1]
                            && b == castleBalcony2Color[2]) {
                        blocksHandler.get().addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_BALCONY2));
                    } else if (r == castleWindLeftColor[0] && g == castleWindLeftColor[1]
                            && b == castleWindLeftColor[2]) {
                        blocksHandler.get().addBackgroundBlock(new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale,
                                BlockType.CASTLE_WINDOW_LEFT));
                    } else if (r == castleWindRightColor[0] && g == castleWindRightColor[1]
                            && b == castleWindRightColor[2]) {
                        blocksHandler.get().addBackgroundBlock(new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale,
                                BlockType.CASTLE_WINDOW_RIGHT));
                    } else if (r == goombaColor[0] && g == goombaColor[1] && b == goombaColor[2]) {
                        enemiesHandler.get()
                                .addEnemy(new Goomba(i * 16, j * 16, 16, 16, gameScale, blocksHandler.get()));
                    } else if (r == koopaColor[0] && g == koopaColor[1] && b == koopaColor[2]) {
                        enemiesHandler.get().addEnemy(new KoopaTroopa(i * 16, (j - 1) * 16, 16, flyingKoopaHeight,
                                gameScale, blocksHandler.get()));
                    } else if (r == flyingKoopaColor[0] && g == flyingKoopaColor[1] && b == flyingKoopaColor[2]) {
                        enemiesHandler.get().addEnemy(new FlyingKoopa(i * 16, (j - 1) * 16, 16, flyingKoopaHeight,
                                gameScale, blocksHandler.get()));
                    }
                }
            }
        }
    }
}
