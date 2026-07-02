package view.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Point;
import model.Trap;

/**
 * Draws the Trap based on its status
 */
public class TrapView {

    private Texture imagetrap;
    private Trap model;
    private Point point;

    /**
     * Loads the image of the trap and sets its drawing position
     */
    public TrapView() {
        this.imagetrap = new Texture("trap.png");
        this.point = new Point(0, 0);
    }

    /**
     * Sets up the trap using its model
     * @param model
     */
    public void setModel(final Trap model) {
        this.model = model;
        this.point = new Point((int) (this.model.getBody().getPosition().x - this.model.getWidth() / 2),
                               (int) (this.model.getBody().getPosition().y - this.model.getHeight() / 2));
    }

    /**
     * Draws the trap
     * @param batch
     */
    public void drawTrap(SpriteBatch batch) {
        if (this.model.getStatus()) {
            batch.draw(this.imagetrap,
                       this.point.x, this.point.y, this.model.getWidth() + 2, this.model.getHeight() + 2);
        }
    }
}
