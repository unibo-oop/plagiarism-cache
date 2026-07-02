package it.unibo.oop.bounce.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.utils.Disposable;

import it.unibo.oop.bounce.game.Bounce;

public class Manager implements Disposable {
	
	private final AssetManager assetManager;
	/**
	 * istanza della classe Manager.
	 */
	public static Manager managerInstance;
	private Bounce bounce;
	private int level = 1;
	
	/**
	 *  usato per la musica.
	 */
	public static Music music;
	private static Preferences preferences;
	/**
     * Risoluzione dello schermo.
     * */
	public static final int X_RESOLUTION = 1200;
	/** -.*/
	public static final int Y_RESOLUTION = 624;

	/**
	 * Movimenti dei bottoni.
	 */
	public static final int MOVE_X_PIXEL = 700;
	/**
	 * -.
	 */
	public static final int MOVE_X_PIXEL_CLOSE = 700;
	
	/**
	 * -.
	 */
	public static final int MOVE_Y_PIXEL_PLAY = 480;
	/**
	 * -.
	 */
	public static final int MOVE_Y_PIXEL_SOUND_ON = 480;
	/**
	 * -.
	 */
	public static final int MOVE_Y_PIXEL_SETTING = 300;
	/**
	 * -.
	 */
	public static final int MOVE_Y_PIXEL_SOUND_OFF = 300;
	/**
	 * -.
	 */
	public static final int MOVE_Y_PIXEL_EXIT = 100;
	/**
	 * -.
	 */
	public static final int MOVE_Y_PIXEL_CLOSE = 0;
	/**
	 * -.
	 */
	public static final int CENTER_FOR_BUTTON = 723;
	/**
	 * -.
	 */
	public static final int POS_X_PIXEL = 900;
	/**
	 * -.
	 */
	public static final int POS_Y_PIXEL = 0;
	
	/**
	 * durata dell'animazione.
	 */
	public static final float DURATION = 0.5f;
	
	/** 
	 * bit Id usato in CollisionImpl  per avere che tipo di interazione tocca con la pallina. 
	 * */
	public static final int NOTHING_ID = 0;
	/** 
	 *  Id Blocco.
	 * */
	public static final int BLOCK_ID = 1;
	/** 
	 *  Id ball.
	 * */
	public static final int BOUNCE_ID = 2;
	/** 
	 *  Id ring.
	 * */
	public static final int RING_ID = 4;
	/** 
	 *  Id of the checkpoint object.
	 * */
	public static final int CHECKPOINT_ID = 8;
	/** 
	 *  Id thorn.
	 * */
	public static final int THORN_ID = 16;
	/** 
	 *  Id life.
	 * */
	public static final int LIFE_ID = 32;
	/** 
	 *  Id pumper.
	 * */
	public static final int PUMPER_ID = 64;
	/** 
	 *  Id deflatter.
	 * */
	public static final int DEFLATER_ID = 128;
	/** 
	 *  Id finish block.
	 * */
	public static final int FINISH_BLOCK_ID = 256;
	/** 
	 *  Id end block.
	 * */
	public static final int END_BLOCK_ID = 512;
	/** 
	 *  Id half block.
	 * */
	public static final int HALFBLOCK = 1024;

	public Manager(final Bounce bounce) {
		this.bounce = bounce;
		if (managerInstance == null) {
			managerInstance = this;
		}
		assetManager = new AssetManager();
		preferences = Gdx.app.getPreferences("setting");
		loadEffects();
	}

	private void loadEffects() {
		music = (Music) Gdx.audio.newMusic(Gdx.files.internal("audio/World_Music.mp3"));
		assetManager.finishLoading();
	}

	public final AssetManager getAssetManager() {
		return assetManager;
	}

	public final void dispose() {
		assetManager.dispose();
	}

	public final Preferences getSetting() {
		return preferences;
	}

	public final boolean getDisableAudio() {
		return preferences.getBoolean("disableMusic");
	}

	public final int getLevel() {
		return level;
	}

	public final int setLevel() {
		return this.level++;
	}
}
