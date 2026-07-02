package hollowmen.model.enemy;

/**
 * This class use Abstract Factory pattern in order to offer Builder
 * based on the name of the {@link Enemy} that need to be instantiate<br>
 * This class is a Singleton
 * @author pigio
 *
 */
public class EnemyFactory {

	private EnemyFactory() {};
	
	private static class Holder {
		public static EnemyFactory INSTANCE = new EnemyFactory();
	}
	
	/**
	 * @return {@link EnemyFactory} unique instance
	 */
	public static EnemyFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * This method gives a {@link EnemyBuilder} based on the input name
	 * <br>
	 * NOTE: if the name specified doesn't exist return null
	 * @param s name of the {@link Enemy} to build
	 * @return {@link EnemyBuilder}
	 */
	public EnemyBuilder getBuilderFor(String s) {
		switch(s) {
		case "slime":
			return new Slime.Builder();
		case "bat":
			return new Bat.Builder();
		case "puppet":
			return new Puppet.Builder();
		}
		return null;
	}
}
