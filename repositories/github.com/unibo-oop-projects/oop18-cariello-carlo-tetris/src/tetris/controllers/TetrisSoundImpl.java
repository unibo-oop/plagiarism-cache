package tetris.controllers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class manages the sound of game. There three different sounds:
 * generalTheme, sound of deleted row and sound of game over.
 * 
 * @author Carlo
 *
 */
public class TetrisSoundImpl implements TetrisSound {

	static AudioClip soundTheme = null;
	static AudioClip soundLine = null;
	static AudioClip soundGameOver = null;

	/**
	 * class constructor
	 */
	public TetrisSoundImpl() {

		File theme = new File("res/sound/theme.wav");
		File line = new File("res/sound/line.wav");
		File gameover = new File("res/sound/gameover.wav");

		URL urlTheme = null;
		URL urlLine = null;
		URL urlGameOver = null;

		if (line.canRead()) {
			try {
				urlTheme = theme.toURI().toURL();
				urlLine = line.toURI().toURL();
				urlGameOver = gameover.toURI().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		AudioClip clipTheme = Applet.newAudioClip(urlTheme);
		AudioClip clipLine = Applet.newAudioClip(urlLine);
		AudioClip clipGameOver = Applet.newAudioClip(urlGameOver);

		soundTheme = clipTheme;
		soundLine = clipLine;
		soundGameOver = clipGameOver;

	}

	public void playTheme() {
		soundTheme.loop();
	}

	public void playLine() {
		soundLine.play();
	}

	public void playGameOver() {
		soundGameOver.play();
	}

	public void stopTheme() {
		soundTheme.stop();
	}

	public void stopLine() {
		soundLine.stop();
	}

	public void stopGameOver() {
		soundGameOver.stop();
	}

}
