package user.debug.testgame;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxPoint;

/**
 * This class represent a common model for the menu buttons.
 */
public abstract class ObjAbstractMenuButton extends GameObject {

    /**
     * Selected is true as long as the mouse is inside the hitbox of this
     * button.
     */
    private boolean selected; // = false;

    private static final int H_BORDER = 30;
    private static final int V_BORDER = 5;
    private final HitboxPoint mousePos = new HitboxPoint(0, 0);

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
        selected = getHitbox().isTouching(mousePos);
    }

    @Override
    public void draw() {
        drawSelf();
        if (selected) {
            z().drawSprite("debug/testGame/buttonIndicator", 0, getX() - 10, getY());
        }
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
        // each button will define its own action when clicked
        performAction();
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
