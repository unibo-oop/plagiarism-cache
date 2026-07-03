package user.menu;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxPoint;

/**
 * This class represent a common model for the menu buttons.
 */
public abstract class AbstractMenuButton extends GameObject {

    /**
     * Selected is true as long as the mouse is inside the hitbox of this
     * button.
     */
    private boolean selected; // = false;

    private static final double MAIN_MENU_BUTTON_SCALE = 4;
    private static final double POT_MENU_BUTTON_SCALE = 3.5;

    private static final int H_BORDER = 30;
    private static final int V_BORDER = 5;
    private final HitboxPoint mousePos = new HitboxPoint(0, 0);

    /**
     * This method sets the scale of the potentiation buttons sprites.
     */
    public void setPotMenuButtonScale() {
        setImageXscale(POT_MENU_BUTTON_SCALE);
        setImageYscale(POT_MENU_BUTTON_SCALE);
    }

    /**
     * This method sets the scale of the main menu buttons sprites.
     */
    public void setMainMenuButtonScale() {
        setImageXscale(MAIN_MENU_BUTTON_SCALE);
        setImageYscale(MAIN_MENU_BUTTON_SCALE);
    }

    @Override
    public void create() {
        // set the aspect (each subclass will define its own aspect)
        setAspect();
        // generate an hitbox based on my aspect
        setHitbox(generateHitboxFromCurrentSettings(H_BORDER, V_BORDER));
        // add interaction with mouse button
        addMouseClickedInteraction(ZengineMouseConstant.MB_LEFT);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // move the hitbox to the mouse position to chech if selected
        mousePos.setX(z().mouseX());
        mousePos.setY(z().mouseY());
        setSelected(getHitbox().isTouching(mousePos));
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
        // each button will define its own action when clicked
        performAction();
    }

    /**
     * This method returns true if the mouse pointer is over the object.
     * 
     * @return true if the mouse pointer is over the object.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * This method sets the variable selected.
     * 
     * @param selected
     *            is set to true as long as the mouse is inside the hitbox of
     *            this button; otherwise it is set to false.
     */
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    // subclasses will implement these
    /**
     * this should contain initialization of spriteindex, imageXscale,
     * imageYscale etc.
     */
    public abstract void setAspect();

    /**
     * this should contain actions performed when the mouse is clicked.
     */
    public abstract void performAction();

}
