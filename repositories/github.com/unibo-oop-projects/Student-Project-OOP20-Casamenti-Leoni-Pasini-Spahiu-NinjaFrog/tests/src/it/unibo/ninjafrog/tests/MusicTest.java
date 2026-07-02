package it.unibo.ninjafrog.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import it.unibo.ninjafrog.game.utilities.SoundManagerImpl;

@RunWith(GdxTestRunner.class)
public class MusicTest {
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ASSETS_PATH = ".." + FILE_SEPARATOR + "core" + FILE_SEPARATOR + "assets" + FILE_SEPARATOR;
	
	public void MenuSongExists() {
		assertTrue("This test will only pass if the mp3 file of the menu exist in the assets folder.",
				Gdx.files.internal( ASSETS_PATH + "introSong.mp3").exists());
	}
	
	public void PlaySongExists() {
		assertTrue("This test will only pass if the mp3 file of the play screen exist in the assets folder.",
				Gdx.files.internal( ASSETS_PATH + "playSong.mp3").exists());
	}
	@Test
	public void SoundManagerExists() {
		final SoundManagerImpl soundManager = new SoundManagerImpl();
		assertNotNull(soundManager);
	}
	
}
