package tq2.implementations;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import tq2.interfaces.Sound;

/**
 * This class implements the interface Sound. It contains a sound clip that can be played, looped and stopped.
 * It can also return the path of said sound clip, as well as play a copy of it that will automatically close when over.
 * 
 * @author Francesco Gori
 */
public class SoundImpl implements Sound {

	/** The sound clip. */
	protected Clip clip;
	
	/** The path to the sound clip. */
	protected String path;
	
	/**
	 * Instantiates a new Sound object that will contain the audio at the specified path.
	 *
	 * @param path the path
	 */
	public SoundImpl (String path) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
		
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
					16,baseFormat.getChannels(), baseFormat.getChannels()*2, baseFormat.getSampleRate(), false);
			
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			
			this.clip = AudioSystem.getClip();
			this.clip.open(dais);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.err.println("An error occoured reading the audio file " + path);
			e.printStackTrace();
		}
		
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#reset()
	 */
	@Override
	public void reset() {
		if(this.clip != null) {
			this.stop();
			this.clip.setFramePosition(0);
			this.clip.setLoopPoints(0, this.clip.getFrameLength() - 1);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#play()
	 */
	@Override
	public void play() {
		if(this.clip != null) {
			this.reset();
			this.clip.start();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#playInstance()
	 */
	@Override
	public void playInstance() {
		if(this.clip != null) {
			
			//create a new Sound to play the sound, and close it when the clip is over
			SoundImpl soundIstance = new SoundImpl (this.getPath());
			soundIstance.play();
			
			soundIstance.addLineListener(new LineListener() {
				public void update(LineEvent evt) {
					if (evt.getType() == LineEvent.Type.STOP) {
						evt.getLine().close();
					}
				}
			});
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#loop(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void loop(Integer loops, Integer firstFrame, Integer lastFrame) {
		this.clip.setLoopPoints(firstFrame, lastFrame);
		this.loop(loops);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#loop(java.lang.Integer)
	 */
	@Override
	public void loop(Integer loops) {
		if (loops <= 0) {
			this.clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			this.clip.loop(loops);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#loop()
	 */
	@Override
	public void loop() {
		this.loop(0);
	}
		
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#close()
	 */
	@Override
	public void close() {
		this.stop();
		this.clip.close();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#stop()
	 */
	@Override
	public void stop() {
		if (this.clip.isRunning()) {
			this.clip.stop();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Sound#getPath()
	 */
	@Override
	public String getPath() {
		return this.path;
	}
	
	/**
	 * Adds a line listener to the clip contained in the object.
	 *
	 * @param listener the listener
	 */
	public void addLineListener(LineListener listener) {
		this.clip.addLineListener(listener);
	}
}
