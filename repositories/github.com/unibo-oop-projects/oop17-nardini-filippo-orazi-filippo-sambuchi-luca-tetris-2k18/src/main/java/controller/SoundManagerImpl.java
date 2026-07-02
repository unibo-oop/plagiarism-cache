package main.java.controller;

import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class SoundManagerImpl implements SoundManager{

	private float volume ;
	private final Clip clip ;
	private GameEvent action;
    private  final FloatControl control ;
		
	
	public SoundManagerImpl() throws LineUnavailableException {
		clip = AudioSystem.getClip();
		control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	
	
	@Override
	public void playSound(GameEvent action) {
		
		  this.action = action;
		  if (Sounds.getInstance().getSound(action).isPresent()) {
              Sounds.getInstance().getSound(action).get().play();
		  }
		
	}

	@Override
	public float getVolume() {
		return this.volume;
	}

	@Override
	public void setVolume(float volume) {
		 float range = control.getMaximum() - control.getMinimum();
		 float effectiveVolume = (range * volume) + control.getMinimum();
		 control.setValue(effectiveVolume);
		 this.volume = effectiveVolume;
		
	}



	@Override
	public void playAllSounds(List<GameEvent> actions) {
		actions.forEach(object -> this.playSound(object));
		
	}


	@Override
	public void pauseSound() {
		Sounds.getInstance().getSound(action).get().stop();
		
	}

}
