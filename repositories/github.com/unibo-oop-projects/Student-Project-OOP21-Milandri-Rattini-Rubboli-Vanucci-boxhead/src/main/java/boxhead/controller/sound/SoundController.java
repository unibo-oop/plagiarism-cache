package boxhead.controller.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
	//private static String BACKGROUND="/media/background.mp3";
	private final Media background;
	MediaPlayer mediaplayer;
	
	/**
	 * Constructor to initialize the background, the only sound implemented
	 */
	public SoundController() {
	this.background=new Media(getClass().getResource("/media/background.mp3").toString());;
	this.mediaplayer=new MediaPlayer(this.background);
	this.mediaplayer.setAutoPlay(true);
	this.mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
	this.mediaplayer.setVolume(0.5);
	}
	
	/**
	 * Mute the background music
	 */
	public void mute() {
		this.mediaplayer.setVolume(0);
	}
	
	/**
	 * Unmute the background music
	 */
	public void unmute() {
		this.mediaplayer.setVolume(0.5);
	}
	
}
