package model.util.history;

import javafx.scene.image.Image;
import model.effects.Effect;

/**
 * Implements Component.
 */
public class ComponentImpl implements Component {
    /**
     * Component is formed by the image field and the effect field.
     */
    private Image image;
    private Effect effect;

    /**
     * Empty constructor.
     */
    public ComponentImpl() {
        // Does nothing.
    }

    /**
     * @param image  of this component.
     * 
     * @param effect of this component.
     */
    public ComponentImpl(final Image image, final Effect effect) {
        // Fills the fields.
        this.image = image;
        this.effect = effect;
    }

    /**
     * @see model.util.history.Component#getImage() .
     */
    @Override
    public Image getImage() {
        // return image
        return this.image;
    }

    /**
     * @see model.util.history.Component#setImage(javafx.scene.image.Image) .
     */
    @Override
    public Component setImage(final Image image) {
        // set image
        this.image = image;
        return this;
    }

    /**
     * @see model.util.history.Component#getEffect() .
     */
    @Override
    public Effect getEffect() {
        // return effect
        return this.effect;
    }

    /**
     * @see model.util.history.Component#setEffect(model.effects.Effect) .
     */
    @Override
    public Component setEffect(final Effect effect) {
        // set effect
        this.effect = effect;
        return this;
    }

}
