package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;

/**
 * Class used to draw Player.
 */
public final class PlayerDrawer extends AbstractDrawer {

    private BufferedImage player;
    private BufferedImage sword;
    private BufferedImage greatsword;
    private final PlayerStatus gamePlayer;

    /**
     * The Player coming from the Controller.
     * 
     * @param player The Player character that must be drawn
     * @param gamePanel The panel that must be updated
     */
    public PlayerDrawer(final PlayerStatus player, final GamePanel gamePanel) {
        super(gamePanel, player);
        this.gamePlayer = player;
        try {
            this.player = imageLoader(ImagePath.PLAYER_UP);
            this.sword = imageLoader(ImagePath.SWORD);
            this.greatsword = imageLoader(ImagePath.GREATSWORD);
        } catch (IOException e) {
            Logger.getLogger(PlayerDrawer.class.getName()).fine(e.getMessage());
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        BufferedImage img = this.player;
        BufferedImage imgSword = this.sword;
        String direction;

        switch (this.gamePlayer.getDirection()) {
            case "UP_RIGHT":
            case "RIGHT":
            case "DOWN_RIGHT":
                direction = "right";
                break;
            case "UP_LEFT":
            case "LEFT":
            case "DOWN_LEFT":
                direction = "left";
                break;
            case "UP":
                direction = "up";
                break;
            default:
                direction = "down";
                break;
        }

        img = rotateImage(img, direction);

        final int playerX = this.gamePlayer.getPlayerX();
        final int playerY = this.gamePlayer.getPlayerY();

        this.drawImage(graphics, img, playerX, playerY);

        if ("SWORD".equals(this.gamePlayer.getSwordCollision())) {
            final int width = (int) this.gamePlayer.getSwordWidth();
            final int height = (int) this.gamePlayer.getSwordHeight();
            if ("GREATSWORD".equals(this.gamePlayer.getSwordType())) {
                imgSword = this.greatsword;
            }
            imgSword = rotateImage(imgSword, direction);

            this.drawImage(graphics, imgSword, this.gamePlayer.getSwordX(), this.gamePlayer.getSwordY(), width, height);
        }
    }

}
