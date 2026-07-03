// this class has too much public methods because it is the connection point between the
// user and the game engine. Every function call is directed here and then redirected to the
// correct component. The user must not know about the components.

package zengine.core; ////

import zengine.constants.ZengineColor;
import zengine.constants.ZengineKeyboardConstant;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject.Executor;
import zengine.interfaces.GameEngine;

/**
 * Zengine is the public component of the whole engine hierachy. Its only
 * function is to delegate each function call to the specific sub-component, so
 * that the user does not have to worry which component implements which
 * function. Zengine does also provide some static strings that represents the
 * default path where each resource is loaded.
 *
 */
public final class Zengine implements GameEngine {
    // this class is a singleton that is used to create a copy of each part of
    // the Zengine hierachy and keeps a reference to each.

    // basically, it initializes the application by creating its various
    // components
    // functions are called to this singleton

    // no need to implement ZengineComponent because this class is public and it
    // is above all other components.

    private static Zengine zengineRef = new Zengine();

    private final ZengineSystem system; // = null;
    private final ZengineGUI gui; // = null;
    private final ZengineLogger logger; // = null;
    private final ZengineInput input; // = null;
    private final ZengineUtilities utilities; // = null;
    // private final ZengineLooper looper; // = null;
    private final ZengineAssets assets; // = null;
    private final ZengineAudio audio; // = null;

    /**
     * root package of the user class hierachy.
     */
    public static final String USR_CLASS_PATH = "user.";

    /**
     * root folder where sprites are loaded.
     */
    public static final String USR_SPRITE_PATH = "sprites/";

    /**
     * format of the subimages contained in a sprite.
     */
    public static final String USR_SPRITE_EXT = ".png";

    /**
     * name of the file containing informations about a sprite, such as its x
     * offset and y offset coordinates. The file must be included inside the
     * desired sprite folder, along with the subimages.
     */
    public static final String USR_SPRITEINFO_FILENAME = "spriteInfo.txt";

    /**
     * root folder where fonts are loaded.
     */
    public static final String USR_FONT_PATH = "fonts/";

    /**
     * format of the font files.
     */
    public static final String USR_FONT_EXT = ".ttf";

    /**
     * root folder where sound effects are loaded.
     */
    public static final String USR_SOUND_PATH = "audio/sounds/";

    /**
     * format of the sound effect files.
     */
    public static final String USR_SOUND_EXT = ".wav";

    /**
     * root folder where music is loaded.
     */
    public static final String USR_MUSIC_PATH = "audio/music/";

    /**
     * format of the music files.
     */
    public static final String USR_MUSIC_EXT = ".mp3";

    private Zengine() {
        system = ZengineSystem.getSystem();
        gui = ZengineGUI.getGUI();
        logger = ZengineLogger.getLogger();
        input = ZengineInput.getInput();
        utilities = ZengineUtilities.getUtilities();
        ZengineLooper.getLooper();
        assets = ZengineAssets.getAssets();
        audio = ZengineAudio.getAudio();

        // initialize(); // NOT here
    }

    // could have been package private
    /**
     * initializes Zengine. Call this only once at the start of the application.
     * DO NOT CALL this if you don't know exactly what you're doing. Use
     * <code>gameRestart()</code> instead for a safe reinitialization of the
     * engine.
     */
    public void initialize() {
        if (isRunningInJar()) {
            logger.loggerMessage("Zengine is running from inside Jar");
        } else {
            logger.loggerMessage("Zengine is running from outside Jar");
        }

        assets.initialize();
        system.initialize();
    }

    /**
     * returns a reference to the engine singleton, as instance of GameEngine.
     * It is guaranteed that each call of this method always returns the same
     * object, if called from everywhere.
     * 
     * @return the reference of the engine, Zengine in this case
     */
    public static GameEngine getEngine() {
        return zengineRef;
    }

