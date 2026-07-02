package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.enemy.Pawn;

/**
 * Implementation to draw the pawn.
 */
public final class PawnDrawer extends AbstractDrawer {

    private BufferedImage pawn;

    private final List<Pawn> pawns;

    /**
     * The Pawn Drawer.
     * 
     * @param player    the current status of the Player character
     * @param gamePanel the Game Panel where the pawn must be drawn
     * @param pawns     a list of all the pawns to draw
     */
    public PawnDrawer(final PlayerStatus player, final GamePanel gamePanel, final List<Pawn> pawns) {
        super(gamePanel, player);
        this.pawns = new ArrayList<>(pawns);
        try {
            this.pawn = this.imageLoader(ImagePath.PAWN_UP);
        } catch (IOException e) {
            Logger.getLogger(PawnDrawer.class.getName()).fine(e.getMessage());
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final Pawn pawn : this.pawns) {
            if (!pawn.getBounds().getCollisionType().equals(CollisionType.INACTIVE)) {
                String direction;
                switch (pawn.getDirection()) {
                    case UP:
                        direction = "up";
                        break;
                    case DOWN:
                        direction = "down";
                        break;
                    case LEFT:
                        direction = "left";
                        break;
                    case RIGHT:
                        direction = "right";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid direction");
                }
                final BufferedImage imgPawn = this.rotateImage(this.pawn, direction);
                this.drawImage(graphics, imgPawn, (int) pawn.getBounds().getCollisionArea().getX(),
                    (int) pawn.getBounds().getCollisionArea().getY());
            }
        }
    }

}
