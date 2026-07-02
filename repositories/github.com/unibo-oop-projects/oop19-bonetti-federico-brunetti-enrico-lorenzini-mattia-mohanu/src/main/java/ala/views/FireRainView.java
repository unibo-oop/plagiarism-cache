package ala.views;

import ala.models.FireRainModel;
import javafx.scene.layout.Pane;
/**
 * FireRainView class.
 * 
 */
public class FireRainView {

    //Attributes:
    private Pane fireRainLayer;
    private FireRainModel fireRainModel;
    private FireBallView fireBallViewA;
    private FireBallView fireBallViewB;
    private FireBallView fireBallViewC;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param fireRainLayer
     * @param fireRainModel
     * 
     */
    public FireRainView(final Pane fireRainLayer, final FireRainModel fireRainModel) {
        this.fireRainModel = fireRainModel;
        this.fireRainLayer = fireRainLayer;

        this.fireBallViewA = new FireBallView(fireRainLayer, this.fireRainModel.getFireBallModelA());
        this.fireBallViewB = new FireBallView(fireRainLayer, this.fireRainModel.getFireBallModelB());
        this.fireBallViewC = new FireBallView(fireRainLayer, this.fireRainModel.getFireBallModelC());
    }

    //Getters&Setters:
    public final Pane getFireRainLayer() {
        return fireRainLayer;
    }

    public final void setFireRainLayer(final Pane fireRainLayer) {
        this.fireRainLayer = fireRainLayer;
    }

    public final FireRainModel getFireRainModel() {
        return fireRainModel;
    }

    public final void setFireRainModel(final FireRainModel fireRainModel) {
        this.fireRainModel = fireRainModel;
    }

    public final FireBallView getFireBallViewA() {
        return fireBallViewA;
    }

    public final void setFireBallViewA(final FireBallView fireBallViewA) {
        this.fireBallViewA = fireBallViewA;
    }

    public final FireBallView getFireBallViewB() {
        return fireBallViewB;
    }

    public final void setFireBallViewB(final FireBallView fireBallViewB) {
        this.fireBallViewB = fireBallViewB;
    }

    public final FireBallView getFireBallViewC() {
        return fireBallViewC;
    }

    public final void setFireBallViewC(final FireBallView fireBallViewC) {
        this.fireBallViewC = fireBallViewC;
    }
}
