package view.graphics;

import javafx.scene.image.Image;
import model.entities.GameObject;
import model.entities.PowerUp;

public class PwUpComponentGraphics implements ComponentGraphics {

    private transient Image pwupImage;
    private final String texture;

    public PwUpComponentGraphics(final String texturePath) {
        this.texture = texturePath;
        this.pwupImage = new Image(ClassLoader.getSystemResourceAsStream(texturePath));
    }

    /**
     *  draw the paddle by passing the specific graphic information to the graphic adapter. 
     */
    @Override
    public void update(final GameObject pwup, final AdapterGraphics graphicsAdapt) {
        this.pwupImage = new Image(ClassLoader.getSystemResourceAsStream(texture));
        graphicsAdapt.drawPowerUp((PowerUp) pwup, this.pwupImage);
    }

}
