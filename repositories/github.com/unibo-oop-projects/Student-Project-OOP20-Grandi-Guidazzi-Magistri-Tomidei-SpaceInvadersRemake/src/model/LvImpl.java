package model;

import java.util.Optional;

import model.entities.SpecificEntityType;

/**
 * {@link Level} implementation
 *
 */
public class LvImpl implements Level{
		
	private Optional<String> bossType;
	private int aliens;
	
	/**
	 * {@link Level} implementation
	 * @param boss
	 * @param aliens
	 */
	public LvImpl(String boss, int aliens) {
		this.bossType = Optional.ofNullable(boss); //if bossType is null it contains an optional empty else a string
		this.aliens= aliens;
	}

	/**
	 * {@link Level} implementation
	 */
	public LvImpl(int levelNum) {
		switch(levelNum) {
			case 1:
				this.bossType = Optional.empty();
				this.aliens = 25;
				break;
			case 2:
				this.bossType = Optional.ofNullable(SpecificEntityType.BOSS_1.toString());
				break;
			case 3:
				this.bossType = Optional.empty();
				this.aliens = 30;
				break;
			case 4:
				this.bossType = Optional.ofNullable(SpecificEntityType.BOSS_2.toString());
				break;
			case 5:
				this.bossType = Optional.empty();
				this.aliens = 35;
				break;
			case 6:
				this.bossType = Optional.ofNullable(SpecificEntityType.BOSS_3.toString());
				break;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAliens() {
		return this.aliens;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<String> getBoss() {
		return this.bossType;
	}
	
}
