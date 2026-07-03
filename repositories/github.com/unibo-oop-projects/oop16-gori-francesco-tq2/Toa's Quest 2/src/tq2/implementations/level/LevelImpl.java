package tq2.implementations.level;

import java.util.HashMap;
import java.util.LinkedList;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tq2.implementations.SoundImpl;
import tq2.implementations.graphics.Spritesheet;
import tq2.interfaces.Controls;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;
import tq2.interfaces.Level;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.Sound;

/**
 * The Class LevelImpl.
 * 
 * @author Francesco Gori
 */

public abstract class LevelImpl implements Level {
	
	/** The controls relative to this level. */
	protected Controls controls;
	
	/** The images to be used to build the layers of this level. */
	protected LinkedList <BufferedImage> layersImages;
	
	/** The paths to the images to be used to build the layers of this level. */
	protected LinkedList <String> layersPaths;
	
	/** The list of the X parallaxes values of the layers of the level. */
	protected LinkedList <Double> layersParallaxesX;
	
	/** The list of the Y parallaxes values of the layers of the level. */
	protected LinkedList <Double> layersParallaxesY;

	/** The spritesheets images. */
	protected HashMap <String, Spritesheet> spritesheetsImages; 
	
	/** The sounds to be played in this level. */
	protected HashMap<String, Sound> sounds;
	
	/** The Handler. */
	protected Handler handler;
	
	/** The width of the area of level. */
	protected Integer width;
	
	/** The height of the area of level. */
	protected Integer height;

	
	/**
	 * Instantiates a new LevelImpl.
	 *
	 * @param handler the Handler
	 */
	public LevelImpl (Handler handler) {
		this.layersImages = new LinkedList<BufferedImage>();
		this.layersPaths = new LinkedList<String>();
		this.layersParallaxesX = new LinkedList<Double>();
		this.layersParallaxesY = new LinkedList<Double>();
		this.sounds = new HashMap <String, Sound>();
		this.spritesheetsImages = new HashMap <String, Spritesheet>();
		
		this.handler = handler;
		
		this.width = 0;
		this.height = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Level#getCursor()
	 */
	@Override
	public Cursor getCursor() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Level#getHandler()
	 */
	@Override
	public Handler getHandler() {
		return this.handler;
	}

	/* (non-Javadoc)
	 * @see platform.level.Level2#getLayers()
	 */
	@Override
	public LinkedList<BufferedImage> getLayerImages() {
		return this.layersImages;
	}
	
	/* (non-Javadoc)
	 * @see platform.level.Level2#getSpritesheets()
	 */
	@Override
	public HashMap <String, Spritesheet> getSpritesheets() {
		return this.spritesheetsImages;
	}
	
	/* (non-Javadoc)
	 * @see platform.level.Level2#tick()
	 */
	@Override
	public abstract void tick();

	/* (non-Javadoc)
	 * @see platform.level.Level2#getWidth()
	 */
	@Override
	public Integer getWidth() {
		return width;
	}

	/* (non-Javadoc)
	 * @see platform.level.Level2#getHeight()
	 */
	@Override
	public Integer getHeight() {
		return height;
	}

	/**
	 * Adds the information to generate a new layer to the lists in this object
	 *
	 * @param path the path to the image to generate the layer
	 * @param parallaxX the X parallax value of this layer
	 * @param parallaxY the Y parallax value of this layer
	 */
	protected void addLayer(String path, Double parallaxX, Double parallaxY) {
		this.layersPaths.add(path);
		this.layersParallaxesX.add(parallaxX);
		this.layersParallaxesY.add(parallaxY);
	}
	
	/* (non-Javadoc)
	 * @see platform.level.Level2#getControls()
	 */
	@Override
	public Controls getControls() {
		return this.controls;
	}
	
	/* (non-Javadoc)
	 * @see platform.level.Level2#loadLevel()
	 */
	@Override
	public void loadLevel() {
		for (String path:layersPaths) {
			try {
				this.layersImages.add(ImageIO.read(getClass().getResource(path)));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("An error occoured while loading the layers of level " + this.getHandler().getGame().getCurrentLevelIndex());
			}
		}
	}

	/* (non-Javadoc)
	 * @see platform.level.Level2#clearLevel()
	 */
	@Override
	public void clearLevel() {
		
		//destroy each object in each layer, and close all sounds contained in this object
		if (this.getHandler().getLayers() != null) {
			for (LevelLayer layer : this.getHandler().getLayers()) {
				if (layer != null) {
					for (int i=0; i<layer.size();i++) {
						Entity e = layer.get(i);
						e.destroy();
					}
					layer.clear();
				}
			}
			this.getHandler().getLayers().clear();	
		}
		
		for (Sound s:this.sounds.values()) {
			s.close();
		}
	}
	
	/**
	 * Play the sound associated with the specified name, if present.
	 *
	 * @param soundName the name associated with the sound
	 */
	public void playSound (String soundName) {
		
		Sound sound = this.sounds.get(soundName);
		if (sound != null) {
			sound.play();
		}
	}
	
	/**
	 * Loop the sound associated with the specified name, if present.
	 * The sound will loop for the specified number of times.
	 * If 0 is specified the sound will loop continuously
	 *
	 * @param soundName the name associated with the sound
	 * @param loops the number of times the sound will loop
	 */
	public void loopSound (String soundName, Integer loops) {
	
		Sound sound = this.sounds.get(soundName);
		if (sound != null) {
			sound.loop(loops);
		}
}
	
	/**
	 * Loop continuously the sound associated with the specified name, if present.
	 * The sound will play until stopped
	 * 
	 * @param soundName the name associated with the sound
	 */
	public void loopSound (String soundName) {
		
		this.loopSound(soundName, 0);
	}
	
	/**
	 * Stop the sound associated with the specified name, if present.
	 *
	 * @param soundName the name associated with the sound
	 */
	public void stopSound (String soundName) {
		Sound sound = this.sounds.get(soundName);
		if (sound != null) {
			sound.reset();
		}
	}
	
	/**
	 * Play a copy of the sound associated with the specified name, if present.
	 * Such copy will close itself once it's over.
	 * Useful for many instances of the same sounds that overlap.
	 *
	 * @param soundName the name associated with the sound
	 */
	public void playSoundIstance (String soundName) {
		
		Sound sound = new SoundImpl (this.sounds.get(soundName).getPath());
		if (sound != null) {
			sound.play();
		}
	}
}
