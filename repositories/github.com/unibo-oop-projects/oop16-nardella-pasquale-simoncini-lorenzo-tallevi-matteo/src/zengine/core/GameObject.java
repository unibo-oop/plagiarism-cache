// this class has too much public methods because many of them are getters and setters for internal properities.
// Others are final utility methods that are often used as shortcuts for most common operations such as moving and building
// hitboxes. It is not dangerous to carry these methods around.
package zengine.core;

import java.util.HashSet;
import java.util.Set;

import zengine.constants.ZengineMouseConstant;
import zengine.geometry.Hitbox;
import zengine.geometry.HitboxEmpty;
import zengine.geometry.HitboxRectangle;
import zengine.interfaces.GameEngine;

/**
 * This abstract class represents the default class Zengine can manipulate and
 * interact. Extend this class to model all of your game object that should
 * interact with Zengine and its methods.
 */
public abstract class GameObject {

    /**
     * reference to the Zengine. This is just a shortcut for
     * <code>Zengine.getZengine()</code>
     */
    private final GameEngine ze = Zengine.getEngine();

    // default reference to the hitbox
    private Hitbox hitbox = new HitboxEmpty();

    // default appearance parameters
    private String spriteIndex = "";
    private double imageIndex; // = 0;
    private double imageSpeed = 1;
    private double imageXscale = 1;
    private double imageYscale = 1;
    private double imageAngle; // = 0;
    private double imageAlpha = 1;

    // position parameters
    private double x; // = 0;
    private double y; // = 0;

    // ojects with lower depth are drawn in front of the others
    private int depth; // = 0;

    // list of classes the object should interact with, when colliding
    private final Set<String> interactionList = new HashSet<>();

    // list of mouse buttons this object reacts to when clicked
    private final Set<ZengineMouseConstant> mouseInteractionList = new HashSet<>();

    // staus parameters
    // an inactive object does not respond to update, draw and collide events,
    // thus making it frozen, invisible and ethereal
    private boolean active = true;
    // a frozen object does not respond to update and collision events, but will
    // still be visible
    private boolean frozen; // = false;
    // a persistent object will survive when switching from a room to another
    private boolean persistent; // = false;
    // an invisible object will not respond to the draw event, thus making it
    // invisible. It will still interact with other objects as usual
    private boolean visible = true;
    // an ethereal object will be excluded from every automatic collide event,
    // making it untouchable. hitbox is unaffected
    private boolean ethereal; // = false;

    // the moment this object is destroyed, isdead is set true. the following
    // update the object will ultimately be cleared form zengine
    private boolean dead; // = false;

    // EVENTS ------------------------------------------------------
    /**
     * this event will be called when this object is created by
     * ze.instanceCreate() or ze.instanceAdd() or similar.
     */
    public abstract void create();

    /**
     * this event will be called when this object is destroyed by zengine, for
     * example during a ze.instanceDestroy(). It will not be called when
     * switching from a room to another;
     */
    public abstract void destroy();

    /**
     * this method will be called by Zengine once per frame, as defined by the
     * game's FPS. For example, 30 times a second or 60 times per second. The
     * majority of the code that describes the behaviour of the object can go
     * here.
     */
    public abstract void update();

    /**
     * this method will be called each time Zengine updates the screen. Note
     * that this happens multiple times each second, but not necessarily as many
     * times as the update() method. This means that you should run your
     * behaviour codes inside update() and not inside draw(). In other terms,
     * use the draw() event for drawing functions only.
     */
    public abstract void draw();

    /**
     * this method is called synchronous with the update() event (multiple times
     * per second, described by the game's FPS). However it is called once for
     * every other object that this object is colliding with. The other objects
     * that this object should be interacting with are described by
     * interactionList. The paramether "other" contains a reference to the other
     * object that is currently touching this
     * 
     * @param other
     *            a reference of the other object involved in the collision
     */
    public abstract void collide(GameObject other);

    /**
     * this method is called each time this object is clicked, more specifically
     * each time the user clicks this object's hitbox with a button described
     * with the method addMouseClickedInteraction().
     * 
     * @param button
     *            the mouse button currently being pressed
     */
    public abstract void mouseClicked(ZengineMouseConstant button);

    // UTILITY --------------------------------------------------------
    /**
     * returns the reference to the Zengine. This is just a shortcut for
     * <code>Zengine.getEngine()</code>
     * 
     * @return the reference of the zengine
     */
    public final GameEngine z() {
        return ze;
    }

