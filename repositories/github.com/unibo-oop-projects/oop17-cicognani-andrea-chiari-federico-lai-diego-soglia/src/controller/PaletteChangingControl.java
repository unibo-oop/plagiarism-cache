/**
 * Class for changing controls during events
* @author Diego
*
*
 */

package controller;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Rectangle2D;
import javafx.scene.transform.Scale;

import java.util.Random;


public class PaletteChangingControl extends Component {

    private PositionComponent position;
    private ViewComponent view;
    private Texture texture;

    /**
     * Set up texture control
     *
     * @param texture
     */

    public PaletteChangingControl(Texture texture) {
        this.texture = texture;
    }

    /**
     *
     * @param texture
     * Add texture on game and give it the right position and animation
     *
     */
    @Override
    public void onAdded() {
        view.setView(texture);
        view.getView().getTransforms().addAll(new Scale(0.26, 0.26, 0, 0));
    }

    private double lastX = 0;
    private double lastY = 0;

    private double timeToSwitch = 0;
    private int spriteColor = 0;

    private Random random = FXGLMath.getRandom();

    @Override
    /**
     *
     *@param tpf (timeToSwitch)
     *@return movement direction and animation
     */
    public void onUpdate(double tpf) {
        timeToSwitch += tpf;

        if (timeToSwitch >= 5.0) {
            spriteColor = 160 * random.nextInt(6);
            timeToSwitch = 0;
        }

        double dx = position.getX() - lastX;
        double dy = position.getY() - lastY;

        lastX = position.getX();
        lastY = position.getY();

        if (dx == 0 && dy == 0) {
            // didn't move
            return;
        }

        if (Math.abs(dx) > Math.abs(dy)) {
            // move was horizontal
            if (dx > 0) {
                texture.setViewport(new Rectangle2D(130 * 3, spriteColor, 130, 160));
            } else {
                texture.setViewport(new Rectangle2D(130 * 2, spriteColor, 130, 160));
            }
        } else {
            // move was vertical
            if (dy > 0) {
                texture.setViewport(new Rectangle2D(0, spriteColor, 130, 160));
            } else {
                texture.setViewport(new Rectangle2D(130, spriteColor, 130, 160));
            }
        }
    }
}
