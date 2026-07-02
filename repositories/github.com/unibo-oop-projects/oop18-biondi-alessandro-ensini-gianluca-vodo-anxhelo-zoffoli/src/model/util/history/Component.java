package model.util.history;

import model.effects.Effect;
import javafx.scene.image.Image;

/**
 * Represents a the contract for a generic component.
 */
public interface Component {

    /**
     * @return image of this component
     */
    Image getImage();

    /**
     * @param image 's the changed image
     * @return component
     */
    Component setImage(Image image);

    /**
     * @return the applicated effect
     */
    Effect getEffect();

    /**
     * @param effect 's the effect that has been applied
     * @return component
     */
    Component setEffect(Effect effect);

}
