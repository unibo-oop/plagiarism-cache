package ala.views;

import ala.models.LuciferModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * LuciferView class.
 * 
 */
public final class LuciferView extends LuciferBasicView implements LuciferAbilitiesView {

    //Attributes:
    //Standard Lucifer sprites
    private static final Image IMG_LUCIFER_CALM_POSE_RIGHT = new Image(LuciferBasicView.class.getResource("/LuciferCalmPoseRight.gif").toExternalForm()); //Initial Pose.
    private static final Image IMG_LUCIFER_CALM_POSE_LEFT = new Image(LuciferBasicView.class.getResource("/LuciferCalmPoseLeft.gif").toExternalForm());
    private static final Image IMG_LUCIFER_WALK_LEFT = new Image(LuciferBasicView.class.getResource("/LuciferWalkLeft.gif").toExternalForm());
    private static final Image IMG_LUCIFER_WALK_RIGHT = new Image(LuciferBasicView.class.getResource("/LuciferWalkRight.gif").toExternalForm());
    private static final Image IMG_LUCIFER_FIREBALL_LEFT = new Image(LuciferBasicView.class.getResource("/LuciferFireballLeft.gif").toExternalForm());
    private static final Image IMG_LUCIFER_FIREBALL_RIGHT = new Image(LuciferBasicView.class.getResource("/LuciferFireballRight.gif").toExternalForm());
    private static final Image IMG_LUCIFER_SCRATCH_LEFT = new Image(LuciferBasicView.class.getResource("/LuciferScratchLeft.gif").toExternalForm());
    private static final Image IMG_LUCIFER_SCRATCH_RIGHT = new Image(LuciferBasicView.class.getResource("/LuciferScratchRight.gif").toExternalForm());

    private LuciferModel luciferModel;

    /**
     * Constructor.
     * 
     * @param layer
     * @param luciferModel
     * 
     */
    public LuciferView(final Pane layer, final LuciferModel luciferModel) {
        super(layer, IMG_LUCIFER_CALM_POSE_RIGHT, luciferModel);
        this.luciferModel = luciferModel;
    }

    //Getters&Setters: 
    public void setImgLuciferWalkLeft() {
        this.getImageView().setImage(IMG_LUCIFER_WALK_LEFT);
    }

    public void setImgLuciferWalkRight() {
        this.getImageView().setImage(IMG_LUCIFER_WALK_RIGHT);
    }

    public void setImgLuciferCalmPosRight() {
        this.getImageView().setImage(IMG_LUCIFER_CALM_POSE_RIGHT);
    }

    public void setImgLuciferCalmPosLeft() {
        this.getImageView().setImage(IMG_LUCIFER_CALM_POSE_LEFT);
    }

    public void setImgLuciferFireballRight() {
        this.getImageView().setImage(IMG_LUCIFER_FIREBALL_RIGHT);
    }

    public void setImgLuciferFireballLeft() {
        this.getImageView().setImage(IMG_LUCIFER_FIREBALL_LEFT);
    }

    public void setImgLuciferScratchRight() {
        this.getImageView().setImage(IMG_LUCIFER_SCRATCH_RIGHT);
    }

    public void setImgLuciferScratchLeft() {
        this.getImageView().setImage(IMG_LUCIFER_SCRATCH_LEFT);
    }

    public static Image getImgLuciferCalmPoseRight() {
        return IMG_LUCIFER_CALM_POSE_RIGHT;
    }

    public static Image getImgLuciferCalmPoseLeft() {
        return IMG_LUCIFER_CALM_POSE_LEFT;
    }

    public static Image getImgLuciferWalkLeft() {
        return IMG_LUCIFER_WALK_LEFT;
    }

    public static Image getImgLuciferWalkRight() {
        return IMG_LUCIFER_WALK_RIGHT;
    }

    public static Image getImgLuciferFireballLeft() {
        return IMG_LUCIFER_FIREBALL_LEFT;
    }

    public static Image getImgLuciferFireballRight() {
        return IMG_LUCIFER_FIREBALL_RIGHT;
    }

    public static Image getImgLuciferScratchLeft() {
        return IMG_LUCIFER_SCRATCH_LEFT;
    }

    public static Image getImgLuciferScratchRight() {
        return IMG_LUCIFER_SCRATCH_RIGHT;
    }

    public LuciferModel getLuciferModel() {
        return luciferModel;
    }

    public void setLuciferModel(final LuciferModel luciferModel) {
        this.luciferModel = luciferModel;
    }

    //Methods:
    /**
     * Return true il Lucifer sprite is right and False if is left.
     * 
     */
    @Override
    public boolean luciferDirection() { //true RIGHT | false LEFT
        return (this.getImageView().getImage().equals(IMG_LUCIFER_CALM_POSE_RIGHT) || this.getImageView().getImage().equals(IMG_LUCIFER_WALK_RIGHT) || this.getImageView().getImage().equals(IMG_LUCIFER_FIREBALL_RIGHT) || this.getImageView().getImage().equals(IMG_LUCIFER_SCRATCH_RIGHT));
    }
}
