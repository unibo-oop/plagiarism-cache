package graphics;

import java.awt.Graphics2D;

import utilities.Position;

/**
 * It models the graphic component for any GameObject.
 */
public interface GraphicsComponent {

    /**
     * Render method for any component.
     * @param g : graphics to draw.
     * @param position : location where draw the image to.
     */
    void render(Graphics2D g, Position position);

    /**
     * Loading sprites.
     * @param sheet : where to take sprites for game.
     */
    void loadSprites(SpriteSheet sheet);
}
