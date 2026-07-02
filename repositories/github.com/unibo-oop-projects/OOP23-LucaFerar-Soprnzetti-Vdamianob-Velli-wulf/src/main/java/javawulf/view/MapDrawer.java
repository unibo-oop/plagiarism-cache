package javawulf.view;

import java.awt.Graphics2D;

import javawulf.model.map.Map;
import javawulf.model.map.TilePosition;
import javawulf.model.player.Player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Used in GamePanel for drawing Map (Game World).
 */
public final class MapDrawer implements Drawer {
    private final Map map;
    private BufferedImage imgRoom;
    private BufferedImage imgWall;
    private BufferedImage imgCorridor;
    private BufferedImage imgCentralRoom;
    private BufferedImage imgPortal;
    private final GamePanel gamePanel;

    /**
     * MapDrawer is a component of GamePanel and it used for draw map Enviroment:
     * all tiles close to the player, with a move-effect redrawing everytime player
     * is moving. Constructor inizializes all tile images from memory.
     * 
     * @param map
     * @param gamePanel
     */
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI2"
        },
        justification = "It isn't a problem expose internal representation to GamePanel"
    )
    public MapDrawer(final Map map, final GamePanel gamePanel) {
        this.map = map;
        this.gamePanel = gamePanel;
        try {
            this.imgRoom = ImageIO.read(getClass().getResourceAsStream(ImagePath.ROOM_TILE.getPath()));
            this.imgWall = ImageIO.read(getClass().getResourceAsStream(ImagePath.WALL_TILE.getPath()));
            this.imgCorridor = ImageIO.read(getClass().getResourceAsStream(ImagePath.CORRIDOR_TILE.getPath()));
            this.imgCentralRoom = ImageIO.read(getClass().getResourceAsStream(ImagePath.CENTRAL_ROOM_TILE.getPath()));
            this.imgPortal = ImageIO.read(getClass().getResourceAsStream(ImagePath.PORTAL_TILE.getPath()));
        } catch (IOException e) {
            final Logger log = Logger.getLogger(MapDrawer.class.getName());
            log.fine("Exception during path image load.");
        }

    }

    @Override
    public void draw(final Graphics2D graphics) {
        final int offset = 1;
        final TilePosition playerPos = this.map.getTilePosition(this.map.getPlayer().getPosition()).get();
        for (int x = playerPos.getX() - 8; x < playerPos.getX() + 8; x++) {
            for (int y = playerPos.getY() - 8 - offset; y < playerPos.getY() + 8; y++) {
                BufferedImage img;
                if (this.map.getTilesMap().containsKey(new TilePosition(x, y))) {
                    switch (this.map.getTilesMap().get(new TilePosition(x, y))) {
                        case ROOM:
                            img = imgRoom;
                            break;
                        case CENTRAL_ROOM:
                            img = imgCentralRoom;
                            break;
                        case CORRIDOR:
                            img = imgCorridor;
                            break;
                        case PORTAL:
                            img = imgPortal;
                            break;
                        default:
                            img = imgRoom;
                            break;
                    }
                } else {
                    img = imgWall;
                }
                graphics.drawImage(img,
                        x * GamePanel.TILESIZE + (this.gamePanel.getWidth() / 2 - Player.OBJECT_SIZE / 2)
                                - (int) map.getPlayer().getBounds().getCollisionArea().getX() * GamePanel.SCALE,
                        y * GamePanel.TILESIZE + (this.gamePanel.getHeight() / 2 - Player.OBJECT_SIZE / 2)
                                - (int) map.getPlayer().getBounds().getCollisionArea().getY() * GamePanel.SCALE,
                        GamePanel.TILESIZE, GamePanel.TILESIZE, null);
            }
        }
    }

}
