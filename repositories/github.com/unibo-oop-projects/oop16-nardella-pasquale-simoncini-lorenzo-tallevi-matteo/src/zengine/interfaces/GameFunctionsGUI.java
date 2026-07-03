package zengine.interfaces;

import zengine.constants.ZengineColor;

/**
 * This interface represents the Zengine component that renders images, texts
 * and primitives. It contains methods that must be called inside the draw()
 * event of GameObject. It does also contain an abstraction of the camera that
 * watches the game world.
 */
public interface GameFunctionsGUI {

    /**
     * draws the given sprite in the game world with extended settings.
     * 
     * @param sprite
     *            name of the sprite to be rendered
     * @param subimage
     *            frame of the sprite to be rendered (0=first, 1=second etc)
     * @param x
     *            x position within the game world to render the sprite
     * @param y
     *            y position within the game world to render the sprite
     * @param xscale
     *            horizontal scaling factor (0.5=half, 1=normal, 2=twice as
     *            large etc)
     * @param yscale
     *            certical scaling factor (0.5=half, 1=normal, 2=twice as large
     *            etc)
     * @param angle
     *            rotation of the sprite, in degrees
     * @param alpha
     *            opacity factor (0=completely invisible, 0.5=half transparent,
     *            1=normal)
     */
    void drawSpriteExt(String sprite, double subimage, double x, double y, double xscale, double yscale, double angle,
            double alpha);

    /**
     * draws the given sprite at a fixed position relative to the camera. This
     * method is useful if you want to create some display information that
     * always follow the view.
     * 
     * @param sprite
     *            name of the sprite to be rendered
     * @param subimage
     *            frame of the sprite to be rendered (0=first, 1=second etc)
     * @param x
     *            x position from the top-left corner of the view
     * @param y
     *            y position from the top-left corner of the view
     * @param xscale
     *            horizontal scaling factor (0.5=half, 1=normal, 2=twice as
     *            large etc)
     * @param yscale
     *            certical scaling factor (0.5=half, 1=normal, 2=twice as large
     *            etc)
     * @param angle
     *            rotation of the sprite, in degrees
     * @param alpha
     *            opacity factor (0=completely invisible, 0.5=half transparent,
     *            1=normal)
     */
    void drawSpriteHUD(String sprite, double subimage, int x, int y, double xscale, double yscale, double angle, double alpha);

    /**
     * draws a sprite in a simulated 3d space. Using the z coordinate you can
     * make the sprite appear closer or further to the camera, giving an
     * illusion of perspective. This function is useful if you want to simulate
     * parallax for scenario elements. Remember that even if the sprite appears
     * closer to the camera, it will still be drawn below other objects with
     * higher depth, so you may want to have your depth consistent with the
     * sprite's Z coordinate.
     * 
     * @param sprite
     *            name of the sprite to be rendered
     * @param subimage
     *            frame of the sprite to be rendered (0=first, 1=second etc)
     * @param x
     *            x position within the game world to render the sprite
     * @param y
     *            y position within the game world to render the sprite
     * @param z
     *            z position (with z=0 the sprite will lay on the default plane
     *            of every other sprite)
     * @param xscale
     *            horizontal scaling factor (0.5=half, 1=normal, 2=twice as
     *            large etc)
     * @param yscale
     *            certical scaling factor (0.5=half, 1=normal, 2=twice as large
     *            etc)
     * @param angle
     *            rotation of the sprite, in degrees
     * @param alpha
     *            opacity factor (0=completely invisible, 0.5=half transparent,
     *            1=normal)
     */
    void drawSprite3D(String sprite, double subimage, double x, double y, double z, double xscale, double yscale, double angle,
            double alpha);

    /**
     * draws the given sprite in the game world, at the specified coordinates.
     * 
     * @param sprite
     *            name of the sprite to be rendered
     * @param subimage
     *            frame of the sprite to be rendered (0=first, 1=second etc)
     * @param x
     *            x position within the game world to render the sprite
     * @param y
     *            y position within the game world to render the sprite
     */
    void drawSprite(String sprite, double subimage, double x, double y);