    /**
     * gets the object's current x coordinate within Zengine's game world.
     * 
     * @return the object's current x coordinate within Zengine's game world
     */
    public final double getX() {
        return x;
    }

    /**
     * gets the object's current y coordinate within Zengine's game world.
     * 
     * @return the object's current y coordinate within Zengine's game world
     */
    public final double getY() {
        return y;
    }

    /**
     * sets this object's x coordinate within Zengine's game world to the new
     * specified value.
     * 
     * @param x
     *            new value to assign to the x coordinate
     */
    public final void setX(final double x) {
        this.x = x;
    }

    /**
     * sets this object's y coordinate within Zengine's game world to the new
     * specified value.
     * 
     * @param y
     *            new value to assign to the y coordinate
     */
    public final void setY(final double y) {
        this.y = y;
    }

    /**
     * shifts x coordinate by given amount and returns the new position.
     * 
     * @param amount
     *            how much to shift the x coordinate
     * @return the new x coordinate
     */
    public final double moveX(final double amount) {
        this.setX(this.getX() + amount);
        return this.getX();
    }

    /**
     * shifts y coordinate by given amount and returns the new position.
     * 
     * @param amount
     *            how much to shift the y coordinate
     * @return the new y coordinate
     */
    public final double moveY(final double amount) {
        this.setY(this.getY() + amount);
        return this.getY();
    }

    /**
     * returns this objects'current depth, which indicates whether this object
     * will be drawn in front or behind other GameObjects, based on each
     * object's own depth.
     * 
     * @return this objects'current depth
     */
    public final int getDepth() {
        return depth;
    }

    /**
     * sets this object's depth, which indicates whether this object will be
     * drawn in front or behind other GameObjects, based on each object's own
     * depth. High NEGATIVE vale means the object is drawn BELOW, while high
     * POSITIVE value means the object is drawn ABOVE all others. Objects of the
     * same depth are drawn above or below each other with no particular order.
     * 
     * @param depth
     *            new depth to assign
     */
    public final void setDepth(final int depth) {
        this.depth = depth;
        ZengineSystem.getSystem().sortInstanceListDepth();
    }

    /**
     * gets a reference to this object's current Hitbox, which is a shape that
     * determines how the objects interacts with collisions, mouse clicks and
     * other functions that require geometry.
     * 
     * @return the reference of the hitbox object
     */
    public final Hitbox getHitbox() {
        if (hitbox == null) {
            return new HitboxEmpty();
        } else {
            return hitbox;
        }
    }

    /**
     * sets this object's Hitbox, which is a shape that determines how the
     * objects interacts with collisions, mouse clicks and other functions that
     * require geometry.
     * 
     * @param hitbox
     *            new hitbox to assign to this object
     */
    public final void setHitbox(final Hitbox hitbox) {
        if (hitbox == null) {
            this.hitbox = new HitboxEmpty();
        } else {
            this.hitbox = hitbox;
        }
    }

    /**
     * moves the hitbox at the current x and y coordinates. It is suggested to
     * call this method after every other action, inside the update() event, in
     * order to keep the hitbox attached to the object.
     */
    public final void moveHitboxToCurrentPosition() {
        getHitbox().setX(getX());
        getHitbox().setY(getY());
    }

    /**
     * returns the name of the sprite currently assigned to this GameObject. If
     * no sprite is assigned, an empty string is returned. The sprite is an
     * image that is used to visually represent this object inside Zengine's
     * camera or world.
     * 
     * @return a string representing the name of the current sprite of this
     *         object, or
     */
    public final String getSpriteIndex() {
        return spriteIndex;
    }

    /**
     * sets this object's sprite to the sprite with the specified name. When
     * changing sprite, the imageIndex is reset to 0. If you assign an invalid
     * sprite name, nothing happens.
     * 
     * @param spriteIndex
     *            name of the new sprite to assign to this object
     */
    public final void setSpriteIndex(final String spriteIndex) {
        switchSpriteIndex(spriteIndex);
        this.setImageIndex(0);
    }

    /**
     * changes sprite to the new sprite with the given name. The difference with
     * <code>setSpriteIndex()</code> is that in this case, the imageIndex is not
     * reset.
     * 
     * @param newSpriteIndex
     *            name of the new sprite to assign to this object
     * 
     */
    public final void switchSpriteIndex(final String newSpriteIndex) {
        if (z().spriteExists(newSpriteIndex)) {
            this.spriteIndex = newSpriteIndex;
            setImageIndex(z().wrapToModulus(getImageIndex(), z().spriteGetNumber(newSpriteIndex)));
        }
    }

