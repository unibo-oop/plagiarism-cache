package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;

/**
 * Implementation to draw the amulet pieces.
 */
public final class AmuletPiecesDrawer extends AbstractDrawer {

    private BufferedImage amuletPiece;

    private final List<AmuletPiece> amuletPieces;

    /**
     * Builds the amulet pieces passed from the Controller.
     * 
     * @param gamePanel     the Game Panel where the pieces must be drawn
     * @param player        the current status of the Player character
     * @param amuletPieces  a list of all the pieces to draw
     */
    public AmuletPiecesDrawer(final GamePanel gamePanel, final PlayerStatus player,
            final List<AmuletPiece> amuletPieces) {
        super(gamePanel, player);
        this.amuletPieces = new ArrayList<>(amuletPieces);
        try {
            this.amuletPiece = this.imageLoader(ImagePath.AMULET_PIECE);
        } catch (IOException e) {
            Logger.getLogger(AmuletPiecesDrawer.class.getName()).fine(e.getMessage());
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final AmuletPiece piece : amuletPieces) {
            if (!piece.getBounds().getCollisionType().equals(CollisionType.INACTIVE)) {
                this.drawImage(graphics, this.amuletPiece, (int) piece.getBounds().getCollisionArea().getX(),
                    (int) piece.getBounds().getCollisionArea().getY());
            }
        }
    }
}
