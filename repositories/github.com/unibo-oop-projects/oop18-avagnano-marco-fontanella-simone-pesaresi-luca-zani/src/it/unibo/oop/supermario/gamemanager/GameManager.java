package it.unibo.oop.supermario.gamemanager;

import org.junit.experimental.theories.Theories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.utils.Disposable;

import it.unibo.oop.supermario.game.SuperMario;

/**
 * 
 *
 */
public class GameManager implements Disposable {
    private final AssetManager assetManager;
    /**
     *  the instance of the class.
     * */
    public static GameManager instance;
    /** 
     * this is the audio whith the game music.
     * */
    public static Music music;
    /** 
     * the contructor of the class needs this parameter, contains variuos utilites.
     * */
    public SuperMario superMario;
    /** 
     * the current level the user is playing.
     * */
    private int currentLevel = 1;
    /** 
     * the preference for the audio to stay the same even after the closure of the program.
     * */
    private static Preferences pref;
    /**
     * Virtual screen size.
     * */
    public static final int V_WIDTH = 400;
    /** -.*/
    public static final int V_HEIGHT = 208;
    /** -.*/
    public static final int DISTANCE_CAM = 384;
    /**
     * pixel per meter.
     * */
    public static final float PPM = 100;
    /** 
     * the bit rapresenting the nothing.
     * */
    public static final int NOTHING_BIT = 0;
    /** 
     * the bit rapresenting the ground.
     * */
    public static final int GROUND_BIT = 1;
    /** 
     * the bit rapresenting mario.
     * */
    public static final int MARIO_BIT = 2;
    /**
     * the bit rapresenting the bricks.
     * */
    public static final int BRICK_BIT = 4;
    /**
     * the bit rapresenting the coins.
     * */
    public static final int COIN_BIT = 8;
    /**
     * the bit rapresenting a destroyed object.
     * */
    public static final int DESTROYED_BIT = 16;
    /**
     * the bit rapresenting the pipes.
     * */
    public static final int PIPE_BIT = 32;
    /**
     * the bit rapresenting an enemy.
     * */
    public static final int ENEMY_BIT = 64;
    /** 
     * the bit rapresenting an enemy head.
     * */
    public static final int ENEMY_HEAD_BIT = 128;
    /** 
     * the bit rapresenting an item.
     * */
    public static final int ITEM_BIT = 256;
    /**
     * the bit rapresenting the mario's head.
     * */
    public static final int MARIO_HEAD_BIT = 512;
    /**
     * the bit rapresenting a fireball.
     * */
    public static final int FIREBALL_BIT = 1024;
    /**
     * the bit rapresenting a power up.
     * */
    public static final int POWER_UP = 2048;
    /**
     * the bit rapresenting the final flag.
     * */
    public static final int FLAG_BIT = 4096;
    /**
     * the bit rapresenting the character's feet.
     * */ 
    public static final int FEET_BIT = 8192;

    /**
     * 
     * @param superMario contains its proper spritebatch, so this screen do not need a new one.
     */
    public GameManager(final SuperMario superMario) {
        this.superMario = superMario;
        if (instance == null) {
            instance = this;
        }
        assetManager = new AssetManager();
        pref = Gdx.app.getPreferences("setting");
        loadAudio();
    }


    /**
     * this method load into the memory the various sounds.
     */
    private void loadAudio() {
        music = (Music) Gdx.audio.newMusic(Gdx.files.internal("Soundtrack_level.mp3"));
        assetManager.load("audio/mario/jump_small.wav", Sound.class);
        assetManager.load("audio/mario/powerup.wav", Sound.class);
        assetManager.load("audio/mario/fireball.ogg", Sound.class);
        assetManager.load("audio/mario/mariodie.wav", Sound.class);
        assetManager.load("audio/mario/powerdown.wav", Sound.class);
        assetManager.load("audio/items/break_block.wav", Sound.class);
        assetManager.load("audio/items/bump.wav", Sound.class);
        assetManager.load("audio/items/coin.wav", Sound.class);
        assetManager.load("audio/items/flagpole.wav", Sound.class);
        assetManager.load("world_clear.wav", Sound.class);
        assetManager.load("stomp.wav", Sound.class);
        assetManager.finishLoading();
    }

    /**
     * 
     * @return the assetManager
     */
    public final AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    /**
     * 
     */
    public void dispose() {
        assetManager.dispose();
    }

    /**
     * 
     * @return the preferences
     */
    public Preferences getSetting() {
        return pref;
    }

    /**
     * 
     * @return the skin path
     */
    public String getSkin() {
        return pref.getString("skin");
    }

    /**
     * 
     * @return the current level
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * set the current level.
     * @param level is the new level.
     */
    public void setLevel(final int level) {
        currentLevel = level;
    }

    /**
     * set if or not disable the audio.
     * @return true if the audio is disabled
     */
    public boolean getDisableAudio() {
        return pref.getBoolean("disableAudio");
    }

}