    /**
     * returns the number of the subimage currently active for this object. The
     * subimage represents which frame of the sprite will be displayed by
     * drawSelf(). For example, if the current sprite has 6 subimages, the
     * subimage could be any value between 0 and 5.9999... if the imageIndex is
     * 4.5745, it means that the current subimage is subimage n� 4. The first
     * subimage of an animation is always n� 0. If a sprite has only one
     * subimage, it means it is composed only by subimage n� 0.
     * 
     * @return the current imageIndex value for this object
     */
    public final double getImageIndex() {
        return imageIndex;
    }

    /**
     * sets the current number of the subimage active for this object. The
     * subimage represents which frame of the sprite will be displayed by
     * drawSelf(). For example, if the current sprite has 6 subimages, the
     * subimage could be any value between 0 and 5.9999... if the imageIndex is
     * 4.5745, it means that the current subimage is subimage n� 4. The first
     * subimage of an animation is always n� 0. If a sprite has only one
     * subimage, it means it is composed only by subimage n� 0.
     * 
     * @param imageIndex
     *            new number of the subimage to display
     */
    public final void setImageIndex(final double imageIndex) {
        if (z().spriteExists(getSpriteIndex())) {
            this.imageIndex = z().wrapToModulus(imageIndex, z().spriteGetNumber(getSpriteIndex()));
        } else {
            this.imageIndex = 0;
        }
    }

    /**
     * returns the current animations speed of this object's sprite. The
     * animation speed indicates how many frames are advanced for every call of
     * the update() event. For example, an imageSpeed of 1 means one frame per
     * update(), which could be 30 fps, 60 fps depending on the game speed. An
     * imageSpeed of 2 means 2 frames per update(), 0.5 means half rame for
     * update() and so on.
     * 
     * @return the animation speed of this object's sprite
     */
    public final double getImageSpeed() {
        return imageSpeed;
    }