    /**
     * returns a reference of the Zengine. This will allow the caller to have
     * access to all methods of Zengine, not only the game functions only. DO
     * NOT CALL this if you do not know what you are doing or the application
     * may work unproperly.
     * 
     * @return the reference of Zengine
     */
    public static Zengine getZengine() {
        // PACKAGE PRIVATE should be a better visibility for this method.
        // this method must be package private. Only the Zengine components have
        // access to all the methods of Zengine, including initialize(). The
        // user must use getEngine() to obtain the reference of the engine,
        // which filters unsafe functions.
        return zengineRef;
    }

    @Override
    public void drawSpriteExt(final String sprite, final double subimage, final double x, final double y, final double xscale,
            final double yscale, final double angle, final double alpha) {
        gui.drawSpriteExt(sprite, subimage, x, y, xscale, yscale, angle, alpha);
    }

    @Override
    public void drawSprite(final String sprite, final double subimage, final double x, final double y) {
        gui.drawSprite(sprite, subimage, x, y);
    }

    @Override
    public void drawTextDebug(final String text, final int x, final int y) {
        gui.drawTextDebug(text, x, y);
    }

    @Override
    public boolean keyboardCheckPressed(final ZengineKeyboardConstant keyCode) {
        return input.keyboardCheckPressed(keyCode);
    }

    @Override
    public boolean keyboardCheck(final ZengineKeyboardConstant keyCode) {
        return input.keyboardCheck(keyCode);
    }

    @Override
    public boolean keyboardCheckReleased(final ZengineKeyboardConstant keyCode) {
        return input.keyboardCheckReleased(keyCode);
    }

    @Override
    public GameObject instanceCreate(final double x, final double y, final String className) {
        return system.instanceCreate(x, y, className);
    }

    @Override
    public boolean instanceDestroy(final GameObject obj) {
        return system.instanceDestroy(obj);
    }

    @Override
    public boolean instanceExists(final GameObject obj) {
        return system.instanceExists(obj);
    }

    @Override
    public boolean instanceExists(final String className) {
        return system.instanceExists(className);
    }

    @Override
    public int instanceNumberGeneral() {
        return system.instanceNumberGeneral();
    }

    @Override
    public int instanceNumber(final String className) {
        return system.instanceNumber(className);
    }

    @Override
    public boolean isInstanceOf(final GameObject obj, final String className) {
        return system.isInstanceOf(obj, className);
    }

    @Override
    @SafeVarargs
    public final <T> T choose(final T... args) {
        return utilities.choose(args);
    }

    @Override
    public <T extends Comparable<T>> T max(final T a, final T b) {
        return utilities.max(a, b);
    }

    @Override
    public <T extends Comparable<T>> T min(final T a, final T b) {
        return utilities.min(a, b);
    }

    @Override
    public <T extends Comparable<T>> T clamp(final T value, final T x1, final T x2) {
        return utilities.clamp(value, x1, x2);
    }

    @Override
    public <T extends Comparable<T>> boolean valueInRange(final T value, final T x1, final T x2) {
        return utilities.valueInRange(value, x1, x2);
    }

    @Override
    public <T extends Comparable<T>> boolean rangeOverlaps(final T x1, final T x2, final T y1, final T y2) {
        return utilities.rangeOverlaps(x1, x2, y1, y2);
    }

    @Override
    public double pointDistance(final double x1, final double y1, final double x2, final double y2) {
        return utilities.pointDistance(x1, y1, x2, y2);
    }

    @Override
    public double pointDirection(final double x1, final double y1, final double x2, final double y2) {
        return utilities.pointDirection(x1, y1, x2, y2);
    }

    @Override
    public boolean pointInCircle(final double pointX, final double pointY, final double circleX, final double circleY,
            final double radius) {
        return utilities.pointInCircle(pointX, pointY, circleX, circleY, radius);
    }

