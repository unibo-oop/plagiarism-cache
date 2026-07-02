package javarogue.ui.config;

import java.util.Optional;

import javarogue.utility.Resolution;

public class ConfigModelImpl implements ConfigModel {

	private Optional<Resolution> resolution = Optional.empty();
	private Optional<String> tileSet = Optional.empty();
	private boolean isFullscreen = false;
	
	private long seed;
	
	@Override
	public void setResolution(Resolution resolution) {
		this.resolution = Optional.of(resolution);
	}

	@Override
	public Optional<Resolution> getResolution() {
		return this.resolution;
	}
	
	@Override
	public void setFullscreen(boolean isFullscreen) {
		this.isFullscreen = isFullscreen;
	}
	
	@Override
	public boolean getFullscreen() {
		return this.isFullscreen;
	}

	@Override
	public void setTileset(String path) {
		this.tileSet = Optional.of(path);
	}

	@Override
	public Optional<String> getTileset() {
		return this.tileSet;
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
	}
	
	 @Override
	public long getSeed() {
		return this.seed;
	}
	
}
