package enumerators;

public enum Level {
	LEVEL_1 (1.0, "fire.jpg", "plat1.png", EnemyCharacter.PARABEETLE, CoinType.SIMPLE),
	LEVEL_2 (1.2, "sky.jpg", "plat2.png", EnemyCharacter.GOOMBA, CoinType.SIMPLE),
	LEVEL_3 (1.3, "space.jpg", "plat2.png", EnemyCharacter.BOMB, CoinType.SIMPLE),
	LEVEL_4 (1.4, "space.jpg", "plat2.png", EnemyCharacter.FROSTY, CoinType.SIMPLE);
	
	private final double difficulty;
	private final String backgroundPath;
	private final String platformImagePath;
	private final EnemyCharacter enemyCharacter;
	private final CoinType coinType;
	
	private Level(final double difficulty, final String backgroundPath, final String platformImagePath, final EnemyCharacter enemyCharacter, final CoinType coinType) {
		this.difficulty = difficulty;
		this.backgroundPath = backgroundPath;
		this.platformImagePath = platformImagePath;
		this.enemyCharacter = enemyCharacter;
		this.coinType = coinType;
	}
	
	public double getDifficulty() {
        return difficulty;
    }

	public String getBackgroundPath() {
		return "images/" + backgroundPath;
	}
	
	public String getPlatformImagePath() {
		return "images/" + platformImagePath;
	}
	
	public EnemyCharacter getEnemyCharacter() {
		return enemyCharacter;
	}
	
	public CoinType getCoinType() {
	    return coinType;
	}
}
