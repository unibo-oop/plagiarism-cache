package main.java.controller;

import java.util.List;
import main.java.controller.GameEvent;
public interface SoundManager {
	
	/*final Task task = new Task() {

        @Override
        protected Object call() throws Exception {
            int s = INDEFINITE;
            AudioClip audio = new AudioClip(getClass().getResource("aquarium.mp3").toExternalForm());
            audio.setVolume(0.5f);
            audio.setCycleCount(s);
            audio.play();
            return null;
        }
    };
    Thread thread = new Thread(task);
    thread.start();*/
	
	 void playSound(final GameEvent action);
	 
	 
	 float getVolume();
	 
	 
	 void setVolume(float volume);
	 
	 
	 void pauseSound();
	 
	 
	 void playAllSounds(final List<GameEvent> actions) ;
	 
	 
	
	

}
