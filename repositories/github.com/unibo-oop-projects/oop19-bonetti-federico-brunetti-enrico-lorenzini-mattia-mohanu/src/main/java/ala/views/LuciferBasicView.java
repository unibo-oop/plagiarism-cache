package ala.views;

import ala.models.LuciferBasicModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * LuciferBasicView class.
 * 
 */
public abstract class LuciferBasicView extends GameObjectAliveView {
    //Attributes:
    private static final double X_BARS_POSITION = 10;
    private static final double Y_HEALTH_BAR_POSITION = 650;
    private static final double Y_STAMINA_BAR_POSITION = 670;
    private static final double Y_FINAL_BAR_POSITION = 690;

    private LuciferBasicModel luciferBasicModel;
    private LuciferHealthView luciferHealthView;
    private LuciferStaminaView luciferStaminaView;
    private LuciferFinalAttackBarView luciferFinalAttackBarView;
    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param luciferBasicModel
     * 
     */
    public LuciferBasicView(final Pane layer, final Image image, final LuciferBasicModel luciferBasicModel) {
        super(layer, image, luciferBasicModel);
        this.luciferBasicModel = luciferBasicModel;
        this.luciferHealthView = new LuciferHealthView(layer, X_BARS_POSITION, Y_HEALTH_BAR_POSITION); 
        this.luciferStaminaView = new LuciferStaminaView(layer, X_BARS_POSITION, Y_STAMINA_BAR_POSITION);
        this.luciferFinalAttackBarView = new LuciferFinalAttackBarView(layer, X_BARS_POSITION, Y_FINAL_BAR_POSITION);
    }

    //Getters&Setters:
    /**
     * @return LuciferBasicModel
     */
    public LuciferBasicModel getLuciferFlyModel() {
        return luciferBasicModel;
    }

    public final void setLuciferFlyModel(final LuciferBasicModel luciferFlyModel) {
        this.luciferBasicModel = luciferFlyModel;
    }

    public final LuciferHealthView getLuciferHealthView() {
        return luciferHealthView;
    }

    public final LuciferStaminaView getLuciferStaminaView() {
        return luciferStaminaView;
    }

    public final LuciferFinalAttackBarView getLuciferFinalAttackBarView() {
        return luciferFinalAttackBarView;
    }
}
