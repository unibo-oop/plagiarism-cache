package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.Collectable;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.Shield;

/**
 * Implementation to draw the items collectable.
 */
public final class ItemDrawer extends AbstractDrawer {

    private final Map<Class<? extends Collectable>, BufferedImage> images = new HashMap<>();
    private final List<Collectable> items;

    /**
     * Builds the items passed from the Controller.
     * 
     * @param gamePanel the Game Panel where the items must be drawn
     * @param items     a list of all the items to draw
     * @param player    the current status of the Player character
     */
    public ItemDrawer(final GamePanel gamePanel, final List<Collectable> items, final PlayerStatus player) {
        super(gamePanel, player);
        this.items = new ArrayList<>(items);
        try {
            images.put(Cure.class, this.imageLoader(ImagePath.CURE));
            images.put(CureMax.class, this.imageLoader(ImagePath.CURE_MAX));
            images.put(ExtraHeart.class, this.imageLoader(ImagePath.EXTRA_HEART));
            images.put(GreatSword.class, this.imageLoader(ImagePath.GREATSWORD));
            images.put(Shield.class, this.imageLoader(ImagePath.SHIELD_ITEM));
        } catch (IOException e) {
            Logger.getLogger(ItemDrawer.class.getName()).fine(e.getMessage());
        }

    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (final Collectable item : items) {
            final BufferedImage image = images.get(item.getClass());
            if (!item.getBounds().getCollisionType().equals(CollisionType.INACTIVE)) {
                this.drawImage(graphics, image, (int) item.getBounds().getCollisionArea().getX(),
                        (int) item.getBounds().getCollisionArea().getY());
            }
        }
    }

}
