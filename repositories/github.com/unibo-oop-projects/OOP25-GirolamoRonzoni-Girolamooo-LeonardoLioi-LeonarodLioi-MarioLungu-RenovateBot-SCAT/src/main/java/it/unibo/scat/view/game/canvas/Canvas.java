package it.unibo.scat.view.game.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.common.EntityState;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.game.api.SpriteManager;

/**
 * The main panel that draws the game.
 * It takes the game objects, converts their position to screen pixels, and
 * draws the images.
 */
@SuppressFBWarnings(value = { "SE_TRANSIENT_FIELD_NOT_RESTORED",
        "EI_EXPOSE_REP2" }, justification = "Component not intended for serialization;Reference intentionally shared")
public final class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient ViewActionsInterface menuActionsInterface;
    private transient volatile List<EntityState> entities;
    private final transient Image voidImage;
    private final AtomicInteger invadersAnimationFrame = new AtomicInteger(0);
    private int lastInvadersHash;

    private transient SpriteManager spriteManger;

    /**
     * Sets up the canvas to draw the game.
     * 
     * @param menuActionsInterface used to get the list of objects to draw.
     */
    public Canvas(final ViewActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;
        entities = null; // to do for the checkstyle
        voidImage = new ImageIcon(
                Objects.requireNonNull(SpriteManagerImpl.class.getResource(UIConstants.NULL_PATH))).getImage();

        setForeground(Color.WHITE);
        setFont(UIConstants.FONT_S);
        update();
    }

    /**
     * Updates the game data.
     */
    public void update() {
        entities = menuActionsInterface.fetchEntitiesFromModel();

        final int invHash = hashPositions(entities, EntityType.INVADER_1, EntityType.INVADER_2, EntityType.INVADER_3);
        if (invHash != lastInvadersHash) {
            invadersAnimationFrame.set(invadersAnimationFrame.get() == 1 ? 0 : 1);
            lastInvadersHash = invHash;
        }
    }

    /**
     * Calculates a hash value based on the position of all the entieis of one (or
     * more) EntityType.
     * The hash changes only when at least one Entity of the group changes
     * position.
     *
     * @param entityList the entities list.
     * @param types      the entity types to include.
     * @return a hash of the group position, or 0 if there are no entities of the
     *         given type.
     */
    private static int hashPositions(final List<EntityState> entityList, final EntityType... types) {
        final int hashingValue = 31;
        int minX = Constants.BORDER_RIGHT;
        int maxX = Constants.BORDER_LEFT;
        int minY = Constants.BORDER_BOTTOM;
        int maxY = Constants.BORDER_UP;

        for (final EntityState entity : entityList) {
            for (final EntityType type : types) {
                if (entity.getType() == type) {
                    minX = Math.min(minX, entity.getPosition().getX());
                    maxX = Math.max(maxX, entity.getPosition().getX());
                    minY = Math.min(minY, entity.getPosition().getY());
                    maxY = Math.max(maxY, entity.getPosition().getY());
                    break;
                }
            }
        }

        int hash = 1;
        hash = hashingValue * hash + minX;
        hash = hashingValue * hash + minY;
        hash = hashingValue * hash + maxX;
        hash = hashingValue * hash + maxY;
        return hash;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (entities == null) {
            throw new IllegalStateException("Null entities in Canvas!");
        }

        if (spriteManger == null) {
            initSpriteManger();
        }

        final List<EntityState> list = entities;

        final int scaleX = getWidth() / Constants.BORDER_RIGHT;
        final int scaleY = getHeight() / Constants.BORDER_BOTTOM;

        for (final EntityState entity : list) {

            // ENTITIES
            final int x = entity.getPosition().getX() * scaleX;
            final int y = entity.getPosition().getY() * scaleY;

            final Image imm = fetchImage(entity);
            g.drawImage(imm, x, y, null);

            // BUNKERS LIFE COUNTERS
            if (entity.getType() == EntityType.BUNKER) {
                final int newX = x + (entity.getWidth() / 2 * scaleX);
                final int newY = y + ((entity.getHeight() + 1) * scaleY);
                g.drawString(String.valueOf(entity.getHealth()), newX, newY);
            }
        }
    }

    /**
     * Chooses the correct image for an entity.
     * 
     * @param entity the entity to draw.
     * @return the image to be drawn.
     */
    private Image fetchImage(final EntityState entity) {

        switch (entity.getType()) {
            case INVADER_1, INVADER_2, INVADER_3 -> {
                return spriteManger.getSprite(entity.getType(), invadersAnimationFrame.get());
            }
            case BONUS_INVADER -> {
                return spriteManger.getSprite(entity.getType(), 0);
            }
            case PLAYER, PLAYER_SHOT -> {
                return spriteManger.getSprite(entity.getType(), menuActionsInterface.getChosenShipIndex());
            }
            case INVADER_SHOT -> {
                return spriteManger.getSprite(entity.getType(), 1);
            }
            case BUNKER -> {
                if (entity.getHealth() > Constants.BUNKER_HEALTH / 3 * 2) {
                    return spriteManger.getSprite(entity.getType(), 0);
                } else if (entity.getHealth() > Constants.BUNKER_HEALTH / 3) {
                    return spriteManger.getSprite(entity.getType(), 1);
                }
                return spriteManger.getSprite(entity.getType(), 2);
            }
        }

        return voidImage;
    }

    /**
     * Initializes the SpriteManager.
     */
    private void initSpriteManger() {
        final int scaleX = getWidth() / Constants.BORDER_RIGHT;
        final int scaleY = getHeight() / Constants.BORDER_BOTTOM;
        spriteManger = new SpriteManagerImpl(scaleX, scaleY);
    }
}