    /**
     * sets the animations speed of this object's sprite. The animation speed
     * indicates how many frames are advanced for every call of the update()
     * event. For example, an imageSpeed of 1 means one frame per update(),
     * which could be 30 fps, 60 fps depending on the game speed. An imageSpeed
     * of 2 means 2 frames per update(), 0.5 means half rame for update() and so
     * on.
     * 
     * @param imageSpeed
     *            new speed to assign
     */
    public final void setImageSpeed(final double imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    /**
     * If called during update(), animates the sprite by scrolling through the
     * sprite's frames as fast as described by animation speed. Basically, this
     * method increases (or decreases) the ImageIndex by an amount described by
     * ImageSpeed. Returns true if the animation has looped and has restarted to
     * the initial frame. The return value is useful if you want, for example,
     * destroy an object when its animation is over (like a particle effect).
     * 
     * @return true if the animation has looped and has restarted to the initial
     *         frame, which means the animation is over.
     */
    public final boolean animationUpdate() {
        final double initial = getImageIndex();
        setImageIndex(getImageIndex() + getImageSpeed());

        if (getImageSpeed() > 0) {
            return getImageIndex() <= initial;
        } else {
            return getImageIndex() > initial;
        }
    }

    /**
     * returns the current horizontal scaling factor of this object's sprite. A
     * scaling factor of 1 indicates normal scaling, 0.5 means half as wide, 2
     * twice as wide and so on.
     * 
     * @return the current horizontal scaling factor of this object's sprite
     */
    public final double getImageXscale() {
        return imageXscale;
    }

    /**
     * sets the horizontal scaling factor of this object's sprite. A scaling
     * factor of 1 indicates normal scaling, 0.5 means half as wide, 2 twice as
     * wide and so on.
     * 
     * @param imageXscale
     *            new scaling factor to assign
     */
    public final void setImageXscale(final double imageXscale) {
        this.imageXscale = imageXscale;
    }

    /**
     * returns the current vertical scaling factor of this object's sprite. A
     * scaling factor of 1 indicates normal scaling, 0.5 means half as tall, 2
     * twice as tall and so on.
     * 
     * @return the current vertical scaling factor of this object's sprite
     */
    public final double getImageYscale() {
        return imageYscale;
    }

    /**
     * sets the vertical scaling factor of this object's sprite. A scaling
     * factor of 1 indicates normal scaling, 0.5 means half as tall, 2 twice as
     * tall and so on.
     * 
     * @param imageYscale
     *            new scaling factor to assign
     */
    public final void setImageYscale(final double imageYscale) {
        this.imageYscale = imageYscale;
    }

    /**
     * returns the angle that the current sprite is currently being rotated.
     * Rotation happend around the sprite's xOffset and yOffset, which are a
     * sprite's intrinsic parameters (see in the manual how to define a sprite).
     * A rotation of 0 means no rotation, 90 means 90 degrees counter-clockwise,
     * 180 is half rotation and so on.
     * 
     * @return the angle assigned to the current sprite
     */
    public final double getImageAngle() {
        return imageAngle;
    }

    /**
     * sets the angle of rotation of the current sprite. Rotation happend around
     * the sprite's xOffset and yOffset, which are a sprite's intrinsic
     * parameters (see in the manual how to define a sprite). A rotation of 0
     * means no rotation, 90 means 90 degrees counter-clockwise, 180 is half
     * rotation and so on.
     * 
     * @param imageAngle
     *            new angle to assign
     */
    public final void setImageAngle(final double imageAngle) {
        this.imageAngle = imageAngle;
    }

    /**
     * returns the opacity factor assigned to the current sprite. The opacity
     * factor describes how transparent the current sprite is. A factor of 1
     * means normal opacity, 0.5 half transparemt, 0 means fully transparent
     * (which is invisible).
     * 
     * @return the opacity factor assigned to the current sprite
     */
    public final double getImageAlpha() {
        return imageAlpha;
    }

    /**
     * sets the opacity factor assigned to the current sprite. The opacity
     * factor describes how transparent the current sprite is. A factor of 1
     * means normal opacity, 0.5 half transparemt, 0 means fully transparent
     * (which is invisible).
     * 
     * @param imageAlpha
     *            the new opacity factor to assign (value is clamped between 0
     *            and 1)
     */
    public final void setImageAlpha(final double imageAlpha) {
        this.imageAlpha = ze.clamp(imageAlpha, 0d, 1d);
    }

    /**
     * checks if this object is active. An inactive object does not respond to
     * update, draw and collide events, or any other event except create() and
     * destroy(), thus making it frozen, invisible and ethereal
     * 
     * @return true if the object is active
     */
    public final boolean isActive() {
        return active;
    }

    /**
     * Activates or deactivates this object. An inactive object does not respond
     * to update, draw and collide events, or any other event except create()
     * and destroy(), thus making it frozen, invisible and ethereal
     * 
     * @param active
     *            true to activate the object, false to deactivate it
     */
    public final void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * checks if this object is frozen. A frozen object does not respond to
     * update, mouseClicked and collision events, but will still be visible.
     * 
     * @return true if this object is frozen.
     */
    public final boolean isFrozen() {
        return frozen;
    }

    /**
     * Freezes or unfreezes this object. A frozen object does not respond to
     * update, mouseClicked and collision events, but will still be visible.
     * 
     * @param frozen
     *            true to freeze this object, false to unfreeze it
     */
    public final void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * checks if this object is persistent. A persistent object will survive
     * when switching between rooms (it can still be destroyed manually).
     * 
     * @return true if this object is persistent
     */
    public final boolean isPersistent() {
        return persistent;
    }

    /**
     * sets this object's persistance feature. A persistent object will survive
     * when switching between rooms (it can still be destroyed manually).
     * 
     * @param persistent
     *            true to activate the persistance, false to deactivate it
     */
    public final void setPersistent(final boolean persistent) {
        this.persistent = persistent;
    }

    /**
     * Checks if this object is visible. An invisible object will not respond to
     * the draw event, thus making it invisible. It will still interact with
     * other objects as usual.
     * 
     * @return true if the object is visible
     */
    public final boolean isVisible() {
        return visible;
    }

    /**
     * sets the visibility of this object. An invisible object will not respond
     * to the draw event, thus making it invisible. It will still interact with
     * other objects as usual
     * 
     * @param visible
     *            true to show the object, false to hide it
     */
    public final void setVisible(final boolean visible) {
        this.visible = visible;
    }

    /**
     * checks if this object is ethereal. An ethereal object will be excluded
     * from every automatic collide event, making it untouchable. The hitbox is
     * unaffected. Even if the object is ethereal, you can still manually check
     * for collisions inside the update() event by checking the hitbox. Also,
     * the object will still respond to the mouseClicked() event. If you want to
     * make an object truly untouchable, set its hitbox to a new HitboxEmpty()
     * 
     * @return true if this object is ethereal
     */
    public final boolean isEthereal() {
        return ethereal;
    }

    /**
     * activates or deactivates the ethereality of this object. An ethereal
     * object will be excluded from every automatic collide event, making it
     * untouchable. The hitbox is unaffected. Even if the object is ethereal,
     * you can still manually check for collisions inside the update() event by
     * checking the hitbox. Also, the object will still respond to the
     * mouseClicked() event. If you want to make an object truly untouchable,
     * set its hitbox to a new HitboxEmpty()
     *
     * @param ethereal
     *            true to activate ethereality, false to deactivate it.
     */
    public final void setEthereal(final boolean ethereal) {
        this.ethereal = ethereal;
    }

    /**
     * this method is reserved to the game engine. If you call this, the
     * application may work unproperly. Do not use it, use
     * <code>z().instanceDestroy(this)</code> instead.
     * 
     * @param dead
     *            wether the object is marked as dead
     */
    protected final void setDead(final boolean dead) {
        // PACKAGE PRIVATE should be a better visibility for this method instead
        // of protected.
        // only the Zengine and its component must use this method, it is okay
        // if they see it and the user does not. Exposing this to the user would
        // allow him/her to perform unsafe operations.
        // the "dead" property is used by the Zengine and the user must not know
        // about it.
        this.dead = dead;
    }

    /**
     * this method is reserved to the game engine. If you call this, the
     * application may work unproperly.
     * 
     * @return true if the object is dead
     */
    protected final boolean isDead() {
        // PACKAGE PRIVATE should be a better visibility for this method instead
        // of protected.
        // only the Zengine and its component must use this method, it is okay
        // if they see it and the user does not. Exposing this to the user would
        // allow him/her to perform unsafe operations.
        // the "dead" property is used by the Zengine and the user must not know
        // about it.
        return dead;
    }

    /**
     * draws the object, as described by its current aspect parameters (sprite,
     * imageIndex, x and y position, imageAngle etc..).
     */
    public final void drawSelf() {
        ze.drawSpriteExt(getSpriteIndex(), getImageIndex(), getX(), getY(), getImageXscale(), getImageYscale(), getImageAngle(),
                getImageAlpha());
    }

    /**
     * generates a new HitboxRectangle based on the given sprite.
     * 
     * @param sprite
     *            sprite to take inspiration form
     * @param x
     *            x position of the new hitbox
     * @param y
     *            y position of the new hitbox
     * @param xScale
     *            horizontal scaling factor to apply to the sprite for this
     *            computation
     * @param yScale
     *            horizontal scaling factor to apply to the sprite for this
     *            computation
     * @param hBorder
     *            horizontal border to leave to the left and right of the
     *            sprite, in pixels (0=exactly adherent to the sprite)
     * @param vBorder
     *            vertical border to leave to the top and bottom of the sprite,
     *            in pixels (0=exactly adherent to the sprite)
     * @return the newly generated hitbox
     */
    public final HitboxRectangle generateHitboxFromSprite(final String sprite, final double x, final double y,
            final double xScale, final double yScale, final double hBorder, final double vBorder) {
        if (ze.spriteExists(sprite)) {
            return new HitboxRectangle(x - ze.spriteGetXoffset(sprite) * xScale - hBorder,
                    y - ze.spriteGetYoffset(sprite) * yScale - vBorder, ze.spriteGetWidth(sprite) * xScale + hBorder * 2,
                    ze.spriteGetHeight(sprite) * yScale + vBorder * 2);
        } else {
            return new HitboxRectangle(x, y, 0, 0);
        }
    }

    /**
     * generates a new Hitboxrectangle according to this object's current
     * appearance settings, scaling and position (no rotation).
     * 
     * @param hBorder
     *            horizontal border to leave to the left and right of the
     *            sprite, in pixels (0=exactly adherent to the sprite)
     * @param vBorder
     *            vertical border to leave to the top and bottom of the sprite,
     *            in pixels (0=exactly adherent to the sprite)
     * @return the newly generated hitbox
     */
    public final HitboxRectangle generateHitboxFromCurrentSettings(final double hBorder, final double vBorder) {
        return generateHitboxFromSprite(getSpriteIndex(), getX(), getY(), getImageXscale(), getImageYscale(), hBorder, vBorder);
    }

    /**
     * returns true if this object's x and y are outside the current room's
     * boundaries. If no room exists, it will always return false.
     * 
     * @return true if this object is inside the current room, false if outside
     *         room or no room exists.
     */
    public final boolean isOutsideRoom() {
        if (z().roomExists(z().roomCurrent())) {
            return (getX() < 0 || getX() > ze.roomWidth() || getY() < 0 || getY() > ze.roomHeight());
        } else {
            return false;
        }
    }

    /**
     * wraps this object's position to the room boundaries. More specifically,
     * if it moves outside the border, it will appear to the other side of the
     * room. If no room exists, it does nothing.
     */
    public final void wrapToRoomBoundaries() {
        if (z().roomExists(z().roomCurrent())) {
            setX(ze.wrapToModulus(getX(), (int) ze.roomWidth()));
            setY(ze.wrapToModulus(getY(), (int) ze.roomHeight()));
        }
    }

    /**
     * adds the given class to the list of classes that this object should
     * collide with. From this moment on, each time this object collides with a
     * member of className, the collide() event will be executed
     * 
     * @param className
     *            class to register
     * @return true if the registration was successful
     */
    public final boolean addCollisionInteraction(final String className) {
        // if (!ZengineSystem.getSystem().isCollisionEvent()) {
        if (ze.classExists(className)) {
            return interactionList.add(className);
        } else {
            ze.loggerWarning("Trying to add collision interaction with nonexistent class " + className);
            return false;
        }
        // } else {
        // ze.loggerWarning("tried to modify collision interactions during
        // collision event");
        // return false;
        // }
    }

    /**
     * activates the given button for a mouseClick event. From now on, every
     * time this object is clicked by the given button, the mouseClicked() event
     * will be called
     * 
     * @param button
     *            button to register
     * @return true if the registration was successful
     */
    public final boolean addMouseClickedInteraction(final ZengineMouseConstant button) {
        // if (!ZengineSystem.getSystem().isMouseClickedEvent()) {
        return mouseInteractionList.add(button);
        // } else {
        // ze.loggerWarning("tried to modify mouse interactions during
        // mouseClicked event");
        // return false;
        // }
    }

    /**
     * clears the list of collision interactions.
     */
    public void clearCollisionInteraction() {
        if (!ZengineSystem.getSystem().isCollisionEvent()) {
            interactionList.clear();
        } else {
            ze.loggerWarning("tried to clear collision interactions during collision event");
        }
    }

    /**
     * clears the list of interactions with mouse buttons.
     */
    public final void clearMouseClickedInteraction() {
        if (!ZengineSystem.getSystem().isMouseClickedEvent()) {
            mouseInteractionList.clear();
        } else {
            ze.loggerWarning("tried to clear mouse interactions during mouseClicked event");
        }
    }

    /**
     * returns true if this object is colliding with the other, as described by
     * each hitbox.
     * 
     * @param other
     *            other object to check collision with
     * @return true if the two objects are colliding
     */
    public final boolean isColliding(final GameObject other) {
        if (hitbox != null && other.hitbox != null) {
            return hitbox.isTouching(other.hitbox);
        } else {
            return false;
        }
    }

    // this was package private but it is not completely unsafe to expose it to
    // the public
    /**
     * returns a set of strings of all the collision interactions currently set.
     * The strings represents the name of the classes that trigger the collide()
     * event.
     * 
     * @return a set of strings of all the collision interactions currently set
     */
    public final Set<String> getCollisionInteraction() {
        return new HashSet<>(interactionList);
    }

    // this was package private but it is not completely unsafe to expose it to
    // the public
    /**
     * returns a set of MoseCounstants that describe the mouse interactions
     * currently set. Every mouse button contained in the set is a button that
     * may trigger the mouseClicked() event.
     * 
     * @return a set of MoseCounstants that describe the mouse interactions
     *         currently set
     */
    public final Set<ZengineMouseConstant> getMouseInteraction() {
        return new HashSet<>(mouseInteractionList);
    }

    // stuff to work with lambdas, dont touch

    // it is redundant and confusing to make this method public or protected,
    // since it just calls the execute method of the lambda. It is okay for this
    // method to be package private, as the user has other methods to work with
    // lambdas. This method is used by Zengine, in particular by the with()
    // function. It is not unsafe to make execute() public, but I prefer it to
    // be invisible form outside.
    final void execute(final Executor e, final GameObject self) {
        if (e != null && self != null) {
            e.execute(self);
        }
    }

    /**
     * This functional interface is used as argument to the with() function of
     * Zengine. It contains the code to be executed inside the method execute().
     * The self parameter indicates which object is currently being iterated.
     */
    @FunctionalInterface
    public interface Executor {
        /**
         * executes a generic code.
         * 
         * @param self
         *            reference to the GameObject that should execute the
         *            desired code
         */
        void execute(GameObject self);
    }
}
