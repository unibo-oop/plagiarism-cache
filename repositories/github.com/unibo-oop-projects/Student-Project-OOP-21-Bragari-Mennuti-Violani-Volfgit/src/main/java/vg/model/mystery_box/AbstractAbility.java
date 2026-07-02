package vg.model.mystery_box;

import javafx.geometry.Dimension2D;
import vg.model.entity.staticEntity.StaticEntity;
import vg.model.mystery_box.logic_blink.LogicBlink;
import vg.model.mystery_box.logic_blink.StaticFactoryBlink;
import vg.utils.V2D;

/**
 * AbstractAbility is the base class for all the abilities.
 */
public abstract class AbstractAbility extends StaticEntity {
    private static final long serialVersionUID = 1L;
    private static final Dimension2D DIMENSION_BOX = new Dimension2D(60, 60);
    private static final V2D INIT_POSITION = new V2D(0, 0);
    private final Dimension2D dimension;
    private final EAbility idAbility;
    private final ETypeAbility typeAbility;
    private final LogicBlink logicBlink;
    private final LogicBlink logicBlinkPickUp;
    private boolean show;
    private boolean isActivated;


    public AbstractAbility(final EAbility idAbility, final ETypeAbility typeAbility) {
        super(INIT_POSITION, (int) (DIMENSION_BOX.getWidth() / 2));
        this.dimension = DIMENSION_BOX;
        this.idAbility = idAbility;
        this.typeAbility = typeAbility;
        this.logicBlink = StaticFactoryBlink.createLogicBlinkMysteryBox();
        this.logicBlinkPickUp = StaticFactoryBlink.createLogicBlinkPickUp();
        this.isActivated = false;
    }

    /**
     * This method is used to get the id of the ability.
     * @return the id of the ability.
     */
    public EAbility getIdAbility() {
        return this.idAbility;
    }
    /**
     * This method is used to get the path of the ability.
     * @return the path of the ability.
     */
    public String getPathReveled() {
        return this.idAbility.getPathReveled();
    }
    /**
     * This method is used to get the type of the ability.
     * @return the type of the ability.
     */
    public ETypeAbility getTypeAbility() {
        return this.typeAbility;
    }
    /**
     * This method is used to get the dimension of the box.
     * @return the dimension of the box.
     */
    public Dimension2D getDimension() {
        return this.dimension;
    }
    /**
     * This method is used to verify if the box is shown.
     * @return true if the box is shown, false otherwise.
     */
    public boolean isShow() {
        return this.show;
    }
    /**
     * This method is used to update the blinking.
     * @param isBlinking defines if the ability is blinking.
     */
    public void setBlinking(final boolean isBlinking) {
        this.logicBlink.setBlinking(isBlinking);
    }
    /**
     * This method is used to update the blinking.
     * @param elapsedTime defines the time elapsed.
     */
    public void updateBlinking(final long elapsedTime) {
        this.logicBlink.updateBlinking(elapsedTime);
        this.show = this.logicBlink.isShow();
    }
    /**
     * This method is used to verify if the ability has been activated.
     * @return true if the activation is true, false otherwise.
     */
    public boolean isActivated() {
        return this.isActivated;
    }
    /**
     * Show the box.
     */
    public void show() {
        this.show = true;
    }
    /**
     * Hide the box.
     */
    public void hide() {
        this.show = false;
    }
    /**
     * This method is used to set the blink when the box is picked up.
     */
    public void setActiveBlinkPickUp() {
        this.logicBlinkPickUp.setBlinking(true);
    }
    /**
     * This method is used to update the blink when the box is picked up.
     * The blink is active for a short time.
     * @param elapsedTime defines the time elapsed.
     */
    public void updateBlinkingPickUp(final long elapsedTime) {
        this.logicBlinkPickUp.updateBlinking(elapsedTime);
        this.show = this.logicBlinkPickUp.isShow();
        if (!this.show) {
            this.logicBlinkPickUp.setBlinking(false);
            this.logicBlink.setBlinking(false);
        }
    }
    /**
     * This method set isActivated to true.
     */
    protected void activated() {
        this.isActivated = true;
    }
}
