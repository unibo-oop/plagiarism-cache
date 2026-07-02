package virtualworld;

import java.util.Iterator;

import model.LevelParametizer;

public interface WaveGenerator extends Iterator<Wave> {

	public LevelParametizer getParam(); 
	
	public int getCurrent();
	
}
