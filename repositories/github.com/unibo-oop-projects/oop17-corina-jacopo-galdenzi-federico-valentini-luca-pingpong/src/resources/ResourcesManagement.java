package resources;

import java.io.File;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Score;

public class ResourcesManagement {
	private ResourcesManagement() {
		
	}
	
	private final static String CUSTOM_FONT= "Capture_it.ttf";
	private final static String MENU_BUTTONS_SOUND = "menuButtons.wav";
	private final static String RESOURCES_DIRECTORY = "resources";
	private final static String MAIN_DIRECTORY = System.getProperty("user.dir");
	private final static String FILE_SEPARATOR = System.getProperty("file.separator");
	private final static String MATCH_SCORES = "matchScores.obj";
	
	private static String getResourceDirectoryPath() {
		return MAIN_DIRECTORY+
			   FILE_SEPARATOR+
			   RESOURCES_DIRECTORY+
			   FILE_SEPARATOR;
	}
	
	public static String getCustomFontPath() {
		return getResourceDirectoryPath()+
			   CUSTOM_FONT;
	}
	public static String getMenuButtonSoundPath() {
		return getResourceDirectoryPath()+
			   MENU_BUTTONS_SOUND;
	}
	public static String getScoreHistoryPath() {
		return getResourceDirectoryPath()+
			   MATCH_SCORES;
	}
	public static void saveScore(Score obj) {
		new Serializer().saveScore(obj, getScoreHistoryPath());
	}
	
	public static List<Score> getScoreHistory(){
		return new Serializer().getScoreHistory(getScoreHistoryPath());
	}
	
					
	public static void playSound(String path){
		try {
		
		// Open an audio input stream.           
		File soundFile = new File(path); //you could also get the sound file with an URL
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		// Get a sound clip resource.
		Clip clip = AudioSystem.getClip();
		// Open audio clip and load samples from the audio input stream.
		clip.open(audioIn);
		clip.start();
		} catch (Exception e) {
		
		}
	}
}