    /**
     * draws the given text to the given position within the window. Text will
     * use a default font and the color white.
     * 
     * @param text
     *            string to draw
     * @param x
     *            x position from the top-left corner of the window
     * @param y
     *            y position from the top-left corner of the window
     */
    void drawTextDebug(String text, int x, int y);

    /**
     * draws a text in the game world by using the given font and color. keep in
     * mind that texts will NOT scale according to the view's scale, they will
     * always appear the same size. Also, texts are always aligned at their
     * top-left corner.
     * 
     * @param text
     *            string to draw
     * @param x
     *            x position within the game world
     * @param y
     *            y position within the game world
     * @param fontAlias
     *            alias of the font to use (the alias you assigned when defining
     *            the font)
     * @param color
     *            color of the text
     */
    void drawText(String text, double x, double y, String fontAlias, ZengineColor color);

    /**
     * draws a text at a fixed position relative to the camera, by using the
     * given font and color. This method is useful if you want to create some
     * display information that always follow the view. Remember that texts are
     * always aligned at their top-left corner.
     * 
     * @param text
     *            string to draw
     * @param x
     *            x position from the top-left corner of the view
     * @param y
     *            y position from the top-left corner of the view
     * @param fontAlias
     *            alias of the font to use (the alias you assigned when defining
     *            the font)
     * @param color
     *            color of the text
     */
    void drawTextHUD(String text, int x, int y, String fontAlias, ZengineColor color);

    /**
     * draws a colored rectangle at the given coordinates.
     * 
     * @param x
     *            x coordinate of the top-left corner within the game world
     * @param y
     *            y coordinate of the top-left corner within the game world
     * @param width
     *            width of the rectangle
     * @param height
     *            height of the rectangle
     * @param color
     *            color of the rectangle
     * @param filled
     *            wether to draw the whole rectangle or just the contour
     */
    void drawRectangle(double x, double y, double width, double height, ZengineColor color, boolean filled);

    /**
     * draws a colored ellipse at the given coordinates.
     * 
     * @param x
     *            x coordinate of the top-left corner of the bounding box within
     *            the game world
     * @param y
     *            y coordinate of the top-left corner of the bounding box within
     *            the game world
     * @param width
     *            width of the ellipse
     * @param height
     *            height of the ellipse
     * @param color
     *            color of the ellipse
     * @param filled
     *            wether to draw the whole ellipse or just the contour
     */
    void drawEllipse(double x, double y, double width, double height, ZengineColor color, boolean filled);

    /**
     * draws a colored circle at the given coordinates.
     * 
     * @param x
     *            x position of the center of the circle within the game world
     * @param y
     *            y position of the center of the circle within the game world
     * @param radius
     *            radius of the circle
     * @param color
     *            color of the circle
     * @param filled
     *            wether to draw the whole circle or just the contour
     */
    void drawCircle(double x, double y, double radius, ZengineColor color, boolean filled);

    /**
     * draws a colored line at the given coordinates.
     * 
     * @param x1
     *            x coordinate of the first edge of the line within the game
     *            world
     * @param y1
     *            y coordinate of the first edge of the line within the game
     *            world
     * @param x2
     *            x coordinate of the second edge of the line within the game
     *            world
     * @param y2
     *            y coordinate of the second edge of the line within the game
     *            world
     * @param color
     *            color of the line
     */
    void drawLine(double x1, double y1, double x2, double y2, ZengineColor color);

    /**
     * draws a colored point within the game world, represented as a small
     * cross.
     * 
     * @param x
     *            x coordinate of the point within the game world
     * @param y
     *            y coordinate of the point within the game world
     * @param color
     *            the color of the point
     */
    void drawPoint(double x, double y, ZengineColor color);

    /**
     * sets the view's center to be focused on the specified x and y coordinates
     * within the game world.
     * 
     * @param x
     *            x position to focus
     * @param y
     *            y position to focus
     */
    void viewSetPosition(double x, double y);