    @Override
    public boolean pointInRectangle(final double pointX, final double pointY, final double x1, final double y1, final double x2,
            final double y2) {
        return utilities.pointInRectangle(pointX, pointY, x1, y1, x2, y2);
    }

    @Override
    public boolean rectangleOverlaps(final double ax1, final double ay1, final double ax2, final double ay2, final double bx1,
            final double by1, final double bx2, final double by2) {
        return utilities.rectangleOverlaps(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }

    @Override
    public double lengthDirX(final double len, final double dir) {
        return utilities.lengthDirX(len, dir);
    }

    @Override
    public double lengthDirY(final double len, final double dir) {
        return utilities.lengthDirY(len, dir);
    }

    @Override
    public <T extends GameObject> T instanceAdd(final double x, final double y, final T obj) {
        return system.instanceAdd(x, y, obj);
    }

    @Override
    public boolean instanceIsColliding(final GameObject obj1, final GameObject obj2) {
        return system.instanceIsColliding(obj1, obj2);
    }

    @Override
    public void loggerEnableMessages(final boolean yesOrNo) {
        logger.loggerEnableMessages(yesOrNo);
    }

    @Override
    public void loggerEnableWarnings(final boolean yesOrNo) {
        logger.loggerEnableWarnings(yesOrNo);
    }

    @Override
    public void loggerMessage(final String message) {
        logger.loggerMessage(message);
    }

    @Override
    public void loggerWarning(final String warning) {
        logger.loggerWarning(warning);
    }

    @Override
    public void loggerError(final String error) {
        logger.loggerError(error);
    }

    @Override
    public String instanceClassName(final GameObject obj) {
        return system.instanceClassName(obj);
    }

    @Override
    public void viewSetPosition(final double x, final double y) {
        gui.viewSetPosition(x, y);
    }

    @Override
    public double viewGetX() {
        return gui.viewGetX();
    }

    @Override
    public double viewGetY() {
        return gui.viewGetY();
    }

    @Override
    public void viewSetScale(final double scale) {
        gui.viewSetScale(scale);
    }

    @Override
    public double viewGetScale() {
        return gui.viewGetScale();
    }

    @Override
    public double mouseX() {
        return input.mouseX();
    }

    @Override
    public double mouseY() {
        return input.mouseY();
    }

    @Override
    public int displayMouseX() {
        return input.displayMouseX();
    }

    @Override
    public int displayMouseY() {
        return input.displayMouseY();
    }

    @Override
    public int windowMouseX() {
        return input.windowMouseX();
    }

    @Override
    public int windowMouseY() {
        return input.windowMouseY();
    }

    @Override
    public double viewGetCornerX() {
        return gui.viewGetCornerX();
    }

    @Override
    public double viewGetCornerY() {
        return gui.viewGetCornerY();
    }

    @Override
    public double windowToWorldX(final int x) {
        return gui.windowToWorldX(x);
    }

    @Override
    public double windowToWorldY(final int y) {
        return gui.windowToWorldY(y);
    }

    @Override
    public int worldToWindowX(final double x) {
        return gui.worldToWindowX(x);
    }

    @Override
    public int worldToWindowY(final double y) {
        return gui.worldToWindowY(y);
    }

    @Override
    public boolean spriteExists(final String sprite) {
        return assets.spriteExists(sprite);
    }

    @Override
    public int spriteGetNumber(final String sprite) {
        return assets.spriteGetNumber(sprite);
    }

    @Override
    public int spriteGetXoffset(final String sprite) {
        return assets.spriteGetXoffset(sprite);
    }

    @Override
    public int spriteGetYoffset(final String sprite) {
        return assets.spriteGetYoffset(sprite);
    }

    @Override
    public boolean isRunningInJar() {
        return utilities.isRunningInJar();
    }

    @Override
    public int gameTerminate(final int exitCode) {
        return system.gameTerminate(exitCode);
    }

    @Override
    public void with(final GameObject obj, final Executor e) {
        system.with(obj, e);
    }

    @Override
    public void with(final String className, final Executor e) {
        system.with(className, e);
    }

    @Override
    public boolean instanceDestroyAll(final String className, final GameObject ignoreMe) {
        return system.instanceDestroyAll(className, ignoreMe);
    }

    @Override
    public boolean mouseCheck(final ZengineMouseConstant mouseButtonCode) {
        return input.mouseCheck(mouseButtonCode);
    }

    @Override
    public boolean mouseCheckPressed(final ZengineMouseConstant mouseButtonCode) {
        return input.mouseCheckPressed(mouseButtonCode);
    }

    @Override
    public boolean mouseCheckReleased(final ZengineMouseConstant mouseButtonCode) {
        return input.mouseCheckReleased(mouseButtonCode);
    }

    @Override
    public void soundPlay(final String soundName) {
        audio.soundPlay(soundName);
    }

    @Override
    public void soundStop(final String soundName) {
        audio.soundStop(soundName);
    }

    @Override
    public void musicPlay(final String musicName) {
        audio.musicPlay(musicName);
    }

    @Override
    public void musicStop() {
        audio.musicStop();
    }

    @Override
    public boolean rectangleOverlapsCircle(final double x1, final double y1, final double x2, final double y2,
            final double circleX, final double circleY, final double radius) {
        return utilities.rectangleOverlapsCircle(x1, y1, x2, y2, circleX, circleY, radius);
    }

    @Override
    public int spriteGetWidth(final String sprite) {
        return assets.spriteGetWidth(sprite);
    }

    @Override
    public int spriteGetHeight(final String sprite) {
        return assets.spriteGetHeight(sprite);
    }

    @Override
    public double pointXrotate(final double x, final double y, final double centerX, final double centerY, final double angle) {
        return utilities.pointXrotate(x, y, centerX, centerY, angle);
    }

    @Override
    public double pointYrotate(final double x, final double y, final double centerX, final double centerY, final double angle) {
        return utilities.pointYrotate(x, y, centerX, centerY, angle);
    }

    @Override
    public boolean classExists(final String className) {
        return system.classExists(className);
    }

    @Override
    public boolean instanceIsColliding(final GameObject obj, final String className) {
        return system.instanceIsColliding(obj, className);
    }

    @Override
    public int abs(final int num) {
        return utilities.abs(num);
    }

    @Override
    public double abs(final double num) {
        return utilities.abs(num);
    }

    @Override
    public double random(final double num) {
        return utilities.random(num);
    }

    @Override
    public double randomRange(final double num1, final double num2) {
        return utilities.randomRange(num1, num2);
    }

    @Override
    public int windowGetWidth() {
        return gui.windowGetWidth();
    }

    @Override
    public int windowGetHeight() {
        return gui.windowGetHeight();
    }

    @Override
    public GameObject instanceNearest(final double x, final double y, final String... classNames) {
        return system.instanceNearest(x, y, classNames);
    }

    @Override
    public GameObject instanceFurthest(final double x, final double y, final String... classNames) {
        return system.instanceFurthest(x, y, classNames);
    }

    @Override
    public void gameRestart() {
        system.gameRestart();
    }

    @Override
    public boolean instanceDestroyEverything(final GameObject ignoreMe) {
        return system.instanceDestroyEverything(ignoreMe);
    }

    @Override
    public boolean isInitializeEvent() {
        return system.isInitializeEvent();
    }

    @Override
    public boolean isCreateEvent() {
        return system.isCreateEvent();
    }

    @Override
    public boolean isDestroyEvent() {
        return system.isDestroyEvent();
    }

    @Override
    public boolean isUpdateEvent() {
        return system.isUpdateEvent();
    }

    @Override
    public boolean isDrawEvent() {
        return system.isDrawEvent();
    }

    @Override
    public boolean isCollisionEvent() {
        return system.isCollisionEvent();
    }

    @Override
    public boolean isMouseClickedEvent() {
        return system.isMouseClickedEvent();
    }

    @Override
    public boolean roomDefine(final String roomName, final int width, final int height) {
        return system.roomDefine(roomName, width, height);
    }

    @Override
    public boolean roomAddElement(final String roomName, final String className, final double x, final double y) {
        return system.roomAddElement(roomName, className, x, y);
    }

    @Override
    public boolean roomAddSituation(final String roomName) {
        return system.roomAddSituation(roomName);
    }

    @Override
    public boolean roomMerge(final String roomToCopyTo, final String roomToCopyFrom, final boolean keepOriginalDimension) {
        return system.roomMerge(roomToCopyTo, roomToCopyFrom, keepOriginalDimension);
    }

    @Override
    public boolean roomClear(final String roomName) {
        return system.roomClear(roomName);
    }

    @Override
    public boolean roomGoto(final String roomName) {
        return system.roomGoto(roomName);
    }

    @Override
    public boolean roomLoad(final String roomName) {
        return system.roomLoad(roomName);
    }

    @Override
    public double roomGetWidth(final String roomName) {
        return system.roomGetWidth(roomName);
    }

    @Override
    public double roomGetHeight(final String roomName) {
        return system.roomGetHeight(roomName);
    }

    @Override
    public int roomGetNumberOfElements(final String roomName) {
        return system.roomGetNumberOfElements(roomName);
    }

    @Override
    public String roomCurrent() {
        return system.roomCurrent();
    }

    @Override
    public boolean roomExists(final String roomName) {
        return system.roomExists(roomName);
    }

    @Override
    public double roomWidth() {
        return system.roomWidth();
    }

    @Override
    public double roomHeight() {
        return system.roomHeight();
    }

    @Override
    public RoomCode roomSetCreationCode(final String roomName, final RoomCode code) {
        return system.roomSetCreationCode(roomName, code);
    }

    @Override
    public void drawRectangle(final double x, final double y, final double width, final double height, final ZengineColor color,
            final boolean filled) {
        gui.drawRectangle(x, y, width, height, color, filled);
    }

    @Override
    public void drawEllipse(final double x, final double y, final double width, final double height, final ZengineColor color,
            final boolean filled) {
        gui.drawEllipse(x, y, width, height, color, filled);
    }

    @Override
    public void drawCircle(final double x, final double y, final double radius, final ZengineColor color, final boolean filled) {
        gui.drawCircle(x, y, radius, color, filled);
    }

    @Override
    public void drawLine(final double x1, final double y1, final double x2, final double y2, final ZengineColor color) {
        gui.drawLine(x1, y1, x2, y2, color);
    }

    @Override
    public void drawPoint(final double x, final double y, final ZengineColor color) {
        gui.drawPoint(x, y, color);
    }

    @Override
    public boolean fontDefine(final String fontName, final String alias, final float size) {
        return assets.fontDefine(fontName, alias, size);
    }

    @Override
    public boolean fontExists(final String fontName) {
        return assets.fontExists(fontName);
    }

    @Override
    public boolean fontAliasExists(final String alias) {
        return assets.fontAliasExists(alias);
    }

    @Override
    public double fontGetHeight(final String alias) {
        return assets.fontGetHeight(alias);
    }

    @Override
    public void drawSpriteHUD(final String sprite, final double subimage, final int x, final int y, final double xscale,
            final double yscale, final double angle, final double alpha) {
        gui.drawSpriteHUD(sprite, subimage, x, y, xscale, yscale, angle, alpha);
    }

    @Override
    public void drawText(final String text, final double x, final double y, final String fontAlias, final ZengineColor color) {
        gui.drawText(text, x, y, fontAlias, color);
    }

    @Override
    public void drawTextHUD(final String text, final int x, final int y, final String fontAlias, final ZengineColor color) {
        gui.drawTextHUD(text, x, y, fontAlias, color);
    }

    @Override
    public void viewSetLocked(final boolean locked) {
        gui.viewSetLocked(locked);
    }

    @Override
    public boolean viewIsLocked() {
        return gui.viewIsLocked();
    }

    @Override
    public double viewGetWidth() {
        return gui.viewGetWidth();
    }

    @Override
    public double viewGetHeight() {
        return gui.viewGetHeight();
    }

    @Override
    public void viewSetX(final double x) {
        gui.viewSetX(x);
    }

    @Override
    public void viewSetY(final double y) {
        gui.viewSetY(y);
    }

    @Override
    public double wrapToModulus(final double value, final int modulus) {
        return utilities.wrapToModulus(value, modulus);
    }

    @Override
    public boolean roomRestart() {
        return system.roomRestart();
    }

    @Override
    public GameObject instanceGet(final String className) {
        return system.instanceGet(className);
    }

    @Override
    public boolean soundExists(final String soundName) {
        return assets.soundExists(soundName);
    }

    @Override
    public boolean musicExists(final String musicName) {
        return assets.musicExists(musicName);
    }

    @Override
    public boolean soundIsPlaying(final String soundName) {
        return audio.soundIsPlaying(soundName);
    }

    @Override
    public boolean musicIsPlaying(final String musicName) {
        return audio.musicIsPlaying(musicName);
    }

    @Override
    public int soundNumber(final String soundName) {
        return audio.soundNumber(soundName);
    }

    @Override
    public String musicCurrent() {
        return audio.musicCurrent();
    }

    @Override
    public String musicLast() {
        return audio.musicLast();
    }

    @Override
    public boolean soundHasEnded(final String soundName) {
        return audio.soundHasEnded(soundName);
    }

    @Override
    public boolean musicHasEnded() {
        return audio.musicHasEnded();
    }

    @Override
    public boolean musicIsPlaying() {
        return audio.musicIsPlaying();
    }

    @Override
    public boolean musicHasStopped() {
        return audio.musicHasStopped();
    }

    @Override
    public double angleValue(final double angle) {
        return utilities.angleValue(angle);
    }

    @Override
    public double angleDifference(final double angle1, final double angle2) {
        return utilities.angleDifference(angle1, angle2);
    }

    @Override
    public double angleShortestRotationVerse(final double angle1, final double angle2) {
        return utilities.angleShortestRotationVerse(angle1, angle2);
    }

    @Override
    public double angleRotate(final double currentAngle, final double destinationAngle, final double rotSpeed) {
        return utilities.angleRotate(currentAngle, destinationAngle, rotSpeed);
    }

    @Override
    public double angleRotateHyperbolic(final double currentAngle, final double destinationAngle, final double slownessFactor) {
        return utilities.angleRotateHyperbolic(currentAngle, destinationAngle, slownessFactor);
    }

    @Override
    public double angleRotateHyperbolic(final double currentAngle, final double destinationAngle, final double slownessFactor,
            final double minSpeed, final double maxSpeed) {
        return utilities.angleRotateHyperbolic(currentAngle, destinationAngle, slownessFactor, minSpeed, maxSpeed);
    }

    @Override
    public double lerp(final double a, final double b, final double amount) {
        return utilities.lerp(a, b, amount);
    }

    @Override
    public void drawSprite3D(final String sprite, final double subimage, final double x, final double y, final double z,
            final double xscale, final double yscale, final double angle, final double alpha) {
        gui.drawSprite3D(sprite, subimage, x, y, z, xscale, yscale, angle, alpha);
    }

    @Override
    public double power(final double base, final double exponent) {
        return utilities.power(base, exponent);
    }

    @Override
    public RoomCode roomSetEndingCode(final String roomName, final RoomCode code) {
        return system.roomSetEndingCode(roomName, code);
    }
}
