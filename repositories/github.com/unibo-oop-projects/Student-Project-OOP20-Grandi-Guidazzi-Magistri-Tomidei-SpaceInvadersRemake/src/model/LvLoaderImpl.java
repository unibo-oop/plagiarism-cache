package model;

import java.util.Optional;


/**
 * {@link LvLoader} implementation
 *
 */
public class LvLoaderImpl implements LvLoader{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Level> loadLevel(int levelNumber) {
		return Optional.ofNullable(new LvImpl(levelNumber));
	}
}