    /**
     * sets the view's center to the specified x within the game world.
     * 
     * @param x
     *            x position to focus
     */
    void viewSetX(double x);

    /**
     * sets the view's center to the specified y within the game world.
     * 
     * @param y
     *            y position to focus
     */
    void viewSetY(double y);

    /**
     * returns the current x position of the view's center within the game
     * world.
     * 
     * @return the x coordinate of the view's current focus
     */
    double viewGetX();

    /**
     * returns the current y position of the view's center within the game
     * world.
     * 
     * @return the y coordinate of the view's current focus
     */
    double viewGetY();

    /**
     * sets the current scaling factor of the view, which corresponds to the
     * distance from the scene.
     * 
     * @param scale
     *            scaling (or distance) factor (1=normal, 2=twice as far from
     *            the scene etc)
     */
    void viewSetScale(double scale);

    /**
     * returns the view's current scaling factor, which corresponds to the
     * distance from the scene.
     * 
     * @return current scaling factor of the view (1=normal, 2=twice as far from
     *         the scene etc)
     */
    double viewGetScale();

    /**
     * returns the width of the view, as seen from the game world.
     * 
     * @return the width of the view
     */
    double viewGetWidth();

    /**
     * returns the height of the view, as seen from the game world.
     * 
     * @return the height of the view
     */
    double viewGetHeight();

    /**
     * returns the current x position of the view's top-left corner within the
     * game world (this includes the scale factor).
     * 
     * @return current x position of the view's top-left corner within the game
     *         world
     */
    double viewGetCornerX();

    /**
     * returns the current y position of the view's top-left corner within the
     * game world (this includes the scale factor).
     * 
     * @return current y position of the view's top-left corner within the game
     *         world
     */
    double viewGetCornerY();

    /**
     * Locks or unlocks the view. While locked, the view is forced to stay
     * inside the current room's borders, while unlocked, there are no
     * limitations.
     * 
     * @param locked
     *            wether to lock the view
     */
    void viewSetLocked(boolean locked);

    /**
     * returns true if the view is currently locked. While locked, the view is
     * forced to stay inside the current room's borders, while unlocked, there
     * are no limitations.
     * 
     * @return true if the view is locked
     */
    boolean viewIsLocked();

    /**
     * converts the given x coordinate from being a window coordinate to being a
     * world coordinate.
     * 
     * @param x
     *            x coordinate relative to the window
     * @return x as seen from the game world
     */
    double windowToWorldX(int x);

    /**
     * converts the given y coordinate from being a window coordinate to being a
     * world coordinate.
     * 
     * @param y
     *            y coordinate relative to the window
     * @return y as seen from the game world
     */
    double windowToWorldY(int y);

    /**
     * converts the given x coordinate from being a world coordinate to being a
     * window coordinate.
     * 
     * @param x
     *            x coordinate relative to the game world
     * @return x as seen form the window's tol-left corner
     */
    int worldToWindowX(double x);

    /**
     * converts the given y coordinate from being a world coordinate to being a
     * window coordinate.
     * 
     * @param y
     *            y coordinate relative to the game world
     * @return y as seen form the window's tol-left corner
     */
    int worldToWindowY(double y);

    // to implement in future releases
    /**
     * sets the width of the game window, in pixels.
     * 
     * @param width
     *            width to assign
     */
    // void windowSetWidth(int width);

    // to implement in future releases
    /**
     * sets the height of the game window, in pixels.
     * 
     * @param height
     *            height to assign
     */
    // void windowSetHeight(int height);

    // to implement in future releases
    /**
     * sets both width and height of the game window, in pixels.
     * 
     * @param width
     *            width to assign
     * @param height
     *            height to assign
     */
    // void windowSetSize(int width, int height);

    /**
     * returns the current horizontal size of the game window.
     * 
     * @return the width of the window
     */
    int windowGetWidth();

    /**
     * returns the current vertical size of the game window.
     * 
     * @return the geight of the window
     */
    int windowGetHeight();
}
