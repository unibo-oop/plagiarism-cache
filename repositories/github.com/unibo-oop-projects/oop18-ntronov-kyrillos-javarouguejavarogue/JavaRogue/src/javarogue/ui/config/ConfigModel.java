package javarogue.ui.config;

import java.util.Optional;

import javarogue.utility.Resolution;

/**
 * 
 * Model of the Configuration Window, contains selected options.
 *
 */
public interface ConfigModel {

	/**
	 * Saves resolution.
	 * 
	 * @param resolution
	 */
	public void setResolution(Resolution resolution);
	
	/**
	 * 
	 * Gets resolution if present.
	 * 
	 * @return Optional of resolution (empty if no selection has been made).
	 */
	public Optional<Resolution> getResolution();
	
	public void setFullscreen(boolean isFullscreen);
	
	public boolean getFullscreen();
	
	public void setTileset(String path);
	
	public Optional<String> getTileset();
	
	public void setSeed(long seed);
	
	public long getSeed();
}
