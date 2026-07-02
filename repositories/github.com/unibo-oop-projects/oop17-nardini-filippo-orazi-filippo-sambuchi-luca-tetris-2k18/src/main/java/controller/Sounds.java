package main.java.controller;



import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;


import javafx.scene.media.AudioClip;
import main.java.controller.GameEvent;

public class Sounds {

	private static Sounds singleton;
	
	
	 private Sounds() {
	 }
	 
	 
	 public static Sounds getInstance() {
	        if (Objects.isNull(singleton)) {
	            singleton = new Sounds();
	        }
	        return singleton;
	 }
	 
	 
	 public Optional<AudioClip> getSound(final GameEvent sound) {
		 
	        if (Arrays.asList(GameEvent.values()).contains(sound)) {
	        	  return Optional.of(new AudioClip(sound.getpath()));
	        }
	        return Optional.empty();
	}
	 
}

