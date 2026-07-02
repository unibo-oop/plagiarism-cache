package ala.views;

import ala.models.LuciferFlyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * LuciferFlyView class.
 * 
 */
public final class LuciferFlyView extends LuciferBasicView {

    //Attributes:
    //Flying Lucifer sprites
    private static final Image IMG_LUCIFER_FLY = new Image(LuciferBasicView.class.getResource("/LuciferFly.gif").toExternalForm());
    private static final Image IMG_LUCIFER_FLY_SCRATCH = new Image(LuciferBasicView.class.getResource("/LuciferFlyScratch.gif").toExternalForm());
    private static final Image IMG_LUCIFER_FLY_FIREBALL = new Image(LuciferBasicView.class.getResource("/LuciferFlyFireball.gif").toExternalForm());

    private LuciferFlyModel luciferFlyModel;
    /**
     * Constructor.
     * 
     * @param layer
     * @param luciferFlyModel
     * 
     */
    public LuciferFlyView(final Pane layer, final LuciferFlyModel luciferFlyModel) {
        super(layer, IMG_LUCIFER_FLY, luciferFlyModel);
        this.luciferFlyModel = luciferFlyModel;
    }

    //Getters&Setters: 
    public void setImgLuciferFly() {
        this.getImageView().setImage(IMG_LUCIFER_FLY);
    }

    public void setImgLuciferFlyScratch() {
        this.getImageView().setImage(IMG_LUCIFER_FLY_SCRATCH);
    }

    public void setImgLuciferFlyFireball() {
        this.getImageView().setImage(IMG_LUCIFER_FLY_FIREBALL);
    }

    public static Image getImgLuciferFly() {
        return IMG_LUCIFER_FLY;
    }

    public static Image getImgLuciferFlyScratch() {
        return IMG_LUCIFER_FLY_SCRATCH;
    }

    public static Image getImgLuciferFlyFireball() {
        return IMG_LUCIFER_FLY_FIREBALL;
    }

    public LuciferFlyModel getLuciferFlyModel() {
        return luciferFlyModel;
    }

    public void setLuciferFlyModel(final LuciferFlyModel luciferFlyModel) {
        this.luciferFlyModel = luciferFlyModel;
    }
}
