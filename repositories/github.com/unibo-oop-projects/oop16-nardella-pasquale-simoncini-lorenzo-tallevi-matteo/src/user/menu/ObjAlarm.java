package user.menu;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * this object serves to postpone for a certain time a certain action.
 */
public class ObjAlarm extends GameObject {

    private int timer;
    private Executor action;

    @Override
    public void create() {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        action.execute(this);
    }

    @Override
    public void update() {
        if (timer > 0) {
            timer--;
        } else {
            z().instanceDestroy(this);
        }
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void collide(final GameObject other) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
        // TODO Auto-generated method stub

    }

    /**
     * This method sets the variable delay.
     * 
     * @param delay
     *            the number of frames that are needed to wait.
     */
    public void setDelay(final int delay) {
        timer = delay;
    }

    /**
     * This method sets the variable action.
     * 
     * @param action
     *            sets the action to do.
     */
    public void setAction(final Executor action) {
        this.action = action;
    }

}
