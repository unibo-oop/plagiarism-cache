package hollowmen.model.enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import hollowmen.model.Enemy;
import hollowmen.model.utils.Cloner;
import hollowmen.utilities.RandomSelector;
import hollowmen.utilities.Tris;

/**
 * This class keeps every {@link Enemy} that can be find in the game<br>
 * This class need to holds any {@code Enemy} before starting an exploration
 * or the Application may crush<br>
 * Each time {@code EnemyPool} returns an {@code Enemy}, it returns a deep copy
 * of it<br>
 * This class is a Singleton
 * @author pigio
 *
 */
public class EnemyPool {

	
	private Map<Tris<String, Integer, String>, Enemy> pool;
	
	private EnemyPool(){
		this.pool = new HashMap<>();
	};

	private static class Holder {
		public static EnemyPool INSTANCE = new EnemyPool();
	}

	/**
	 * 
	 * @return {@link EnemyPool} unique instance
	 */
	public static EnemyPool getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * This method add <b>enemy</b> to the pool
	 * @param enemy {@link Enemy}
	 * @see {@link EnemyFactory} for create an {@code Enemy}
	 */
	public void addEnemy(Enemy enemy) {
		enemy.getBody().setActive(false);
		pool.put(new Tris<>(enemy.getInfo().getName(), enemy.getLevel(), enemy.getTitle()), enemy);
	}
	
	/**
	 * This method gives a specific {@code Enemy} based on input
	 * @param name of the {@code Enemy}
	 * @param level of the {@code Enemy}
	 * @param title of the {@code Enemy}
	 * @return A deep clone of the {@link Enemy} or null if not present
	 */
	public Enemy getSpecificEnemy(String name, int level, String title) {
		return Cloner.enemy(this.pool.get(new Tris<>(name, level, title)));
	}
	
	/**
	 * This method gives a completly random {@code Enemy}
	 * @return A deep clone of the {@link Enemy} or null if no {@code Enemy} was added to the pool
	 */
	public Enemy getCompletelyRandom() {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(pool.entrySet().stream().map(e -> e.getValue()).toArray()));
	}
	
	/**
	 * This method applies <b>func</b> to the pool and then gives a random {@code Enemy}
	 * @param func {@link Predicate} of {@code String} applied on {@code Enemy}'s name
	 * @return A deep clone of the {@link Enemy} or null if no {@code Enemy}
	 * suit the <b>func</b>
	 */
	public Enemy getRandomForName(Predicate<String> func) {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
					.filter(e -> func.test(e.getKey().getX()))
					.map(e -> e.getValue())
					.toArray()));
	}
	
	/**
	 * This method applies <b>func</b> to the pool and then gives a random {@code Enemy}
	 * @param func {@link Predicate} of {@code Integer} applied on {@code Enemy}'s level
	 * @return A deep clone of the {@link Enemy} or null if no {@code Enemy}
	 * suit the <b>func</b>
	 */
	public Enemy getRandomForLevel(Predicate<Integer> func) {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
				.filter(e -> func.test(e.getKey().getY()))
				.map(e -> e.getValue())
				.toArray()));
	}
	
	/**
	 * This method applies <b>func</b> to the pool and then gives a random {@code Enemy}
	 * @param func {@link Predicate} of {@code String} applied on {@code Enemy}'s title
	 * @return A deep clone of the {@link Enemy} or null if no {@code Enemy}
	 * suit the <b>func</b>
	 */
	public Enemy getRandomForTitle(Predicate<String> func) {
		return Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
					.filter(e -> func.test(e.getKey().getZ()))
					.map(e -> e.getValue())
					.toArray()));
	}
	
	/**
	 * This method applies both <b>func...</b> to the pool and then gives a random {@code Enemy}
	 * @param funcInt {@link Predicate} of {@code Integer} applied on {@code Enemy}'s level
	 * @param funcTit {@link Predicate} of {@code String} applied on {@code Enemy}'s title
	 * @return A deep clone of the {@link Enemy} or null if no {@code Enemy}
	 * suit the <b>func</b>
	 */
	public Enemy getRandomForLevelTitle(Predicate<Integer> funcInt, Predicate<String> funcTit) {
		Enemy en = Cloner.enemy((Enemy) RandomSelector.getAnyFrom(this.pool.entrySet().stream()
				.filter(e -> funcInt.test(e.getKey().getY()))
				.filter(e -> funcTit.test(e.getKey().getZ()))
				.map(e -> e.getValue())
				.toArray()));
		return en;
	}
	
}